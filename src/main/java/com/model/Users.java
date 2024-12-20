package com.model;

public class Users {
private int userId;
private String username;
private String fname;
private String email;
private String password;
private String phone;
private Role role;
public Users() {}
public Users(int userId, String username, String fname, String email, String password, String phone, Role role) {
	
	this.userId = userId;
	this.username = username;
	this.fname = fname;
	this.email = email;
	this.password = password;
	this.phone = phone;
	this.role = role;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getFname() {
	return fname;
}
public void setFname(String fname) {
	this.fname = fname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public Role getRole() {
	return role;
}
public void setRole(Role role) {
	this.role = role;
}




}
