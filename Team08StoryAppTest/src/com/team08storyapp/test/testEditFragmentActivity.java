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
import com.team08storyapp.EditFragmentActivity;
import com.team08storyapp.Photo;
import com.team08storyapp.PicAdapter;
import com.team08storyapp.R;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;

@SuppressWarnings("deprecation")
public class testEditFragmentActivity extends
ActivityInstrumentationTestCase2<EditFragmentActivity>{

    private Activity activity;
    private PicAdapter adapter;
    private EditText editText;
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
    
    
    public testEditFragmentActivity() {
	super(EditFragmentActivity.class);
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


	Intent intent = new Intent();
	intent.putExtra("story", story);
	intent.putExtra("storyFragmentId", 1);
	intent.putExtra("mode", 0);
	setActivityIntent(intent);

	activity = getActivity();

	editText = (EditText) activity.findViewById(R.id.headerDialogue);
	picView = (ImageView) activity.findViewById(R.id.picture);
	picGallery = (Gallery) activity.findViewById(R.id.gallery);
	listView = (ListView) activity.findViewById(android.R.id.list);

	adapter = new PicAdapter(activity.getApplicationContext(),
		storyFragment.getPhotos(), 1, 1);

    }
    
    public void testPreConditions() {

   	assertNotNull(activity);
   	assertNotNull(listView);
   	assertNotNull(editText);
   	assertNotNull(picView);
   	assertNotNull(picGallery);
   	assertNotNull(adapter);

       }
    
    
    public void testEditTextItem() {

   	assertEquals("Text", editText.getText().toString());

       }
    
    public void testListViewItem() {

   	assertEquals("Go to 3!",
   		((Choice) listView.getItemAtPosition(1)).getText());

       }
    
    public void testAdapterItem() {

   	assertNotNull(adapter.getItem(0));
   	assertEquals(adapter.getItem(0), 0);

   	assertNotNull(adapter.getItem(1));
   	assertEquals(adapter.getItem(1), 1);

       }

    
}
