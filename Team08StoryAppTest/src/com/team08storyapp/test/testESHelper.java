package com.team08storyapp.test;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import android.util.Log;

import com.team08storyapp.ESHelper;
import junit.framework.TestCase;

import com.team08storyapp.Choice;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;

public class testESHelper extends TestCase {

	private ESHelper esHelper;

	private static final String TAG = "ESHelper";
	
	public testESHelper(String name) {
		super(name);
	}

	/**
	 * This method is used to create a sample story for testing of adding and
	 * updating.
	 * 
	 * @return Story object
	 */
	private Story initializeSampleStory() {
		Story sampleStory = new Story("The Walk", "Michele Paulichuk");
		sampleStory.setFirstStoryFragment(1);

		ArrayList<StoryFragment> storyFragmentList = new ArrayList<StoryFragment>();
		ArrayList<Choice> choices = new ArrayList<Choice>();

		// Story Fragment 1
		sampleStory.setFirstStoryFragment(1);
		StoryFragment storyFragment1 = new StoryFragment(1);
		storyFragment1
				.setStoryText("Like any other day, Amara decided to take her dog out for a walk. She left the house and exited the yard.");
		choices.add(new Choice(2, 1,
				"Amara turned south heading for her friend’s house."));
		choices.add(new Choice(7, 2,
				"Amara turned north heading for the store."));
		choices.add(new Choice(3, 3,
				"Amara turned east heading for the river valley."));
		storyFragment1.setChoices(choices);
		storyFragmentList.add(storyFragment1);

		// Story Fragment 2
		StoryFragment storyFragment2 = new StoryFragment(1);
		storyFragment2
				.setStoryText("As Amara walked toward her friend’s house her dog started acting up. It pulled this way and that way. Then it stopped and started barking at a bush.");
		choices.clear();
		choices.add(new Choice(4, 1, "In the bush, Amara found a small boy."));
		choices.add(new Choice(5, 2,
				"In the bush, Amara found a hundred dollar bill."));
		storyFragment2.setChoices(choices);
		storyFragmentList.add(storyFragment2);

		// Story Fragment 3
		StoryFragment storyFragment3 = new StoryFragment(1);
		storyFragment3
				.setStoryText("Amara gets to the river valley and decides to take a path she has yet to explore. As Amara walks along she comes across a cave in the side of the valley.");
		choices.clear();
		choices.add(new Choice(8, 1,
				"Amara decides to take a peek in the cave."));
		choices.add(new Choice(9, 2,
				"Amara thinks the cave is creepy and continues walking."));
		storyFragment3.setChoices(choices);
		storyFragmentList.add(storyFragment3);

		// Story Fragment 4
		StoryFragment storyFragment4 = new StoryFragment(1);
		storyFragment4
				.setStoryText("At first Amara was startled by the boy. Then she realized he was crying softly. So she asked the boy what’s wrong. The boy was lost.");
		choices.clear();
		choices.add(new Choice(10, 1,
				"Amara decides to help the boy find his way home."));
		choices.add(new Choice(11, 2, "Amara is uncertain what to do."));
		storyFragment4.setChoices(choices);
		storyFragmentList.add(storyFragment4);

		// Story Fragment 5
		StoryFragment storyFragment5 = new StoryFragment(1);
		storyFragment5
				.setStoryText("Amara picked up the bill and looked around. There was no one around and therefore anyone to claim the money. So Amara decided to keep the bill and continue on to her friend’s house.");
		choices.clear();
		choices.add(new Choice(6, 1, "Continue"));
		storyFragment5.setChoices(choices);
		storyFragmentList.add(storyFragment5);

		// Story Fragment 6
		StoryFragment storyFragment6 = new StoryFragment(1);
		storyFragment6
				.setStoryText("When Amara got to her friend’s house she explained her excitement at finding one hundred dollars in a bush, all thanks to her dog. Her friend suggests she should reward her dog a treat with part of the money.");
		choices.clear();
		choices.add(new Choice(7, 1, "Continue"));
		storyFragment6.setChoices(choices);
		storyFragmentList.add(storyFragment6);

		// Story Fragment 7
		StoryFragment storyFragment7 = new StoryFragment(1);
		storyFragment7
				.setStoryText("Amara heads to the store and buys her dog a big juicy bone. After which, they head home for a nap. THE END");
		storyFragmentList.add(storyFragment7);

		// Story Fragment 8
		StoryFragment storyFragment8 = new StoryFragment(1);
		storyFragment8
				.setStoryText("Amara steps towards the cave, as a little boy crawls out of it.");
		choices.clear();
		choices.add(new Choice(4, 1, "Continue"));
		storyFragment8.setChoices(choices);
		storyFragmentList.add(storyFragment8);

		// Story Fragment 9
		StoryFragment storyFragment9 = new StoryFragment(1);
		storyFragment9
				.setStoryText("Amara walks past the cave in a hurry. She notices the clouds have formed into rain clouds and decides it’s time to head home. THE END");
		storyFragmentList.add(storyFragment9);

		// Story Fragment 10
		StoryFragment storyFragment10 = new StoryFragment(1);
		storyFragment10
				.setStoryText("The boy says he knows his phone number and his mom is home but has no way to call her. Amara pulls out her cell and dials the number. She explains she found the boy and where she is.");
		choices.clear();
		choices.add(new Choice(12, 1, "Continue"));
		storyFragment10.setChoices(choices);
		storyFragmentList.add(storyFragment10);

		// Story Fragment 11
		StoryFragment storyFragment11 = new StoryFragment(1);
		storyFragment11
				.setStoryText("As Amara and the boy stand there, they hear a voice calling the name Timothy. The boy stops his crying and starts yelling Momma. The boy’s mother comes rushing up and is relieved to find her missing son. You decide you have enough excitement for one walk and turn to head home for a nap. THE END");
		storyFragmentList.add(storyFragment11);

		// Story Fragment 12
		StoryFragment storyFragment12 = new StoryFragment(1);
		storyFragment12
				.setStoryText("The boy’s mother arrives shortly and is relieved to find her son. As a reward for finding her son, she invites to take you out for ice cream with them. You decide to abandon your walk and take her offer up. THE END");
		storyFragmentList.add(storyFragment12);

		sampleStory.setStoryFragments(storyFragmentList);
		return sampleStory;
	}

