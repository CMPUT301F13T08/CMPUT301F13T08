package com.team08storyapp;

import java.io.Serializable;

public class Choice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String text;
	int toGoToStoryFragmentID;
	int choiceId;

	public Choice() {

	}

	public Choice(int toGoToStoryFragmentID, int choiceId, String text) {
		super();
		this.text = text;
		this.toGoToStoryFragmentID = toGoToStoryFragmentID;
		this.choiceId = choiceId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getStoryFragmentID() {
		return toGoToStoryFragmentID;
	}

	public void setStoryFragmentID(int toGoToStoryFragmentID) {
		this.toGoToStoryFragmentID = toGoToStoryFragmentID;
	}

	public int getChoiceId() {
		return choiceId;
	}

	public void setChoiceId(int choiceId) {
		this.choiceId = choiceId;
	}

	@Override
	public String toString() {
		return "Choice [choiceId=" + choiceId + ", toGoToStoryFragmentID="
				+ toGoToStoryFragmentID + ", text=" + text + "]";
	}
}
