package com.model;

import java.time.LocalDate;

public class BorrowOff {
    private int borrowOffid;
    private String fname;
    private int phone;
    private String email;
    private LocalDate birth;
    private LocalDate borrowdate;
    private LocalDate duedate;
    private Books book; 

    public BorrowOff() {}
    
    
    
    
    
    public BorrowOff(int borrowOffid, String fname, int phone, String email, LocalDate birth, LocalDate borrowdate,
			LocalDate duedate, Books book) {
		super();
		this.borrowOffid = borrowOffid;
		this.fname = fname;
		this.phone = phone;
		this.email = email;
		this.birth = birth;
		this.borrowdate = borrowdate;
		this.duedate = duedate;
		this.book = book;
	}





	public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

   
    public int getBorrowOffid() {
        return borrowOffid;
    }

    public void setBorrowOffid(int borrowOffid) {
        this.borrowOffid = borrowOffid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public LocalDate getBorrowdate() {
        return borrowdate;
    }

    public void setBorrowdate(LocalDate borrowdate) {
        this.borrowdate = borrowdate;
    }

    public LocalDate getDuedate() {
        return duedate;
    }

    public void setDuedate(LocalDate duedate) {
        this.duedate = duedate;
    }
}
