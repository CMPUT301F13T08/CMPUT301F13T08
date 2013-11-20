/*
AUTHORS
========
Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen.

LICENSE
=======
Copyright  ©  2013 Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen,  
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
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.EditText;
import android.widget.ListView;

/**
 * OfflineStoriesActivity is a view class that displays a list of stories that
 * have been downloaded. Users are able to read a story in the list simply by
 * clicking on it (no internet connection required).
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 1.0 November 8, 2013
 * @since 1.0
 */

public class OfflineStoriesActivity extends ListActivity {

    public int position;
    public AdapterContextMenuInfo info;
    private static final String TAG = "OfflineStoriesActivity";

    private FileHelper fHelper;
    private ListView lv;
    private String searchText;
    private EditText et;
    private Story currentStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	setContentView(R.layout.activity_offline_stories);
	setTitle(R.string.downloaded_stories);
	lv = (ListView) findViewById(android.R.id.list);
	fHelper = new FileHelper(this, 0);
	et = (EditText) findViewById(R.id.search);

	try {
	    fillData(fHelper.getOfflineStories());
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	registerForContextMenu(getListView());

    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
	super.onListItemClick(l, v, position, id);

	/*
	 * Get the selected story object currentStory = (Story)
	 * lv.getAdapter().getItem(position); create intent to pass the selected
	 * story object and the first story fragment id to the
	 * StoryFragmentActivity
	 */
	currentStory = (Story) l.getItemAtPosition(position);
	Intent firstStoryFragment = new Intent(getApplicationContext(),
		StoryFragmentActivity.class);

	/* send the story object through the intent */
	firstStoryFragment.putExtra("story", currentStory);

	int nextStoryFragmentId = currentStory.getFirstStoryFragmentId();

	/* send the first story fragment id through the intent */
	firstStoryFragment.putExtra("storyFragmentId", nextStoryFragmentId);
	firstStoryFragment.putExtra("mode", 1);

	/*
	 * start the StoryFragmentActivity to display the first fragment of the
	 * selected story
	 */
	startActivity(firstStoryFragment);
    }

    /**
     * fillData poplates our list with a collection of stories that have been
     * downloaded. Stories found in this list can be viewed without an Internet
     * connection.
     * 
     * @param sList
     *            An Arraylist of stories used to populate the listview.
     */
    private void fillData(ArrayList<Story> sList) {
	lv.setAdapter(new StoryInfoAdapter(this, android.R.id.list, sList));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
	    Intent intent) {
	super.onActivityResult(requestCode, resultCode, intent);
	try {
	    /*
	     * Populate the listview with the online stories at the start of the
	     * activity
	     */
	    fillData(fHelper.getOfflineStories());
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    protected void onResume() {
	super.onResume();
	try {
	    /* Re-populate the listview with the online stories */
	    fillData(fHelper.getOfflineStories());
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
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

    /**
     * This method is called from a button click that allows the user to ask for
     * a random Offline Story to be viewed for their reading pleasure. It will
     * get a random story and pass this story to the next activity for
     * displaying to the user. Once this is complete the user should be
     * presented with a display of the first page of the story.
     * 
     * @param view
     *            The screen used to display the Offline Story list for the
     *            user.
     */
    public void onClickFeelingLuckButton(View view) {
	Story randomStory = null;

	/* Generate and get a random Story for the user */
	try {
	    ArrayList<Story> storyList = fHelper.getOfflineStories();
	    if (storyList.size() > 0) {
		int randomStoryIndex = StoryController.feelingLucky(storyList
			.size());
		randomStory = storyList.get(randomStoryIndex);
	    }
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	if (randomStory != null) {
	    Intent firstStoryFragment = new Intent(getApplicationContext(),
		    StoryFragmentActivity.class);
	    /*
	     * decode the Story so that the photos are returned to their normal
	     * format
	     */
	    try {
		currentStory = fHelper.decodeStory(randomStory, 0);
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
