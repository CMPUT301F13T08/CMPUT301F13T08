package com.team08storyapp.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.content.Context;
import android.test.AndroidTestCase;

import com.team08storyapp.FileHelper;
import com.team08storyapp.Photo;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;

public class testFileHelper extends AndroidTestCase {

    private FileHelper fHelper;

    private Story s1;
    private int storyCount;

    private Story s2;
    
    private StoryFragment fragment1;
    private ArrayList<StoryFragment> sfList;
    private ArrayList<StoryFragment> sfList1;
    private Context context;

    private String content;

    @Before
    // set up testing data for testing methods.
    public void setUp() throws FileNotFoundException, IOException {

	context = getContext();
	fHelper = new FileHelper(context, 0);

	storyCount = 2;

	s1 = new Story(12, "Morroco likoko", "Alice Wu");
	s1.setOfflineStoryId(12);
	sfList = new ArrayList<StoryFragment>();
	content = "	This is the city, where sins grow, profits expand, people gets colder,"
		+ " and Michael"
		+ "De Santa, the retired criminal wanted to start his new life. \n    But this comes to an end, when he finds"
		+ "out his son, Jimmy, is set up in a credit card fraud by a local dealership. \n    The rage occupies him, leading him"
		+ "to teach that manager a 'lesson'";
	StoryFragment sf1 = new StoryFragment(1, content);

	Photo p1 = new Photo();
	p1.setPhotoID(1);
	p1.setPictureName("Image12Fragment1Photo1.png");
	ArrayList<Photo> pList = new ArrayList<Photo>();
	pList.add(p1);
	sf1.setPhotos(pList);

	sfList.add(sf1);
	s1.setFirstStoryFragmentId(1);
	s1.setStoryFragments(sfList);
	fHelper.addOfflineStory(s1);
	
	s2 = new Story(13, "FA LA LA LA ", "FA FA LA LA");
	sfList1 = new ArrayList<StoryFragment>();
	s2.setStoryFragments(sfList1);
	fHelper.addOfflineStory(s2);
    }

    /*
     * Test case for Use Case 9
     * 
     * Taking the story that is cached by ESHelper.getOnlineStory(StoryId),
     * addOfflineStory(story) will store the story in local file. So if a story
     * is successfully added, the test function testAddOfflineStory() will
     * return true.
     */
    @Test
    public void testAddOfflineStory() throws FileNotFoundException, IOException {
	assertTrue(fHelper.addOfflineStory(s1));
    }

    /*
     * Test case for Use Case 5, 6, 7, 8
     * 
     * UpdateOfflineStory() updates an offline story. If the change is
     * successfully saved and applied to the story, the test method
     * testUpdateOfflineStory() should return true.
     * 
     * NOTE TO JOSH:
     * Sorry we didn't see any difference between use case 6 and use case 11.
     */
    @Test
    public void testUpdateOfflineStory() throws FileNotFoundException,
	    IOException {
	sfList.add(fragment1);
	s1.setStoryFragments(sfList);
	assertTrue(fHelper.updateOfflineStory(s1));
    }

    /*
     * Test case for Use Case 1
     * 
     * testGetOfflineStory tests getting a story from local file with a given
     * id. A story should be returned with that given id. And the basic
     * information of that story(title, author, id) should not be null. And we
     * also check the length of fragment list to make sure no fragment is lost.
     */
    @Test
    public void testGetOfflineStory() throws FileNotFoundException, IOException {
	Story story = fHelper.getOfflineStory(12);

	/* make sure we get the right story */
	assertNotNull(story);
	assertNotNull(story.getAuthor());
	assertNotNull(story.getTitle());
	assertEquals(story.getStoryFragments().size(), sfList.size());
	assertEquals(story.getOfflineStoryId(), 12);

	/* test we are reading the same text as we wrote into the content string */
	assertEquals(story.getStoryFragments().get(0).getStoryText(), content);
	
	/* test we are reading the same photo*/
	assertEquals(story.getStoryFragments().get(0).getPhotos().get(0).getPictureName(), "Image12Fragment1Photo1.png");
	
	/* make sure the story we read has no choices */
	assertTrue(story.getStoryFragments().get(0).getChoices() == null);
    }

    /*
     * Test Case for Use Case 2
     * 
     * The testGetStories method tests retrieval of all the stories stored in
     * local file. The local file will contain at least one story for this test.
     * The list returned from the method call fHelper.getStories should be the
     * same size as the one stored locally.
     */
    @Test
    public void testGetOfflineStories() throws FileNotFoundException,
	    IOException {
	assertEquals(fHelper.getOfflineStories().size(), storyCount);
    }

    /*
     * Test Case for Use Case 4
     * 
     * The testSearchForStory method tests the search functionality of the
     * stories on thelocal file. Given a string to search the method call
     * fHelper.searchForStory should return a list of the size we are expecting
     * for the given search text.
     */
    @Test
    public void testSearchOfflineStories() {
	assertEquals(fHelper.searchOfflineStories("co").size(), 1);
	assertEquals(fHelper.searchOfflineStories("    ").size(), 2);
	assertEquals(fHelper.searchOfflineStories("\n").size(), 2);
	assertEquals(fHelper.searchOfflineStories("DAT").size(), 0);
    }

    
    @Test
    public void testEncodeStory() throws FileNotFoundException, IOException {
	Story encodedStory = fHelper.encodeStory(s1);
	assertNull(encodedStory.getStoryFragments().get(0).getPhotos().get(0)
		.getEncodedPicture());
	assertEquals(encodedStory.getStoryFragments().get(0).getPhotos().get(0)
		.getPictureName(), "Image12Fragment1Photo1.png");
    }

    @Test
    public void testDecodeStory() throws Exception {
	Story encodedStory = fHelper.encodeStory(s1);
	assertNull(fHelper.decodeStory(encodedStory, 1).getStoryFragments()
		.get(0).getPhotos().get(0).getEncodedPicture());
    }

    @After
    protected void tearDown() {
	context.deleteFile("Download12");
	context.deleteFile("Download13");
    }

}
