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
	    Story s1 = new Story(11, "Los Santos", "Alice Wu");
	    ArrayList<StoryFragment> sfList = new ArrayList<StoryFragment>();
	    StoryFragment sf1 = new StoryFragment(1, "	This is the city, where sins grow, profits expand, people gets colder," +
	    		" and Michael" +
	    		"De Santa, the retired criminal wanted to start his new life. \n    But this comes to an end, when he finds" +
	    		"out his son, Jimmy, is set up in a credit card fraud by a local dealership. \n    The rage occupies him, leading him" +
	    		"to teach that manager a 'lesson'" );
	    ArrayList<Choice> cList = new ArrayList<Choice>();
	    Choice c1 = new Choice(2, 1, "Use the car Jimmy bought from the dealership to crash into the dealership.");
	    Choice c2 = new Choice(3, 2, "Wait for someone from dealdership to 'steal' Jimmy's car and scares that someone to crahs into the dealership.");
	    cList.add(c2);
	    cList.add(c1);
	    sf1.setChoices(cList);
	    sfList.add(sf1);
	    
	    StoryFragment sf2 = new StoryFragment(2, " Jimmy's Range Rover is drove right through the glass wall, crashing a brand new display Aston Martin DB9 " +
	    		"into the manager's office.\n" +
	    		"    The manager is shocked and furious, trying to started a friendly conversation. However the conversation is started by Michael's full fist and ended by " +
	    		"a hit of a bat.\n    The manager lies on the ground, bleeading and moaning.\n" +
	    		"    'I hope you'll enjoy your coma.', Michael hits the manager one last time. However he didn't notice there's a young fellow behind him." +
	    		"    'Hey man, you know, I'm responsible for that 'deal'. You may get me fired for this!', the young, confuse-looking black man said.\n" +
	    		"    'Why don't you come to work for me?', leaves the young man with his card, Michael walks away." );
	    cList = new ArrayList<Choice>();
	    c1 = new Choice(4, 1, "The young man calls Michael right away..");
	    cList.add(c1);
	    sf2.setChoices(cList);
	    sfList.add(sf2);
	    
	    StoryFragment sf3 = new StoryFragment(3, "   'Hey young man, keep driving, we are going to do something crazy.',pointing the gun right at the driver's righ temple, " +
	    		"Michael says and smirks.\n" +
	    		"    'Easy, easy, may I know what's going on bro?'.\n"+
	    		"    'Absolutely, drive to the dealership, and then crash it! You got a problem with that?'\n" +
	    		"    'Not at all, you've got the gun,'");
	    cList = new ArrayList<Choice>();
	    c1 = new Choice(2, 1, "The driver follows Michael's instruction and drice right to the dealership.");
	    cList.add(c1);
	    sf3.setChoices(cList);
	    sfList.add(sf3);
	    
	    StoryFragment sf4 = new StoryFragment(4, "    'Hey, this is Michael, leave a message I'll call you back, if I want.'" );
	    sfList.add(sf4);
	    s1.setFirstStoryFragment(1);
	    s1.setStoryFragments(sfList);
	    
	    
	    fHelper.addOfflineStory(s1);

	} catch (FileNotFoundException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	} catch (IOException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}

	try {
	    ArrayList<Story> temp = fHelper.getOfflineStories();
	    for (Story s : temp) {
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

	switch (item.getItemId()) {
	case PUBLISH_ID:
	    // TODO: call esHelper to publish
	    try {
		Story encodedStory = fHelper.encodeStory(currentStory);
		if (currentStory.getOnlineStoryId() > 0) {
		    esHelper.addOrUpdateOnlineStory(encodedStory);

		    Toast.makeText(getApplicationContext(),
			    "Your Story is Successfully Published",
			    Toast.LENGTH_LONG).show();
		} else {
		    currentStory.setOnlineStoryId(esHelper
			    .addOrUpdateOnlineStory(encodedStory));

		    Toast.makeText(getApplicationContext(),
			    "Your Story is Successfully Published",
			    Toast.LENGTH_LONG).show();
		}
	    } catch (Exception e) {
		Toast.makeText(getApplicationContext(),
			"Your Story is Successfully Published",
			Toast.LENGTH_LONG).show();
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
	    lv.addHeaderView(header);
	}
	lv.setAdapter(new StoryInfoAdapter(this, android.R.id.list, sList));
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
	super.onListItemClick(l, v, position, id);
	// following 4 lines will display the information on selected item.

	// Get the selected story object
	currentStory = (Story) lv.getAdapter().getItem(position);

	// create intent to pass the selected story object and the first story
	// fragment id to the StoryFragmentActivity
	Intent firstStoryFragment = new Intent(getApplicationContext(),
		MyStoryFragmentActivity.class);

	// send the story object through the intent
	firstStoryFragment.putExtra("story", currentStory);

	int nextStoryFragmentId = currentStory.getFirstStoryFragment();

	// send the first story fragment id through the intent
	firstStoryFragment.putExtra("storyFragmentId", nextStoryFragmentId);

	// start the StoryFragmentActivity to display the first fragment of the
	// selected story
	startActivity(firstStoryFragment);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
	    Intent intent) {
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