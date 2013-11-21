package com.team08storyapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetDetector {
    private static InternetDetector internetDetector = new InternetDetector();

    private InternetDetector() {
    }
    
    public static InternetDetector getInstance(){
	return internetDetector;
    }

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
