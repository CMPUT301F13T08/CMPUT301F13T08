package com.team08storyapp;

public class StoryInfo {
	private String author;
	private String title;
	private int id;
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StoryInfo(String title, String author, int id){
		this.title = title;
		this.author = author;
		this.id = id;
	}		

}
