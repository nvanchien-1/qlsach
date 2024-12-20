package com.model;

public class GoogleAccount {
	private String  id;
	private String email;
	private String name;
	private Role role;
	private boolean verified_email;
	public GoogleAccount() {}
	public GoogleAccount(String id, String email, String name, Role role, boolean verified_email) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.role = role;
		this.verified_email = verified_email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public boolean isVerified_email() {
		return verified_email;
	}
	public void setVerified_email(boolean verified_email) {
		this.verified_email = verified_email;
	}
	
	
}
