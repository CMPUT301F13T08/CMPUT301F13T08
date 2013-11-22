package com.team08storyapp.test;

import com.team08storyapp.BuiltInHelp;
import com.team08storyapp.MainActivity;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

public class testBuiltInHelp extends
	ActivityInstrumentationTestCase2<MainActivity> {

    Activity activity;

    public testBuiltInHelp() {
	super(MainActivity.class);
    }

    /* Set up testing data for testing methods. */
    public void setUp() {
	activity = getActivity();
    }

    /*
     * Test the constructor of BuiltInHelp is actually properly being
     * instantiated
     */
    public void testConstructorBuiltInHelp() {
	BuiltInHelp help = new BuiltInHelp(activity);
	assertNotNull(help);
    }
    
    
}
