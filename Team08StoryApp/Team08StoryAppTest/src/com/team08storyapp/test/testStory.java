package com.team08storyapp.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

import com.team08storyapp.Choice;
import com.team08storyapp.Photo;
import com.team08storyapp.SQLiteHelper;
import com.team08storyapp.Story;
import com.team08storyapp.StoryController;
import com.team08storyapp.StoryFragment;

public class testStory extends TestCase {
	
	private StoryFragment firstFragment;
	private String storyAuthor;
	private ArrayList<StoryFragment> storyFragments;
	private int storyID;
	private String storyTitle;
	
	@Before
	protected void setUp() throws Exception{
		
		storyID = 1;
		storyAuthor = "author";
		storyTitle = "HelloWorld";
		firstFragment = new StoryFragment("first fragment");
		storyFragments = new ArrayList<StoryFragment>();
		
	}
	
	
	public void testStory() {
		fail("Not yet implemented");
	}

}
