package com.neu.project.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class EmailAccount {
	@Id
	private int email_Id;
	private String emailAccount;
	private String emailPassword;
	
	public String getEmailPassword() {
		return emailPassword;
	}

	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}

	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getEmail_Id() {
		return email_Id;
	}

	public void setEmail_Id(int email_Id) {
		this.email_Id = email_Id;
	}

	public String getEmailAccount() {
		return emailAccount;
	}

	public void setEmailAccount(String emailAccount) {
		this.emailAccount = emailAccount;
	}

	
	
	

}
