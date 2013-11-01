package com.team08storyapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class OnlineStoriesActivity extends ListActivity {

    // make sure to assign the "menu" thing with menu's value, otherwise
    // context menu won't respond.
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

    private Story initializeSampleStory() {
	Story sampleStory = new Story("The Walk Again", "Michele Paulichuk");
	sampleStory.setOnlineStoryId(1);
	//System.out.println("STORYYYYYY IDDDDDD");
	//System.out.println(sampleStory.getOnlineStoryId());
	ArrayList<StoryFragment> storyFragmentList = new ArrayList<StoryFragment>();
	//ArrayList<Choice> choices = new ArrayList<Choice>();

	// Story Fragment 1
	sampleStory.setFirstStoryFragment(1);
	StoryFragment storyFragment1 = new StoryFragment(1);
	storyFragment1
		.setStoryText("Like any other day, Amara decided to take her dog out for a walk. She left the house and exited the yard.");
	ArrayList<Choice> choices1 = new ArrayList<Choice>();
	choices1.add(new Choice(2, 1,
		"Amara turned south heading for her friend�s house."));
	choices1.add(new Choice(7, 2,
		"Amara turned north heading for the store."));
	choices1.add(new Choice(3, 3,
		"Amara turned east heading for the river valley."));
	storyFragment1.setChoices(choices1);
	storyFragmentList.add(storyFragment1);

	// Story Fragment 2
	StoryFragment storyFragment2 = new StoryFragment(2);
	storyFragment2
		.setStoryText("As Amara walked toward her friend�s house her dog started acting up. It pulled this way and that way. Then it stopped and started barking at a bush.");
	ArrayList<Choice> choices2 = new ArrayList<Choice>();
	choices2.add(new Choice(4, 1, "In the bush, Amara found a small boy."));
	choices2.add(new Choice(5, 2,
		"In the bush, Amara found a hundred dollar bill."));
	storyFragment2.setChoices(choices2);
	storyFragmentList.add(storyFragment2);

	// Story Fragment 3
	StoryFragment storyFragment3 = new StoryFragment(3);
	storyFragment3
		.setStoryText("Amara gets to the river valley and decides to take a path she has yet to explore. As Amara walks along she comes across a cave in the side of the valley.");
	ArrayList<Choice> choices3 = new ArrayList<Choice>();
	choices3.add(new Choice(8, 1,
		"Amara decides to take a peek in the cave."));
	choices3.add(new Choice(9, 2,
		"Amara thinks the cave is creepy and continues walking."));
	storyFragment3.setChoices(choices3);
	storyFragmentList.add(storyFragment3);

	// Story Fragment 4
	StoryFragment storyFragment4 = new StoryFragment(4);
	storyFragment4
		.setStoryText("At first Amara was startled by the boy. Then she realized he was crying softly. So she asked the boy what�s wrong. The boy was lost.");
	ArrayList<Choice> choices4 = new ArrayList<Choice>();
	choices4.add(new Choice(10, 1,
		"Amara decides to help the boy find his way home."));
	choices4.add(new Choice(11, 2, "Amara is uncertain what to do."));
	storyFragment4.setChoices(choices4);
	storyFragmentList.add(storyFragment4);

	// Story Fragment 5
	StoryFragment storyFragment5 = new StoryFragment(5);
	storyFragment5
		.setStoryText("Amara picked up the bill and looked around. There was no one around and therefore anyone to claim the money. So Amara decided to keep the bill and continue on to her friend�s house.");
	ArrayList<Choice> choices5 = new ArrayList<Choice>();
	choices5.add(new Choice(6, 1, "Continue"));
	storyFragment5.setChoices(choices5);
	storyFragmentList.add(storyFragment5);

	// Story Fragment 6
	StoryFragment storyFragment6 = new StoryFragment(6);
	storyFragment6
		.setStoryText("When Amara got to her friend�s house she explained her excitement at finding one hundred dollars in a bush, all thanks to her dog. Her friend suggests she should reward her dog a treat with part of the money.");
	ArrayList<Choice> choices6 = new ArrayList<Choice>();
	choices6.add(new Choice(7, 1, "Continue"));
	storyFragment6.setChoices(choices6);
	storyFragmentList.add(storyFragment6);

	// Story Fragment 7
	StoryFragment storyFragment7 = new StoryFragment(7);
	storyFragment7
		.setStoryText("Amara heads to the store and buys her dog a big juicy bone. After which, they head home for a nap. THE END");
	storyFragmentList.add(storyFragment7);

	// Story Fragment 8
	StoryFragment storyFragment8 = new StoryFragment(8);
	storyFragment8
		.setStoryText("Amara steps towards the cave, as a little boy crawls out of it.");
	ArrayList<Choice> choices8 = new ArrayList<Choice>();
	choices8.add(new Choice(4, 1, "Continue"));
	storyFragment8.setChoices(choices8);
	storyFragmentList.add(storyFragment8);

	// Story Fragment 9
	StoryFragment storyFragment9 = new StoryFragment(9);
	storyFragment9
		.setStoryText("Amara walks past the cave in a hurry. She notices the clouds have formed into rain clouds and decides it�s time to head home. THE END");
	storyFragmentList.add(storyFragment9);

	// Story Fragment 10
	StoryFragment storyFragment10 = new StoryFragment(10);
	storyFragment10
		.setStoryText("The boy says he knows his phone number and his mom is home but has no way to call her. Amara pulls out her cell and dials the number. She explains she found the boy and where she is.");
	ArrayList<Choice> choices9 = new ArrayList<Choice>();
	choices9.add(new Choice(12, 1, "Continue"));
	storyFragment10.setChoices(choices9);
	storyFragmentList.add(storyFragment10);

	// Story Fragment 11
	StoryFragment storyFragment11 = new StoryFragment(11);
	storyFragment11
		.setStoryText("As Amara and the boy stand there, they hear a voice calling the name Timothy. The boy stops his crying and starts yelling Momma. The boy�s mother comes rushing up and is relieved to find her missing son. You decide you have enough excitement for one walk and turn to head home for a nap. THE END");
	storyFragmentList.add(storyFragment11);

	// Story Fragment 12
	StoryFragment storyFragment12 = new StoryFragment(12);
	storyFragment12
		.setStoryText("The boy�s mother arrives shortly and is relieved to find her son. As a reward for finding her son, she invites to take you out for ice cream with them. You decide to abandon your walk and take her offer up. THE END");
	storyFragmentList.add(storyFragment12);

	sampleStory.setStoryFragments(storyFragmentList);
	return sampleStory;
    }
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_story_list);
	lv = (ListView) findViewById(android.R.id.list);
	header = getLayoutInflater().inflate(R.layout.header_search, null);
	Button searchButton = (Button) header.findViewById(R.id.searchButton);
	et = (EditText) header.findViewById(R.id.searchText);

	esHelper = new ESHelper();
	
	// to test the Download:
	//Story sampleStory = initializeSampleStory();
	//esHelper.addOnlineStory(sampleStory);
	
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

	currentStory = (Story) lv.getAdapter().getItem(position);
	//System.out.println(currentStory.getTitle());
	//System.out.println(currentStory.getAuthor());
	//System.out.println(currentStory.getOnlineStoryId());


	switch (item.getItemId()) {
	case DOWNLOAD_ID:
	    // TODO: save selected story (currentStory) to file

	    fHelper = new FileHelper(this, 0);
	    try {
		fHelper.addOfflineStory(currentStory);
	    } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    
	    
	case READ_ID:

	    // TODO: get selected story object - TODO: read the story -
	    // initialize currentStory to story object

	    // create intent to pass the selected story object and the first
	    // story fragment id to the StoryFragmentActivity
	    // create intent to pass the selected story object and the first story fragment id to the StoryFragmentActivity
	        Intent firstStoryFragment = new Intent(getApplicationContext(), StoryFragmentActivity.class);		            
	    
	        // send the story object through the intent
	        firstStoryFragment.putExtra("story", currentStory);
	        
	       
	        // send the first story fragment id through the intent
	        int nextStoryFragmentId = currentStory.getFirstStoryFragment();
	        

	        firstStoryFragment.putExtra("storyFragmentId", nextStoryFragmentId);
	   
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

}