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

    /*
     * NOT SURE HOW TO TEST TEXT VIEWS BECAUSE YOU CANNOT SET TEXT ON AN EDIT
     * TEXT
     */

    /*
     * public void testAuthorItem(){
     * 
     * authorText.setText("Ana Marcu"); assertEquals("Ana Marcu",
     * authorText.getText());
     * 
     * }
     * 
     * public void testTitleItem(){
     * 
     * titleText.setText("Jungle Music"); assertEquals("Jungle Music",
     * titleText.getText());
     * 
     * }
     */
}
