package com.team08storyapp.test;

import com.team08storyapp.ESHelper;
import junit.framework.TestCase;

import com.team08storyapp.Choice;
import com.team08storyapp.Photo;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;

public class testESHelper extends TestCase {

	private ESHelper esHelper;
	private Story addStory;
	private Story updateStory;
	private Photo addPhoto;
	private StoryFragment addStoryFragment;
	private StoryFragment updateStoryFragment;
	private Choice addChoice;
	private Choice updateChoice;
	private String searchText;
	
	
	public testESHelper(String name) {
		super(name);
	}

	protected void setUp(){
		esHelper = new ESHelper();
		addStory = new Story("title", "author");
		updateStory = new Story(1, "updatedTitle", "updatedAuthor");
		addPhoto = new Photo();
		addStoryFragment = new StoryFragment("story text");
		updateStoryFragment = new StoryFragment(1);
		updateStoryFragment.setStoryText("story");
		addChoice = new Choice();
		updateChoice =new Choice();
		searchText = "title or author";
	}
	
	public void testAddStory(){

	}
	
	public void testAddStoryFragment(){
		
	}
	
	public void testAddAnnotationPhoto(){
		
	}
	
	public void testAddStoryPhoto(){
		
	}
	
	public void testAddChoice(){
		
	}
	
	public void testUpdateStory(){
		
	}
	
	public void testUpdateStoryFragment(){
		
	}
	
	public void testUpdateChoice(){
		
	}
	
	public void testGetStory(){
		
	}
	
	public void testGetStoryFragment(){
		
	}
	
	public void testGetStoryPhotos(){
		
	}
	
	public void testGetAnnotationPhotos(){
		
	}
	
	public void testGetChoices(){
		
	}
	
	public void testSearchForStory(){
		
	}
}
