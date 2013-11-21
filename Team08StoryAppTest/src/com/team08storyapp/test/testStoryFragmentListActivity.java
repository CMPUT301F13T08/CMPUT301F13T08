package com.team08storyapp.test;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;
import com.team08storyapp.StoryFragmentListActivity;

public class testStoryFragmentListActivity extends
	ActivityInstrumentationTestCase2<StoryFragmentListActivity> {

    private Activity activity;
    private ListView listView;
    private StoryFragment firstStoryFragment;
    private StoryFragment secondStoryFragment;
    private StoryFragment thirdStoryFragment;
    private Story story;
    private ArrayList<StoryFragment> storyFragments;

    public testStoryFragmentListActivity() {
	super(StoryFragmentListActivity.class);
    }

    public void setUp() {

	firstStoryFragment = new StoryFragment(1);
	firstStoryFragment.setStoryText("FirstText");
	secondStoryFragment = new StoryFragment(2);
	secondStoryFragment.setStoryText("SecondText");
	thirdStoryFragment = new StoryFragment(3);
	thirdStoryFragment.setStoryText("ThirdText");

	storyFragments = new ArrayList<StoryFragment>();
	storyFragments.add(firstStoryFragment);
	storyFragments.add(secondStoryFragment);
	storyFragments.add(thirdStoryFragment);

	story = new Story("title", "author");
	story.setOfflineStoryId(1);
	story.setOnlineStoryId(1);
	story.setFirstStoryFragmentId(1);
	story.setStoryFragments(storyFragments);

	/*
	 * Pass intent to StoryFragmentListActivity with the current story
	 * object
	 */

	Intent intent = new Intent();
	intent.putExtra("story", story);
	setActivityIntent(intent);

	activity = getActivity();

	listView = (ListView) activity.findViewById(android.R.id.list);

    }

    public void testPreConditions() {

	assertNotNull(activity);
	assertNotNull(listView);

    }

    /*
     * Verify that all three fragments in the current story's fragment list are
     * populated in the listView
     */public void testListViewItem() {

	assertEquals("FirstText",
		((StoryFragment) listView.getItemAtPosition(0)).getStoryText());

	assertEquals("SecondText",
		((StoryFragment) listView.getItemAtPosition(1)).getStoryText());

	assertEquals("ThirdText",
		((StoryFragment) listView.getItemAtPosition(2)).getStoryText());
    }

}
