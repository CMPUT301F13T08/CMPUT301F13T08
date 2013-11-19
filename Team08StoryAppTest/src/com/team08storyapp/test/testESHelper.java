package com.team08storyapp.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;

import android.test.ActivityInstrumentationTestCase2;

import com.team08storyapp.Choice;
import com.team08storyapp.ESHelper;
import com.team08storyapp.MainActivity;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;

@SuppressWarnings("deprecation")
public class testESHelper extends
	ActivityInstrumentationTestCase2<MainActivity> {

    private ESHelper esHelper;
    int expectedIdAddStory;
    int actualIdAddStory;
    Story addStory;
    int storyIdGetStory;
    int storyFragmentListSizeGetStory;
    String searchText;
    int searchListSize;
    int storyIdUpdateStory;
    StoryFragment storyFragmentToUpdate;

    public testESHelper() {
	super(MainActivity.class);
    }

    /*
     * The initializeSampleStory method is used to create a sample story to use
     * as test data for the below tests.
     */
    private Story initializeSampleTestStory() {
	Story sampleStory = new Story("Sample Story for Testing",
		"Michele Paulichuk");

	ArrayList<StoryFragment> storyFragmentList = new ArrayList<StoryFragment>();

	/* Story Fragment 1 */
	sampleStory.setFirstStoryFragmentId(1);
	StoryFragment storyFragment1 = new StoryFragment(1);
	storyFragment1
		.setStoryText("Like any other day, Amara decided to take her dog out for a walk. She left the house and exited the yard.");
	ArrayList<Choice> choices1 = new ArrayList<Choice>();
	choices1.add(new Choice(2, 1,
		"Amara turned south heading for her friend's house."));
	choices1.add(new Choice(7, 2,
		"Amara turned north heading for the store."));
	choices1.add(new Choice(3, 3,
		"Amara turned east heading for the river valley."));
	storyFragment1.setChoices(choices1);
	storyFragmentList.add(storyFragment1);

	/* Story Fragment 2 */
	StoryFragment storyFragment2 = new StoryFragment(2);
	storyFragment2
		.setStoryText("As Amara walked toward her friend's house her dog started acting up. It pulled this way and that way. Then it stopped and started barking at a bush.");
	ArrayList<Choice> choices2 = new ArrayList<Choice>();
	choices2.add(new Choice(4, 1, "In the bush, Amara found a small boy."));
	choices2.add(new Choice(5, 2,
		"In the bush, Amara found a hundred dollar bill."));
	storyFragment2.setChoices(choices2);
	storyFragmentList.add(storyFragment2);

	/* Story Fragment 3 */
	StoryFragment storyFragment3 = new StoryFragment(3);
	storyFragment3
		.setStoryText("Amara gets to the river valley and decides to take a path she has yet to explore. As Amara walks along she comes across a cave in the side of the valley.");
	ArrayList<Choice> choices3 = new ArrayList<Choice>();
	choices3.add(new Choice(8, 1,
		"Amara decides to take a peek in the cave."));
	choices3.add(new Choice(9, 2,
		"Amara thinks the cave is creepy and continues walking."));
	storyFragment3.setChoices(choices3);
	storyFragmentList.add(storyFragment3);

	/* Story Fragment 4 */
	StoryFragment storyFragment4 = new StoryFragment(4);
	storyFragment4
		.setStoryText("At first Amara was startled by the boy. Then she realized he was crying softly. So she asked the boy what's wrong. The boy was lost.");
	ArrayList<Choice> choices4 = new ArrayList<Choice>();
	choices4.add(new Choice(10, 1,
		"Amara decides to help the boy find his way home."));
	choices4.add(new Choice(11, 2, "Amara is uncertain what to do."));
	storyFragment4.setChoices(choices4);
	storyFragmentList.add(storyFragment4);

	/* Story Fragment 5 */
	StoryFragment storyFragment5 = new StoryFragment(5);
	storyFragment5
		.setStoryText("Amara picked up the bill and looked around. There was no one around and therefore anyone to claim the money. So Amara decided to keep the bill and continue on to her friend's house.");
	ArrayList<Choice> choices5 = new ArrayList<Choice>();
	choices5.add(new Choice(6, 1, "Continue"));
	storyFragment5.setChoices(choices5);
	storyFragmentList.add(storyFragment5);

	/* Story Fragment 6 */
	StoryFragment storyFragment6 = new StoryFragment(6);
	storyFragment6
		.setStoryText("When Amara got to her friend's house she explained her excitement at finding one hundred dollars in a bush, all thanks to her dog. Her friend suggests she should reward her dog a treat with part of the money.");
	ArrayList<Choice> choices6 = new ArrayList<Choice>();
	choices6.add(new Choice(7, 1, "Continue"));
	storyFragment6.setChoices(choices6);
	storyFragmentList.add(storyFragment6);

	/* Story Fragment 7 */
	StoryFragment storyFragment7 = new StoryFragment(7);
	storyFragment7
		.setStoryText("Amara heads to the store and buys her dog a big juicy bone. After which, they head home for a nap. THE END");
	storyFragmentList.add(storyFragment7);

	/* Story Fragment 8 */
	StoryFragment storyFragment8 = new StoryFragment(8);
	storyFragment8
		.setStoryText("Amara steps towards the cave, as a little boy crawls out of it.");
	ArrayList<Choice> choices8 = new ArrayList<Choice>();
	choices8.add(new Choice(4, 1, "Continue"));
	storyFragment8.setChoices(choices8);
	storyFragmentList.add(storyFragment8);

	/* Story Fragment 9 */
	StoryFragment storyFragment9 = new StoryFragment(9);
	storyFragment9
		.setStoryText("Amara walks past the cave in a hurry. She notices the clouds have formed into rain clouds and decides it's time to head home. THE END");
	storyFragmentList.add(storyFragment9);

	/* Story Fragment 10 */
	StoryFragment storyFragment10 = new StoryFragment(10);
	storyFragment10
		.setStoryText("The boy says he knows his phone number and his mom is home but has no way to call her. Amara pulls out her cell and dials the number. She explains she found the boy and where she is.");
	ArrayList<Choice> choices9 = new ArrayList<Choice>();
	choices9.add(new Choice(12, 1, "Continue"));
	storyFragment10.setChoices(choices9);
	storyFragmentList.add(storyFragment10);

	/* Story Fragment 11 */
	StoryFragment storyFragment11 = new StoryFragment(11);
	storyFragment11
		.setStoryText("As Amara and the boy stand there, they hear a voice calling the name Timothy. The boy stops his crying and starts yelling Momma. The boy's mother comes rushing up and is relieved to find her missing son. You decide you have enough excitement for one walk and turn to head home for a nap. THE END");
	storyFragmentList.add(storyFragment11);

	/* Story Fragment 12 */
	StoryFragment storyFragment12 = new StoryFragment(12);
	storyFragment12
		.setStoryText("The boy's mother arrives shortly and is relieved to find her son. As a reward for finding her son, she invites to take you out for ice cream with them. You decide to abandon your walk and take her offer up. THE END");
	storyFragmentList.add(storyFragment12);

	sampleStory.setStoryFragments(storyFragmentList);
	return sampleStory;
    }

    private Story getStoryToRedoUpdateTest() {
	Story sampleStory = new Story("The Walk", "Michele Paulichuk");
	sampleStory.setOnlineStoryId(storyIdUpdateStory);

	ArrayList<StoryFragment> storyFragmentList = new ArrayList<StoryFragment>();

	/* Story Fragment 1 */
	sampleStory.setFirstStoryFragmentId(1);
	StoryFragment storyFragment1 = new StoryFragment(1);
	storyFragment1
		.setStoryText("Like any other day, Amara decided to take her dog out for a walk. She left the house and exited the yard.");
	ArrayList<Choice> choices1 = new ArrayList<Choice>();
	choices1.add(new Choice(2, 1,
		"Amara turned south heading for her friend's house."));
	choices1.add(new Choice(7, 2,
		"Amara turned north heading for the store."));
	choices1.add(new Choice(3, 3,
		"Amara turned east heading for the river valley."));
	storyFragment1.setChoices(choices1);
	storyFragmentList.add(storyFragment1);

	/* Story Fragment 2 */
	StoryFragment storyFragment2 = new StoryFragment(2);
	storyFragment2
		.setStoryText("As Amara walked toward her friend's house her dog started acting up. It pulled this way and that way. Then it stopped and started barking at a bush.");
	ArrayList<Choice> choices2 = new ArrayList<Choice>();
	choices2.add(new Choice(4, 1, "In the bush, Amara found a small boy."));
	choices2.add(new Choice(5, 2,
		"In the bush, Amara found a hundred dollar bill."));
	storyFragment2.setChoices(choices2);
	storyFragmentList.add(storyFragment2);

	/* Story Fragment 3 */
	StoryFragment storyFragment3 = new StoryFragment(3);
	storyFragment3
		.setStoryText("Amara gets to the river valley and decides to take a path she has yet to explore. As Amara walks along she comes across a cave in the side of the valley.");
	ArrayList<Choice> choices3 = new ArrayList<Choice>();
	choices3.add(new Choice(8, 1,
		"Amara decides to take a peek in the cave."));
	choices3.add(new Choice(9, 2,
		"Amara thinks the cave is creepy and continues walking."));
	storyFragment3.setChoices(choices3);
	storyFragmentList.add(storyFragment3);

	/* Story Fragment 4 */
	StoryFragment storyFragment4 = new StoryFragment(4);
	storyFragment4
		.setStoryText("At first Amara was startled by the boy. Then she realized he was crying softly. So she asked the boy what's wrong. The boy was lost.");
	ArrayList<Choice> choices4 = new ArrayList<Choice>();
	choices4.add(new Choice(10, 1,
		"Amara decides to help the boy find his way home."));
	choices4.add(new Choice(11, 2, "Amara is uncertain what to do."));
	storyFragment4.setChoices(choices4);
	storyFragmentList.add(storyFragment4);

	/* Story Fragment 5 */
	StoryFragment storyFragment5 = new StoryFragment(5);
	storyFragment5
		.setStoryText("Amara picked up the bill and looked around. There was no one around and therefore anyone to claim the money. So Amara decided to keep the bill and continue on to her friend's house.");
	ArrayList<Choice> choices5 = new ArrayList<Choice>();
	choices5.add(new Choice(6, 1, "Continue"));
	storyFragment5.setChoices(choices5);
	storyFragmentList.add(storyFragment5);

	/* Story Fragment 6 */
	StoryFragment storyFragment6 = new StoryFragment(6);
	storyFragment6
		.setStoryText("When Amara got to her friend's house she explained her excitement at finding one hundred dollars in a bush, all thanks to her dog. Her friend suggests she should reward her dog a treat with part of the money.");
	ArrayList<Choice> choices6 = new ArrayList<Choice>();
	choices6.add(new Choice(7, 1, "Continue"));
	storyFragment6.setChoices(choices6);
	storyFragmentList.add(storyFragment6);

	/* Story Fragment 7 */
	StoryFragment storyFragment7 = new StoryFragment(7);
	storyFragment7
		.setStoryText("Amara heads to the store and buys her dog a big juicy bone. After which, they head home for a nap. THE END");
	storyFragmentList.add(storyFragment7);

	/* Story Fragment 8 */
	StoryFragment storyFragment8 = new StoryFragment(8);
	storyFragment8
		.setStoryText("Amara steps towards the cave, as a little boy crawls out of it.");
	ArrayList<Choice> choices8 = new ArrayList<Choice>();
	choices8.add(new Choice(4, 1, "Continue"));
	storyFragment8.setChoices(choices8);
	storyFragmentList.add(storyFragment8);

	/* Story Fragment 9 */
	StoryFragment storyFragment9 = new StoryFragment(9);
	storyFragment9
		.setStoryText("Amara walks past the cave in a hurry. She notices the clouds have formed into rain clouds and decides it's time to head home. THE END");
	storyFragmentList.add(storyFragment9);

	/* Story Fragment 10 */
	StoryFragment storyFragment10 = new StoryFragment(10);
	storyFragment10
		.setStoryText("The boy says he knows his phone number and his mom is home but has no way to call her. Amara pulls out her cell and dials the number. She explains she found the boy and where she is.");
	ArrayList<Choice> choices9 = new ArrayList<Choice>();
	choices9.add(new Choice(12, 1, "Continue"));
	storyFragment10.setChoices(choices9);
	storyFragmentList.add(storyFragment10);

	/* Story Fragment 11 */
	StoryFragment storyFragment11 = new StoryFragment(11);
	storyFragment11
		.setStoryText("As Amara and the boy stand there, they hear a voice calling the name Timothy. The boy stops his crying and starts yelling Momma. The boy's mother comes rushing up and is relieved to find her missing son. You decide you have enough excitement for one walk and turn to head home for a nap. THE END");
	storyFragmentList.add(storyFragment11);

	/* Story Fragment 12 */
	StoryFragment storyFragment12 = new StoryFragment(12);
	storyFragment12
		.setStoryText("The boy's mother arrives shortly and is relieved to find her son. As a reward for finding her son, she invites to take you out for ice cream with them. You decide to abandon your walk and take her offer up. THE END");
	storyFragmentList.add(storyFragment12);

	sampleStory.setStoryFragments(storyFragmentList);
	return sampleStory;
    }

    /* Set up testing data for testing methods. */
    public void setUp() {
	esHelper = new ESHelper();

	/*
	 * ***Setup for testAddOnlineStory*** create the story for adding to the
	 * webservice
	 */
	addStory = initializeSampleTestStory();

	/*
	 * ***Setup for testGetOnlineStory*** set the predetermined story and
	 * it's known number of story fragments
	 */storyIdGetStory = 1;
	storyFragmentListSizeGetStory = 12;

	/*
	 * ***Setup for testSearchOnlineStory*** set the searchText to the
	 * predetermined text to search the webservice stories author and titles
	 * for
	 */
	searchText = "walk";

	/*
	 * set the searchListSize to the known size of the list the search
	 * should return
	 */
	searchListSize = 1;

	/* ***Setup for testUpdateOnlineStory*** */
	storyIdUpdateStory = 1;
    }

    /*
     * Test Case for Use Case 2 & 16
     * 
     * The testGetOnlineStories method tests retrieving all stories on the
     * webservice via ESHelper's getOnlineStories method.
     * 
     * The test retrieves all stories from the webservice twice. If the size of
     * the first call is greater than zero and the sizes of the two calls are
     * equally than it is successfully retrieving all stories online.
     */
    public void testGetOnlineStories() {
	/*
	 * retrieve the stories and check that the size is greater than 0 as
	 * there should be stories available
	 */
	int onlineStoryListSizeCall1 = esHelper.getOnlineStories().size();
	assertTrue(onlineStoryListSizeCall1 > 0);

	/*
	 * retrieve the stories again and check that the size of the first call
	 * is the same as the size of the second call
	 */
	int onlineStoryListSizeCall2 = esHelper.getOnlineStories().size();
	assertTrue(onlineStoryListSizeCall1 == onlineStoryListSizeCall2);

    }

    /*
     * Test Case for Use Case 3
     * 
     * The testAddOnlineStory method tests adding a story to the webservice via
     * ESHelper's addOrUpdateOnlineStory method.
     * 
     * The test initializes a sample story to add to the webservice and
     * determines the onlineStoryId that the story will receive on successful
     * addition to the webservice. It checks this id against the one received
     * after the call is made. It then calls the webservice to get the story at
     * that id on the webservice. It compares the retreived story to the initial
     * story to see if the objects are the same. If all these tests pass than it
     * can be said that the add portion of addOrUpdateOnline story passes adding
     * a story to the webservice.
     */
    public void testAddOnlineStory() {
	/* get the id that is expected to be set to the newly added story */
	expectedIdAddStory = esHelper.getOnlineStories().size() + 1;

	/* add the story */
	actualIdAddStory = esHelper.addOrUpdateOnlineStory(addStory);

	/* that the id expected is the id assigned */
	assertTrue(actualIdAddStory == expectedIdAddStory);

	/* Retrieve the story from online for further testing */
	Story onlineStory = esHelper.getOnlineStory(actualIdAddStory);

	/*
	 * test that the story for the id given was received, if it was not
	 * Received this may indicate that the story was not added
	 */
	assertTrue(onlineStory != null);

	/*
	 * test the properties of the story against the initial story to ensure
	 * the story added is indeed the same story
	 */
	assertEquals(addStory.getAuthor(), onlineStory.getAuthor());
	assertEquals(addStory.getTitle(), onlineStory.getTitle());
	assertEquals(addStory.getFirstStoryFragmentId(),
		onlineStory.getFirstStoryFragmentId());
	assertEquals(addStory.getStoryFragments().size(), onlineStory
		.getStoryFragments().size());
    }

    /*
     * Test Case for Use Case 1
     * 
     * The testGetOnlineStory method tests retrieving a story to the webservice
     * via ESHelper's getOnlineStory method.
     * 
     * The test retrieves a specific story from the webservice via a
     * specifically selected online story id. The test is predetermined for that
     * story and thus knows how many fragments that story should currently have.
     * So the test test that indeed that specific story is retrieved
     * successfully by checking the id, that the author and title are not null,
     * and it contains the correct number of fragments.
     */
    public void testGetOnlineStory() {
	/* retrieve story from the webservice from the specified id */
	Story story = esHelper.getOnlineStory(storyIdGetStory);

	/* test that the retrieved story is indeed retrieved and correct */
	assertTrue(!story.equals(null));
	assertTrue(story.getOnlineStoryId() == storyIdGetStory);
	assertTrue(!story.getAuthor().equals(null)
		&& !story.getTitle().equals(null)
		&& story.getStoryFragments().size() == storyFragmentListSizeGetStory);
    }

    /*
     * Test Case for Use Case 4
     * 
     * The testSearchOnlineStories method tests retrieving all stories on the
     * webservice via ESHelper's searchOnlineStories method.
     * 
     * The test retrieves a specific list of stories from the webservice based
     * on a predetermined search. The search is predetermined and thus it is
     * known how many of the stories currently online should contain the search
     * text. When the search is performed a check is done to test that the
     * number of stories returned is the same as expected.
     */
    public void testSearchOnlineStories() {
	/*
	 * retrieve the story's from the search and compare the size of the list
	 * to the known size
	 */
	assertTrue(esHelper.searchOnlineStories(searchText).size() == searchListSize);
    }

    /*
     * Test Case for Use Case 10
     * 
     * The testUpdateOnlineStory method tests updating a story to the webservice
     * via ESHelper's addOrUpdateOnlineStory method.
     * 
     * The test retrieves a specific story from the webservice. Then modifies
     * one of it's story fragments story text, saving the original text for
     * later comparison. It then tries to update the story on the webservice. If
     * the id returned matches the current one for the story than it appears to
     * have updated successfully. However, more tests are needed for this result
     * to be accepted. So the test calls the webservice to retrieve the story
     * again and some comparisons are done to see if the story on the webservice
     * does contain the updated text.
     */
    public void testUpdateOnlineStory() {
	/* retrieve a story from the webservice to update */
	Story updateStory = esHelper.getOnlineStory(storyIdUpdateStory);

	/* update a portion of the story */
	ArrayList<StoryFragment> storyFragments = updateStory
		.getStoryFragments();
	storyFragmentToUpdate = storyFragments.get(1);
	storyFragments.get(1).setStoryText("Changed the text of the story");
	updateStory.setStoryFragments(storyFragments);

	/*
	 * test that the update processed successfully by checking that the id
	 * returned is the id the story had previously
	 */
	assertTrue(esHelper.addOrUpdateOnlineStory(updateStory) == updateStory
		.getOnlineStoryId());

	/* Retrieve the story from online for further testing */
	Story onlineStory = esHelper.getOnlineStory(updateStory
		.getOnlineStoryId());

	/*
	 * test that the story for the id given was received, if it was not
	 * Received this may indicate that the story was not added
	 */
	assertTrue(onlineStory != null);

	/*
	 * test the properties of the story against the initial story to ensure
	 * the story updated still contains the same author, title, and first
	 * fragment
	 */
	assertEquals(updateStory.getAuthor(), onlineStory.getAuthor());
	assertEquals(updateStory.getTitle(), onlineStory.getTitle());
	assertEquals(updateStory.getFirstStoryFragmentId(),
		onlineStory.getFirstStoryFragmentId());

	/*
	 * test that the story fragment we changed is different from the
	 * original story fragment.
	 */
	ArrayList<StoryFragment> onlineStoryFragments = onlineStory
		.getStoryFragments();
	assertNotSame(storyFragmentToUpdate.getStoryText(),
		onlineStoryFragments.get(0).getStoryText());
    }

    /* Remove test data from the application */
    protected void tearDown() throws IOException {

	/* Delete the story added to online */
	@SuppressWarnings("resource")
	HttpClient httpclient = new DefaultHttpClient();
	HttpDelete httpDelete = new HttpDelete(
		"http://cmput301.softwareprocess.es:8080/cmput301f13t08/stories/"
			+ actualIdAddStory);
	httpDelete.addHeader("Accept", "application/json");

	HttpResponse response = httpclient.execute(httpDelete);

	String status = response.getStatusLine().toString();
	System.out.println(status);

	HttpEntity entity = response.getEntity();
	BufferedReader br = new BufferedReader(new InputStreamReader(
		entity.getContent()));
	String output;
	System.err.println("Output from Server -> ");
	while ((output = br.readLine()) != null) {
	    System.err.println(output);
	}

	/* Change back the updated story */
	Story story = getStoryToRedoUpdateTest();
	esHelper.addOrUpdateOnlineStory(story);

    }
}
