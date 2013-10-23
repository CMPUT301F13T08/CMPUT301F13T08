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
	
	/*
	 * Test case for Use Case 9
	 * 
	 * Taking the story that is cached by ESHelper.getOnlineStory(StoryId),
	 * addOfflineStory(story) will store the story in local file. So if a
	 * story is successfully added, the test function testAddOfflineStory()
	 *  will return true.
	 */
	public void testAddOfflineStory(){
		story0.setFirstStoryFragment(fragment);
		story0.setStoryFragments(fragmentList);
		assertTrue(fHelper.addOfflineStory(story0));
	}
	
	/*
	 * Test case for Use Case 5, 6, 7, 8
	 * 
	 * UpdateOfflineStory() updates an offline story. 
	 * If the change is successfully saved and applied to the story, 
	 * the test method testUpdateOfflineStory() should return true.
	 */
	public void testUpdateOfflineStory(){
		fragmentList.add(fragment1);
		story0.setStoryFragments(fragmentList);
		assertTrue(fHelper.updateOfflineStory(story0));
	}
	
	/*
	 * Test case for Use Case 1
	 * 
	 * testGetOfflineStory tests getting a story from local file with a given id.
	 * A story should be returned with that given id. And the basic information of
	 * that story(title, author, id) should not be null. And we also check the length
	 * of fragment list to make sure no fragment is lost.
	 */
	public void testGetOfflineStory(){
		Story story = fHelper.getOfflineStory(storyId);
		
		assertNotNull(story);
		assertNotNull(story.getAuthor());
		assertNotNull(story.getTitle());
		assertEquals(story.getStoryFragments().size(), fragmentList.size());
		assertEquals(story.getStoryId(), storyId);
		
	}
	
	/* Test Case for Use Case 2
	 * 
	 * The testGetStories method tests retrieval of all the stories stored in local file. The
	 * local file will contain at least one story for this test. The list returned from the method 
	 * call fHelper.getStories should be the same size as the one stored locally.
	 */
	public void testGetOfflineStories(){
		assertEquals(fHelper.getOfflineStories().size(), storyCount);
	}
	
	/* Test Case for Use Case 4
	 * 
	 * The testSearchForStory method tests the search functionality of the stories on the 
	 *local file. Given a string to search the method call fHelper.searchForStory should return
	 * a list of the size we are expecting for the given search text.
	 * 
	 */
	public void testSearchOfflineStories(){
		assertEquals(fHelper.searchOfflineStories(searchText).size(), resultCount);
	}
	
}
