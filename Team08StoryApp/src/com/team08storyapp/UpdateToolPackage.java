package com.team08storyapp;

import android.app.Activity;

public class UpdateToolPackage {

    private FileHelper fHelper;
    private Activity activity;
    private ESHelper esHelper;

    public UpdateToolPackage(FileHelper fHelper, ESHelper esHelper, Activity activity) {
	this.fHelper = fHelper;
	this.activity = activity;
	this.esHelper = esHelper;
    }

    public FileHelper getfHelper() {
	return fHelper;
    }
    
    public ESHelper getESHelper(){
	return esHelper;
    }

    public Activity getActivity() {
	// TODO Auto-generated method stub
	return activity;
    }

}
