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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * InternetDetector is a singleton class that mainly checks the connectivity of
 * the network. It's plain and requires nothing in constructor, and doesn't have
 * any fields except for an instance of itself.
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
public class InternetDetector {

    private static InternetDetector internetDetector = new InternetDetector();

    private InternetDetector() {
    }

    public static InternetDetector getInstance() {
	return internetDetector;
    }

    /**
     * This function takes in the context of current activity to get the service
     * of CONNECTIVITY_SERVICE. And returns the condition of network connection.
     * This function is usually called before sync to check if it's okay to sync.
     * 
     * @param context
     *            a context object of the activity where the function is called
     * @return true: if the device has network connection false: if the device
     *         doesn't have network connection
     */
    protected static boolean connectedToInternet(Context context) {
	ConnectivityManager connectivityManager = (ConnectivityManager) context
		.getSystemService(Context.CONNECTIVITY_SERVICE);
	if (connectivityManager != null) {
	    NetworkInfo[] info = connectivityManager.getAllNetworkInfo();
	    if (info != null) {
		for (int i = 0; i < info.length; i++) {
		    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
			return true;
		    }
		}
	    }
	}
	return false;
    }

}
