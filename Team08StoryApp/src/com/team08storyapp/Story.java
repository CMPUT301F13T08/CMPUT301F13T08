package com.team08storyapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Story implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private int storyId;
    private String title;
    private String author;
    private ArrayList<StoryFragment> storyFragments;
    private int firstStoryFragmentId;

    public Story() {
	storyFragments = new ArrayList<StoryFragment>();
    }

    public Story(String title, String author) {
	this.title = title;
	this.author = author;
	storyFragments = new ArrayList<StoryFragment>();
    }

    public Story(int storyId, String title, String author) {
	this.storyId = storyId;
	this.title = title;
	this.author = author;
	storyFragments = new ArrayList<StoryFragment>();
    }

    public Story(int storyId, String title, String author,
	    ArrayList<StoryFragment> storyFragments, int firstStoryFragmentId) {
	super();
	this.storyId = storyId;
	this.title = title;
	this.author = author;
	storyFragments = new ArrayList<StoryFragment>();
    }

    public int getStoryId() {
	return storyId;
    }

    public void setStoryId(int storyId) {
	this.storyId = storyId;
    }

    public String getTitle() {
	return title;
    }

    public String getAuthor() {
	return author;
    }

    public ArrayList<StoryFragment> getStoryFragments() {
	return storyFragments;
    }

    public void setStoryFragments(ArrayList<StoryFragment> storyFragments) {
	this.storyFragments = storyFragments;
    }

    public int getFirstStoryFragment() {
	return firstStoryFragmentId;
    }

    public void setFirstStoryFragment(int firstStoryFragmentId) {
	this.firstStoryFragmentId = firstStoryFragmentId;
    }

    @Override
    public String toString() {
	return "Story [storyId=" + storyId + ", title=" + title + ", author="
		+ author + ", storyFragments=" + storyFragments
		+ ", firstStoryFragmentId=" + firstStoryFragmentId + "]";
    }
}
