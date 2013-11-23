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

import android.content.Context;

/**
 * SyncManager is a singleton class that handles the sync of changes on locally
 * authored stories.
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 1.0 November 8, 2013
 * @since 1.0
 */
public class SyncManager {

    private static SyncManager syncManager = new SyncManager();

    /**
     * Constructor of SyncManager
     * 
     */
    private SyncManager() {
    }

    /**
     * This function is called to return an instance of SyncManager
     * 
     * @return syncManager an instance of SyncManager
     */
    public static SyncManager getInstance() {
	return syncManager;
    }

    /**
     * sync is usually called in an activity's onCreate(), onResume(), and when
     * the network is connected. It simply checks if a sync should be done
     * first. If necessary, it will call an AsyncTask object, UpdateTask, to
     * upload changes to web server. Otherwise, the function will stop working.
     * 
     * @param context
     *            a context object of the activity that depends on the activity
     *            where SyncManager is called
     */
    protected static void sync(Context context) {

	/* Initialize two helpers */
	FileHelper fHelper = new FileHelper(context, 1);
	ESHelper esHelper = new ESHelper();

	/* check if a sync should be performed and can be performed */
	if (fHelper.getUpdateFilesIds().size() > 0
		&& InternetDetector.connectedToInternet(context)) {
	    UpdateToolPackage utp1 = new UpdateToolPackage(fHelper, esHelper,
		    context);
	    UpdateTask sync = new UpdateTask(utp1);
	    sync.execute();
	}
    }
}