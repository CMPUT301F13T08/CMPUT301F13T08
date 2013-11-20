package com.team08storyapp.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;

import com.team08storyapp.Annotation;
import com.team08storyapp.Choice;
import com.team08storyapp.MainActivity;
import com.team08storyapp.Photo;
import com.team08storyapp.Story;
import com.team08storyapp.StoryController;
import com.team08storyapp.StoryFragment;

public class testStoryController extends
	ActivityInstrumentationTestCase2<MainActivity> {

    private int nextStoryFragmentId;
    private StoryFragment firstStoryFragment;
    private StoryFragment retrievedStoryFragment;
    private StoryFragment secondStoryFragment;
    private StoryFragment thirdStoryFragment;
    private StoryFragment fourthStoryFragment;
    private StoryFragment firstReplaceStoryFragment;
    ArrayList<StoryFragment> storyFragmentList;
    ArrayList<Choice> Choices;
    ArrayList<Annotation> Annotations;
    ArrayList<Photo> Photos;
    private StoryFragment testAddStoryFragment;
    private StoryFragment joinStoryFragment1;
    private StoryFragment joinStoryFragment2;
    private Story story;
    private Story replacedStory;
    private int storyListSize;
    private Choice testChoice1;
    private Choice testChoice2;
    private Choice testChoice3;
    private Choice testChoice4;
    private Annotation testAnn1;
    private Annotation testAnn2;
    private Photo testPhoto1;
    private Photo testPhoto2;

    public testStoryController() {
	super(MainActivity.class);
    }

    protected void setUp() {
	/* Instantiate the Array List of Story Fragments */
	storyFragmentList = new ArrayList<StoryFragment>();

	/* Set the next fragment id to 2 - see testReadStoryFragment */
	nextStoryFragmentId = 2;
	firstStoryFragment = new StoryFragment(1, "fragment text");
	secondStoryFragment = new StoryFragment(2, "another fragment text");

	/* Set two more fragments for test random Choice */
	thirdStoryFragment = new StoryFragment(3, "third fragment text");
	fourthStoryFragment = new StoryFragment(4, "fourth fragment text");
	firstReplaceStoryFragment = new StoryFragment(1, "first new fragment");

	/* StoryFragment list has two fragments with ids 1 and 2 */
	storyFragmentList.add(firstStoryFragment);
	storyFragmentList.add(secondStoryFragment);
	storyFragmentList.add(thirdStoryFragment);
	storyFragmentList.add(fourthStoryFragment);

	testAddStoryFragment = new StoryFragment(1, "adding Story Fragment");

	joinStoryFragment1 = testAddStoryFragment;
	joinStoryFragment2 = new StoryFragment(2, "joining to Story Fragment 1");

	story = new Story("title", "author");

	replacedStory = new Story("Replace", "Fragment");
	replacedStory.getStoryFragments().add(firstStoryFragment);
	replacedStory.getStoryFragments().add(secondStoryFragment);

	/* sample Story List Size for testing random Story selection */
	storyListSize = 12;

	/* Sample array list of choices for testing randomChoice */
	Choices = new ArrayList<Choice>();
	testChoice1 = new Choice(1, 1, "This is test 1");
	testChoice2 = new Choice(2, 2, "This is test 2");
	testChoice3 = new Choice(3, 3, "This is test 3");
	testChoice4 = new Choice(4, 4, "This is test 4");
	Choices.add(testChoice1);
	Choices.add(testChoice2);
	Choices.add(testChoice3);
	Choices.add(testChoice4);

	Annotations = new ArrayList<Annotation>();
	testAnn1 = new Annotation();
	testAnn1.setAnnotationID(1);
	testAnn2 = new Annotation();
	testAnn2.setAnnotationID(2);
	Annotations.add(testAnn1);
	Annotations.add(testAnn2);

	Photos = new ArrayList<Photo>();
	testPhoto1 = new Photo();
	testPhoto1.setPhotoID(1);
	testPhoto2 = new Photo();
	testPhoto2.setPhotoID(2);
	Photos.add(testPhoto1);
	Photos.add(testPhoto2);
    }

    /*
     * Constructor Test for Story object, with parameters title and author. Also
     * set firstStoryFragmentId, offlineStoryId, onlineStoryId, and
     * storyFragments.
     */
    public void testFirstConstructorStory() {
	Story story = new Story("title", "author");

	assertEquals("title", story.getTitle());
	assertEquals("author", story.getAuthor());

	story.setFirstStoryFragmentId(1);
	story.setOfflineStoryId(2);
	story.setOnlineStoryId(3);
	story.setStoryFragments(storyFragmentList);

	assertEquals(1, story.getFirstStoryFragmentId());
	assertEquals(2, story.getOfflineStoryId());
	assertEquals(3, story.getOnlineStoryId());
	assertEquals(1, story.getStoryFragments().get(0).getStoryFragmentId());
    }

    /*
     * Constructor Test for Story object, with parameters offlineStorId, title,
     * and author. Also set firstStoryFragmentId, onlineStoryId, and
     * storyFragments.
     */
    public void testSecondConstructorStory() {
	Story story = new Story(1, "title", "author");

	assertEquals(1, story.getOfflineStoryId());
	assertEquals("title", story.getTitle());
	assertEquals("author", story.getAuthor());

	story.setFirstStoryFragmentId(1);
	story.setOnlineStoryId(3);
	story.setStoryFragments(storyFragmentList);

	assertEquals(1, story.getFirstStoryFragmentId());
	assertEquals(3, story.getOnlineStoryId());
	assertEquals(1, story.getStoryFragments().get(0).getStoryFragmentId());

    }

    /*
     * Constructor Test for StoryFragment object, with parameters
     * storyFragmentId. Also set storyText, choices, annotations, an photos.
     */
    public void testFirstConstructorStoryFragment() {
	StoryFragment storyFragment = new StoryFragment(1);

	assertEquals(1, storyFragment.getStoryFragmentId());

	storyFragment.setStoryText("text");
	storyFragment.setChoices(Choices);
	storyFragment.setAnnotations(Annotations);
	storyFragment.setPhotos(Photos);

	assertEquals("text", storyFragment.getStoryText());
	assertEquals("This is test 1", storyFragment.getChoices().get(0)
		.getText());
	assertEquals(2, storyFragment.getAnnotations().get(1).getAnnotationID());
	assertEquals(2, storyFragment.getPhotos().get(1).getPhotoID());
    }

    /*
     * Constructor Test for StoryFragment object, with parameters
     * storyFragmentId and storyText. Also set choices, annotations, photos
     */
    public void testSecondConstructorStoryFragment() {
	StoryFragment storyFragment = new StoryFragment(1, "text");

	assertEquals(1, storyFragment.getStoryFragmentId());
	assertEquals("text", storyFragment.getStoryText());

	storyFragment.setChoices(Choices);
	storyFragment.setAnnotations(Annotations);
	storyFragment.setPhotos(Photos);

	assertEquals("This is test 1", storyFragment.getChoices().get(0)
		.getText());
	assertEquals(2, storyFragment.getAnnotations().get(1).getAnnotationID());
	assertEquals(2, storyFragment.getPhotos().get(1).getPhotoID());

    }

    /*
     * Constructor Test for Choice object, with parameters
     * toGoToStoryFragmentID, choiceId, and text
     */
    public void testConstructorChoice() {
	Choice choice = new Choice(2, 1, "text");

	assertEquals(2, choice.getStoryFragmentID());
	assertEquals(1, choice.getChoiceId());
	assertEquals("text", choice.getText());

    }

    /*
     * Test for Use Case 5
     * 
     * The testAddStoryFragment method tests adding a story fragment to an
     * existing story. It will take a Story and add the given Story Fragment to
     * it. Then it will retrieve the Story from the Author's MyStories local
     * storage and compare the Story Fragment to the one added. If they are the
     * same the Story Fragment was added successfully.
     * 
     * NOTE: The test in this method will fail as this method this is tested
     * will be handled in Project Part 4
     */
    public void testAddStoryFragment() {
	/* Check that the Story Fragment exists in the Story */
	Story s = StoryController.addStoryFragment(testAddStoryFragment, story);
	assertTrue(s.getStoryFragments().contains(testAddStoryFragment));
    }

    /*
     * Test for Use Case 7 & 8
     * 
     * The testaddChoice method tests adding a choice to a story fragment of an
     * existing story. This will take the current Story Fragment, the Id of the
     * Story Fragment the Choice leads to and the Choice the Author has created
     * and add it to the Story Fragment. Once it has been added we will get the
     * Story Fragment and check for the Choice comparing it to the original
     * object.
     */
    public void testaddChoice() {
	/* set the text for the Choice and add it to the Story Fragment */
	int storyFragment2Id = joinStoryFragment2.getStoryFragmentId();
	String choiceText = "This is a choice";
	StoryFragment storyFragment = StoryController.addChoice(choiceText,
		joinStoryFragment1, storyFragment2Id);

	/* Check that the last choice added is the Choice we added */
	ArrayList<Choice> choices = storyFragment.getChoices();
	Choice choice = choices.get(choices.size() - 1);
	assertTrue(choice.getText() == choiceText);
	assertTrue(choice.getStoryFragmentID() == storyFragment2Id);
    }

    /*
     * Call the method readStoryFragment() of story controller, to retrieve a
     * fragment from a list, by id the returned fragment should be the same as
     * the fragment in the storyFragmentList with id 2.
     */
    public void testReadStoryFragment() {
	retrievedStoryFragment = StoryController.readStoryFragment(
		storyFragmentList, nextStoryFragmentId);

	assertEquals(retrievedStoryFragment, secondStoryFragment);
    }

    /*
     * Test for Use Case 19
     * 
     * The testFeelingLuck method tests getting a random Story index for the
     * user to read a random Story. Using a set Story List size, the call to
     * feelingLucky will return a random Story index. The first test to make
     * sure that we are receiving a random Story is that the Story index is
     * between zero and the size minus one. The second test is to run the call
     * again and check that the two calls resulted in two different Story
     * indexes.
     */
    public void testFeelingLucky() {

	/*
	 * Test that the random Story index is between 0 and storyListSize minus
	 * one
	 */
	int randomStoryIndex = StoryController.feelingLucky(storyListSize);
	assertTrue(randomStoryIndex >= 0
		&& randomStoryIndex <= storyListSize - 1);

	/*
	 * Test that the method is returning randomly by comparing two random
	 * call results to each other
	 */
	int randomStoryIndex2 = StoryController.feelingLucky(storyListSize);
	assertTrue(randomStoryIndex != randomStoryIndex2);
    }

    /*
     * Test for Use Case 17
     * 
     * The testRandomChoice method tests getting a random next fragment ID from
     * a list of choices the current fragment has. Using a set array list of
     * different choices of a fragment, the call of testRandomChoice should
     * return the next fragment ID of a choice at random.
     */
    public void testRandomChoice() {

	/*
	 * Test that a random fragment ID is generated between the set fragments
	 * above (1, 2, 3, 4)
	 */
	int randomFragment0 = StoryController.randomChoice(Choices);

	/*
	 * Tests that the method is returning different fragment IDs randomly by
	 * comparing two different call results.
	 */
	int randomFragment1 = StoryController.randomChoice(Choices);
	assertTrue(randomFragment0 != randomFragment1);

    }

    /*
     * Test whether a fragment is replaced in the fragment list of the story.
     * The given fragment should be inserted at the given index in the list. An
     * updated story object is returned by the controller method.
     */
    public void testUpdateStoryFragment() {
	replacedStory = StoryController.updateStoryFragment(replacedStory,
		firstReplaceStoryFragment, 1, 0);
	assertEquals("first new fragment", replacedStory.getStoryFragments()
		.get(0).getStoryText());
    }

}
