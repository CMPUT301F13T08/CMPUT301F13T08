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
	    StoryFragment sf1 = new StoryFragment(1, "	Rorschard finds out the death of Edward Blake, the famous Comedian.\n" +
	    		"	He inspected the room and want to find out the murderer. But, he needs to inform some of his" +
	    		"old friends.");
	    Choice c1 = new Choice(2, 1, "Go to Adrian Veidt, the Ozymandians.");
	    Choice c2 = new Choice(3, 2, "Go to NiteOwl, his besty back in 60s.");
	    Choice c3 = new Choice(4, 3, "Go to Dr.Manhattan, who is supposed to know everything and able to foresee everything,");
	    ArrayList<Choice> cList1 = new ArrayList<Choice>();
	    cList1.add(c1);
	    cList1.add(c2);
	    cList1.add(c3);
	    sf1.setChoices(cList1);
	    StoryFragment sf2 = new StoryFragment(2, "	Adrian is shocked by Comedian's death, 'The comedian dead? But why?'\n" +
	    		"	'You are supposed to be the smartest man in the world. You tell me.'\n" +
	    		"Adrian gives some other assumptions, the soviets, or a political killing. Rorcharch is not satisfied with " +
	    		"either of them. So he decides to leave.");
	    c1 = new Choice(3, 1, "Go to NiteOwl, his besty back in 60s");
	    ArrayList<Choice> cList2 = new ArrayList<Choice>();
	    cList2.add(c1);
	    sf2.setChoices(cList2);
	    
	    StoryFragment sf3 = new StoryFragment(3, "	NiteOwl is not home. Please come back later.");
	    ArrayList<StoryFragment> sflist = new ArrayList<StoryFragment>();
	    sflist.add(sf1);
	    sflist.add(sf2);
	    sflist.add(sf3);
	    s2.setFirstStoryFragment(1);
	    s2.setStoryFragments(sflist);
	    
	    
	    
	    
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