package com.team08storyapp.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.ListView;

import com.team08storyapp.ESHelper;
import com.team08storyapp.FileHelper;
import com.team08storyapp.OnlineStoriesActivity;
import com.team08storyapp.R;
import com.team08storyapp.Story;

public class testOnlineStoriesActivity extends
	ActivityInstrumentationTestCase2<OnlineStoriesActivity> {

    private Activity activity;
    @SuppressWarnings("unused")
    private ESHelper esHelper;
    @SuppressWarnings("unused")
    private FileHelper fHelper;
    private ListView listView;
    private EditText editText;
    private Story story;
    private String title;
    private int containOsbourne = 0;

    public testOnlineStoriesActivity() {
	super(OnlineStoriesActivity.class);
    }

    public void setUp() {

	activity = getActivity();

	fHelper = new FileHelper(activity, 1);
	esHelper = new ESHelper();

	listView = (ListView) activity.findViewById(android.R.id.list);
	editText = (EditText) activity.findViewById(R.id.search);

    }

    public void testPreConditions() {

	assertNotNull(listView);
	assertNotNull(editText);

    }

    public void testEditViewItem() {

	assertEquals("", editText.getText().toString());

    }

    /* Check that "Osbourne's Adventures" is one of the online stories */
    public void testListViewItem() {

	int firstVisibleRow = listView.getFirstVisiblePosition();
	int lastVisibleRow = listView.getLastVisiblePosition();

	for (int i = firstVisibleRow; i <= lastVisibleRow; i++) {
	    story = (Story) listView.getItemAtPosition(i);
	    title = story.getTitle().toString();

	    if (title.equals("Osbourne's Adventures")) {
		containOsbourne = 1;
	    }

	}

	assertEquals(1, containOsbourne);

    }

}
