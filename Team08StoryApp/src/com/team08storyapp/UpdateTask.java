/*
AUTHORS
========
Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen.

LICENSE
=======
Copyright  ���  2013 Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen,  
Free Software Foundation, Inc., Marky Mark  License GPLv3+: GNU
GPL version 3 or later <http://gnu.org/licenses/gpl.html>.
This program is free software: you can redistribute it and/or modify it under the terms of 
the GNU General Public License as published by the Free Software Foundation, either 
version 3 of the License, or (at your option) any later version. This program is distributed 
in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 
warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public 
License for more details. You should have received a copy of the GNU General Public License 
along with this program.  If not, see <http://www.gnu.org/licenses/>.
              
3rd Party Libraries
=============
Retrieved Oct. 27, 2013 - https://github.com/rayzhangcl/ESDemo
-This demo was used to help with JSON and ESHelper which is under the CC0 licenses

Retrieved Oct. 29, 2013  - http://hc.apache.org/downloads.cgi
-This is for the fluent library which is licensed under apache V2

Retrieved Oct. 29, 2013 
- https://code.google.com/p/google-gson/downloads/detail?name=google-gson-2.2.4-release.zip&can=2&q=
-This is for JSON which is licensed under apache V2
 */
package com.team08storyapp;

import java.util.ArrayList;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

/**
 * UpdateTask is an AsyncTask Object that performs sync function in the
 * background.
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 1.0 November 8, 2013
 * @since 1.0
 * 
 */
public class UpdateTask extends AsyncTask<Void, Integer, String> {

    private Context context;
    private FileHelper fHelper;
    private ESHelper esHelper;
    private NotificationHelper notificationHelper;

    /**
     * Constructor of UpdateTask requires an updateToolPackage that contains all
     * the information it needs:
     * <ul>
     * a FileHelper object to get the ids of stories that need to be uploaded
     * </ul>
     * <ul>
     * an ESHelepr object to upload stories
     * </ul>
     * <ul>
     * a Context object to instantiate a NotificationHelper object that puts up
     * a notification in status bar
     * </ul>
     * 
     * @param updatePkg
     *            an UpdateTookPackage that contains objects (fileHelper,
     *            esHelper, context) that are from the activity where
     *            constructor is called
     */
    public UpdateTask(UpdateToolPackage updatePkg) {
	context = updatePkg.getContext();
	fHelper = updatePkg.getfHelper();
	esHelper = updatePkg.getESHelper();
	notificationHelper = new NotificationHelper(context);
    }

    /**
     * This function just uses notificationHelper to create a new notification
     * of "sync" in the status bar. And it's called before executing the
     * "doInBackgroun()" function.
     */
    protected void onPreExecute() {
	super.onPreExecute();
	notificationHelper.createNotification();
    }

    /**
     * Doing sync in the background thread. It's executed when the execute() is
     * called to an UpdateTask object through dot operation.
     * 
     * @param updatePkg
     *            an UpdateTookPackage that contains objects (fileHelper,
     *            esHelper, context) that are from the activity where
     *            constructor is called
     */
    @Override
    protected String doInBackground(Void... updatePkg) {
	/* Get the ids of stories that need to be uploaded */
	ArrayList<String> ids = fHelper.getUpdateFilesIds();
	for (int i = 0; i < ids.size(); i++) {
	    int intId = parseId(ids, i);
	    if(intId < 1){
		continue;
	    }
	    /* upload a story */
	    try {
		Story updateStory = fHelper.getOfflineStory(intId);
		Story encodedStory = fHelper.encodeStory(updateStory);
		if (updateStory.getOnlineStoryId() < 1) {
		    updateStory.setOnlineStoryId(esHelper
			    .addOrUpdateOnlineStory(encodedStory));
		    fHelper.updateOfflineStory(updateStory);
		} else {
		    esHelper.addOrUpdateOnlineStory(encodedStory);
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	}
	/* delete all ids of stories that need to be uploaded */
	fHelper.clearUpdateQueue();
	return null;
    }

    private int parseId(ArrayList<String> ids, int i) {
	String id = ids.get(i);
	int intId = 0;
	try {
	    intId = Integer.parseInt(id);
	} catch (NumberFormatException e) {
	    e.printStackTrace();
	}
	return intId;
    }

    /**
     * Delete the notification from status bar and toast a message to let the
     * author know the changes have been uploaded
     */
    protected void onPostExecute(String result) {
	notificationHelper.completed();
	Toast.makeText(context, "You changes have been uploaded.",
		Toast.LENGTH_SHORT).show();
    }

}
