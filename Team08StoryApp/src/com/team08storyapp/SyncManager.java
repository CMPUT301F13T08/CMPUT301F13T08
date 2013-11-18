package com.team08storyapp;

import java.util.ArrayList;

public class SyncManager {
    private static SyncManager syncManager = new SyncManager();

    private SyncManager() {
    }

    public static SyncManager getInstance() {
	return syncManager;
    }

    protected static void sync(FileHelper fHelper, ESHelper esHelper) {
	ArrayList<String> storyIdList = fHelper.getUpdateFilesIds();
	for (String id : storyIdList) {
	    System.out.println("I'm gonna update this!:" + id);
	    int intId = 0;
	    try {
		intId = Integer.parseInt(id);
	    } catch (NumberFormatException e) {
		e.printStackTrace();
		continue;
	    }
	    try {
		Story updateStory = fHelper.getOfflineStory(intId);
		System.out.println("BEING UPDATE:" + updateStory.toString());
		int onlineId = esHelper.addOrUpdateOnlineStory(updateStory);
		if (updateStory.getOnlineStoryId() < 1) {
		    updateStory.setOnlineStoryId(onlineId);
		    fHelper.updateOfflineStory(updateStory);
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}
	fHelper.clearUpdateQueue();
	System.out.println("UPDATE QUEUE CLEAR");

    }
}
