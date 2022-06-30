package com.ace.studentmanagement.dto;

public class UserDTO {
	private String userId;
	private String userEmail;
	private String userName;	
	private String userPassword;
	private String userRole;
	public UserDTO() {
		
	}
	public UserDTO(String userId, String userName, String userEmail, String userPassword, String userRole) {
		this.userId = userId;
		this.userEmail = userEmail;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userRole = userRole;
	}
	public String getUserEmail() {
		return userEmail;
		
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
