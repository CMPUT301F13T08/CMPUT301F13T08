/*
AUTHORS
========
Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen.

LICENSE
=======
Copyright  ���������  2013 Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen,  
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

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * StoryFragmentListActivity is a view class that displays a list of fragments
 * for a given story. Users are able to either read or edit a fragment by
 * long-clicking a fragment in the list and selecting one of the above options
 * through a context menu.
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 1.0 November 24, 2013
 * @since 1.0
 */

public class StoryFragmentListActivity extends Activity {
    private ListView lv;
    private ArrayList<StoryFragment> sfList;
    private Story currentStory;
    private int currentStoryId;
    private FileHelper fHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	SyncManager.sync(this);
	setContentView(R.layout.activity_story_fragment_list);
	lv = (ListView) findViewById(android.R.id.list);
	fHelper = new FileHelper(this, 1);

	/* Retrieve from the intent the Story and Story Fragments */
	Intent passedIntent = getIntent();
	currentStory = (Story) passedIntent.getSerializableExtra("story");
	this.setTitle(currentStory.getTitle());
	currentStoryId = currentStory.getOfflineStoryId();
	sfList = currentStory.getStoryFragments();
	lv.setAdapter(new StoryFragmentAdapter(this, android.R.id.list, sfList));

	lv.setOnItemClickListener(new OnItemClickListener() {
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view,
		    int position, long id) {
		StoryFragment sf = (StoryFragment) parent
			.getItemAtPosition(position);
		int storyFragmentId = sf.getStoryFragmentId();
		Intent intent = new Intent(StoryFragmentListActivity.this,
			EditFragmentActivity.class);
		intent.putExtra("storyFragmentId", storyFragmentId);
		intent.putExtra("story", currentStory);
		intent.putExtra("mode", 1);
		startActivity(intent);
	    }
	});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

	/*
	 * Inflate the menu; this adds items to the action bar if they are
	 * present.
	 */
	getMenuInflater().inflate(R.menu.story_fragment_list, menu);
	return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

	/* Handle item selection */
	switch (item.getItemId()) {
	case R.id.action_mainmenu:
	    Intent mainIntent = new Intent(getApplicationContext(),
		    MainActivity.class);
	    mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
		    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
	    startActivity(mainIntent);
	case R.id.help:

	    /*
	     * Help option was selected by the user, display the popup dialog
	     * for the current activity.
	     */
	    BuiltInHelp.showDialog(StoryFragmentListActivity.this,
		    getString(R.string.story_fragment_list_help_title),
		    getString(R.string.story_fragment_list_help_text));
	    return true;
	default:
	    return super.onOptionsItemSelected(item);
	}
    }

    @Override
    public void onBackPressed() {
	Intent intent = new Intent(StoryFragmentListActivity.this,
		MyStoriesActivity.class);
	startActivityForResult(intent, 1);
    }

    protected void onResume() {
	try {
	    SyncManager.sync(this);
	    currentStory = fHelper.getOfflineStory(currentStoryId);
	    sfList = currentStory.getStoryFragments();
	    lv.setAdapter(new StoryFragmentAdapter(this, android.R.id.list,
		    sfList));
	} catch (Exception e) {
	    e.printStackTrace();
	}

	super.onResume();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	if (resultCode == RESULT_OK) {
	    currentStory = (Story) data.getSerializableExtra("story");
	    sfList = currentStory.getStoryFragments();
	    lv.setAdapter(new StoryFragmentAdapter(this, android.R.id.list,
		    sfList));
	} else {
	    super.onActivityResult(requestCode, resultCode, data);
	}
    }

    /**
     * This is the clickLisenter used when the Create Story Fragment button is
     * clicked. It creates the Intent that starts the activity to move to the
     * Create/Edit Story Fragment screen.
     * 
     * @param view
     *            The Story Fragment List screen.
     * @throws Exception
     */
    public void toEditFragment(View view) throws Exception {
	StoryFragment nextStoryFragment = new StoryFragment(currentStory
		.getStoryFragments().size() + 1);
	currentStory.getStoryFragments().add(nextStoryFragment);

	/* add the story to file system */
	try {
	    fHelper.updateOfflineStory(currentStory);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	Intent intent = new Intent(getApplicationContext(),
		EditFragmentActivity.class);
	intent.putExtra("storyFragmentId",
		nextStoryFragment.getStoryFragmentId());
	intent.putExtra("story", currentStory);
	intent.putExtra("mode", 1);
	startActivity(intent);
    }
}
