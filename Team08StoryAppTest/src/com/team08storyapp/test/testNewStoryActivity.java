package com.team08storyapp.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import com.team08storyapp.NewStoryActivity;
import com.team08storyapp.R;
import com.team08storyapp.Story;

public class testNewStoryActivity extends
	ActivityInstrumentationTestCase2<NewStoryActivity> {

    private Activity activity;
    private EditText authorText;
    private EditText titleText;
    private String author;
    private String title;
    @SuppressWarnings("unused")
    private Story story;

    public testNewStoryActivity() {
	super(NewStoryActivity.class);
    }

    public void setUp() {

	author = "Ana Marcu";
	title = "Jungle Music";

	story = new Story(author, title);

	activity = getActivity();

	authorText = (EditText) activity.findViewById(R.id.enterAuthor);
	titleText = (EditText) activity.findViewById(R.id.enterTitle);

    }

    /* Check that the two EditTexts are created for the activity */
    public void testPreConditions() {

	assertNotNull(activity);
	assertNotNull(authorText);
	assertNotNull(titleText);

    }

    public void testAuthorText() {

	assertEquals("", authorText.getText().toString());

    }

    public void testTitleText() {

	assertEquals("", titleText.getText().toString());

    }

}
