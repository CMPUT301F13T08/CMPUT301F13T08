package com.team08storyapp;

public class Annotation {
	
	private int annotationID;
	private String text;
	private int storyFragmentID;
	private Photo photo;
	
	public Annotation(int storyFragmentID, Photo photo, String text){
		this.storyFragmentID = storyFragmentID;
		this.photo = photo;
		this.text = text;
	}
	
	public Annotation(int annotationID, int storyFragmentID, Photo photo, String text){
		this.annotationID = annotationID;
		this.storyFragmentID = storyFragmentID;
		this.photo = photo;
		this.text = text;
	}
	
	public void setAnnotationID(int annotationID){
		this.annotationID = annotationID;
	}
	
	public int getAnnotationID(){
		return annotationID;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getText(){
		return text;
	}
	
	public void setStoryFragmentID(int storyFragmentID){
		this.storyFragmentID = storyFragmentID;
	}
	
	public int getStoryFragmentID(){
		return storyFragmentID;
	}
	
	public void setPhoto(Photo photo){
		this.photo = photo;
	}
	
	public Photo getPhoto(){
		return photo;
	}
	

}
