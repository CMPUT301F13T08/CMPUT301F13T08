package com.team08storyapp.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.team08storyapp.ESHelper;
import com.team08storyapp.FileHelper;
import com.team08storyapp.MyStoriesActivity;
import com.team08storyapp.R;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;

public class testMyStoriesActivity extends
	ActivityInstrumentationTestCase2<MyStoriesActivity> {

    private Activity activity;
    private View header;
    private Button searchButton;
    private ESHelper esHelper;
    private FileHelper fHelper;
    private ListView listView;
    private int containLosSantos = 0;
    private Story story;
    private String title;

    public testMyStoriesActivity() {
	super(MyStoriesActivity.class);
    }

    public void setUp() {

	activity = getActivity();

	fHelper = new FileHelper(activity, 1);
	esHelper = new ESHelper();

	header = activity.getLayoutInflater().inflate(R.layout.header_search,
		null);
	searchButton = (Button) header.findViewById(R.id.searchButton);
	listView = (ListView) activity.findViewById(android.R.id.list);

    }

    public void testPreConditions() {

	assertNotNull(header);
	assertNotNull(searchButton);
	assertNotNull(listView);

    }

    public void testHeaderText() {

	assertEquals("", header.getContext().toString());

    }

    public void testListViewItem() {
	
	for (int i = 1; i == listView.getCount(); i++) {

	    story = (Story) listView.getItemAtPosition(i);
	    title = story.getTitle().toString();
	    //title = ((Story) listView.getItemAtPosition(i)).getTitle().toString();
	   
	    if (title == "Los Santos"){
		containLosSantos = 1;
	    } 
	    
	}
	
	assertEquals(1, containLosSantos);

    }

}
