package com.team08storyapp.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import junit.framework.TestCase;
import android.content.Context;

import com.team08storyapp.Choice;
import com.team08storyapp.FileHelper;
import com.team08storyapp.Photo;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;


public class testFileHelper extends TestCase {

    private FileHelper fHelper;

    private int storyId;
    private Story story0;
    private int storyCount;

    private int fragment;
    private StoryFragment fragment1;
    private ArrayList<StoryFragment> fragmentList;
    private Context context;

    public testFileHelper() {
	super();
    }

    // set up testing data for testing methods.
    public void setUp() throws FileNotFoundException, IOException {

	fHelper = new FileHelper(context, 1);

	storyId = 1;
	storyCount = 1;

	Story s1 = new Story(11, "Los Santos", "Alice Wu");
	ArrayList<StoryFragment> sfList = new ArrayList<StoryFragment>();
	StoryFragment sf1 = new StoryFragment(
		1,
		"	This is the city, where sins grow, profits expand, people gets colder,"
			+ " and Michael"
			+ "De Santa, the retired criminal wanted to start his new life. \n    But this comes to an end, when he finds"
			+ "out his son, Jimmy, is set up in a credit card fraud by a local dealership. \n    The rage occupies him, leading him"
			+ "to teach that manager a 'lesson'");
	ArrayList<Choice> cList = new ArrayList<Choice>();
	Choice c1 = new Choice(2, 1,
		"Use the car Jimmy bought from the dealership to crash into the dealership.");
	Choice c2 = new Choice(
		3,
		2,
		"Wait for someone from dealdership to 'steal' Jimmy's car and scares that someone to crahs into the dealership.");
	cList.add(c2);
	cList.add(c1);
	sf1.setChoices(cList);
	Photo p1 = new Photo();
	p1.setPhotoID(1);
	p1.setPictureName("Image1Fragment1Photo1.png");
	sfList.add(sf1);
	s1.setOnlineStoryId(7);

	StoryFragment sf2 = new StoryFragment(
		2,
		" Jimmy's Range Rover is drove right through the glass wall, crashing a brand new display Aston Martin DB9 "
			+ "into the manager's office.\n"
			+ "    The manager is shocked and furious, trying to started a friendly conversation. However the conversation is started by Michael's full fist and ended by "
			+ "a hit of a bat.\n    The manager lies on the ground, bleeading and moaning.\n"
			+ "    'I hope you'll enjoy your coma.', Michael hits the manager one last time. However he didn't notice there's a young fellow behind him."
			+ "    'Hey man, you know, I'm responsible for that 'deal'. You may get me fired for this!', the young, confuse-looking black man said.\n"
			+ "    'Why don't you come to work for me?', leaves the young man with his card, Michael walks away.");
	cList = new ArrayList<Choice>();
	c1 = new Choice(4, 1, "The young man calls Michael right away..");
	cList.add(c1);
	sf2.setChoices(cList);
	sfList.add(sf2);

	StoryFragment sf3 = new StoryFragment(
		3,
		"   'Hey young man, keep driving, we are going to do something crazy.',pointing the gun right at the driver's righ temple, "
			+ "Michael says and smirks.\n"
			+ "    'Easy, easy, may I know what's going on bro?'.\n"
			+ "    'Absolutely, drive to the dealership, and then crash it! You got a problem with that?'\n"
			+ "    'Not at all, you've got the gun,'");
	cList = new ArrayList<Choice>();
	c1 = new Choice(2, 1,
		"The driver follows Michael's instruction and drice right to the dealership.");
	cList.add(c1);
	sf3.setChoices(cList);
	sfList.add(sf3);

	StoryFragment sf4 = new StoryFragment(4,
		"    'Hey, this is Michael, leave a message I'll call you back, if I want.'");
	sfList.add(sf4);
	s1.setFirstStoryFragment(1);
	s1.setStoryFragments(sfList);

	fHelper.addOfflineStory(s1);
    }

    /*
     * Test case for Use Case 9
     * 
     * Taking the story that is cached by ESHelper.getOnlineStory(StoryId),
     * addOfflineStory(story) will store the story in local file. So if a story
     * is successfully added, the test function testAddOfflineStory() will
     * return true.
     */
    public void testAddOfflineStory() throws FileNotFoundException, IOException {
	story0.setFirstStoryFragment(fragment);
	story0.setStoryFragments(fragmentList);
	assertTrue(fHelper.addOfflineStory(story0));
    }

    /*
     * Test case for Use Case 5, 6, 7, 8
     * 
     * UpdateOfflineStory() updates an offline story. If the change is
     * successfully saved and applied to the story, the test method
     * testUpdateOfflineStory() should return true.
     */
    public void testUpdateOfflineStory() throws FileNotFoundException,
	    IOException {
	fragmentList.add(fragment1);
	story0.setStoryFragments(fragmentList);
	assertTrue(fHelper.updateOfflineStory(story0));
    }

    /*
     * Test case for Use Case 1
     * 
     * testGetOfflineStory tests getting a story from local file with a given
     * id. A story should be returned with that given id. And the basic
     * information of that story(title, author, id) should not be null. And we
     * also check the length of fragment list to make sure no fragment is lost.
     */
    public void testGetOfflineStory() throws FileNotFoundException, IOException {
	Story story = fHelper.getOfflineStory(1);

	assertNotNull(story);
	assertNotNull(story.getAuthor());
	assertNotNull(story.getTitle());
	assertEquals(story.getStoryFragments().size(), fragmentList.size());
	assertEquals(story.getOfflineStoryId(), 1);

    }

    /*
     * Test Case for Use Case 2
     * 
     * The testGetStories method tests retrieval of all the stories stored in
     * local file. The local file will contain at least one story for this test.
     * The list returned from the method call fHelper.getStories should be the
     * same size as the one stored locally.
     */
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

    public void testSearchOfflineStories() {
	assertEquals(fHelper.searchOfflineStories("LoS").size(),
		1);
	assertEquals(fHelper.searchOfflineStories("    ").size(), 1);
	assertEquals(fHelper.searchOfflineStories("\n").size(), 1);	
    }
    
    public void testEncodeStory() throws FileNotFoundException, IOException{
	Story encodedStory = fHelper.encodeStory(fHelper.getOfflineStory(1));
	assertNull(encodedStory.getStoryFragments().get(0).getPhotos().get(0).getEncodedPicture());	
    }
    
    public void testDecodeStory() throws Exception{
	Story encodedStory = fHelper.encodeStory(fHelper.getOfflineStory(1));
	assert(encodedStory == fHelper.decodeStory(encodedStory, 1));
    }

}
