package com.team08storyapp.test;

import com.team08storyapp.MainActivity;

import android.test.ActivityInstrumentationTestCase2;

/*
 * This class cannot be tested as the only method is a common static method 
 * that shows a dialog box for help. This dialog box would require UI testing
 * to see if it did pop up.
 * 
 * */
public class testBuiltInHelp extends
	ActivityInstrumentationTestCase2<MainActivity> {

    public testBuiltInHelp() {
	super(MainActivity.class);
    }

    /* Set up testing data for testing methods. */
    public void setUp() {
    }

}
