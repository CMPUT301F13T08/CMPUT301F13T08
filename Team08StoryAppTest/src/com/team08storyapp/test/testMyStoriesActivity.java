package com.team08storyapp.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.team08storyapp.ESHelper;
import com.team08storyapp.FileHelper;
import com.team08storyapp.MyStoriesActivity;
import com.team08storyapp.R;
import com.team08storyapp.Story;

public class testMyStoriesActivity extends
	ActivityInstrumentationTestCase2<MyStoriesActivity> {

    private Activity activity;
    private View header;
    private Button searchButton;
    @SuppressWarnings("unused")
    private ESHelper esHelper;
    @SuppressWarnings("unused")
    private FileHelper fHelper;
    private ListView listView;
    private EditText editText;
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

	/*
	 * Hey Ana:
	 * 
	 * Sorry, I moved the header search bar to activity_my_stories
	 * xml to maintain the consistency of our layout. So you don't have to
	 * do the header thing. Just do the search bar as you did in
	 * OnlineStories/OfflineStoriesActivity will be fine.
	 * 
	 * Sorry for bringing the bugs and any inconvenience.
	 * 
	 * Alice
	 */
	header = activity.getLayoutInflater().inflate(R.layout.header_search,
		null);
	searchButton = (Button) header.findViewById(R.id.searchButton);
	listView = (ListView) activity.findViewById(android.R.id.list);
	editText = (EditText) header.findViewById(R.id.searchText);
    }

    public void testPreConditions() {

	assertNotNull(header);
	assertNotNull(searchButton);
	assertNotNull(listView);
	assertNotNull(editText);

    }

    public void testEditViewItem() {

	assertEquals("", editText.getText().toString());

    }

    // not sure why fails
    public void testListViewItem() {

	int firstVisibleRow = listView.getFirstVisiblePosition();
	int lastVisibleRow = listView.getLastVisiblePosition();

	for (int i = firstVisibleRow; i <= lastVisibleRow; i++) {
	    story = (Story) listView.getItemAtPosition(i);
	    title = story.getTitle().toString();

	    if (title.equals("Los Santos")) {
		containLosSantos = 1;
	    }

	}

	assertEquals(1, containLosSantos);

    }

}
