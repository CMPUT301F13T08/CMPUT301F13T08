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

	Story s1 = new Story(1, "Fight Club", "Chuck Palahniuk");
	StoryFragment sf1 = new StoryFragment(
		1,
		"You left the airport without your suitcase. And returned home to"
			+ "an arson crime. The on-site manager asked you if you have places to stay.");
	Choice c1 = new Choice(2, 1,
		"You replied no in shock, then walked away to phone booth.");

	StoryFragment sf2 = new StoryFragment(2, "You are so careless when crossing the road. You are hit by a car" +
			"And DEAD.");
	sf1.getChoices().add(c1);
	s1.setFirstStoryFragment(1);
	s1.getStoryFragments().add(sf1);
	s1.getStoryFragments().add(sf2);
	try {
	    fHelper.addOfflineStory(s1);
	    fillData(fHelper.getOfflineStories(), onCreate);
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.offline_stories, menu);
	return true;
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
