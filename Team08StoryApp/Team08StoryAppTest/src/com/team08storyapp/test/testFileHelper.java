package com.team08storyapp.test;

import java.util.ArrayList;

import junit.framework.TestCase;

import com.team08storyapp.FileHelper;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;

public class testFileHelper extends TestCase {
	
	private FileHelper fHelper;
	
	private int storyId;
	private Story story0;
	private int storyCount;
	
	private StoryFragment fragment;
	private StoryFragment fragment1;
	private ArrayList<StoryFragment> fragmentList;
	
	private String searchText;
	private int resultCount;


	
	public testFileHelper(){
		super();
	}
	
	//  set up testing data for testing methods.
	public void setUp(){
		
		fHelper = new FileHelper();
		
		storyId = 0;
		story0 = new Story("title", "author");
		storyCount = 1;
		
		fragment = new StoryFragment("fragment1");
		fragment1 = new StoryFragment("2");
		fragmentList = new ArrayList<StoryFragment>();
		fragmentList.add(fragment);
		
		searchText = "title";
		resultCount = 1;
		
	}
	
	public void testAddOfflineStory(){
		story0.setFirstStoryFragment(fragment);
		story0.setStoryFragments(fragmentList);
		assertTrue(fHelper.addOfflineStory(story0));
	}
	
	public void testUpdateOfflineStory(){
		fragmentList.add(fragment1);
		story0.setStoryFragments(fragmentList);
		assertTrue(fHelper.updateOfflineStory(story0));
	}
	
	public void testGetOfflineStory(){
		Story story = fHelper.getOfflineStory(storyId);
		
		assertNotNull(story);
		assertNotNull(story.getAuthor());
		assertNotNull(story.getTitle());
		assertEquals(story.getStoryFragments().size(), fragmentList.size());
		assertEquals(story.getStoryId(), storyId);
		
	}
	
	public void testGetOfflineStories(){
		assertEquals(fHelper.getOfflineStories().size(), storyCount);
	}
	
	public void testSearchOfflineStories(){
		assertEquals(fHelper.searchOfflineStories(searchText).size(), resultCount);
	}
	
}
