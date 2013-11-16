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
	ArrayList<Story> storyList = new ArrayList<Story>();
	try {
	    storyList = fHelper.getOfflineStories();
	} catch (Exception e) {

	}
	for (Story story : storyList) {
	    if (!story.getUnchanged()) {
		try {
		    esHelper.addOrUpdateOnlineStory(story);
		    fHelper.resetOfflineStory(story);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	}
    }
}
