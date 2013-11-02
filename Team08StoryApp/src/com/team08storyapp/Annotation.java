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
	private String photo;
	private String encodedAnnotation;

	public String getEncodedAnnotation() {
	    return encodedAnnotation;
	}

	public void setEncodedAnnotation(String encodedAnnotation) {
	    this.encodedAnnotation = encodedAnnotation;
	}

	public Annotation() {

	}

	public Annotation(int storyFragmentID, String photo, String text) {
		this.storyFragmentID = storyFragmentID;
		this.photo = photo;
		this.text = text;
	}

	public Annotation(int annotationID, int storyFragmentID, String photo,
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

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhoto() {
		return photo;
	}

	@Override
	public String toString() {
		return "Annotation [storyFragmentID=" + storyFragmentID
				+ ", annotationID=" + annotationID + ", text=" + text
				+ ", photo=" + photo + "]";
	}

}
