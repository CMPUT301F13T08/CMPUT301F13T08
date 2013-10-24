package com.team08storyapp;

public class Choice {
	String text;
	int storyFragmentID;
	int Id;
	
	public Choice(String text, int StoryFragmentID, int Id){
		this.text = text;
		this.storyFragmentID = StoryFragmentID;
		this.Id = Id;
	}
	
	public void setText(String string) {
		this.text = string;
	}
	
	public String getText(){
		return text;
	}
	
	public void setId(int choiceID){
		this.Id = choiceID;
	}
	
	public int getStoryFragmentID(){
		return storyFragmentID;
	}
	
	public void setFragmentID(int storyFragmentID){
		this.storyFragmentID = storyFragmentID;
	}
	
	public int getId(){
		return Id;
	}
	

}
