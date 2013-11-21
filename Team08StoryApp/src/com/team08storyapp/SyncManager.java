package com.team08storyapp;

import android.app.Activity;

public class SyncManager {

    private static SyncManager syncManager = new SyncManager();

    private SyncManager() {
    }

    public static SyncManager getInstance() {
	return syncManager;
    }

    protected static void sync(Activity activity) {
	FileHelper fHelper = new FileHelper(activity.getApplicationContext(), 1);
	ESHelper esHelper = new ESHelper();
	if (fHelper.getUpdateFilesIds().size() > 0
		&& InternetDetector.connectedToInternet(activity
			.getApplicationContext())) {
	    UpdateToolPackage utp1 = new UpdateToolPackage(fHelper, esHelper,
		    activity);
	    UpdateTask sync = new UpdateTask(utp1);
	    sync.execute();
	}
    }
}