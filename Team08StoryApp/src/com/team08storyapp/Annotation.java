package com.team08storyapp;

import java.io.Serializable;

public class Annotation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int annotationID;
	private String text;
	private int storyFragmentID;
	private byte[] photo;

	public Annotation() {

	}

	public Annotation(int storyFragmentID, byte[] photo, String text) {
		this.storyFragmentID = storyFragmentID;
		this.photo = photo;
		this.text = text;
	}

	public Annotation(int annotationID, int storyFragmentID, byte[] photo,
			String text) {
		super();
		this.annotationID = annotationID;
		this.storyFragmentID = storyFragmentID;
		this.photo = photo;
		this.text = text;
	}

	public void setAnnotationID(int annotationID) {
		this.annotationID = annotationID;
	}

	public int getAnnotationID() {
		return annotationID;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setStoryFragmentID(int storyFragmentID) {
		this.storyFragmentID = storyFragmentID;
	}

	public int getStoryFragmentID() {
		return storyFragmentID;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public byte[] getPhoto() {
		return photo;
	}

	@Override
	public String toString() {
		return "Annotation [storyFragmentID=" + storyFragmentID
				+ ", annotationID=" + annotationID + ", text=" + text
				+ ", photo=" + photo + "]";
	}

}
