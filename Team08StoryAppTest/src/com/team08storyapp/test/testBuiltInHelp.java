package com.team08storyapp.test;

import com.team08storyapp.MainActivity;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

/*
 * This class cannot be tested as the only method is a common static method 
 * that shows a dialog box for help. This dialog box would require UI testing
 * to see if it did pop up.
 * 
 * */
public class testBuiltInHelp extends
	ActivityInstrumentationTestCase2<MainActivity> {

    Activity activity;
    
    public testBuiltInHelp() {
	super(MainActivity.class);
    }

    public void setUp() {
	activity = getActivity();
    }

    public void testPreConditions(){
	assertNotNull(activity);
    }
}
