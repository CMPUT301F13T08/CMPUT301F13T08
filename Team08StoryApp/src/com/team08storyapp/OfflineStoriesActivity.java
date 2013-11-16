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
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
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
    private static final boolean onUpdate = true;
    private static final boolean onCreate = false;

    private FileHelper fHelper;
    private View header;
    private ListView lv;
    private String searchText;
    private EditText et;
    private Story currentStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	setContentView(R.layout.activity_story_list);
	lv = (ListView) findViewById(android.R.id.list);
	fHelper = new FileHelper(this, 0);
	header = getLayoutInflater().inflate(R.layout.header_search, null);

	/* assigns searchbutton to a button in our layout */
	Button searchButton = (Button) header.findViewById(R.id.searchButton);
	et = (EditText) header.findViewById(R.id.searchText);

	searchButton.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
		searchText = et.getText().toString();
		if (searchText != null && searchText != "") {
		    fillData(fHelper.searchOfflineStories(searchText), onUpdate);
		} else {
		    try {
			fillData(fHelper.getOfflineStories(), onUpdate);
		    } catch (FileNotFoundException e) {
			e.printStackTrace();
		    } catch (IOException e) {
			e.printStackTrace();
		    }
		}
	    }

	});

	try {
	    fillData(fHelper.getOfflineStories(), onCreate);
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
     * @param update
     *            indicates whether or not a header exists.
     */
    private void fillData(ArrayList<Story> sList, boolean update) {
	if (!update) {
	    lv.addHeaderView(header);
	}
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
	    fillData(fHelper.getOfflineStories(), onUpdate);
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
	    fillData(fHelper.getOfflineStories(), onUpdate);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }
}
