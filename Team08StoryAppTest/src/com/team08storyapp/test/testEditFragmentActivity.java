package com.team08storyapp.test;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;

import com.team08storyapp.Choice;
import com.team08storyapp.ChoiceAdapter;
import com.team08storyapp.EditFragmentActivity;
import com.team08storyapp.Photo;
import com.team08storyapp.PicAdapter;
import com.team08storyapp.R;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;

@SuppressWarnings("deprecation")
public class testEditFragmentActivity extends
	ActivityInstrumentationTestCase2<EditFragmentActivity> {

    private Activity activity;
    private ImageView picView;
    private Gallery picGallery;
    private EditText editText;
    private ListView listView;
    private Story story;
    private StoryFragment storyFragment;
    private ArrayList<StoryFragment> storyFragments;
    private Choice choice1;
    private Choice choice2;
    private ArrayList<Choice> choices;
    private Photo photo1;
    private Photo photo2;
    private ArrayList<Photo> photos;
    private PicAdapter picAdapter;
    private ChoiceAdapter choiceAdapter;

    public testEditFragmentActivity() {
	super(EditFragmentActivity.class);
    }

    public void setUp() {

	story = new Story("title", "author");
	storyFragment = new StoryFragment(1, "Text");

	choice1 = new Choice(1, 2, "GoTo2!");
	choice2 = new Choice(2, 3, "GoTo3!");
	choices = new ArrayList<Choice>();
	choices.add(choice1);
	choices.add(choice2);
	storyFragment.setChoices(choices);

	photo1 = new Photo();
	photo1.setPhotoID(1);
	photo2 = new Photo();
	photo2.setPhotoID(2);
	photos = new ArrayList<Photo>();
	photos.add(photo1);
	photos.add(photo2);
	storyFragment.setPhotos(photos);

	storyFragments = new ArrayList<StoryFragment>();
	storyFragments.add(storyFragment);

	story.setOfflineStoryId(1);
	story.setStoryFragments(storyFragments);

	/*
	 * Set the intent for EditFragmentActivity with the local story object
	 * and the id of the fragment in the story
	 */
	Intent intent = new Intent();
	intent.putExtra("story", story);
	intent.putExtra("storyFragmentId", 1);
	setActivityIntent(intent);

	activity = getActivity();

	editText = (EditText) activity.findViewById(R.id.headerDialogue);
	picView = (ImageView) activity.findViewById(R.id.picture);
	picGallery = (Gallery) activity.findViewById(R.id.gallery);
	listView = (ListView) activity.findViewById(android.R.id.list);

	picAdapter = new PicAdapter(activity.getApplicationContext(),
		storyFragment.getPhotos(), 1, 1);

	choiceAdapter = new ChoiceAdapter(activity, android.R.id.list,
		storyFragment.getChoices());

    }

    public void testPreConditions() {
	assertNotNull(editText);
	assertNotNull(listView);
	assertNotNull(picView);
	assertNotNull(picGallery);
	assertNotNull(picAdapter);
	assertNotNull(choiceAdapter);
    }

    public void testEditTextItem() {
	assertEquals("Text", editText.getText().toString());
    }

    /* The two choices of storyFragment should be populated in the listView */
    public void testListViewItem() {

	assertEquals("Go to 2!",
		((Choice) listView.getItemAtPosition(0)).getText());

	assertEquals("Go to 3!",
		((Choice) listView.getItemAtPosition(1)).getText());
    }

    public void testPicAdapterItem() {

	assertNotNull(picAdapter.getItem(0));
	assertEquals(picAdapter.getItem(0), 0);

	assertNotNull(picAdapter.getItem(1));
	assertEquals(picAdapter.getItem(1), 1);

    }

    /*
     * public void testChoiceAdapterItem() {
     * 
     * assertNotNull(choiceAdapter.getItem(0));
     * assertEquals(choiceAdapter.getItem(0), 0);
     * 
     * assertNotNull(choiceAdapter.getItem(1));
     * assertEquals(choiceAdapter.getItem(1), 1);
     * 
     * }
     */

}
