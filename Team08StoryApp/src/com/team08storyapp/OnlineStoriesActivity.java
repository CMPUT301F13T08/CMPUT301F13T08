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
import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * OnlineStoriesActivity is a view class that displays a list of stories that
 * are available online. Users are able to either download or read an online
 * story by long-clicking a story in list and selecting one of the above options
 * through a context menu.
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 1.0 November 8, 2013
 * @since 1.0
 */

public class OnlineStoriesActivity extends ListActivity {

    public int position;
    public AdapterContextMenuInfo info;

    private static final int DOWNLOAD_ID = Menu.FIRST;
    private static final int READ_ID = Menu.FIRST + 1;
    private static final String TAG = "OnlineStoriesActivity";

    private FileHelper fHelper;
    private ESHelper esHelper;
    private String searchText;
    private EditText et;
    private ListView lv;
    private Story currentStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	SyncManager.sync(this);
	setContentView(R.layout.activity_online_stories);
	lv = (ListView) findViewById(android.R.id.list);
	et = (EditText) findViewById(R.id.search);

	esHelper = new ESHelper();

	/*
	 * Populate listview with the stories currently online Cache the stories
	 * currently online
	 */
	ArrayList<Story> result = esHelper.getOnlineStories();
	boolean connected = InternetDetector
		.connectedToInternet(getApplicationContext());
	while (result == null && connected) {
	    result = esHelper.getOnlineStories();
	    connected = InternetDetector
		    .connectedToInternet(getApplicationContext());
	}
	if (!connected) {
	    Toast.makeText(getApplicationContext(), "No Internet Connection",
		    Toast.LENGTH_LONG).show();
	    finish();
	}
	fillData(result);

