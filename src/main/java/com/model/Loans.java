package com.model;

import java.time.LocalDate;

public class Loans {
private int loanId;
private LocalDate loanDate;
private LocalDate  returnDate ;
private Books Book= new Books();
private Users User = new Users();
public Loans(){}
public Loans(int loanId, LocalDate loanDate, LocalDate returnDate, Books book, Users user) {
	
	this.loanId = loanId;
	this.loanDate = loanDate;
	this.returnDate = returnDate;
	Book = book;
	User = user;
}
public int getLoanId() {
	return loanId;
}
public void setLoanId(int loanId) {
	this.loanId = loanId;
}
public LocalDate getLoanDate() {
	return loanDate;
}
public void setLoanDate(LocalDate loanDate) {
	this.loanDate = loanDate;
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
