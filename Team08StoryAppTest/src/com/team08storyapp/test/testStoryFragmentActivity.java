package com.team08storyapp.test;

import java.util.ArrayList;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.ViewGroup;
import android.widget.LinearLayout;


import com.team08storyapp.Choice;
import com.team08storyapp.ChoiceAdapter;
import com.team08storyapp.Photo;
import com.team08storyapp.StoryFragment;
import com.team08storyapp.StoryFragmentActivity;


public class testStoryFragmentActivity extends
ActivityInstrumentationTestCase2<StoryFragmentActivity>{

    private Activity activity;
    private ChoiceAdapter adapter;
    private ArrayList<Choice> Choices;
    private ArrayList<Photo> Photos;
    private StoryFragment storyFragment;
    private Choice choice1;
    private Choice choice2;
    private Photo photo1;
    private Photo photo2;
    
    public testStoryFragmentActivity() {
  	super(StoryFragmentActivity.class);
      }
      
    public void setUp(){
	
	storyFragment = new StoryFragment(1);
	storyFragment.setStoryText("Story Fragment Text");
	
	Choices = new ArrayList<Choice>();
	choice1 = new Choice(2, 1, "Go to 2!");
	choice2 = new Choice(3, 2, "Go to 3!");
	Choices.add(choice1);
	Choices.add(choice2);
	
	storyFragment.setChoices(Choices);
	
	activity = super.getActivity();

	
	
    }
   
   
    
}