	/*
	 * Test Case for Use Case 3
	 * 
	 * The testAddStory method tests adding a story to the server. If the story
	 * is added successfully the method call to esHelper.addStory should return
	 * true.
	 */
	public void testAddOnlineStory() {
		Story addStory = initializeSampleStory();
		try {
			assertTrue(esHelper.addOnlineStory(addStory));
		} catch (IllegalStateException e) {
			Log.d(TAG, e.getLocalizedMessage());
		} catch (IOException e) {
			Log.d(TAG, e.getLocalizedMessage());
		}
	}

	/*
	 * Test Case for Use Case 10
	 * 
	 * The testUpdateStory method tests updating a story to the server. If the
	 * story updates successfully the method call to esHelper.updateStory should
	 * return true.
	 */
	public void testUpdateOnlineStory() {
		Story updateStory = initializeSampleStory();
		// TODO: Add some code to update story
		assertTrue(esHelper.updateOnlineStory(updateStory));
	}

	/*
	 * Test Case for Use Case 1
	 * 
	 * The testGetStory method tests retrieving a story from the server with a
	 * given story id. A story object should be returned. The object should not
	 * be null, should contain the same story id as given, and should contain
	 * the author, title and story fragments.
	 */
	public void testGetOnlineStory() {
		int storyId = 1;
		int storyFragmentListSize = 12;

		Story story = esHelper.getOnlineStory(storyId);

		assertTrue(!story.equals(null));
		assertTrue(story.getStoryId() == storyId);
		assertTrue(!story.getAuthor().equals(null)
				&& !story.getTitle().equals(null)
				&& story.getStoryFragments().size() == storyFragmentListSize);
	}

	/*
	 * Test Case for Use Case 2 & 16
	 * 
	 * The testGetStories method tests retrieval of all the stories stored on
	 * the server. The server will contain at least one story for this test. The
	 * list returned from the method call esHelper.getStories should be the same
	 * size as the one on the server.
	 */
	public void testGetOnlineStories() {
		int storyListSize = 1;
		assertTrue(esHelper.getOnlineStories().size() == storyListSize);
	}

	/*
	 * Test Case for Use Case 4
	 * 
	 * The testSearchForStory method tests the search functionality of the
	 * stories on the server. Given a string to search the method call
	 * esHelper.searchForStory should return a list of the size we are expecting
	 * for the given search text.
	 */
	public void testSearchOnlineStories() {
		String searchText = "walk";
		int searchListSize = 1;

		try {
			assertTrue(esHelper.searchOnlineStories(searchText).size() == searchListSize);
		} catch (ClientProtocolException e) {
			Log.d(TAG, e.getLocalizedMessage());
		} catch (IOException e) {
			Log.d(TAG, e.getLocalizedMessage());;
		}
	}
}
