package com.team08storyapp.test;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Adapter;
import android.widget.ListView;

import com.team08storyapp.SelectFragmentActivity;
import com.team08storyapp.StoryFragment;

public class testSelectFragmentActivity extends
	ActivityInstrumentationTestCase2<SelectFragmentActivity> {

    private StoryFragment firstStoryFragment;
    private StoryFragment secondStoryFragment;
    private ArrayList<StoryFragment> storyFragmentList;
    private Activity activity;
    private ListView listView;

    public testSelectFragmentActivity() {
	super(SelectFragmentActivity.class);
    }

    public void setUp() {

	storyFragmentList = new ArrayList<StoryFragment>();

	firstStoryFragment = new StoryFragment(1, "fragment text");
	secondStoryFragment = new StoryFragment(2, "another fragment text");

	storyFragmentList.add(firstStoryFragment);
	storyFragmentList.add(secondStoryFragment);

	Intent intent = new Intent();
	intent.putExtra("storyFragments", storyFragmentList);
	setActivityIntent(intent);

	activity = getActivity();

	listView = (ListView) activity.findViewById(android.R.id.list);

    }

    public void testPreConditions() {
	assertNotNull(activity);
	assertNotNull(listView);
    }

    public void testListViewItem() {
	assertEquals("another fragment text",
		((StoryFragment) listView.getItemAtPosition(1)).getStoryText());
    }
}