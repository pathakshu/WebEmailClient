package com.neu.project.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.neu.project.model.Contacts;
import com.neu.project.model.EmailAccount;
import com.neu.project.model.Messages;
import com.neu.project.model.User;

public class UserDAO extends DAO {

	public User queryUserByNameAndPassword(String name, String password)
			throws Exception {
		try {
			// begin();
			Query q = getSession()
					.createQuery(
							"from User where userName = :username and password = :password");
			q.setString("username", name);
			q.setString("password", password);
			User user = (User) q.uniqueResult();
			// commit();
			close();
			return user;
		} catch (HibernateException e) {
			// rollback();
			throw new Exception("Could not get user " + name, e);
		}
	}

	
	  public ArrayList<Messages> getMessages(int userId,String folder) {
	  ArrayList<Messages> messageList = new ArrayList();
	  Query q = getSession().
			  createQuery("from Messages m where m.email_status=:folder and m.email_Id in (select email_Id from EmailAccount where userId=:userId)");
	  q.setInteger("userId", userId) ;
	  q.setString("folder", folder) ;
	  
	  messageList =
	  (ArrayList<Messages>)q.list();
	  
	  return messageList; }
	  
	 /* 
	 * public void addMessage(String message, String fromUser, String toUser,
	 * String date) throws Exception{ try { SessionFactory sessionFactory =
	 * HibernateUtil.getSessionFactory(); Session session =
	 * sessionFactory.openSession(); Transaction tx =
	 * session.beginTransaction(); //Transaction transaction =
	 * getSession().beginTransaction(); Message dbMessage = new Message();
	 * dbMessage.setMessage(message); dbMessage.setFromUser(fromUser);
	 * dbMessage.setUserName(toUser); dbMessage.setMessageDate(date);
	 * session.save(dbMessage);
	 * System.out.println("Message is "+dbMessage.getMessage());
	 * System.out.println
	 * ("Sent by:"+dbMessage.getFromUser()+" Sent to:"+dbMessage.getUserName());
	 * System.out.println("Message date "+dbMessage.getMessageDate());
	 * 
	 * tx.commit(); close();
	 * 
	 * } catch (HibernateException e) { throw new
	 * Exception("Could not add message"); }
	 * 
	 * }
	 * 
	 * public void deleteMessagesQuery(String[] messageID) throws Exception{ try
	 * { SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	 * Session session = sessionFactory.openSession(); for(String mid :
	 * messageID) { Transaction tx = session.beginTransaction(); //Transaction
	 * transaction = getSession().beginTransaction();
	 * 
	 * String hql = "delete from  Message where messageID = :mid"; Query query =
	 * session.createQuery(hql); query.setString("mid",mid); int rowCount =
	 * query.executeUpdate(); System.out.println("Rows affected: " + rowCount);
	 * tx.commit(); } close(); } catch (HibernateException e) { throw new
	 * Exception("Could not add message"); }
	 * 
	 * }
	 */

	public void deleteContactsQuery(String[] contactID) throws Exception {
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			for (String cid : contactID) {
				Transaction tx = session.beginTransaction();
				// Transaction transaction = getSession().beginTransaction();

				String hql = "delete from  Contact where contactID = :cid";
				Query query = session.createQuery(hql);
				query.setString("cid", cid);
				int rowCount = query.executeUpdate();
				System.out.println("Rows affected: " + rowCount);
				tx.commit();
			}
			close();
		} catch (HibernateException e) {
			throw new Exception("Could not add message");
		}

	}
	
	
	public void deleteMessage(int id){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Messages message= (Messages)session.get(Messages.class, new Integer(id));
			message.setEmail_status("TRASH");			
			session.save(message);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		
	}

	public ArrayList<Contacts> getContactsQuery(String userName)
			throws Exception {
		try {
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			Session session = sessionFactory.openSession();
			ArrayList<Contacts> contactList = new ArrayList();
			// System.out.println("User name is "+userName);
			Query q = getSession().createQuery(
					"from Contact where userName = :userName");
			q.setString("userName", userName);
			contactList = (ArrayList<Contacts>) q.list();
			// System.out.println("Number of messages is "+messageList.size());
			close();
			return contactList;

		} catch (HibernateException e) {
			throw new Exception("Could not get contacts");
		}
	}

	public int saveUser(User user) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return user.getUserID();
	}
	
	public int saveEmailAccount(EmailAccount account) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(account);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return account.getEmail_Id();
	}
	
	public void saveSentEmail(Messages messages) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(messages);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}
	
	
	public void starredMessage(int id){
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Messages message= (Messages)session.get(Messages.class, new Integer(id));
			if(message.getEmail_status().equalsIgnoreCase("STARRED"))
				message.setEmail_status("INBOX");
			else
				message.setEmail_status("STARRED");			
			session.save(message);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		
	}
	
	
	
	

}
