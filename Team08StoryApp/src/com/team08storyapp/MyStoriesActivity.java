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

public class MyStoriesActivity extends ListActivity {

    public int position;
    public AdapterContextMenuInfo info;

    private static final int PUBLISH_ID = Menu.FIRST;
    private static final int READ_ID = Menu.FIRST + 1;
    private static final boolean onUpdate = true;
    private static final boolean onCreate = false;

    private ESHelper esHelper;
    private FileHelper fHelper;
    private View footerCreate;
    private View header;
    private EditText et;
    private String searchText;
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
	footerCreate = getLayoutInflater().inflate(
		R.layout.footer_create_button, null);
	Button createButton = (Button) footerCreate
		.findViewById(R.id.createButton);

	fHelper = new FileHelper(this, 1);
	esHelper = new ESHelper();

	try {
	    Story s1 = new Story(11, "To the End", "Alan Cumming");
	    Story s2 = new Story(12, "Watchmen", "Alan Moore");
	    Story s3 = new Story(13, "SlaughterHouse-Five", "");
	    Story s4 = new Story(14, "Animal Farm", "George Owell");
	    Story s5 = new Story(15, "The art of computer programming",
		    "Donald Knuth");
	    Story s6 = new Story(16, "Software and hardware", "D. Patterson");
	    Story s7 = new Story(17, "Macbeth", "William Shakespeare");
	    Story s8 = new Story(18, "King Lear", "William Shakespeare");
	    Story s9 = new Story(19, "The Fall of the House of Usher",
		    "Edgar Allan Poe");
	    Story s10 = new Story(20, "Annabel Lee", "Edgar Allan Poe");
	    Story[] slist = { s1, s2, s3, s4, s5, s6, s7, s8, s9, s10 };
	    for (Story s : slist) {		
		fHelper.addOfflineStory(s);
	    }
	} catch (FileNotFoundException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	} catch (IOException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}

	try {
	    ArrayList<Story> temp = fHelper.getOfflineStories();
	    for(Story s: temp){
		System.out.println(s.getTitle());
	    }
	    fillData(temp, onCreate);
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		}

	    }
	});

	/*
	 * createButton.setOnClickListener(new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { //TODO: start a create a new
	 * story activity });
	 */

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
	menu.add(0, PUBLISH_ID, 0, R.string.publish_menu);
	menu.add(0, READ_ID, 0, R.string.read_menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
	info = (AdapterContextMenuInfo) item.getMenuInfo();
	position = info.position;
	// following 4 lines will display the information on selected item.
	Story currentStory = (Story) lv.getAdapter().getItem(position);
	System.out.println(currentStory.getTitle());
	System.out.println(currentStory.getAuthor());
	System.out.println(currentStory.getOfflineStoryId());

	switch (item.getItemId()) {
	case PUBLISH_ID:
	    // TODO: call esHelper to publish
	    
	   // try {
		esHelper.addOnlineStory(currentStory); 
	    //} catch (Exception e){
		//Toast.makeText(getApplicationContext(),
		//	"Publish Error. Internect Connection Error",
		//	Toast.LENGTH_LONG).show();
	    //}
	    
		Toast.makeText(getApplicationContext(),
			"Your Story is Successfully Published",
			Toast.LENGTH_LONG).show();

	case READ_ID:

	    // TODO: get selected story object- TODO: read the story -
	    // initialize currentStory to story object

	    // create intent to pass the selected story object and the first
	    // story fragment id to the StoryFragmentActivity
	    Intent firstStoryFragment = new Intent(getApplicationContext(),
		    StoryFragmentActivity.class);

	    // send the story object through the intent
	    firstStoryFragment.putExtra("story", (Serializable) currentStory);
	    // send the first story fragment id through the intent
	    firstStoryFragment.putExtra("storyFragmentId",
		    currentStory.getFirstStoryFragment());

	    // startActivity(firstFragment);

	default:
	    return super.onContextItemSelected(item);
	}
    }

    public void fillData(ArrayList<Story> sList, boolean update) {
	if (!update) {
	    lv.addHeaderView(footerCreate);
	    lv.addHeaderView(header);
	}
	lv.setAdapter(new StoryInfoAdapter(this, android.R.id.list, sList));
    }

}