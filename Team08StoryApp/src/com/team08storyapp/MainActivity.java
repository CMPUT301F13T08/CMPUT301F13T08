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

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Toast;

/**
 * MainActivity is a view class that displays the main menu for our application.
 * It is the first thing the user sees when they run the application, and gives
 * the options to view:
 * <ul>
 * <li>Online Stories
 * <li>Downloaded Stories
 * <li>My Stories
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
public class MainActivity extends Activity {

    private static final int HELP_MENU_ID = Menu.FIRST;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	/*
	 * Inflate the menu; this adds items to the action bar if they are
	 * present.
	 */
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }
    
    public void onCreateContextMenu(ContextMenu menu, View v,
	    ContextMenuInfo menuInfo) {
	super.onCreateContextMenu(menu, v, menuInfo);
	menu.add(0, HELP_MENU_ID, 0, R.string.help_menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
	
	if(item.getItemId() == HELP_MENU_ID){
	    Dialog helpDialog = new Dialog(MainActivity.this);
	    return true;
	}
	
	return super.onContextItemSelected(item);   
    }

    /**
     * toOnlineStories creates an intent to move to OnlineStoriesActivity when
     * "Read Online Stories" is clicked.
     * 
     * @param view
     */
    public void toOnlineStories(View view) {
	try {
	    Intent intent = new Intent(MainActivity.this,
		    OnlineStoriesActivity.class);
	    startActivity(intent);
	} catch (Exception e) {
	    e.printStackTrace();
	    if (!InternetDetector.connectedToInternet(getApplicationContext())) {
		Toast.makeText(
			getApplicationContext(),
			"Sorry, An Error Occured. Please Check Your Network And Try Again",
			Toast.LENGTH_LONG).show();
	    }else{
		Toast.makeText(
			getApplicationContext(),
			"Sorry, An Error Occured. Please Try Again.",
			Toast.LENGTH_LONG).show();
	    }
	}

    }

    /**
     * toOfflineStories creates an intent to move to OfflineStoriesActivity when
     * "Read Downloaded Stories" is clicked.
     * 
     * @param view
     */
    public void toOfflineStories(View view) {
	Intent intent = new Intent(MainActivity.this,
		OfflineStoriesActivity.class);
	startActivity(intent);
    }

    /**
     * toMyStories creates an intent to move to MyStoriesActivity when
     * "My Stories" is clicked.
     * 
     * @param view
     */
    public void toMyStories(View view) {
	Intent intent = new Intent(MainActivity.this, MyStoriesActivity.class);
	startActivity(intent);
    }

}
