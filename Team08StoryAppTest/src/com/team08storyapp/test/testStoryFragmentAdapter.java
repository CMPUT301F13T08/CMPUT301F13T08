package com.team08storyapp.test;

import java.util.ArrayList;

import org.junit.Before;
import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.team08storyapp.Photo;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;
import com.team08storyapp.StoryFragmentAdapter;
import com.team08storyapp.StoryFragmentListActivity;

public class testStoryFragmentAdapter extends
	ActivityInstrumentationTestCase2<StoryFragmentListActivity> {

    private Activity activity;
    private StoryFragmentAdapter adapter;
    private ArrayList<StoryFragment> sfList;

    /* Constructor */
    public testStoryFragmentAdapter() {
	super(StoryFragmentListActivity.class);
    }

    @Before
    public void setUp() throws Exception {
	super.setUp();

	/* StoryFragment ArrayList make up. */
	ArrayList<Photo> pList = new ArrayList<Photo>();
	sfList = new ArrayList<StoryFragment>();

	StoryFragment sf = new StoryFragment(1, "Hello. Welcome to "
		+ "Rapture.");
	sf.setPhotos(pList);
	sfList.add(sf);

	StoryFragment sf1 = new StoryFragment(2,
		"You are in Rapture now. Be alert. Here come big "
			+ "daddy and little sister. ");
	sf1.setPhotos(pList);
	sfList.add(sf1);

	StoryFragment sf2 = new StoryFragment(3,
		"You've been slaughtered by the big daddy. "
			+ "Little sister is ready to extract ADAM from you.");
	sf2.setPhotos(pList);
	sfList.add(sf2);

	Story story = new Story(1, "SpongeBob SquarePants", "Whoever");
	story.setStoryFragments(sfList);

	/* Intent Mock Up */
	Intent intent = new Intent();
	intent.putExtra("story", story);
	super.setActivityIntent(intent);
	activity = super.getActivity();

	/* Initialize a StoryFragmentAdapter */
	adapter = new StoryFragmentAdapter(activity, android.R.id.list, sfList);
    }

    public void testGetView() {

	/* Test getView() function */
	ViewGroup parent = new LinearLayout(activity.getApplicationContext());
	adapter.getView(1, null, parent);

	/* Make sure all 3 story fragments in sfList are in adapter */
	assertEquals(adapter.getCount(), 3);

	/*
	 * Make sure right place has the right fragment. Index(Position) 0: sf
	 * Index(Position) 1: sf1 Index(Position) 2: sf2
	 */
	assertEquals(adapter.getItem(0).getStoryFragmentId(), 1);
	assertEquals(adapter.getItem(0).getStoryText(),
		"Hello. Welcome to Rapture.");
	adapter.getView(2, null, parent);

	assertEquals(adapter.getItem(1).getStoryFragmentId(), 2);
	assertEquals(adapter.getItem(1).getStoryText(),
		"You are in Rapture now. Be alert. Here come big "
			+ "daddy and little sister. ");

	assertEquals(adapter.getItem(2).getStoryFragmentId(), 3);
	assertEquals(adapter.getItem(2).getStoryText(),
		"You've been slaughtered by the big daddy. "
			+ "Little sister is ready to extract ADAM from you.");
    }

}
