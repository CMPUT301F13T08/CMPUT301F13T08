package com.team08storyapp.test;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;

import com.team08storyapp.Choice;
import com.team08storyapp.ChoiceAdapter;
import com.team08storyapp.EditFragmentActivity;
import com.team08storyapp.FileHelper;
import com.team08storyapp.Photo;
import com.team08storyapp.PicAdapter;
import com.team08storyapp.R;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;

@SuppressWarnings("deprecation")
public class testEditFragmentActivity extends
	ActivityInstrumentationTestCase2<EditFragmentActivity> {

    private Activity activity;
    private PicAdapter picAdapter;
    private ChoiceAdapter choiceAdapter;
    private EditText editText;
    private ImageView picView;
    private ListView listView;
    private Gallery picGallery;
    private ArrayList<Choice> Choices;
    private ArrayList<Photo> Photos;
    private ArrayList<StoryFragment> storyFragments;
    private StoryFragment storyFragment;
    private StoryFragment secondFragment;
    private Story story;
    private Choice choice1;
    private Choice choice2;
    private Photo photo1;
    private Photo photo2;

    public testEditFragmentActivity() {
	super(EditFragmentActivity.class);
    }

    public void setUp() {
	
	

	storyFragment = new StoryFragment(1, "Text");
	secondFragment = new StoryFragment(2, "OtheText");

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

	storyFragments = new ArrayList<StoryFragment>();
	storyFragments.add(storyFragment);
	storyFragments.add(secondFragment);

	story = new Story("title", "author");

	story.setFirstStoryFragmentId(1);
	story.setOfflineStoryId(1);
	story.setOnlineStoryId(1);
	story.setStoryFragments(storyFragments);

	Intent intent = new Intent();
	intent.putExtra("story", story);
	intent.putExtra("storyFragmentId", 1);
	setActivityIntent(intent);

	activity = getActivity();
	Context context = activity.getApplicationContext();

	FileHelper fHelper = new FileHelper(context, 0);
	
	picView = (ImageView) activity.findViewById(R.id.picture);
	picGallery = (Gallery) activity.findViewById(R.id.gallery);

	picAdapter = new PicAdapter(activity.getApplicationContext(),
		storyFragment.getPhotos(), 1, 1);

	choiceAdapter = new ChoiceAdapter(activity, android.R.id.list,
		storyFragment.getChoices());

	listView = (ListView) activity.findViewById(android.R.id.list);

	editText = (EditText) activity.findViewById(R.id.headerDialogue);
    }

    public void testPreConditions() {

	assertNotNull(activity);
	assertNotNull(listView);
	assertNotNull(editText);
	assertNotNull(picView);
	assertNotNull(picGallery);
	assertNotNull(picAdapter);
	assertNotNull(choiceAdapter);

    }

    public void testEditTextItem() {

	assertEquals("Text", editText.getText().toString());

    }

    public void testPicAdapterItem() {

	assertNotNull(picAdapter.getItem(0));
	assertEquals(picAdapter.getItem(0), 0);

	assertNotNull(picAdapter.getItem(1));
	assertEquals(picAdapter.getItem(1), 1);
    }

    public void testListViewItem() {

	assertEquals("Go to 3!",
		((Choice) listView.getItemAtPosition(1)).getText());
    }

    public void testChoiceAdapterItem() {

	assertNotNull(choiceAdapter.getItem(0));
	assertEquals(choiceAdapter.getItem(0), 0);

	assertNotNull(choiceAdapter.getItem(1));
	assertEquals(choiceAdapter.getItem(1), 1);

    }

}
