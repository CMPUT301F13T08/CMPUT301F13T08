package com.team08storyapp;

import android.content.Context;

public class UpdateToolPackage {

    private FileHelper fHelper;
    private Context context;
    private ESHelper esHelper;

    public UpdateToolPackage(FileHelper fHelper, ESHelper esHelper, Context context) {
	this.fHelper = fHelper;
	this.context = context;
	this.esHelper = esHelper;
    }

    public FileHelper getfHelper() {
	return fHelper;
    }
    
    public ESHelper getESHelper(){
	return esHelper;
    }

    public Context getContext() {
	return context;
    }

}
