package com.team08storyapp;

import java.util.ArrayList;

import android.content.Context;

public class StoryController {

	// Constructor is added since the FileHelper needs 
	// a context from previous activity.
	//private FileHelper fHelper;
	//private Context activityContext
	//public StoryController(Context ctx){
	//	activityContext = ctx;
	//}
	
	/*
	 * Also I'm thinking that the constructor of StoryController
	 * should take at least a storyId as a parameter?
	 */
	public static boolean readStory(Story addStory) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean addStoryFragment(StoryFragment addStoryFragment) {
		// TODO Auto-generated method stub
		return false;
	}

	public static ArrayList<StoryFragment> search(String searchText) {
		// TODO Auto-generated method stub
		// 1. Specify the mode (Online, Downloaded(int mode = 0), My(int mode = 1))
		// 2. if search online stories:
		//        initialize an EShelper and call its addOnlineStory
		// 	  else if search offline stories:
		//		  initialize the fHelper(activityContext, 0)
		//        and return the fHelper.searchOfflineStories("searchText");
		//    else:
		// 		  initialize the fHelper(activityContext, 1)
		//		  and return the fHelper.searchOfflineStories("searchText");
		return null;
	}
	
	
}




