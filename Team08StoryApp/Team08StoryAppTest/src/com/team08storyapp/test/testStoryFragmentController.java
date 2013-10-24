package com.team08storyapp.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;
import com.team08storyapp.StoryFragmentController;
import com.team08storyapp.Choice;
import com.team08storyapp.Annotation;
import com.team08storyapp.Photo;


//StoryFragmentController is a class which manipulates data of StoryFragment objects
public class testStoryFragmentController extends TestCase {

	private StoryFragmentController aSFragmentController;
	
	private Story firstStory;
	private StoryFragment firstFragment;
	private StoryFragment updateFragment;
	private Choice firstChoice;
	private Annotation firstAnnotation;
	private Photo firstPhoto;
	private ArrayList<StoryFragment> storyFragments;
	
	
	//SetUp sets up variables that are to be used in the test cases
	protected void setUp(){
		firstStory = new Story("title", "author");
		
		firstFragment = new StoryFragment(0,"String");
		updateFragment = new StoryFragment(1, "updateString");
		
		
		
	}
	
	//addAnnotation adds an annotation to a StoryFragment Object.
	public void testAddAnnotation(){
		
	}
	
	//addChoice adds a choice to a StoryFragment Object.
	public void testAddChoice(){
		
	}
	
	//addImage adds an image to a StoryFragment Object.
	public void testAddImage(){
		
	}
	
	//updateStoryFragment updates a fragment that has just been modified.
	public void testUpdateStoryFragment(){
		
	}
	
	
}
