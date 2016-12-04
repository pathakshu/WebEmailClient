package com.neu.project.server;

import java.util.ArrayList;
import java.util.Arrays;

import com.neu.project.model.EmailAccount;
import com.neu.project.model.Messages;
import com.neu.project.model.User;



//import javax.mail.*;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.Properties;

public class SendMail {

  public static void sendEMail(final Messages messageToSend,final User user) {
      new Thread(new Runnable() {
          @Override
          public void run() {
              java.util.Properties props = new java.util.Properties();
              props.put("mail.smtp.auth", "true");
              props.put("mail.smtp.starttls.enable", "true");
              props.put("mail.smtp.host", "smtp.gmail.com");
              props.put("mail.smtp.port", "587");
              final ArrayList<EmailAccount> emailAccountsList = new ArrayList(Arrays.asList(user.getEmailAccounts().toArray()));
              javax.mail.Session session = javax.mail.Session.getInstance(props,
                      new javax.mail.Authenticator() {
                          protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                              return new javax.mail.PasswordAuthentication(messageToSend.getSender(), emailAccountsList.get(0).getEmailPassword());
                          }
                      });
              try {
                  javax.mail.Message message = new javax.mail.internet.MimeMessage(session);
                  message.setFrom(new javax.mail.internet.InternetAddress(messageToSend.getSender()));
                  message.setRecipients(javax.mail.Message.RecipientType.TO,
                          javax.mail.internet.InternetAddress.parse(messageToSend.getReceiver()));
                  message.setSubject(messageToSend.getSubject());
                  message.setText(messageToSend.getContent());
                  javax.mail.Transport.send(message);
              } catch (javax.mail.MessagingException e) {
              }
          }
      }).start();
  }

  

}
