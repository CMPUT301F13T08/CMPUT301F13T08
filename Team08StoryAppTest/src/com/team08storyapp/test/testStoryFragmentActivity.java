package com.team08storyapp.test;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.team08storyapp.Choice;
import com.team08storyapp.Photo;
import com.team08storyapp.PicAdapter;
import com.team08storyapp.R;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;
import com.team08storyapp.StoryFragmentActivity;

public class testStoryFragmentActivity extends
	ActivityInstrumentationTestCase2<StoryFragmentActivity> {

    private Activity activity;
    private PicAdapter adapter;
    private TextView textView;
    private ImageView picView;
    private ListView listView;
    private Gallery picGallery;
    private ArrayList<Choice> Choices;
    private ArrayList<Photo> Photos;
    private StoryFragment storyFragment;
    private Story story;
    private Choice choice1;
    private Choice choice2;
    private Photo photo1;
    private Photo photo2;

    public testStoryFragmentActivity() {
	super(StoryFragmentActivity.class);
    }

    public void setUp() {

	storyFragment = new StoryFragment(1);
	storyFragment.setStoryText("Text");

	Choices = new ArrayList<Choice>();
	choice1 = new Choice(2, 1, "Go to 2!");
	choice2 = new Choice(3, 2, "Go to 3!");
	Choices.add(choice1);
	Choices.add(choice2);

	storyFragment.setChoices(Choices);

	Photos = new ArrayList<Photo>();
	photo1 = new Photo();
	photo1.setPhotoID(1);
	photo2 = new Photo();
	photo2.setPhotoID(2);
	Photos.add(photo1);
	Photos.add(photo2);

	storyFragment.setPhotos(Photos);

	story = new Story("title", "author");
	story.setOfflineStoryId(1);
	story.setOnlineStoryId(1);
	story.setFirstStoryFragmentId(1);
	story.getStoryFragments().add(storyFragment);

	/*
	 * Pass intent to StoryFragmentActivity with the current story object,
	 * the current story fragment id, and the mode to indicate the user is
	 * offline or online
	 */
	Intent intent = new Intent();
	intent.putExtra("story", story);
	intent.putExtra("storyFragmentId", 1);
	intent.putExtra("mode", 0);
	setActivityIntent(intent);

	activity = getActivity();

	textView = (TextView) activity.findViewById(R.id.headerText);
	picView = (ImageView) activity.findViewById(R.id.picture);
	picGallery = (Gallery) activity.findViewById(R.id.gallery);
	listView = (ListView) activity.findViewById(android.R.id.list);

	adapter = new PicAdapter(activity.getApplicationContext(),
		storyFragment.getPhotos(), 1, 1);

    }

    public void testPreConditions() {

	assertNotNull(activity);
	assertNotNull(listView);
	assertNotNull(textView);
	assertNotNull(picView);
	assertNotNull(picGallery);
	assertNotNull(adapter);

    }

    /*
     * The text line the TextView widget should correspond to the text of the
     * current fragment
     */
    public void testTextViewItem() {

	assertEquals("Text", textView.getText().toString());

    }

    /*
     * Check whether an item in the choice list of the current fragment is in
     * the choices list view
     */
    public void testListViewItem() {

	assertEquals("Go to 3!",
		((Choice) listView.getItemAtPosition(1)).getText());

    }

    /*
     * Check that the PicAdapter contains the number of photos of the current
     * fragment
     */
    public void testAdapterItem() {

	assertNotNull(adapter.getItem(0));
	assertEquals(adapter.getItem(0), 0);

	assertNotNull(adapter.getItem(1));
	assertEquals(adapter.getItem(1), 1);

    }

}
