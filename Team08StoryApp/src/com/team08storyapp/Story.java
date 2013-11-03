package com.team08storyapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Story implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private int onlineStoryId;
    private int offlineStoryId;
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

    public Story(int offlineStoryId, String title, String author) {
	this.offlineStoryId = offlineStoryId;
	this.title = title;
	this.author = author;
	storyFragments = new ArrayList<StoryFragment>();
    }

    public Story(int offlineStoryId, String title, String author,
	    ArrayList<StoryFragment> storyFragments, int firstStoryFragmentId) {
	super();
	this.offlineStoryId = offlineStoryId;
	this.title = title;
	this.author = author;
	storyFragments = new ArrayList<StoryFragment>();
    }

    public int getOnlineStoryId() {
	return onlineStoryId;
    }

    public void setOnlineStoryId(int onlineStoryId) {
	this.onlineStoryId = onlineStoryId;
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
	return "Story [offlineStoryId=" + offlineStoryId+ "olineStoryId= " + onlineStoryId + "title=" + title + ", author="
		+ author + ", storyFragments=" + storyFragments
		+ ", firstStoryFragmentId=" + firstStoryFragmentId + "]";
    }

    public int getOfflineStoryId() {
	return offlineStoryId;
    }

    public void setOfflineStoryId(int offlineStoryId) {
	this.offlineStoryId = offlineStoryId;
    }
}
