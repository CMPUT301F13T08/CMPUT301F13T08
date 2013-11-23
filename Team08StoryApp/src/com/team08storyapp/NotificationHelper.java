package com.team08storyapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * NotifictionHelper class helps create a notification in status bar when
 * UpdateTask performs sync in background.
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 1.0 November 8, 2013
 * @since 1.0
 */
public class NotificationHelper {

    private Context context;
    private int NOTIFICATION_ID = 1;
    private Notification notification;
    private NotificationManager notificationManager;
    private PendingIntent contentIntent;
    private CharSequence contentTitle;

    public NotificationHelper(Context context) {
	this.context = context;
    }

    /**
     * Put the notification into the status bar and initialize the text in this
     * notification.
     */
    @SuppressWarnings("deprecation")
    public void createNotification() {

	/* get the notification manager */
	notificationManager = (NotificationManager) context
		.getSystemService(Context.NOTIFICATION_SERVICE);

	/* create the notification */
	int icon = android.R.drawable.stat_sys_upload;

	/* Initial text that appears in the status bar */
	CharSequence tickerText = "Sync";
	long when = System.currentTimeMillis();
	notification = new Notification(icon, tickerText, when);

	/* create the content which is shown in the notification pulldown */
	contentTitle = "Sync Process";
	Intent notificationIntent = new Intent();
	contentIntent = PendingIntent.getActivity(context, 0,
		notificationIntent, 0);

	/* add the additional content and intent to the notification */
	notification.setLatestEventInfo(context, contentTitle, "Syncing",
		contentIntent);

	/* make this notification appear in the 'Ongoing events' section */
	notification.flags = Notification.FLAG_ONGOING_EVENT;

	/* show the notification */
	notificationManager.notify(NOTIFICATION_ID, notification);
    }

    /**
     * called when the background task is complete, this removes the
     * notification from the status bar. We could also use this to add a new
     * ‘task complete’ notification
     */
    public void completed() {

	// remove the notification from the status bar
	notificationManager.notify(NOTIFICATION_ID, notification);
	notificationManager.cancel(NOTIFICATION_ID);
    }
}
