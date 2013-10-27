package com.team08storyapp.test;

import java.util.ArrayList;

import com.team08storyapp.ESHelper;
import junit.framework.TestCase;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;

public class testESHelper extends TestCase {

	private ESHelper esHelper;
	
	private int storyId;
	private Story addStory;
	private Story updateStory;
	private int storyListSize;
	
	private int firstFragment;
	private ArrayList<StoryFragment> storyFragments;
	private int storyFragmentListSize;

	private String searchText;
	private int searchListSize;
	
	public testESHelper(String name) {
		super(name);
	}

	// The setUp method is used to setup variables for use in the test cases.
	protected void setUp(){
		esHelper = new ESHelper();
		
		storyId = 1;
		addStory = new Story("title", "author");
		updateStory = new Story(1, "updatedTitle", "updatedAuthor");
		storyListSize = 1;
		
		firstFragment = 1;
		storyFragments = new ArrayList<StoryFragment>();
		storyFragments.add(new StoryFragment());
		storyFragmentListSize = 1;
		
		searchText = "title or author";
		searchListSize = 1;
	}
	
	/* Test Case for Use Case 3
	 * 
	 * The testAddStory method tests adding a story to the server. If the story is added
	 * successfully the method call to esHelper.addStory should return true.
	*/
	public void testAddOnlineStory(){
		addStory.setFirstStoryFragment(firstFragment);
		addStory.setStoryFragments(storyFragments);
		assertTrue(esHelper.addOnlineStory(addStory));	
	}
	
	/* Test Case for Use Case 10
	 * 
	 * The testUpdateStory method tests updating a story to the server. If the story updates
	 * successfully the method call to esHelper.updateStory should return true.
	*/
	public void testUpdateOnlineStory(){
		assertTrue(esHelper.updateOnlineStory(updateStory));
	}
	
	/* Test Case for Use Case 1
	 * 
	 * The testGetStory method tests retrieving a story from the server with a given story id. A
	 * story object should be returned. The object should not be null, should contain the same
	 * story id as given, and should contain the author, title and story fragments.
	 */
	public void testGetOnlineStory(){
		Story story = esHelper.getOnlineStory(storyId);
		
		assertTrue(!story.equals(null));
		assertTrue(story.getStoryId() == storyId);
		assertTrue(!story.getAuthor().equals(null) 
				&& !story.getTitle().equals(null)
				&& story.getStoryFragments().size() == storyFragmentListSize);
	}
	
	/* Test Case for Use Case 2 & 16
	 * 
	 * The testGetStories method tests retrieval of all the stories stored on the server. The
	 * server will contain at least one story for this test. The list returned from the method 
	 * call esHelper.getStories should be the same size as the one on the server.
	 */
	public void testGetOnlineStories(){
		assertTrue(esHelper.getOnlineStories().size() == storyListSize);
	}
	
	/* Test Case for Use Case 4
	 * 
	 * The testSearchForStory method tests the search functionality of the stories on the 
	 * server. Given a string to search the method call esHelper.searchForStory should return
	 * a list of the size we are expecting for the given search text.
	 * 
	 */
	public void testSearchOnlineStories(){
		assertTrue(esHelper.searchOnlineStories(searchText).size() == searchListSize);
	}
}
