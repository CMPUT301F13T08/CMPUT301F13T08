package com.team08storyapp;

public class ChoiceInfo {
	
	private String text;
	private int nextFragmentId;
	private int choiceId;
	
	public ChoiceInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public ChoiceInfo(String text, int nextFragmentId, int choiceId){
		this.text = text;
		this.nextFragmentId = nextFragmentId;
		this.choiceId = choiceId;
	}

	public int getChoiceId() {
		return choiceId;
	}

	public void setChoiceId(int choiceId) {
		this.choiceId = choiceId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getNextFragmentId() {
		return nextFragmentId;
	}

	public void setNextFragmentId(int nextFragmentId) {
		this.nextFragmentId = nextFragmentId;
	}
	
	

}
