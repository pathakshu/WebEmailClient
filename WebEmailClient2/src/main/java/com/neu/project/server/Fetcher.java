package com.neu.project.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.search.FlagTerm;

public class Fetcher implements Runnable {

	private String driver;
	private String db_url;
	private String db_user;
	private String db_password;
	int maxEmailID = -1;
	ArrayList<EMailData> emails = new ArrayList<EMailData>();

	public Fetcher() {

	}

	@Override
	public void run() {
		loadEmailsFromDB();
		System.out.println("***********************" + emails.size());
		// TODO Auto-generated method stub
		int i = 0;
		while (true) {
			// System.out.println("Loop#" + i++);
			for (EMailData emaildata : emails) {
				System.out.println(emaildata.userId);
				getEmails(emaildata);
			}
			loadEmailsFromDB();
			try {
				Thread.sleep(5000 * 60);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void loadEmailsFromDB() {
		emails.clear();
		driver = "com.mysql.jdbc.Driver";
		//very useful property : if any connection is closed, it should not wait for any other resources, should close immediately
		db_url = "jdbc:mysql://localhost:3306/WebEmailClient?dontTrackOpenResources=true";
		db_user = "root";
		db_password = "";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(db_url, db_user,
					db_password);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			if (connection != null) {
				String query = "SELECT userId, emailAccount,emailPassword,email_id  FROM EmailAccount";
				stmt = connection.prepareStatement(query);
				rs = stmt.executeQuery();
				while (rs.next()) {
					EMailData ed = new EMailData();
					ed.userId = rs.getInt(1);
					ed.emailAccount = rs.getString(2);
					ed.emailPassword = rs.getString(3);
					ed.email_id = rs.getInt(4);
					System.out.println(ed.emailAccount);
					emails.add(ed);
				}
				// stmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			close(rs);
			close(stmt);
			close(connection);
		}

	}

	public void getEmails(EMailData emaildata) {

		//Folder folder = null;
		Store store = null;
//		Folder draftsFolder = null;
//		Folder spamFolder = null;
//		Folder outboxFolder = null;
//		Folder trashFolder = null;
//		Folder starredFolder = null;
		try {
			Properties props = System.getProperties();
			props.setProperty("mail.store.protocol", "imaps");

			Session session = Session.getDefaultInstance(props, null);
			// session.setDebug(true);
			store = session.getStore("imaps");
			store.connect("imap.gmail.com", emaildata.emailAccount,
					emaildata.emailPassword);
//			folder = store.getFolder("Inbox");
//			draftsFolder = store.getFolder("Drafts");
//			spamFolder = store.getFolder("Spam");
//			outboxFolder = store.getFolder("[Gmail]/Sent");
//			trashFolder = store.getFolder("Trash");
//			starredFolder = store.getFolder("Starred");
			/*
			 * Others GMail folders : [Gmail]/All Mail This folder contains all
			 * of your Gmail messages. [Gmail]/Drafts Your drafts. [Gmail]/Sent
			 * Mail Messages you sent to other people. [Gmail]/Spam Messages
			 * marked as spam. [Gmail]/Starred Starred messages. [Gmail]/Trash
			 * Messages deleted from Gmail.
			 */
			// folder.open(Folder.READ_WRITE);
			// fetchMessages(folder, emaildata);
			// draftsFolder.open(Folder.READ_ONLY);
			// fetchMessages(draftsFolder, emaildata);
			// spamFolder.open(Folder.READ_ONLY);
			// fetchMessages(spamFolder, emaildata);
			// outboxFolder.open(Folder.READ_ONLY);
			// fetchMessages(outboxFolder, emaildata);
			// trashFolder.open(Folder.READ_ONLY);
			// fetchMessages(trashFolder, emaildata);
			// starredFolder.open(Folder.READ_ONLY);
			// fetchMessages(starredFolder, emaildata);

			Folder[] folders = store.getDefaultFolder().list("*");
			for (Folder f : folders) {
				try {
					f.open(Folder.READ_ONLY);
					System.out.println("->" + f.getName());
					String folderName=f.getName().toLowerCase();
					if(folderName.contains("inbox")||folderName.contains("trash")||folderName.contains("sent")||folderName.contains("draft")||folderName.contains("starred")||folderName.contains("spam"))
					fetchMessages(f, emaildata);
					f.close(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			if (folder != null) {
////				try {
////					// folder.close(true);
////				} catch (MessagingException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
//			}
			if (store != null) {
				try {
					store.close();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void fetchMessages(Folder folder, EMailData emaildata) {
		try {

			Message messages[] = null;
			// if(folder.getName().equalsIgnoreCase("inbox")){
			// messages=folder.search(new FlagTerm(new Flags(
			// Flags.Flag.SEEN), false));
			// }else{
			messages = folder.getMessages();
			// }
			System.out.println("No of Messages : " + folder.getMessageCount());
			System.out.println("No of Unread Messages : "
					+ folder.getUnreadMessageCount());
			for (int i = 0; i < messages.length; ++i) {
				System.out.println("MESSAGE #" + (i + 1) + ":");
				Message msg = messages[i];

				// System.out.println(msg.getContent());
				System.out.println(msg.getSubject());
				/*
				 * if we don''t want to fetch messages already processed if
				 * (!msg.isSet(Flags.Flag.SEEN)) { String from = "unknown"; ...
				 * }
				 */
				String from = "unknown";
				if (msg.getReplyTo().length >= 1) {
					from = msg.getReplyTo()[0].toString();
				} else if (msg.getFrom().length >= 1) {
					from = msg.getFrom()[0].toString();
				}
				String subject = msg.getSubject();
				// System.out.println("Saving ... " + subject + " " + from);
				// you may want to replace the spaces with "_"
				// the TEMP directory is used to store the files
				// String filename = "c:/temp/" + subject;
				// saveParts(msg.getContent(), filename);
				String messageBody = getMessageBody(msg.getContent());
				System.out.println(messageBody);
				String to = InternetAddress.toString(messages[i]
						.getRecipients(Message.RecipientType.TO));

				saveMessage(emaildata, subject, messageBody,
						msg.getReceivedDate(), to, from, folder.getName());

				//msg.setFlag(Flags.Flag.SEEN, true);
				// to delete the message
				// msg.setFlag(Flags.Flag.DELETED, true);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private void saveMessage(EMailData emaildata, String subject,
			String messageBody, Date sendDate, String receiver, String sender,
			String folderName) {

		String emaitDate = sdf.format(sendDate);

		driver = "com.mysql.jdbc.Driver";
		db_url = "jdbc:mysql://localhost:3306/WebEmailClient?dontTrackOpenResources=true";
		db_user = "root";
		db_password = "";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		Connection connection = null;

		try {
			connection = DriverManager.getConnection(db_url, db_user,
					db_password);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			if (connection != null) {
				String query = "insert into Messages values(null,?,?,?,'"
						+ folderName + "',?,?,?)";
				stmt = connection.prepareStatement(query);
				stmt.setString(1, messageBody);
				stmt.setString(2, emaitDate);
				stmt.setInt(3, emaildata.email_id);
				stmt.setString(4, receiver);
				stmt.setString(5, sender);
				stmt.setString(6, subject);
				stmt.executeUpdate();

			}
		} catch (SQLException e) {
			// System.out.println(e.getMessage());
		} finally {
			close(rs);
			close(stmt);
			close(connection);
		}

	}

	public void close(Connection connection) {
		if (connection != null) {

			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		}
	}

	public void close(PreparedStatement statment) {
		if (statment != null) {

			try {
				statment.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		}
	}

	public void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public String getMessageBody(Object content) throws IOException,
			MessagingException {
		String messageBody = "";
		// OutputStream out = null;
		// InputStream in = null;
		try {
			if (content instanceof Multipart) {
				Multipart multi = ((Multipart) content);
				int parts = multi.getCount();
				for (int j = 0; j < parts; ++j) {
					MimeBodyPart part = (MimeBodyPart) multi.getBodyPart(j);
					if (part.getContent() instanceof Multipart) {
						// part-within-a-part, do some recursion...
						getMessageBody(part.getContent());
					} else {
						String extension = "";
						if (part.isMimeType("text/html")) {
							extension = "html";
							messageBody = (String) part.getContent();
						} else {
							if (part.isMimeType("text/plain")) {
								extension = "txt";
								messageBody = (String) part.getContent();
							} else {
								// Try to get the name of the attachment
								extension = part.getDataHandler().getName();
							}
							// filename = filename + "." + extension;
							// System.out.println("... " + filename);
							// out = new FileOutputStream(new File(filename));
							// in = part.getInputStream();
							// int k;
							// while ((k = in.read()) != -1) {
							// out.write(k);
							// }
						}
					}
				}
			}
		} finally {
			// if (in != null) {
			// in.close();
			// }
			// if (out != null) {
			// out.flush();
			// out.close();
			// }
		}
		return messageBody;
	}
}
