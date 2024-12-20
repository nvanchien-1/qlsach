package com.model;

public class Books {
private int bookid;
private  String title;
private String author;
private int   publishedYear ;
private int quantity;
private String image;
private Category category;



public Books() {}







public Books(int bookid, String title, String author, int publishedYear, int quantity, String image,
		Category category) {
	super();
	this.bookid = bookid;
	this.title = title;
	this.author = author;
	this.publishedYear = publishedYear;
	this.quantity = quantity;
	this.image = image;
	this.category = category;
}







public String getImage() {
	return image;
}



public void setImage(String image) {
	this.image = image;
}



public int getBookid() {
	return bookid;
}



public void setBookid(int bookid) {
	this.bookid = bookid;
}



public String getTitle() {
	return title;
}



public void setTitle(String title) {
	this.title = title;
}



public String getAuthor() {
	return author;
}



public void setAuthor(String author) {
	this.author = author;
}



public int getPublishedYear() {
	return publishedYear;
}



public void setPublishedYear(int publishedYear) {
	this.publishedYear = publishedYear;
}



public int getQuantity() {
	return quantity;
}



public void setQuantity(int quantity) {
	this.quantity = quantity;
}



public Category getCategory() {
	return category;
}



public void setCategory(Category category) {
	this.category = category;
}








}
