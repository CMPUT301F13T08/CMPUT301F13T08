package com.team08storyapp.test;

import java.util.ArrayList;

import com.team08storyapp.ESHelper;
import junit.framework.TestCase;

import com.team08storyapp.Choice;
import com.team08storyapp.Photo;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;

public class testESHelper extends TestCase {

	private ESHelper esHelper;
	
	private int storyId;
	private Story addStory;
	private Story updateStory;
	private int storyListSize;
	
	private int storyFragmentId;
	private StoryFragment addStoryFragment;
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
		
		storyFragmentId = 1;
		addStoryFragment = new StoryFragment(1, "text");
		updateStoryFragment = new StoryFragment(1);
		updateStoryFragment.setStoryText("story");	
		firstFragment = new StoryFragment("first fragment");
		storyFragments = new ArrayList<StoryFragment>();
		storyFragments.add(firstFragment);
		storyFragmentListSize = 1;
		
		addChoice = new Choice();
		updateChoice = new Choice();
		choiceListSize = 1;
		
		addStoryPhoto = new Photo();
		addAnnotationPhoto = new Photo();
		photoListSize = 1;
		
		searchText = "title or author";
		searchListSize = 1;
	}
	
	/* The testAddStory method tests adding a story to the server. If the story is added
	 * successfully the method call to esHelper.addStory should return true.
	*/
	public void testAddStory(){
		addStory.setFirstStoryFragment(firstFragment);
		addStory.setStoryFragments(storyFragments);
		assertTrue(esHelper.addStory(addStory));	
	}
	
	/* The testAddStoryFragment method tests adding a story fragment to the server. If 
	 * the story fragment is added successfully the method call to esHelper.addStoryFragment 
	 * should return true.
	*/
	public void testAddStoryFragment(){
		assertTrue(esHelper.addStoryFragment(addStoryFragment));
	}
	
	/* The testAddAnnotationPhoto method tests adding a annotation to the server. If 
	 * the annotation is added successfully the method call to esHelper.addAnnotationPhoto 
	 * should return true.
	*/
	public void testAddAnnotationPhoto(){
		assertTrue(esHelper.addAnnotationPhoto(addAnnotationPhoto));
	}
	
	/* The testAddStoryPhoto method tests adding a story photo to the server. If 
	 * the story photo is added successfully the method call to esHelper.addStoryPhoto 
	 * should return true.
	*/
	public void testAddStoryPhoto(){
		assertTrue(esHelper.addStoryPhoto(addStoryPhoto));
	}
	
	/* The testAddChoice method tests adding a story fragment choice to the server. If 
	 * the story fragment choice is added successfully the method call to esHelper.addChoice 
	 * should return true.
	*/
	public void testAddChoice(){
		assertTrue(esHelper.addChoice(addChoice));
	}
	
	/* The testUpdateStory method tests updating a story to the server. If the story updates
	 * successfully the method call to esHelper.updateStory should return true.
	*/
	public void testUpdateStory(){
		assertTrue(esHelper.updateStory(updateStory));
	}
	
	/* The testUpdateStoryFragment method tests updating a story to the server. If the story 
	 * fragment updates successfully the method call to esHelper.updateStoryFragment should 
	 * return true.
	*/
	public void testUpdateStoryFragment(){
		assertTrue(esHelper.updateStoryFragment(updateStoryFragment));
	}
	
	/* The testUpdateChoice method tests updating a story fragment choice to the server. If 
	 * the story fragment choice updates successfully the method call to esHelper.testUpdateChoice  
	 * should return true.
	*/
	public void testUpdateChoice(){
		assertTrue(esHelper.updateChoice(updateChoice));
	}
	
	/* The testGetStory method tests retrieving a story from the server with a given story id. A
	 * story object should be returned. The object should not be null, should contain the same
	 * story id as given, and should contain the author, title and story fragments.
	 */
	public void testGetStory(){
		Story story = esHelper.getStory(storyId);
		
		assertTrue(!story.equals(null));
		assertTrue(story.getStoryId() == storyId);
		assertTrue(!story.getAuthor().equals(null) 
				&& !story.getTitle().equals(null)
				&& story.getStoryFragments().size() == storyFragmentListSize);
	}
	
	/* The testGetStories method tests retrieval of all the stories stored on the server. The
	 * server will contain at least one story for this test. The list returned from the method 
	 * call esHelper.getStories should be the same size as the one on the server.
	 */
	public void testGetStories(){
		assertTrue(esHelper.getStories().size() == storyListSize);
	}
	
	/* The testGetStoryFragment method tests the retrieving of a story fragment from the server
	 * given a story fragment id. A story fragment object should be returned. The object should not
	 * be null, should contain the same story fragment id as given, and should contain some story
	 * body text.
	 */
	public void testGetStoryFragment(){
		StoryFragment storyFragment = esHelper.getStoryFragment(storyFragmentId);
		
		assertTrue(!storyFragment.equals(null));
		assertTrue(storyFragment.getStoryFragmentId() == storyFragmentId);
		assertTrue(!storyFragment.getStoryText().equals(null));
	}
	
	/* The testGetStoryFragments method tests the retrieval of all story fragments for a
	 * given story id. The method call esHelper.getStoryFragments should return a list of 
	 * story fragments with the same size as expected for the story.
	 */
	public void testGetStoryFragments(){
		assertTrue(esHelper.getStoryFragments(storyId).size() == storyFragmentListSize);
	}
	
	/* The testGetStoryPhotos method tests the retrieval of all photos for a given
	 * story fragment id. This method is tested on a story containing at least one photo and 
	 * the method call esHelper.getStoryPhotos should return a list of the size we are expecting.
	 */
	public void testGetStoryPhotos(){
		assertTrue(esHelper.getStoryPhotos(storyFragmentId).size() == photoListSize);
	}
	
	/* The testGetStoryPhotos method tests the retrieval of all annotations for a given
	 * story fragment id. This method is tested on a story containing at least one 
	 * annotations and the method call esHelper.getAnnotationPhotos should return a list of 
	 * the size we are expecting.
	 */
	public void testGetAnnotationPhotos(){
		assertTrue(esHelper.getAnnotationPhotos(storyFragmentId).size() == photoListSize);
	}
	
	/* The testGetChoices method tests the retrieval of all choices for a given
	 * story fragment id. This method is tested on a story containing at least one 
	 * choice and the method call esHelper.getChoices should return a list of the size 
	 * we are expecting.
	 */
	public void testGetChoices(){
		assertTrue(esHelper.getChoices(storyFragmentId).size() == choiceListSize);		
	}
	
	/* The testSearchForStory method tests the search functionality of the stories on the 
	 * server. Given a string to search the method call esHelper.searchForStory should return
	 * a list of the size we are expecting for the given search text.
	 * 
	 */
	public void testSearchForStory(){
		assertTrue(esHelper.searchForStory(searchText).size() == searchListSize);
	}
}
