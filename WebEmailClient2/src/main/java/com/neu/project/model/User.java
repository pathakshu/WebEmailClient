package com.neu.project.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity

public class User  {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int userID;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	
	private String gender;
	private Date birthDate;
	
	private String profilePicture;
	private String user_Status;
	
	@OneToMany(mappedBy="user")
	private Collection<EmailAccount> emailAccounts = new ArrayList<EmailAccount>();
	
	
	public String getUser_Status() {
		return user_Status;
	}
	public void setUser_Status(String user_Status) {
		this.user_Status = user_Status;
	}
	public Collection<EmailAccount> getEmailAccounts() {
		return emailAccounts;
	}
	public void setEmailAccounts(Collection<EmailAccount> emailAccounts) {
		this.emailAccounts = emailAccounts;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	
	

}
