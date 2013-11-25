package com.team08storyapp.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;

import com.team08storyapp.ESHelper;
import com.team08storyapp.FileHelper;
import com.team08storyapp.MainActivity;
import com.team08storyapp.UpdateTask;
import com.team08storyapp.UpdateToolPackage;

public class testUpdateTask extends ActivityInstrumentationTestCase2<MainActivity>{

    private Activity activity;
    private FileHelper fHelper;
    private ESHelper esHelper;
    
    public testUpdateTask() {
	super(MainActivity.class);
    }
    
    public void setUp(){
	activity = super.getActivity();
    }
    
    public void testConstructorUpdateTask(){
	fHelper = new FileHelper(activity, 1);
	FileHelper f1 = new FileHelper(activity, 1);
	assertNotSame(f1, fHelper);
	esHelper = new ESHelper();
	UpdateToolPackage utp1 = new UpdateToolPackage(fHelper, esHelper, activity);
	
	UpdateTask ut1 = new UpdateTask(utp1);
	UpdateTask ut2 = new UpdateTask(utp1);
	assertNotSame(ut1, ut2);
    }

}
