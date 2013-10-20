package com.team08storyapp.test;

import java.util.ArrayList;

import junit.framework.TestCase;

import com.team08storyapp.SQLiteHelper;
import com.team08storyapp.Choice;
import com.team08storyapp.Photo;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;
// test test
public class testSQLiteHelper extends TestCase {

	private SQLiteHelper SHelper;
	
	private int storyId;
	private Story addStory;
	private Story updateStory;
	private int storyListSize;
	
	private int storyFragmentId;
	private StoryFragment updateStoryFragment;
	private StoryFragment firstFragment;
	private ArrayList<StoryFragment> storyFragments;
	private int storyFragmentListSize;
	
	private Choice addChoice;
	private Choice updateChoice;
	private int choiceListSize;
	
	private Photo addStoryPhoto;
	private Photo addAnnotationPhoto;
	private int photoListSize;

	private String searchText;
	private int searchListSize;
	

	// The setUp method is used to setup variables for use in the test cases.
	protected void setUp(){
		SHelper = new SQLiteHelper();
		
		storyId = 1;
		addStory = new Story("title", "author");
		updateStory = new Story(1, "updatedTitle", "updatedAuthor");
		storyListSize = 1;
		
		storyFragmentId = 1;
		updateStoryFragment = new StoryFragment(1);
		updateStoryFragment.setStoryText("story");	
		firstFragment = new StoryFragment("first fragment");
		storyFragments = new ArrayList<StoryFragment>();
		storyFragments.add(firstFragment);
		storyFragmentListSize = 1;
		
		addChoice = new Choice(firstFragment.getStoryFragmentId());
		updateChoice = new Choice(firstFragment.getStoryFragmentId());
		choiceListSize = 1;
		
		addStoryPhoto = new Photo();
		addAnnotationPhoto = new Photo();
		photoListSize = 1;
		
		searchText = "title or author";
		searchListSize = 1;
	}
	
	/* The testAddStory method tests adding a story to the server. If the story is added
	 * successfully the method call to SHelper.addStory should return true.
	*/
	public void testAddStory(){
		addStory.setFirstStoryFragment(firstFragment);
		addStory.setStoryFragments(storyFragments);
		assertTrue(SHelper.addStory(addStory));	
	}
	
	/* The testAddStoryFragment method tests adding a story fragment to the server. If 
	 * the story fragment is added successfully the method call to SHelper.addStoryFragment 
	 * should return true.
	*/
	public void testAddStoryFragment(){
		assertTrue(SHelper.addStoryFragment(firstFragment));
	}
	
	/* The testAddAnnotationPhoto method tests adding a annotation to the server. If 
	 * the annotation is added successfully the method call to SHelper.addAnnotationPhoto 
	 * should return true.
	*/
	public void testAddAnnotationPhoto(){
		assertTrue(SHelper.addAnnotationPhoto(addAnnotationPhoto));
	}
	
	/* The testAddStoryPhoto method tests adding a story photo to the server. If 
	 * the story photo is added successfully the method call to SHelper.addStoryPhoto 
	 * should return true.
	*/
	public void testAddStoryPhoto(){
		assertTrue(SHelper.addStoryPhoto(addStoryPhoto));
	}
	
	/* The testAddChoice method tests adding a story fragment choice to the server. If 
	 * the story fragment choice is added successfully the method call to SHelper.addChoice 
	 * should return true.
	*/
	public void testAddChoice(){
		assertTrue(SHelper.addChoice(addChoice));
	}
	
	/* The testUpdateStory method tests updating a story to the server. If the story updates
	 * successfully the method call to SHelper.updateStory should return true.
	*/
	public void testUpdateStory(){
		assertTrue(SHelper.updateStory(updateStory));
	}
	
	/* The testUpdateStoryFragment method tests updating a story to the server. If the story 
	 * fragment updates successfully the method call to SHelper.updateStoryFragment should 
	 * return true.
	*/
	public void testUpdateStoryFragment(){
		assertTrue(SHelper.updateStoryFragment(updateStoryFragment));
	}
	
	
	/* The testGetStory method tests retrieving a story from the server with a given story id. A
	 * story object should be returned. The object should not be null, should contain the same
	 * story id as given, and should contain the author, title and story fragments.
	 */
	public void testGetStory(){
		Story story = SHelper.getStory(storyId);
		
		assertNotNull(story);
		assertEquals(story.getStoryId(), storyId);
		assertEquals(story.getStoryFragments().size(), storyFragmentListSize);
	}
	
	/* The testGetStories method tests retrieval of all the stories stored on the server. The
	 * server will contain at least one story for this test. The list returned from the method 
	 * call SHelper.getStories should be the same size as the one on the server.
	 */
	public void testGetStories(){
		assertEquals(SHelper.getStories().size(), storyListSize);
	}
	
	/* The testGetStoryFragment method tests the retrieving of a story fragment from the server
	 * given a story fragment id. A story fragment object should be returned. The object should not
	 * be null, should contain the same story fragment id as given, and should contain some story
	 * body text.
	 */
	public void testGetStoryFragment(){
		StoryFragment storyFragment = SHelper.getStoryFragment(storyFragmentId);
		
		assertNotNull(storyFragment);
		assertEquals(storyFragment.getStoryFragmentId(),storyFragmentId);
		assertNotNull(storyFragment.getStoryText());
	}
	
	/* The testGetStoryFragments method tests the retrieval of all story fragments for a
	 * given story id. The method call SHelper.getStoryFragments should return a list of 
	 * story fragments with the same size as expected for the story.
	 */
	public void testGetStoryFragments(){
		assertEquals(SHelper.getStoryFragments(storyId).size(), storyFragmentListSize);
	}
	
	/* The testGetStoryPhotos method tests the retrieval of all photos for a given
	 * story fragment id. This method is tested on a story containing at least one photo and 
	 * the method call SHelper.getStoryPhotos should return a list of the size we are expecting.
	 */
	public void testGetStoryPhotos(){
		assertEquals(SHelper.getStoryPhotos(storyFragmentId).size(), photoListSize);
	}
	
	/* The testGetStoryPhotos method tests the retrieval of all annotations for a given
	 * story fragment id. This method is tested on a story containing at least one 
	 * annotations and the method call SHelper.getAnnotationPhotos should return a list of 
	 * the size we are expecting.
	 */
	public void testGetAnnotationPhotos(){
		assertEquals(SHelper.getAnnotationPhotos(storyFragmentId).size(), photoListSize);
	}
	
	/* The testGetChoices method tests the retrieval of all choices for a given
	 * story fragment id. This method is tested on a story containing at least one 
	 * choice and the method call SHelper.getChoices should return a list of the size 
	 * we are expecting.
	 */
	public void testGetChoices(){
		assertEquals(SHelper.getChoices(storyFragmentId).size(), choiceListSize);		
	}
	
	/* The testSearchForStory method tests the search functionality of the stories on the 
	 * server. Given a string to search the method call SHelper.searchForStory should return
	 * a list of the size we are expecting for the given search text.
	 * 
	 */
	public void testSearchForStory(){
		assertEquals(SHelper.searchForStory(searchText).size(), searchListSize);
	}
}
