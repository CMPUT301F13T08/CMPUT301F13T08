package com.team08storyapp.test;

import java.util.ArrayList;

import com.team08storyapp.Choice;
import com.team08storyapp.Photo;
import com.team08storyapp.SQLiteHelper;
import com.team08storyapp.Story;
import com.team08storyapp.StoryController;
import com.team08storyapp.StoryFragment;

import junit.framework.TestCase;

public class testStoryController extends TestCase {


	
	
	
	
	

	private StoryFragment addStoryFragment;
	
	
	/* The testAddStoryFragment method tests adding a story fragment to an existing story. If 
	 * the story fragment is added successfully the method call to esHelper.addStoryFragment 
	 * should return true. This is related to user story # 5.
	 **/
	public void testAddStoryFragment(){
		assertTrue(Story.addStoryFragment(addStoryFragment));
	}
	
	
	
}