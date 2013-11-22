package com.team08storyapp;

import android.content.Context;

public class SyncManager {

    private static SyncManager syncManager = new SyncManager();

    private SyncManager() {
    }

    public static SyncManager getInstance() {
	return syncManager;
    }

    protected static void sync(Context context) {
	FileHelper fHelper = new FileHelper(context, 1);
	ESHelper esHelper = new ESHelper();
	if (fHelper.getUpdateFilesIds().size() > 0
		&& InternetDetector.connectedToInternet(context)) {
	    UpdateToolPackage utp1 = new UpdateToolPackage(fHelper, esHelper,
		    context);
	    UpdateTask sync = new UpdateTask(utp1);
	    sync.execute();
	}
    }
}