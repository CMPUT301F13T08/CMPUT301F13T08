package com.team08storyapp;

public class Choice {
	String text;
	int storyFragmentID;
	int Id;
	
	public void setText(String string) {
		// TODO Auto-generated method stub
		this.text = string;
	}
	
	public void setId(int choiceID){
		this.Id = choiceID;
	}
	
	public void setFragmentID(int storyFragmentID){
		this.storyFragmentID = storyFragmentID;
		
	}
	

}
