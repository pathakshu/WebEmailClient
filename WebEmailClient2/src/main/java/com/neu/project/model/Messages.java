package com.neu.project.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Messages {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int message_Id;
	private String sender;
	private String content;
	private String subject;
	private Date date;
	private String receiver;
	private String email_status;
	
	
	
	public String getEmail_status() {
		return email_status;
	}
	public void setEmail_status(String email_status) {
		this.email_status = email_status;
	}
	private int email_Id;
	
	
	public int getEmail_Id() {
		return email_Id;
	}
	public void setEmail_Id(int email_Id) {
		this.email_Id = email_Id;
	}
	public int getMessage_Id() {
		return message_Id;
	}
	public void setMessage_Id(int message_Id) {
		this.message_Id = message_Id;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	
	
	

}
