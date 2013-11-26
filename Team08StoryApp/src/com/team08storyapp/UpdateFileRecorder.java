package com.team08storyapp;

import java.io.FileOutputStream;
import android.content.Context;
import java.util.ArrayList;
import java.io.File;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class UpdateFileRecorder {

    /**
     * appendUpdateQueue function is called whenever a local authored story has
     * been changed since last sync. This function will append the story's
     * offline id into a file named "updateQueue" if it's not in the
     * updateQueue. And the "updateQueue" file is used for keeping records of
     * locally authored stories that have been edited since the last sync.
     * 
     * @param storyId
     *            the offline id of the story that needs to be synced to
     *            webserver
     */
    public static void appendUpdateQueue(int storyId, Context fileContext) {
	try {
	    String filename = "updateQueue";
	    FileOutputStream fos = fileContext.openFileOutput(filename,
		    Context.MODE_APPEND);
	    fos.write((Integer.toString(storyId) + "\n").getBytes());
	    fos.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * This function is usually called under condition of the network is
     * connected and check if a sync is need to be performed. getUpdateFilesIds
     * returns a list of ids that are read from the file "updateQueue". Those
     * ids are the offline ids of stories that need to be synced.
     * 
     * @return updateId It's an ArrayList of Strings, which represent the
     *         offline ids of the stories that need to be synced to webserver.
     */
    public static ArrayList<String> getUpdateFilesIds(Context fileContext) {
	ArrayList<String> updateId = new ArrayList<String>();
	try {
	    File dir = fileContext.getFilesDir();
	    File updateQueue = new File(dir, "updateQueue");
	    InputStream is = new BufferedInputStream(new FileInputStream(
		    updateQueue));
	    if (is != null) {
		InputStreamReader isr = new InputStreamReader(is);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(isr);
		String temp = "";
		while ((temp = br.readLine()) != null) {
		    if (!updateId.contains(temp)) {
			updateId.add(temp);
		    }
		}
	    }
	    is.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return updateId;
    }

    /**
     * clearUpdateQueue deletes the file "updateQueue", which keeps record of
     * stories that need to be uploaded, when a sync/upload is done
     * successfully.
     */
    public static  void clearUpdateQueue(Context fileContext) {
	fileContext.deleteFile("updateQueue");
    }
}