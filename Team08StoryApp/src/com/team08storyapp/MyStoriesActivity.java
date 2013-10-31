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
    private ListView lv;
    private Story currentStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_story_list);
	lv = (ListView) findViewById(android.R.id.list);
	footerCreate = getLayoutInflater().inflate(R.layout.footer_create_button, null);
	Button createButton = (Button) footerCreate.findViewById(R.id.createButton);

	fHelper = new FileHelper(this, 1);
	esHelper = new ESHelper();
	
	try {
	    fillData(fHelper.getOfflineStories(), onCreate);
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	/*createButton.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
		//TODO: start a create a new story activity
	});
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
	menu.add(0, PUBLISH_ID, 0, R.string.download_menu);
	menu.add(0, READ_ID, 0, R.string.read_menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
	info = (AdapterContextMenuInfo) item.getMenuInfo();
	position = info.position;
	// following 4 lines will display the information on selected item.
	Story selectedValue = (Story) lv.getAdapter().getItem(position);	
	System.out.println(selectedValue.getTitle());
	System.out.println(selectedValue.getAuthor());
	System.out.println(selectedValue.getStoryId());
	
	switch (item.getItemId()) {
	case PUBLISH_ID:
	    // TODO: call esHelper to publish
	    if(esHelper.addOnlineStory(selectedValue)){
		Toast.makeText(getApplicationContext(), 
                        "Your Story is Successfully Published", Toast.LENGTH_LONG).show();
	    }else{
		Toast.makeText(getApplicationContext(), 
                        "Publish Error. Internect Connection Error", Toast.LENGTH_LONG).show();
	    }

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
	}
	lv.setAdapter(new StoryInfoAdapter(this, android.R.id.list, sList));
    }

}