package com.team08storyapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.widget.Toast;

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
	    try {
		int intId = Integer.parseInt(id);
	    } catch (NumberFormatException e) {
		e.printStackTrace();
		continue;
	    }
	    try {
		Story updateStory = fHelper.getOfflineStory(Integer
			.parseInt(id));
		System.out.println("BEING UPDATE:" + updateStory.toString());
		esHelper.addOrUpdateOnlineStory(updateStory);
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}
	fHelper.clearUpdateQueue();
	System.out.println("UPDATE QUEUE CLEAR");

    }
}
