package com.model;
public class User {
    private int id;
    private String email;
    private String fname;
    private String address;
    private String date;
    private String sex;
    private String city;
    private String[] job; // Hoặc String nếu không cần là mảng
public User() {}
    // Constructor
    public User(int id, String email, String fname, String address, String date, String sex, String city, String[] job) {
        this.id = id;
        this.email = email;
        this.fname = fname;
        this.address = address;
        this.date = date;
        this.sex = sex;
        this.city = city;
        this.job = job;
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String[] getJob() {
		return job;
	}
	public void setJob(String[] job) {
		this.job = job;
	}

 
}
