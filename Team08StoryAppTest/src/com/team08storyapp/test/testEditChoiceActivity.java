package com.team08storyapp.test;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.TextView;

import com.team08storyapp.EditChoiceActivity;
import com.team08storyapp.R;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;

public class testEditChoiceActivity extends
ActivityInstrumentationTestCase2<EditChoiceActivity>  {

    private Activity activity;
    private TextView textView;
    private EditText editText;
    private Story story;
    private StoryFragment firstStoryFragment;
    private StoryFragment secondStoryFragment;
    private int storyFragmentIndex;
    
    public testEditChoiceActivity() {
	super(EditChoiceActivity.class);
    }
    
    
    public void setUp(){
	
	story = new Story("title", "author");
	firstStoryFragment = new StoryFragment(1, "FirstText");
	secondStoryFragment = new StoryFragment(2, "SecondText");
	
	story.getStoryFragments().add(firstStoryFragment);
	story.getStoryFragments().add(secondStoryFragment);
	
	storyFragmentIndex = 1;
	
	Intent intent = new Intent();
	intent.putExtra("story", story);
	intent.putExtra("storyFragmentIndex", storyFragmentIndex);
	setActivityIntent(intent);
	
	activity = getActivity();
	
	textView = (TextView) activity.findViewById(R.id.choiceFragmentId);
	editText = (EditText) activity.findViewById(R.id.editChoiceText);

    }
    
    
    public void testPreConditions(){
	
	assertNotNull(activity);
	assertNotNull(textView);
	assertNotNull(editText);
	
    }
    
    
    public void testTextViewItem(){
	
	assertEquals("The linked fragment id is: ", textView.getText().toString());
	
    }
    
    public void testEditTextItem(){
	
	assertEquals("", editText.getText().toString());
    }
    
}
