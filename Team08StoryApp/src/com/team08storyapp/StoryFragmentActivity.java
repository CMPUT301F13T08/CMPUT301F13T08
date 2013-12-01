/*
AUTHORS
========
Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen.

LICENSE
=======
Copyright  ��  2013 Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen,  
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
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

/**
 * StoryFragmentActivity is a view class that displays a specific fragment of
 * either and online or downloaded story. Each fragment consists of:
 * <ul>
 * <li>Illustrations
 * <li>Dialogue
 * <li>Choices
 * </ul>
 * <p>
 * Users are also able to add photo annotations to story fragments by using the
 * action bar or the settings menu.
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 1.0 November 8, 2013
 * @since 1.0
 */

@SuppressWarnings("deprecation")
public class StoryFragmentActivity extends Activity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private int currentStoryFragmentId;
    private int currentStoryFragmentIndex;
    private int currentStoryId;
    private int mode;
    private int helperMode;

    /* declare variables for UI setup */
    private PicAdapter imgAdapt;
    private Gallery picGallery;
    private ListView lv;
    private TextView textSection;
    private ImageView picView;

    private Story currentStory;
    private StoryFragment currentStoryFragment;
    private FileHelper fHelper;
    private ESHelper esHelper;
    private Uri imageFileUri;

    @Override
    public void onCreate(Bundle savedInstanceState) {

	super.onCreate(savedInstanceState);

	/* Initialize esHelper */
	esHelper = new ESHelper();

	SyncManager.sync(this);

	/* set up background layout */
	setContentView(R.layout.activity_story_fragment_view);
	lv = (ListView) findViewById(android.R.id.list);
	lv.setOnTouchListener(new OnTouchListener() {
	    @Override
	    public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
		    v.getParent().requestDisallowInterceptTouchEvent(true);
		    break;
		case MotionEvent.ACTION_UP:
		    v.getParent().requestDisallowInterceptTouchEvent(true);
		}
		v.onTouchEvent(event);
		return true;
	    }
	});

	/* set up text header */
	textSection = (TextView) findViewById(R.id.headerText);
	textSection.setOnTouchListener(new View.OnTouchListener() {

	    @Override
	    public boolean onTouch(View arg0, MotionEvent arg1) {
		return false;
	    }
	});
	textSection.setMovementMethod(new ScrollingMovementMethod());
	textSection.setOnTouchListener(new OnTouchListener() {
	    @Override
	    public boolean onTouch(View v, MotionEvent event) {
		int action = event.getAction();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
		    v.getParent().requestDisallowInterceptTouchEvent(true);
		    break;
		case MotionEvent.ACTION_UP:
		    v.getParent().requestDisallowInterceptTouchEvent(true);
		}
		v.onTouchEvent(event);
		return true;
	    }
	});

	/* set up the picView */
	picView = (ImageView) findViewById(R.id.picture);

	/* get the gallery view */
	picGallery = (Gallery) findViewById(R.id.gallery);

	/* set the click listener for each item in the thumbnail gallery */
	picGallery.setOnItemClickListener(new OnItemClickListener() {

	    /* handle clicks */
	    public void onItemClick(AdapterView<?> parent, View v,
		    int position, long id) {
		picView.setImageBitmap(imgAdapt.getPic(position));
	    }
	});

	/*
	 * Get the intent - passed either by Online/OfflineStoriesActivity or by
	 * StoryFragmentActivity
	 */
	Intent storyFragment = getIntent();

	/*
	 * detect the mode: if mode is 0: user is reading an online story. if
	 * mode is 1, user is reading a downloaded story. This mode variable is
	 * crucial because it determines the way that app stores the annotation.
	 */
	mode = storyFragment.getIntExtra("AnnotationMode", 0);
	helperMode = storyFragment.getIntExtra("FileHelperMode", 0);

	/* Initialize fHelper to Download mode */
	fHelper = new FileHelper(this, helperMode);

	/*
	 * Get the story object from the intent See source [7]
	 */
	currentStory = (Story) storyFragment.getSerializableExtra("story");
	setTitle(currentStory.getTitle());

	/* Get the story fragment id from the intent - the fragment to display */
	currentStoryFragmentId = storyFragment
		.getIntExtra("storyFragmentId", 0);
	currentStoryId = currentStory.getOfflineStoryId();

	/*
	 * Gets the currentStoryFragmentIndex in case an update is made on this
	 * fragment.
	 */
	for (int i = 0; i < currentStory.getStoryFragments().size(); i++) {
	    if (currentStory.getStoryFragments().get(i).getStoryFragmentId() == currentStoryFragmentId) {
		currentStoryFragmentIndex = i;
	    }
	}

	/*
	 * Gets the current story fragment object from the story fragment list,
	 * by id.
	 */
	currentStoryFragment = StoryController.readStoryFragment(
		currentStory.getStoryFragments(), currentStoryFragmentId);

	/* Display the current fragment text */
	textSection.setText(currentStoryFragment.getStoryText());

	/* The list of choices from the current fragment */
	ArrayList<Choice> storyFragmentChoices = currentStoryFragment
		.getChoices();

	/*
	 * Populate choices listview with the go to choices from the current
	 * fragment
	 */
	ArrayList<Photo> illustrationList = currentStoryFragment.getPhotos();

	/* create a new adapter based on current story fragment's information */
	imgAdapt = new PicAdapter(this, illustrationList, currentStoryId,
		currentStoryFragmentId);

	/* set the gallery adapter */
	picGallery.setAdapter(imgAdapt);

	/* populate the listview with choices */
	fillChoice(storyFragmentChoices);

	/* set listview item's setOnItemClickListener */
	lv.setOnItemClickListener(new OnItemClickListener() {

	    /* handle clicks */
	    public void onItemClick(AdapterView<?> parent, View v,
		    int position, long id) {

		/* prepare to start to read the next story fragment */
		Intent nextStoryFragment = new Intent(getApplicationContext(),
			StoryFragmentActivity.class);

		/* pass the currentStory to intent */
		nextStoryFragment.putExtra("story", currentStory);

		/* retrieve the selected choice object */
		Choice nextChoice = (Choice) lv.getAdapter().getItem(position);

		/* get the id of next story fragment */
		int nextStoryFragmentId = nextChoice.getStoryFragmentID();

		/* pass the mode */
		nextStoryFragment.putExtra("AnnotationMode", mode);
		nextStoryFragment.putExtra("FileHelperMode", helperMode);

		/* put the id in the intent */
		nextStoryFragment.putExtra("storyFragmentId",
			nextStoryFragmentId);
		startActivity(nextStoryFragment);
	    }
	});

	/* Random Choice button */
	Button rCButton = (Button) findViewById(R.id.button_RandomChoice);

	/*
	 * If the randomChoice attribute of the current story fragment is on
	 * then set the button to be visible
	 */
	if (currentStoryFragment.getRandomChoice() == 1) {

	    /*
	     * If the current Story fragment doesn't have any choices then
	     * button is set to be invisible
	     */
	    if (currentStoryFragment.getChoices().size() == 0) {
		rCButton.setVisibility(View.INVISIBLE);
	    }
	    rCButton.setVisibility(View.VISIBLE);
	}

	/*
	 * If the randomChoice attribute of the current story fragment is off
	 * then set the button to be invisible
	 */
	if (currentStoryFragment.getRandomChoice() == 0) {
	    rCButton.setVisibility(View.INVISIBLE);
	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

	/* Inflate the menu; this adds items to the action bar if it is present. */
	getMenuInflater().inflate(R.menu.story_fragment, menu);
	return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

	/* Handle item selection */
	switch (item.getItemId()) {

	/* when user selects "View annotations" button in action bar */
	case R.id.view_annotations:
	    Intent annoIntent = new Intent(getApplicationContext(),
		    AnnotationViewActivity.class);
	    annoIntent.putExtra("Annotations",
		    currentStoryFragment.getAnnotations());
	    startActivity(annoIntent);
	    return true;

	    /* when user selects "add annotations" icon in action bar */
	case R.id.action_add_annotations:

	    /* a pop up menu asks to choose a picture from gallery or camera */
	    showPopup();
	    return true;

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
	    BuiltInHelp.showDialog(StoryFragmentActivity.this,
		    getString(R.string.story_reading_help_title),
		    getString(R.string.story_reading_help_text));
	    return true;
	default:
	    return super.onOptionsItemSelected(item);
	}
    }

    protected void onResume() {
	SyncManager.sync(this);
	super.onResume();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	if (resultCode == RESULT_OK) {
	    saveAnnotationImage(data);
	    try {
		currentStory = fHelper.getOfflineStory(currentStoryId);
		currentStoryFragment = currentStory.getStoryFragments().get(
			currentStoryFragmentIndex);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    SyncManager.sync(this);
	} else {
	    System.out.println("Not sure what's happening!");

	    /* superclass method */
	    super.onActivityResult(requestCode, resultCode, data);
	}
    }

    /**
     * This method is called when the random choice button is clicked which
     * randomly chooses a choice for the user and takes them to the fragment
     * related to that choice. It will get a random story fragment from the list
     * of possible choices and pass this to the next activity for displaying to
     * the user. Once this is completed the user should be presented with a
     * random fragment page selected from one of the possible choices of the
     * previous fragment.
     * 
     * @param view
     *            The screen of a story fragment.
     */
    public void onClickRandomChoice(View view) {
	ArrayList<Choice> choiceList = currentStoryFragment.getChoices();

	/* chooses a random choice from the list of choices */
	int randomFragmentID = StoryController.randomChoice(choiceList);

	Intent randomStoryFragment = new Intent(getApplicationContext(),
		StoryFragmentActivity.class);
	randomStoryFragment.putExtra("story", currentStory);

	/* send the random fragment id through the intent */
	randomStoryFragment.putExtra("storyFragmentId", randomFragmentID);
	randomStoryFragment.putExtra("mode", 0);

	/*
	 * Start the StoryFragmentAvitivty to display the random fragment that
	 * was randomly chosen
	 */
	startActivity(randomStoryFragment);
    }

    /*
     * function uses the choice list passed in to populate a list view with each
     * choice's text
     */
    private void fillChoice(ArrayList<Choice> cList) {
	ChoiceAdapter adapter = new ChoiceAdapter(this, android.R.id.list,
		cList);

	/* populate the listview with choices */
	lv.setAdapter(adapter);
    }

    /*
     * This function shows up the pop up menu when adding annotations
     */
    private void showPopup() {

	/* root view of popup menu */
	View popUpItemView = findViewById(R.id.action_add_annotations);

	/* instantiate the popup menu */
	PopupMenu popupMenu = new PopupMenu(this, popUpItemView);
	MenuInflater inflater = popupMenu.getMenuInflater();
	inflater.inflate(R.menu.annotation_view, popupMenu.getMenu());

	/* setup popmenu's click listener */
	popupMenu
		.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
		    public boolean onMenuItemClick(MenuItem item) {
			switch (item.getItemId()) {
			case R.id.add_anno_camera:
			    Intent intent = new Intent(
				    MediaStore.ACTION_IMAGE_CAPTURE);
			    intent.putExtra(MediaStore.EXTRA_OUTPUT,
				    imageFileUri);
			    startActivityForResult(intent,
				    CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
			    return true;
			case R.id.add_anno_gallery:

			    /* take the user to their chosen image selection app */
			    Intent pickIntent = new Intent();
			    pickIntent.setType("image/*");
			    pickIntent.setAction(Intent.ACTION_GET_CONTENT);
			    startActivityForResult(Intent.createChooser(
				    pickIntent, "Select Picture"), 1);
			    return true;
			default:
			    return false;
			}
		    }
		});

	/* show the popup menu */
	popupMenu.show();

    }
    
    /*
     * function calls for a new annotation controller and saves the annotation in passed in data.
     */
    private void saveAnnotationImage(Intent data) {

	/* the returned picture URI */
	Uri pickedUri = data.getData();
	AnnotationController ac = new AnnotationController(this, currentStory,
		currentStoryFragment, currentStoryFragmentIndex, fHelper,
		esHelper);
	ac.savePhoto(pickedUri, mode);
    }

}
