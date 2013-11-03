package com.team08storyapp.test;

import java.util.ArrayList;

import com.team08storyapp.Annotation;
import com.team08storyapp.Choice;
import com.team08storyapp.Photo;
import com.team08storyapp.StoryController;
import com.team08storyapp.StoryFragment;

import junit.framework.TestCase;

public class testStoryController extends TestCase {

    	private int nextStoryFragmentId;
	private StoryFragment addStoryFragment;
	private StoryFragment firstStoryFragment;
	private StoryFragment retrievedStoryFragment;
	StoryFragment secondStoryFragment;
	ArrayList<StoryFragment> storyFragmentList = null;
		
	/* The testAddStoryFragment method tests adding a story fragment to an existing story. If 
	 * the story fragment is added successfully the method call to esHelper.addStoryFragment 
	 * should return true. This is related to user story # 5.
	 **/
	public void testAddStoryFragment(){
		assertTrue(StoryController.addStoryFragment(addStoryFragment));
	}
	
	protected void setUp(){		
	    	
	    	// set the next fragment id to 2 - see testReadStoryFragment
	    	nextStoryFragmentId = 2;
	    	firstStoryFragment = new StoryFragment(1, "fragment text");
	    	secondStoryFragment = new StoryFragment(2, "another fragment text");
	    	
	    	// storyFragment list has two fragments with ids 1 and 2
	    	storyFragmentList.add(firstStoryFragment);
	    	storyFragmentList.add(secondStoryFragment);
		
		
	}
	
	
	public void testReadStoryFragment(){
	    	// call the method readStoryFragment() of story controller, to retrieve a fragment from a list, by id
	    	retrievedStoryFragment = StoryController.readStoryFragment(storyFragmentList, nextStoryFragmentId);
	    	//the returned fragment should be the same as the fragment in the storyFragmentList with id 2
	    	assertEquals(retrievedStoryFragment, secondStoryFragment);
	    	
	}
	
}