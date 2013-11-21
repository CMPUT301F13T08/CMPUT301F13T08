package com.team08storyapp.test;

import java.util.ArrayList;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.team08storyapp.Choice;
import com.team08storyapp.ChoiceAdapter;
import com.team08storyapp.StoryFragmentActivity;


public class testStoryFragmentActivity extends
ActivityInstrumentationTestCase2<StoryFragmentActivity>{

    private Activity activity;
    private ChoiceAdapter adapter;
    private ArrayList<Choice> Choices;
    private Choice choice1;
    private Choice choice2;
    
    
    public testStoryFragmentActivity() {
  	super(StoryFragmentActivity.class);
      }
      
    public void setUp(){
	
	Choices = new ArrayList<Choice>();
	choice1 = new Choice(2, 1, "Go to 2!");
	choice2 = new Choice(3, 2, "Go to 3!");
	Choices.add(choice1);
	Choices.add(choice2);
	
	activity = super.getActivity();

	
	
    }
   
   
    
}
