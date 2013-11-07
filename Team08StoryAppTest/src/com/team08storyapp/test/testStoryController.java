package com.team08storyapp.test;

import java.util.ArrayList;

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
     * The test in this method will fail as this method this is tested will be
     * handled in Project Part 4
     */
    public void testAddStoryFragment() {

    }

    /*
     * Test for Use Case
     * 
     * The testaddChoice method tests adding a choice to a story fragment of an
     * existing story.
     * 
     * The test in this method will fail as this method this is tested will be
     * handled in Project Part 4
     */
    public void testaddChoice() {

    }

    public void testconnectStoryFragments() {

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