	registerForContextMenu(getListView());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	/*
	 * Inflate the menu; this adds items to the action bar if they are
	 * present.
	 */
	getMenuInflater().inflate(R.menu.main, menu);
	return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	if (item.getItemId() == R.id.help) {
	    /*
	     * Help option was selected by the user, display the popup dialog
	     * for the current activity.
	     */
	    BuiltInHelp help = new BuiltInHelp(OnlineStoriesActivity.this);
	    help.showDialog();
	    return true;
	}
	return super.onOptionsItemSelected(item);
    }

    public void onCreateContextMenu(ContextMenu menu, View v,
	    ContextMenuInfo menuInfo) {
	super.onCreateContextMenu(menu, v, menuInfo);
	menu.add(0, DOWNLOAD_ID, 0, R.string.download_menu);
	menu.add(0, READ_ID, 0, R.string.read_menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
	info = (AdapterContextMenuInfo) item.getMenuInfo();
	position = info.position;
	/*
	 * Get the story object of the selected story item
	 */
	currentStory = (Story) lv.getAdapter().getItem(position);

	switch (item.getItemId()) {
	case DOWNLOAD_ID:

	    fHelper = new FileHelper(this, 0);
	    Toast.makeText(getApplicationContext(), "Downloading...Please wait", Toast.LENGTH_LONG).show();
	    try {
		currentStory = esHelper.getOnlineStory(currentStory
			.getOnlineStoryId());
		currentStory = fHelper.decodeStory(currentStory, 1);
	    } catch (IOException e1) {
		e1.printStackTrace();
	    } catch (Exception e1) {
		e1.printStackTrace();
	    }
	    try {
		/*
		 * Save the story to file, via FileHelper if the download option
		 * selected
		 */
		if (fHelper.addOfflineStory(currentStory)) {
		    Toast.makeText(
			    getApplicationContext(),
			    "Selected Story is Downloaded.You have "
				    + Integer.toString(fHelper
					    .getOfflineStories().size())
				    + " stories.", Toast.LENGTH_LONG).show();
		} else {
		    Toast.makeText(
			    getApplicationContext(),
			    "Network problem. Please check your network and try again.",
			    Toast.LENGTH_LONG).show();
		}
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    return true;

	case READ_ID:

	    /*
	     * create intent to pass the selected story object and the first
	     * story fragment id to the StoryFragmentActivity create intent to
	     * pass the selected story object and the first story fragment id to
	     * the StoryFragmentActivity
	     */
	    Intent firstStoryFragment = new Intent(getApplicationContext(),
		    StoryFragmentActivity.class);

	    fHelper = new FileHelper(this, 0);
	    Toast.makeText(getApplicationContext(), "Loading...Please wait", Toast.LENGTH_LONG).show();
	    /* send the story object through the intent */
	    try {
		currentStory = esHelper.getOnlineStory(currentStory
			.getOnlineStoryId());
		currentStory = fHelper.decodeStory(currentStory, 0);
	    } catch (IOException e) {
		e.printStackTrace();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    Toast.makeText(getApplicationContext(), "Ready to read..", Toast.LENGTH_SHORT).show();
	    firstStoryFragment.putExtra("story", currentStory);

	    int nextStoryFragmentId = currentStory.getFirstStoryFragmentId();

	    /* send the first story fragment id through the intent */
	    firstStoryFragment.putExtra("storyFragmentId", nextStoryFragmentId);
	    firstStoryFragment.putExtra("mode", 0);

	    /*
	     * start the StoryFragmentActivity to display the first fragment of
	     * the selected story
	     */
	    startActivity(firstStoryFragment);

	    return true;
	default:
	    return super.onContextItemSelected(item);
	}
    }

    /**
     * fillData populates the list with a collection of online stories. These
     * stories can be read or downloaded.
     * 
     * @param sList
     *            An ArrayList of stories used to populate the listview.
     */
    private void fillData(ArrayList<Story> sList) {
	lv.setAdapter(new StoryInfoAdapter(this, android.R.id.list, sList));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
	    Intent intent) {
	super.onActivityResult(requestCode, resultCode, intent);
	/*
	 * Populate the listview with the online stories at the start of the
	 * activity
	 */
	fillData(esHelper.getOnlineStories());
    }

    @Override
    protected void onResume() {
	SyncManager.sync(this);
	super.onResume();
	ArrayList<Story> result = esHelper.getOnlineStories();
	boolean connected = InternetDetector
		.connectedToInternet(getApplicationContext());
	while (result == null && connected) {
	    result = esHelper.getOnlineStories();
	    connected = InternetDetector
		    .connectedToInternet(getApplicationContext());
	}
	if (!connected) {
	    Toast.makeText(getApplicationContext(), "No Internet Connection",
		    Toast.LENGTH_LONG).show();
	    finish();
	}
	/* Re-populate the listview with the online stories */
	fillData(result);
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
	if (searchText != null && !searchText.isEmpty()) {
	    fillData(esHelper.searchOnlineStories(searchText));
	} else {
	    fillData(esHelper.getOnlineStories());
	}
    }

    /**
     * This method is called from a button click that allows the user to ask for
     * a random Online Story to be viewed for their reading pleasure. It will
     * get a random story and pass this story to the next activity for
     * displaying to the user. Once this is complete the user should be
     * presented with a display of the first page of the story.
     * 
     * @param view
     *            The screen used to display the Online Story list for the user.
     * @throws Exception 
     */
    public void onClickFeelingLuckButton(View view){
	/* Generate and get a random Story for the user */
	ArrayList<Story> storyList = esHelper.getOnlineStories();
	if (storyList.size() > 0) {
	    int randomStoryIndex = StoryController.feelingLucky(storyList
		    .size());
	    Story randomStory = storyList.get(randomStoryIndex);
	    int storyId = randomStory.getOnlineStoryId();
	    Story rightStory = esHelper.getOnlineStory(storyId);

	    Intent firstStoryFragment = new Intent(getApplicationContext(),
		    StoryFragmentActivity.class);

	    fHelper = new FileHelper(this, 0);

	    /*
	     * decode the Story so that the photos are returned to their normal
	     * format
	     */
	    try {
		currentStory = fHelper.decodeStory(rightStory, 0);
	    } catch (IOException e) {
		Log.d(TAG, e.getLocalizedMessage());
	    } catch (Exception e) {
		Log.d(TAG, e.getLocalizedMessage());
	    }
	    firstStoryFragment.putExtra("story", currentStory);

	    int nextStoryFragmentId = currentStory.getFirstStoryFragmentId();

	    /* send the first story fragment id through the intent */
	    firstStoryFragment.putExtra("storyFragmentId", nextStoryFragmentId);
	    firstStoryFragment.putExtra("mode", 0);

	    /*
	     * start the StoryFragmentActivity to display the first fragment of
	     * the selected story
	     */
	    startActivity(firstStoryFragment);
	}
    }

}
