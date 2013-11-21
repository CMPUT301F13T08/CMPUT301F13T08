package com.team08storyapp.test;



import android.test.ActivityInstrumentationTestCase2;

import com.team08storyapp.Choice;
import com.team08storyapp.MainActivity;

public class testChoice extends
ActivityInstrumentationTestCase2<MainActivity>{

   
    public testChoice() {
	super(MainActivity.class);
    }

    public void setUp(){
	
    }
    
    /*
     * Constructor Test for Choice object, with parameters
     * toGoToStoryFragmentID, choiceId, and text
     */
    public void testConstructorChoice() {
	Choice choice = new Choice(2, 1, "text");

	assertEquals(2, choice.getStoryFragmentID());
	assertEquals(1, choice.getChoiceId());
	assertEquals("text", choice.getText());

    }
    
}
