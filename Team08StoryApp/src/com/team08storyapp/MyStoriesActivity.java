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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * MyStoriesActivity is a view class that displays a list of stories created by
 * the user. Users can view each story in the list simply by clicking on it.
 * Users are also given the option to create a new story from scratch.
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 1.0 November 8, 2013
 * @since 1.0
 */

public class MyStoriesActivity extends ListActivity {

    /* position is used to discover which list item is being selected */
    public int position;
    public AdapterContextMenuInfo info;

    private static final int PUBLISH_ID = Menu.FIRST;
    private static final int READ_ID = Menu.FIRST + 1;
    private static final int EDIT_ID = Menu.FIRST + 2;
    private ESHelper esHelper;
    private FileHelper fHelper;
    private EditText et;
    private String searchText;
    private ListView lv;
    @SuppressWarnings("unused")
    private Story currentStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_my_stories);
	lv = (ListView) findViewById(android.R.id.list);
	et = (EditText) findViewById(R.id.search);

	fHelper = new FileHelper(this, 1);
	esHelper = new ESHelper();

	SyncManager.sync(this);

	try {
	    fillData(fHelper.getOfflineStories());
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	registerForContextMenu(getListView());
    }

    /**
     * This method is called from a button click that allows the user to search
     * through the list of online Stories. They must have a search string
     * entered into the search text area. If they do not it will just return the
     * full list of online stories. If they have a search string that is
     * contained within a title or author of any online story, that story will
     * be displayed in the list for the user to chose from.
     * 
     * This method uses ElasticSearch (@link http://www.elasticsearch.org/) to
     * search the webservice for the online Stories.
     * 
     * @see ESHelper
     * 
     * @param view
     *            The screen used to display the Online Story list for the user.
     */
    public void onClickSearchButton(View view) {
	searchText = et.getText().toString();
	if (searchText != null && searchText != "") {
	    fillData(fHelper.searchOfflineStories(searchText));
	} else {
	    try {
		fillData(fHelper.getOfflineStories());
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}
    }
    
    public void onCreateContextMenu(ContextMenu menu, View v,
	    ContextMenuInfo menuInfo) {
	super.onCreateContextMenu(menu, v, menuInfo);
	menu.add(0, PUBLISH_ID, 0, R.string.publish_menu);
	menu.add(0, READ_ID, 0, R.string.read_menu);
	menu.add(0, EDIT_ID, 0, R.string.edit_menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
	info = (AdapterContextMenuInfo) item.getMenuInfo();
	position = info.position;
	Story currentStory = (Story) lv.getAdapter().getItem(position);
	switch (item.getItemId()) {
	case PUBLISH_ID:
	    /*
	     * This case handles publishing a story online by passing a story
	     * object to esHelper to be pushed online.
	     */
	    if (InternetDetector.connectedToInternet(getApplicationContext())) {
		try {
		    Encoder encoder = new Encoder(this);
		    Story encodedStory = encoder.encodeStory(currentStory);
		    if (currentStory.getOnlineStoryId() > 0) {
			esHelper.addOrUpdateOnlineStory(encodedStory);

			Toast.makeText(getApplicationContext(),
				"Your Story is Successfully Published",
				Toast.LENGTH_LONG).show();
			return true;
		    } else {
			currentStory.setOnlineStoryId(esHelper
				.addOrUpdateOnlineStory(encodedStory));

			Toast.makeText(getApplicationContext(),
				"Your Story is Successfully Published",
				Toast.LENGTH_LONG).show();
		    }
		} catch (Exception e) {
		    Toast.makeText(getApplicationContext(), "Publish Error",
			    Toast.LENGTH_LONG).show();
		    return true;
		}
	    } else {
		Toast.makeText(getApplicationContext(),
			"Network Error. Please Check Your Network Connection",
			Toast.LENGTH_LONG).show();
	    }
	case EDIT_ID:

	    Intent intent = new Intent(MyStoriesActivity.this,
		    StoryFragmentListActivity.class);
	    intent.putExtra("story", currentStory);
	    startActivity(intent);

	    return true;

	case READ_ID:

	    /*
	     * This case creates an intent to pass the selected story object and
	     * the first story fragment id to the StoryFragmentActivity
	     */

	    Intent firstStoryFragment = new Intent(getApplicationContext(),
		    StoryFragmentActivity.class);
	    firstStoryFragment.putExtra("story", (Serializable) currentStory);
	    firstStoryFragment.putExtra("storyFragmentId",
		    currentStory.getFirstStoryFragmentId());
	    firstStoryFragment.putExtra("AnnotationMode", 2);
	    firstStoryFragment.putExtra("FileHelperMode", 1);
	    startActivity(firstStoryFragment);
	    return true;

	default:
	    return super.onContextItemSelected(item);
	}
    }

    /**
     * This method populates the list view with a list of My Stories.
     * 
     * @param sList
     *            An ArrayList of stories used to populate the list.
     * @param update
     *            A flag that indicates if a footer/header already exists.
     */
    private void fillData(ArrayList<Story> sList) {
	lv.setAdapter(new StoryInfoAdapter(this, android.R.id.list, sList));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
	    Intent intent) {
	super.onActivityResult(requestCode, resultCode, intent);
	try {
	    fillData(fHelper.getOfflineStories());
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void toNewStoryActivity(View view) {
	Intent intent = new Intent(MyStoriesActivity.this,
		NewStoryActivity.class);
	startActivity(intent);
    }

    @Override
    public void onBackPressed() {
	Intent intent = new Intent(MyStoriesActivity.this, MainActivity.class);
	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
		| Intent.FLAG_ACTIVITY_CLEAR_TASK);
	startActivity(intent);
	return;

    }

    protected void onResume() {
	super.onResume();
	try {
	    fillData(fHelper.getOfflineStories());
	    SyncManager.sync(this);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
