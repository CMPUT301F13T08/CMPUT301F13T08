/*
AUTHORS
========
Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen.

LICENSE
=======
Copyright  �  2013 Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen,  
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

/**
 * MyStoriesActivity is a view class that displays a list of stories created by
 * the user. Users can view each story in the list simply by clicking on it.
 * Users are also given the option to create a new story from scratch.
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 1.0 November 8, 2013
 * @since 1.0
 */

public class MyStoriesActivity extends ListActivity {

    /* position is used to discover which list item is being selected */
    public int position;
    public AdapterContextMenuInfo info;

    private static final int PUBLISH_ID = Menu.FIRST;
    private static final int READ_ID = Menu.FIRST + 1;
    private static final int EDIT_ID = Menu.FIRST + 2;
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
	setContentView(R.layout.activity_my_stories);
	lv = (ListView) findViewById(android.R.id.list);
	header = getLayoutInflater().inflate(R.layout.header_search, null);
	Button searchButton = (Button) header.findViewById(R.id.searchButton);
	et = (EditText) header.findViewById(R.id.searchText);
	footerCreate = getLayoutInflater().inflate(
		R.layout.footer_create_button, null);

	fHelper = new FileHelper(this, 1);
	esHelper = new ESHelper();

	/*
	 * The following code will not be present for part 4, but is necessary
	 * to auto-generate a story in My Stories for part 3 demo.
	 */
	try {
	    if (fHelper.getOfflineStory(11) == null) {
		Story s1 = new Story(11, "Los Santos", "Alice Wu");
		ArrayList<StoryFragment> sfList = new ArrayList<StoryFragment>();
		StoryFragment sf1 = new StoryFragment(
			1,
			"	This is the city, where sins grow, profits expand, people gets colder,"
				+ " and Michael"
				+ "De Santa, the retired criminal wanted to start his new life. \n    But this comes to an end, when he finds"
				+ "out his son, Jimmy, is set up in a credit card fraud by a local dealership. \n    The rage occupies him, leading him"
				+ "to teach that manager a 'lesson'");
		ArrayList<Choice> cList = new ArrayList<Choice>();
		Choice c1 = new Choice(2, 1,
			"Use the car Jimmy bought from the dealership to crash into the dealership.");
		Choice c2 = new Choice(
			3,
			2,
			"Wait for someone from dealdership to 'steal' Jimmy's car and scares that someone to crahs into the dealership.");
		cList.add(c2);
		cList.add(c1);
		sf1.setChoices(cList);
		sfList.add(sf1);
		s1.setOnlineStoryId(7);

		StoryFragment sf2 = new StoryFragment(
			2,
			" Jimmy's Range Rover is drove right through the glass wall, crashing a brand new display Aston Martin DB9 "
				+ "into the manager's office.\n"
				+ "    The manager is shocked and furious, trying to started a friendly conversation. However the conversation is started by Michael's full fist and ended by "
				+ "a hit of a bat.\n    The manager lies on the ground, bleeading and moaning.\n"
				+ "    'I hope you'll enjoy your coma.', Michael hits the manager one last time. However he didn't notice there's a young fellow behind him."
				+ "    'Hey man, you know, I'm responsible for that 'deal'. You may get me fired for this!', the young, confuse-looking black man said.\n"
				+ "    'Why don't you come to work for me?', leaves the young man with his card, Michael walks away.");
		cList = new ArrayList<Choice>();
		c1 = new Choice(4, 1,
			"The young man calls Michael right away..");
		cList.add(c1);
		sf2.setChoices(cList);
		sfList.add(sf2);

		StoryFragment sf3 = new StoryFragment(
			3,
			"   'Hey young man, keep driving, we are going to do something crazy.',pointing the gun right at the driver's righ temple, "
				+ "Michael says and smirks.\n"
				+ "    'Easy, easy, may I know what's going on bro?'.\n"
				+ "    'Absolutely, drive to the dealership, and then crash it! You got a problem with that?'\n"
				+ "    'Not at all, you've got the gun,'");
		cList = new ArrayList<Choice>();
		c1 = new Choice(2, 1,
			"The driver follows Michael's instruction and drice right to the dealership.");
		cList.add(c1);
		sf3.setChoices(cList);
		sfList.add(sf3);

		StoryFragment sf4 = new StoryFragment(4,
			"    'Hey, this is Michael, leave a message I'll call you back, if I want.'");
		sfList.add(sf4);
		s1.setFirstStoryFragmentId(1);
		s1.setStoryFragments(sfList);

		fHelper.addOfflineStory(s1);
	    }

	} catch (FileNotFoundException e1) {
	    e1.printStackTrace();
	} catch (IOException e1) {
	    e1.printStackTrace();
	}

