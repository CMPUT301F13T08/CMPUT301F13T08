package com.team08storyapp;

import java.io.FileNotFoundException;
import java.io.IOException;
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

public class OnlineStoriesActivity extends ListActivity {

    // make sure to assign the "menu" thing with menu's value, otherwise
    // context menu won't respond. -- Solved I presume
    // private FileHelper fHelper; should be ESHelper

    public int position;
    public AdapterContextMenuInfo info;

    private static final int DOWNLOAD_ID = Menu.FIRST;
    private static final int READ_ID = Menu.FIRST + 1;
    private static final boolean onUpdate = true;
    private static final boolean onCreate = false;

    private FileHelper fHelper;
    private ESHelper esHelper;
    private View header;
    private String searchText;
    private EditText et;
    private ListView lv;
    private Story currentStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	setContentView(R.layout.activity_story_list);
	lv = (ListView) findViewById(android.R.id.list);
	header = getLayoutInflater().inflate(R.layout.header_search, null);
	Button searchButton = (Button) header.findViewById(R.id.searchButton);
	et = (EditText) header.findViewById(R.id.searchText);

	esHelper = new ESHelper();

	// Populate listview with the stories curently online
	// Cache the stories currently online
	fillData(esHelper.getOnlineStories(), onCreate);

	searchButton.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
		searchText = et.getText().toString();
		if (searchText != null && !searchText.isEmpty()) {
		    fillData(esHelper.searchOnlineStories(searchText), onUpdate);
		} else {
		    fillData(esHelper.getOnlineStories(), onUpdate);
		}

	    }
	});

	registerForContextMenu(getListView());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.online_stories, menu);
	return true;
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
	// following 4 lines will display the information on selected item.

	// Get the story object of the selected story item
	currentStory = (Story) lv.getAdapter().getItem(position);

	switch (item.getItemId()) {
	case DOWNLOAD_ID:

	    fHelper = new FileHelper(this, 0);
	    try {
		// Save the story to file, via FileHelper if the download option
		// selected
		Story decodedStory = fHelper.decodeStory(currentStory);
		fHelper.addOfflineStory(decodedStory);
	    } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	case READ_ID:

	    // create intent to pass the selected story object and the first
	    // story fragment id to the StoryFragmentActivity
	    // create intent to pass the selected story object and the first
	    // story fragment id to the StoryFragmentActivity
	    Intent firstStoryFragment = new Intent(getApplicationContext(),
		    StoryFragmentActivity.class);

	    // send the story object through the intent
	    firstStoryFragment.putExtra("story", currentStory);

	    int nextStoryFragmentId = currentStory.getFirstStoryFragment();

	    // send the first story fragment id through the intent
	    firstStoryFragment.putExtra("storyFragmentId", nextStoryFragmentId);

	    // start the StoryFragmentActivity to display the first fragment of
	    // the selected story
	    startActivity(firstStoryFragment);

	default:
	    return super.onContextItemSelected(item);
	}
    }

    public void fillData(ArrayList<Story> sList, boolean update) {
	if (!update) {
	    lv.addHeaderView(header);
	}
	lv.setAdapter(new StoryInfoAdapter(this, android.R.id.list, sList));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
	    Intent intent) {
	super.onActivityResult(requestCode, resultCode, intent);
	fillData(esHelper.getOnlineStories(), onUpdate);
    }

    protected void onResume() {
	super.onResume();
	fillData(esHelper.getOnlineStories(), onUpdate);

    }
}