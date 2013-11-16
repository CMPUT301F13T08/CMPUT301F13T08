/*
AUTHORS
========
Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen.

LICENSE
=======
Copyright  ©  2013 Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen,  
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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

public class StoryFragmentActivity extends Activity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private int currentStoryFragmentId;
    private int currentStoryFragmentIndex;
    private int currentStoryId;
    private int mode;

    /* declare variables for UI setup */
    private PicAdapter imgAdapt;
    private Gallery picGallery;
    private ListView lv;
    private TextView textSection;
    private View headerGallery;
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

	/* Initialize fHelper to Download mode */
	fHelper = new FileHelper(this, 0);

	/* set up background layout */
	setContentView(R.layout.activity_story_fragment_view);
	lv = (ListView) findViewById(android.R.id.list);

	/* set up text header */
	textSection = (TextView) findViewById(R.id.headerText);
	System.out.println("FOUND");
	textSection.setOnTouchListener(new View.OnTouchListener() {

	    @Override
	    public boolean onTouch(View arg0, MotionEvent arg1) {
		return false;
	    }
	});
	textSection.setMovementMethod(new ScrollingMovementMethod());

	/* set up gallery header */
	headerGallery = getLayoutInflater().inflate(R.layout.header_gallery,
		null);

	/* set up the picView */
	picView = (ImageView) headerGallery.findViewById(R.id.picture);

	/* get the gallery view */
	picGallery = (Gallery) headerGallery.findViewById(R.id.gallery);

	/* set the click listener for each item in the thumbnail gallery */
	picGallery.setOnItemClickListener(new OnItemClickListener() {

	    /* handle clicks */
	    public void onItemClick(AdapterView<?> parent, View v,
		    int position, long id) {
		/*
		 * set the larger image view to display the chosen bitmap
		 * calling method of adapter class
		 */
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
	mode = storyFragment.getIntExtra("mode", 0);

	/*
	 * Get the story object from the intent See source [7]
	 */
	currentStory = (Story) storyFragment.getSerializableExtra("story");

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
	
	System.out.println(currentStoryFragmentId);
	System.out.println(currentStory.getStoryFragments().size());
	System.out.println(currentStory.getStoryFragments().get(0).getStoryText());


	/*
	 * Gets the current story fragment object from the story fragment list,
	 * by id.
	 */
	currentStoryFragment = StoryController.readStoryFragment(
		currentStory.getStoryFragments(), currentStoryFragmentId);
	System.out.println(currentStoryFragment == null);

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
		nextStoryFragment.putExtra("mode", mode);

		/* put the id in the intent */
		nextStoryFragment.putExtra("storyFragmentId",
			nextStoryFragmentId);
		startActivity(nextStoryFragment);
	    }
	});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

	/* Inflate the menu; this adds items to the action bar if it is present. */
	getMenuInflater().inflate(R.menu.annotation_action_bar, menu);
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

	    /* a popup menu asks to choose a picture from gallery or camera */
	    showPopup();
	    return true;

	case R.id.action_mainmenu:
	    Intent mainIntent = new Intent(getApplicationContext(),
		    MainActivity.class);
	    startActivity(mainIntent);
	default:
	    return super.onOptionsItemSelected(item);
	}
    }

    /**
     * @param cList
     */
    private void fillChoice(ArrayList<Choice> cList) {

	/* add headers to background list view */
	lv.addHeaderView(headerGallery);
	ChoiceAdapter adapter = new ChoiceAdapter(this, android.R.id.list,
		cList);

	/* populate the listview with choices */
	lv.setAdapter(adapter);

    }

    /**
     * 
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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	if (resultCode == RESULT_OK) {
	    /* the returned picture URI */
	    Uri pickedUri = data.getData();
	    AnnotationController ac = new AnnotationController(this,
		    getApplicationContext(), currentStory,
		    currentStoryFragment, currentStoryFragmentIndex, fHelper,
		    esHelper);
	    ac.savePhoto(pickedUri, mode);
	    try {
		currentStory = fHelper.getOfflineStory(currentStoryId);
		currentStoryFragment = currentStory.getStoryFragments().get(
			currentStoryFragmentIndex);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	} else {
	    System.out.println("Not sure what's happening!");

	    /* superclass method */
	    super.onActivityResult(requestCode, resultCode, data);
	}
    }

}
