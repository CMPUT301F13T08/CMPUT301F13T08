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
-This demo was used to help with JSON and ESHelper

Retrieved Oct. 29, 2013  - http://hc.apache.org/downloads.cgi
-This is for the fluent library which is licenced under apache V2

Retrieved Oct. 29, 2013 
- https://code.google.com/p/google-gson/downloads/detail?name=google-gson-2.2.4-release.zip&can=2&q=
-This is for JSON which is licenced under apache V2
 */

package com.team08storyapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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

	Button searchButton = (Button) header.findViewById(R.id.searchButton);
	et = (EditText) header.findViewById(R.id.searchText);

	searchButton.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
		searchText = et.getText().toString();
		if (searchText != null && searchText != "") {
		    System.out.println(searchText);
		    fillData(fHelper.searchOfflineStories(searchText), onUpdate);
		} else {
		    try {
			fillData(fHelper.getOfflineStories(), onUpdate);
		    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		}
	    }

	});
	registerForContextMenu(getListView());

    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
	super.onListItemClick(l, v, position, id);
	// following 4 lines will display the information on selected item.

	// Get the selected story object
	currentStory = (Story) lv.getAdapter().getItem(position);

	// create intent to pass the selected story object and the first story
	// fragment id to the StoryFragmentActivity
	Intent firstStoryFragment = new Intent(getApplicationContext(),
		StoryFragmentActivity.class);

	// send the story object through the intent
	firstStoryFragment.putExtra("story", currentStory);

	int nextStoryFragmentId = currentStory.getFirstStoryFragment();

	// send the first story fragment id through the intent
	firstStoryFragment.putExtra("storyFragmentId", nextStoryFragmentId);
	firstStoryFragment.putExtra("mode", 1);

	// start the StoryFragmentActivity to display the first fragment of the
	// selected story
	startActivity(firstStoryFragment);

    }

    public void fillData(ArrayList<Story> sList, boolean update) {
	if (!update) {
	    lv.addHeaderView(header);
	}
	lv.setAdapter(new StoryInfoAdapter(this, android.R.id.list, sList));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        try {
	    fillData(fHelper.getOfflineStories(), onUpdate);
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    protected void onResume() {
	super.onResume();
	try {
	    fillData(fHelper.getOfflineStories(), onUpdate);
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	
    }
}
