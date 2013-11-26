package com.team08storyapp.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import com.team08storyapp.MainActivity;

public class testMainActivity extends
ActivityInstrumentationTestCase2<MainActivity>{

    private Activity activity;
    
    public testMainActivity() {
   	super(MainActivity.class);
       }
 
    public void setUp(){

	activity = getActivity();
	
    }
    
    public void testPreConditions(){
	assertNotNull(activity);
    }
    
}
