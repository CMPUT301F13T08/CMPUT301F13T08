package com.team08storyapp.test;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.team08storyapp.Choice;
import com.team08storyapp.ChoiceAdapter;
import com.team08storyapp.Photo;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;
import com.team08storyapp.StoryFragmentActivity;

public class testChoiceAdapter extends
	ActivityInstrumentationTestCase2<StoryFragmentActivity> {

    private Activity testActivity;
    private Context testContext;
    private Story testStory;
    private StoryFragment testStoryFragment;
    private ChoiceAdapter adapter;
    private ArrayList<Photo> pList;
    private ArrayList<Choice> cList;
    private ArrayList<StoryFragment> testStoryFragmentList;

    
    public testChoiceAdapter(){
	super(StoryFragmentActivity.class);
    }
    
    public void setUp(){
	pList = new ArrayList<Photo>();
	cList = new ArrayList<Choice>();
	
	Choice c1 = new Choice(1,1, "No place for you.");
	cList.add(c1);
	
	testStory = new Story(15, "newstory", "me");

	testStoryFragment = new StoryFragment(1, "Test text.");
	testStoryFragment.setPhotos(pList);
	testStoryFragment.setChoices(cList);
	testStoryFragmentList = new ArrayList<StoryFragment>();
	testStoryFragmentList.add(testStoryFragment);
	testStory.setStoryFragments(testStoryFragmentList);

	Intent intent = new Intent();
	intent.putExtra("story", testStory);
	intent.putExtra("storyFragmentId", 1);
	super.setActivityIntent(intent);
	testActivity = super.getActivity();
	testContext = super.getInstrumentation().getContext();
	
	adapter = new ChoiceAdapter(testActivity, android.R.id.list, cList );
    }
    
    public void testGetView(){
	/* Test getView() function */
	ViewGroup parent = new LinearLayout(testActivity.getApplicationContext());
	adapter.getView(0, null, parent);

	/* Make sure all 3 story fragments in sfList are in adapter */
	assertEquals(adapter.getCount(), 1);

	/*
	 * Make sure right place has the right fragment. Index(Position) 0: sf
	 * Index(Position) 1: sf1 Index(Position) 2: sf2
	 */
	assertEquals(adapter.getItem(0).getChoiceId(), 1);
	assertEquals(adapter.getItem(0).getStoryFragmentID(),
		1);
	assertEquals(adapter.getItem(0).getText(),"No place for you.");

    
    }
    
    

}
