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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;

/**
 * UpdateFileRecorder is a class that records, retrieves, clears the ids of
 * files that need to be uploaded. It's always called and provides the ids of
 * files whenever sync is in process and sync needs to know what files it needs
 * to upload.
 * 
 * <p>
 * In order to manage the record of files that need to be updated,
 * UpdateFileRecorder provides the following methods:
 * <ul>
 * <li>Add an id of a file to updateQueue file where all ids of files that needs
 * to be uploaded are stored.
 * <li>Retrieve a list of ids of files that needs to be uploaded
 * <li>Clear the updateQueue file when a sync is done.
 * </ul>
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 1.0 November 8, 2013
 * @since 1.0
 */
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
     * @param fileContext
     *            the context object of the an activity. It's used for reading/
     *            writing files
     */
    public static void appendUpdateQueue(int storyId, Context fileContext) {
	try {
	    
	    /*
	     * create a file called updateQueue that keeps the record of files
	     * that need to be uploaded
	     */
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
     * @param fileContext
     *            the context object of the an activity. It's used for reading/
     *            writing files
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
     * * clearUpdateQueue deletes the file "updateQueue", which keeps record of
     * stories that need to be uploaded, when a sync/upload is done
     * successfully.
     * 
     * @param fileContext
     *            the context object of the an activity. It's used for reading/
     *            writing files
     */
    public static void clearUpdateQueue(Context fileContext) {
	fileContext.deleteFile("updateQueue");
    }
}