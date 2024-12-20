package com.model;

import java.time.LocalDate ;

public class Borrow {
private int borrowid;
private LocalDate borrowDate;
private LocalDate dueDate;
private LocalDate returnDate;
private Books Book = new Books();
private Users User = new Users();
 public Borrow() {}
public Borrow(int borrowid, LocalDate borrowDate, LocalDate dueDate, LocalDate returnDate, Books book, Users user) {
	super();
	this.borrowid = borrowid;
	this.borrowDate = borrowDate;
	this.dueDate = dueDate;
	this.returnDate = returnDate;
	Book = book;
	User = user;
}
public int getBorrowid() {
	return borrowid;
}
public void setBorrowid(int borrowid) {
	this.borrowid = borrowid;
}
public LocalDate getBorrowDate() {
	return borrowDate;
}
public void setBorrowDate(LocalDate borrowDate) {
	this.borrowDate = borrowDate;
}
public LocalDate getDueDate() {
	return dueDate;
}
public void setDueDate(LocalDate dueDate) {
	this.dueDate = dueDate;
}
public LocalDate getReturnDate() {
	return returnDate;
}
public void setReturnDate(LocalDate returnDate) {
	this.returnDate = returnDate;
}
public Books getBook() {
	return Book;
}
public void setBook(Books book) {
	Book = book;
}
public Users getUser() {
	return User;
}
public void setUser(Users user) {
	User = user;
}


}
