package com.team08storyapp.test;

import java.util.ArrayList;

import com.team08storyapp.Choice;
import com.team08storyapp.Story;
import com.team08storyapp.StoryController;
import com.team08storyapp.StoryFragment;

import junit.framework.TestCase;

public class testStoryController extends TestCase {

    private int nextStoryFragmentId;
    private StoryFragment firstStoryFragment;
    private StoryFragment retrievedStoryFragment;
    StoryFragment secondStoryFragment;
    ArrayList<StoryFragment> storyFragmentList;
    private StoryFragment testAddStoryFragment;
    private StoryFragment joinStoryFragment1;
    private StoryFragment joinStoryFragment2;
    private Story story;

    protected void setUp() {
	/* Instantiate the Array List of Story Fragments */
	storyFragmentList = new ArrayList<StoryFragment>();

	/* Set the next fragment id to 2 - see testReadStoryFragment */
	nextStoryFragmentId = 2;
	firstStoryFragment = new StoryFragment(1, "fragment text");
	secondStoryFragment = new StoryFragment(2, "another fragment text");

	/* StoryFragment list has two fragments with ids 1 and 2 */
	storyFragmentList.add(firstStoryFragment);
	storyFragmentList.add(secondStoryFragment);

	testAddStoryFragment = new StoryFragment(1, "adding Story Fragment");

	joinStoryFragment1 = testAddStoryFragment;
	joinStoryFragment2 = new StoryFragment(2, "joining to Story Fragment 1");

	story = new Story("title", "author");
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
     * 
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

}
