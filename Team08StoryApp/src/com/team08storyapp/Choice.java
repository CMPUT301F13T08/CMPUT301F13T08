package com.team08storyapp;

public class Choice {
	
	String text;
	int curFragmentID;
	int FragmentID;
	int Id;
	
	public Choice(int curFragmentID){
		this.curFragmentID = curFragmentID;
		
	}
	
	public void setText(String string) {
		// TODO Auto-generated method stub
		this.text = string;
	}
	
	public void setId(int CId){
		this.Id = CId;
	}
	
	public void setFragmentID(int FId){
		this.FragmentID = FId;
		
	}

}
