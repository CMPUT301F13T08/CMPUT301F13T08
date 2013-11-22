package com.team08storyapp.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import com.team08storyapp.ESHelper;
import com.team08storyapp.FileHelper;
import com.team08storyapp.MainActivity;
import com.team08storyapp.UpdateToolPackage;

public class testUpdateToolPackage extends ActivityInstrumentationTestCase2<MainActivity>{

    private Activity activity;
    private FileHelper fHelper;
    private ESHelper esHelper;
    public testUpdateToolPackage() {
	super(MainActivity.class);
    }
    
    public void setUp(){
	activity = super.getActivity();
	fHelper = new FileHelper(activity.getApplicationContext(), 1);
	esHelper = new ESHelper();
    }
    
    public void testConstructorUpdateToolPackage(){
	UpdateToolPackage utp = new UpdateToolPackage(fHelper,esHelper, activity);
	
	assertEquals(activity.getApplicationContext(), utp.getContext());
	assertEquals(fHelper, utp.getfHelper());
	assertEquals(esHelper, utp.getESHelper());
    }

}
