package com.team08storyapp;

import java.util.ArrayList;

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
	
	
	public static StoryFragment readStoryFragment(ArrayList<StoryFragment> storyFragments, int storyFragmentId){
		StoryFragment nextFragment = null;
		for(int i = 0; i < storyFragments.size(); i++){
			if (storyFragments.get(i).getStoryFragmentId() == storyFragmentId){
				nextFragment = storyFragments.get(i);
			}
		}
		return nextFragment;
	}


	public static boolean addStoryFragment(StoryFragment addStoryFragment) {
		// TODO Auto-generated method stub
		return false;
	}


	
}