	try {
	    ArrayList<Story> temp = fHelper.getOfflineStories();
	    for (Story s : temp) {
		System.out.println(s.getTitle());
	    }
	    fillData(temp, onCreate);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
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
			e.printStackTrace();
		    } catch (IOException e) {
			e.printStackTrace();
		    }
		}

	    }
	});

	/*
	 * This commented code will be used in Part 4.
	 * createButton.setOnClickListener(new View.OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { //TODO: start a create a new
	 * story activity });
	 */

	registerForContextMenu(getListView());
    }

    public void onCreateContextMenu(ContextMenu menu, View v,
	    ContextMenuInfo menuInfo) {
	super.onCreateContextMenu(menu, v, menuInfo);
	menu.add(0, PUBLISH_ID, 0, R.string.publish_menu);
	menu.add(0, READ_ID, 0, R.string.read_menu);
	menu.add(0, EDIT_ID, 0, R.string.edit_menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
	info = (AdapterContextMenuInfo) item.getMenuInfo();
	position = info.position;
	Story currentStory = (Story) lv.getAdapter().getItem(position);
	switch (item.getItemId()) {
	case PUBLISH_ID:
	    /*
	     * This case handles publishing a story online by passing a story
	     * object to esHelper to be pushed online.
	     */
	    try {
		Story encodedStory = fHelper.encodeStory(currentStory);
		if (currentStory.getOnlineStoryId() > 0) {
		    esHelper.addOrUpdateOnlineStory(encodedStory);

		    Toast.makeText(getApplicationContext(),
			    "Your Story is Successfully Published",
			    Toast.LENGTH_LONG).show();
		    return true;
		} else {
		    currentStory.setOnlineStoryId(esHelper
			    .addOrUpdateOnlineStory(encodedStory));

		    Toast.makeText(getApplicationContext(),
			    "Your Story is Successfully Published",
			    Toast.LENGTH_LONG).show();
		}
	    } catch (Exception e) {
		Toast.makeText(getApplicationContext(), "Publish Error",
			Toast.LENGTH_LONG).show();
		return true;
	    }
	case EDIT_ID:

	    Intent intent = new Intent(MyStoriesActivity.this,
		    StoryFragmentListActivity.class);
	    intent.putExtra("story", currentStory);
	    System.out.println(currentStory.getStoryFragments().size());
	    startActivity(intent);

	    return true;

	case READ_ID:

	    /*
	     * This case creates an intent to pass the selected story object and
	     * the first story fragment id to the StoryFragmentActivity
	     */

	    Intent firstStoryFragment = new Intent(getApplicationContext(),
		    StoryFragmentActivity.class);
	    firstStoryFragment.putExtra("story", (Serializable) currentStory);
	    firstStoryFragment.putExtra("storyFragmentId",
		    currentStory.getFirstStoryFragmentId());
	    firstStoryFragment.putExtra("mode", 2);
	    startActivity(firstStoryFragment);
	    return true;

	default:
	    return super.onContextItemSelected(item);
	}
    }

    /**
     * This method populates the list view with a list of My Stories.
     * 
     * @param sList
     *            An ArrayList of stories used to populate the list.
     * @param update
     *            A flag that indicates if a footer/header already exists.
     */
    private void fillData(ArrayList<Story> sList, boolean update) {
	if (!update) {
	    // lv.addHeaderView(footerCreate);
	    lv.addHeaderView(header);
	}
	lv.setAdapter(new StoryInfoAdapter(this, android.R.id.list, sList));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
	    Intent intent) {
	super.onActivityResult(requestCode, resultCode, intent);
	try {
	    fillData(fHelper.getOfflineStories(), onUpdate);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void toNewStoryActivity(View view) {
	Intent intent = new Intent(MyStoriesActivity.this,
		NewStoryActivity.class);
	startActivity(intent);
    }

    protected void onResume() {
	super.onResume();
	try {
	    fillData(fHelper.getOfflineStories(), onUpdate);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}