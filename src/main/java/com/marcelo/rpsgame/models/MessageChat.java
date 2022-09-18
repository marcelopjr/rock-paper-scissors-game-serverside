package com.marcelo.rpsgame.models;

import java.time.LocalDate;

public class MessageChat {
	
	private LocalDate date;
	private String author;
	private String content;
	
	public LocalDate getDate() {
		return date;
	} 
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
