package com.team08storyapp.test;

import java.util.ArrayList;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.team08storyapp.MyStoriesActivity;
import com.team08storyapp.Story;
import com.team08storyapp.StoryInfoAdapter;

public class testStoryInfoAdapter extends
	ActivityInstrumentationTestCase2<MyStoriesActivity> {

    private StoryInfoAdapter adapter;
    private Activity activity;

    public testStoryInfoAdapter() {
	super(MyStoriesActivity.class);
    }

    public void setUp() {
	Story s1 = new Story(1, "Bioshock manual", "Whoever");
	Story s2 = new Story(2, "Fight Club", "C");
	Story s3 = new Story(3, "King Lear", "Shakespear");
	ArrayList<Story> sList = new ArrayList<Story>();
	sList.add(s1);
	sList.add(s2);
	sList.add(s3);

	activity = super.getActivity();

	adapter = new StoryInfoAdapter(activity, android.R.id.list, sList);
    }
    
    public void testGetView(){
	ViewGroup parent = new LinearLayout(activity.getApplicationContext());
	adapter.getView(1, null, parent);

	/* Make sure all 3 story fragments in sfList are in adapter */
	assertEquals(adapter.getCount(), 3);

	/*
	 * Make sure right place has the right fragment. Index(Position) 0: sf
	 * Index(Position) 1: sf1 Index(Position) 2: sf2
	 */
	assertEquals(adapter.getItem(0).getOfflineStoryId(), 1);
	assertEquals(adapter.getItem(0).getTitle(),
		"Bioshock manual");
	assertEquals(adapter.getItem(0).getAuthor(), "Whoever");
	
	adapter.getView(2, null, parent);
	assertEquals(adapter.getItem(1).getOfflineStoryId(), 2);
	assertEquals(adapter.getItem(1).getTitle(),
		"Fight Club");
	assertEquals(adapter.getItem(1).getAuthor(), "C");
	
	adapter.getView(2, null, parent);
	assertEquals(adapter.getItem(2).getOfflineStoryId(), 3);
	assertEquals(adapter.getItem(2).getTitle(),
		"King Lear");
	assertEquals(adapter.getItem(2).getAuthor(), "Shakespear");
    }

}
