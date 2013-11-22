package com.team08storyapp;

import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class UpdateTask extends AsyncTask<Void, Integer, String> {

    private Context context;
    private FileHelper fHelper;
    private ESHelper esHelper;
    private NotificationHelper notificationHelper;

    public UpdateTask(UpdateToolPackage updatePkg) { 	
	context = updatePkg.getContext();
	fHelper = updatePkg.getfHelper();
	esHelper = updatePkg.getESHelper();
	notificationHelper = new NotificationHelper(context);
    }

    protected void onPreExecute() {
	super.onPreExecute();
	notificationHelper.createNotification();
    }

    @Override
    protected String doInBackground(Void... updatePkg) {
	ArrayList<String> ids = fHelper.getUpdateFilesIds();
	for (int i = 0; i < ids.size(); i++) {
	    String id = ids.get(i);
	    int intId = 0;
	    try {
		intId = Integer.parseInt(id);
	    } catch (NumberFormatException e) {
		e.printStackTrace();
		continue;
	    }
	    try {
		Story updateStory = fHelper.getOfflineStory(intId);
		Story encodedStory = fHelper.encodeStory(updateStory);
		if(updateStory.getOnlineStoryId() < 1){
		    updateStory.setOnlineStoryId(esHelper.addOrUpdateOnlineStory(encodedStory));
		    fHelper.updateOfflineStory(updateStory);
		}else{
		    esHelper.addOrUpdateOnlineStory(encodedStory);
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}
	fHelper.clearUpdateQueue();
	return null;
    }


    protected void onPostExecute(String result) {
	notificationHelper.completed();
	Toast.makeText(context, "You changes have been uploaded.",
		Toast.LENGTH_SHORT).show();
    }

}
