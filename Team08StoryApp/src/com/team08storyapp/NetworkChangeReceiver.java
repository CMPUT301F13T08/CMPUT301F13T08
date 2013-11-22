package com.team08storyapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {
    
    @Override
    public void onReceive(final Context context, final Intent intent) {
 
        boolean status = InternetDetector.connectedToInternet(context);
 
        if(status){
            SyncManager.sync(context);
        }else{
            Toast.makeText(context, "Lose Network Connection.", Toast.LENGTH_LONG).show();
        }
    }

}
