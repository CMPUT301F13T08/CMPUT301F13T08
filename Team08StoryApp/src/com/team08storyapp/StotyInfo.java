package com.team08storyapp;

public class StotyInfo {

	private String author;
	private String title;
	private String id;
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public StotyInfo(String title, String author, String id){
		this.title = title;
		this.author = author;
		this.id = id;
	}

}
