package com.team08storyapp;

import java.io.Serializable;
import java.util.ArrayList;

public class StoryFragment implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private int storyFragmentId;
    private String storyText;
    private ArrayList<Photo> photos;
    private ArrayList<Choice> choices;
    private ArrayList<Annotation> annotations;
    private ArrayList<String> encodedPhotos;
    private ArrayList<String> encodedAnnotations;

    public StoryFragment() {

	choices = new ArrayList<Choice>();
	photos = new ArrayList<Photo>();
	annotations = new ArrayList<Annotation>();
    }

    public StoryFragment(int storyFragmentId) {
	this.storyFragmentId = storyFragmentId;
	choices = new ArrayList<Choice>();
	photos = new ArrayList<Photo>();
	annotations = new ArrayList<Annotation>();
    }

    public StoryFragment(int storyFragmentId, String storyText) {
	this.storyFragmentId = storyFragmentId;
	this.storyText = storyText;
	choices = new ArrayList<Choice>();
	photos = new ArrayList<Photo>();
	annotations = new ArrayList<Annotation>();
    }

    public StoryFragment(String storyText) {
	this.storyText = storyText;
	choices = new ArrayList<Choice>();
	photos = new ArrayList<Photo>();
	annotations = new ArrayList<Annotation>();
    }

    public StoryFragment(int storyFragmentId, String storyText,
	    ArrayList<Photo> photos, ArrayList<Choice> choices,
	    ArrayList<Annotation> annotations) {
	super();
	this.storyFragmentId = storyFragmentId;
	this.storyText = storyText;
	this.photos = photos;
	this.choices = choices;
	this.annotations = annotations;
	choices = new ArrayList<Choice>();
	photos = new ArrayList<Photo>();
	annotations = new ArrayList<Annotation>();
    }

    public int getStoryFragmentId() {
	return storyFragmentId;
    }

    public void setStoryFragmentId(int storyFragmentId) {
	this.storyFragmentId = storyFragmentId;
    }

    public String getStoryText() {
	return storyText;
    }

    public void setStoryText(String storyText) {
	this.storyText = storyText;
    }

    public ArrayList<Photo> getPhotos() {
	return photos;
    }

    public void setPhotos(ArrayList<Photo> photos) {
	this.photos = photos;
    }

    public ArrayList<Choice> getChoices() {
	return choices;
    }

    public void setChoices(ArrayList<Choice> choices) {
	this.choices = choices;
    }

    public ArrayList<Annotation> getAnnotations() {
	return annotations;
    }

    public void setAnnotations(ArrayList<Annotation> annotations) {
	this.annotations = annotations;
    }
    
    public ArrayList<String> getEncodedPhotos() {
        return encodedPhotos;
    }

    public ArrayList<String> getEncodedAnnotations() {
        return encodedAnnotations;
    }

    public void setEncodedPhotos(ArrayList<String> encodedPhotos) {
        this.encodedPhotos = encodedPhotos;
    }

    public void setEncodedAnnotations(ArrayList<String> encodedAnnotations) {
        this.encodedAnnotations = encodedAnnotations;
    }

    @Override
    public String toString() {
	return "StoryFragment [storyFragmentId=" + storyFragmentId
		+ ", storyText=" + storyText + ", photos=" + photos
		+ ", choices=" + choices + ", annotations=" + annotations + "]";
    }


}
