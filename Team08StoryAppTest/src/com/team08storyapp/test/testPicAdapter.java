package com.team08storyapp.test;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.ActivityInstrumentationTestCase2;

import com.team08storyapp.Photo;
import com.team08storyapp.PicAdapter;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;
import com.team08storyapp.StoryFragmentActivity;

public class testPicAdapter extends
ActivityInstrumentationTestCase2<StoryFragmentActivity>{

    private ArrayList<StoryFragment> sfList;
    private Activity activity;
    private PicAdapter adapter;
    
    public testPicAdapter() throws Exception{
	super(StoryFragmentActivity.class);
    }
    
    public void setUp() throws Exception{
	super.setUp();
	/* StoryFragment ArrayList make up. */
	ArrayList<Photo> pList = new ArrayList<Photo>();
	sfList = new ArrayList<StoryFragment>();
	
	StoryFragment sf = new StoryFragment(1, "Hello. Welcome to "
		+ "Rapture.");
	sf.setPhotos(pList);
	sfList.add(sf);
	
	Story story = new Story(1, "BioShock2", "GuessWho");
	story.setStoryFragments(sfList);
	
	/* Intent Mock Up */
	Intent intent = new Intent();
	intent.putExtra("story", story);
	intent.putExtra("storyFragmentId", 1);
	super.setActivityIntent(intent);
	activity = super.getActivity();
	
	/* Initialize a StoryFragmentAdapter */
	adapter = new PicAdapter(activity.getApplicationContext(), pList, 1, 1);
	
    }
    
    public void testAddPic(){
	Bitmap star = BitmapFactory.decodeResource(activity.getResources(), android.R.drawable.btn_star);
	int currentPic = 0;
	assertEquals(adapter.getCount(), 5);
	adapter.addPic(currentPic, star);
	
	assertNotNull(adapter.getItem(currentPic));
	assertEquals(adapter.getItem(currentPic), star);	
    }
    
    public void testGetCount(){	
	assertEquals(adapter.getCount(), 5);	
    }

}