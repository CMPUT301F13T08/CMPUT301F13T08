/**
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
-This demo was used to help with JSON and ESHelper

Retrieved Oct. 29, 2013  - http://hc.apache.org/downloads.cgi
-This is for the fluent library which is licenced under apache V2

Retrieved Oct. 29, 2013 
- https://code.google.com/p/google-gson/downloads/detail?name=google-gson-2.2.4-release.zip&can=2&q=
-This is for JSON which is licenced under apache V2
 */

package com.team08storyapp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
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
import android.widget.Toast;

public class StoryFragmentActivity extends Activity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int MODE_ONLINE = 0;
    private static final int MODE_OFFLINE = 1;
    private int currentStoryFragmentId;
    private int currentStoryFragmentIndex;
    private int currentStoryId;
    private int mode;

    // declare variables for UI setup
    private PicAdapter imgAdapt;
    private Gallery picGallery;
    private ListView lv;
    private View headerText;
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

	// Initialize esHelper
	esHelper = new ESHelper();

	// Initialize fHelper to Download mode
	fHelper = new FileHelper(this, 0);

	// set up background layout
	setContentView(R.layout.activity_story_list);
	lv = (ListView) findViewById(android.R.id.list);

	// set up text header
	headerText = getLayoutInflater().inflate(R.layout.header_text, null);
	headerText.setBackgroundColor(0x0099cc);
	TextView textSection = (TextView) headerText
		.findViewById(R.id.headerText);
	textSection.setOnTouchListener(new View.OnTouchListener() {

	    @Override
	    public boolean onTouch(View arg0, MotionEvent arg1) {
		return false;
	    }
	});
	textSection.setMovementMethod(new ScrollingMovementMethod());

	// set up gallery header
	headerGallery = getLayoutInflater().inflate(R.layout.header_gallery,
		null);

	// set up the picView
	picView = (ImageView) headerGallery.findViewById(R.id.picture);

	// get the gallery view
	picGallery = (Gallery) headerGallery.findViewById(R.id.gallery);

	// set the click listener for each item in the thumbnail gallery
	picGallery.setOnItemClickListener(new OnItemClickListener() {

	    // handle clicks
	    public void onItemClick(AdapterView<?> parent, View v,
		    int position, long id) {
		// set the larger image view to display the chosen bitmap
		// calling method of adapter class
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

	// Get the story fragment id from the intent - the fragment to display
	currentStoryFragmentId = storyFragment
		.getIntExtra("storyFragmentId", 0);
	currentStoryId = currentStory.getOfflineStoryId();

	/*
	 * get the currentStoryFragmentIndex in case an update is made on this
	 * fragment.
	 */
	for (int i = 0; i < currentStory.getStoryFragments().size(); i++) {
	    if (currentStory.getStoryFragments().get(i).getStoryFragmentId() == currentStoryFragmentId) {
		currentStoryFragmentIndex = i;
	    }
	}

	/*
	 * The current story fragment object - from the story fragment list, by
	 * id
	 */
	currentStoryFragment = StoryController.readStoryFragment(
		currentStory.getStoryFragments(), currentStoryFragmentId);

	// Display the current fragment text
	textSection.setText(currentStoryFragment.getStoryText());

	// The list of choices from the current fragment
	ArrayList<Choice> storyFragmentChoices = currentStoryFragment
		.getChoices();

	/*
	 * Populate choices listview with the go to choices from the current
	 * fragment
	 */
	ArrayList<Photo> illustrationList = currentStoryFragment.getPhotos();

	// create a new adapter based on current story fragment's information
	imgAdapt = new PicAdapter(this, illustrationList, currentStoryId,
		currentStoryFragmentId);

	// set the gallery adapter
	picGallery.setAdapter(imgAdapt);

	// populate the listview with choices
	fillChoice(storyFragmentChoices);

	// set listview item's setOnItemClickListener
	lv.setOnItemClickListener(new OnItemClickListener() {

	    // handle clicks
	    public void onItemClick(AdapterView<?> parent, View v,
		    int position, long id) {

		// prepare to start to read the next story fragment
		Intent nextStoryFragment = new Intent(getApplicationContext(),
			StoryFragmentActivity.class);

		// pass the currentStory to intent
		nextStoryFragment.putExtra("story", currentStory);

		// retrieve the selected choice object
		Choice nextChoice = (Choice) lv.getAdapter().getItem(position);

		// get the id of next story fragment
		int nextStoryFragmentId = nextChoice.getStoryFragmentID();

		// put the id in the intent
		nextStoryFragment.putExtra("storyFragmentId",
			nextStoryFragmentId);
		startActivity(nextStoryFragment);
	    }
	});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.annotation_action_bar, menu);
	return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

	// Handle item selection
	switch (item.getItemId()) {

	// when user selects "View annotations" button in action bar
	case R.id.view_annotations:
	    Intent annoIntent = new Intent(getApplicationContext(),
		    AnnotationViewActivity.class);
	    annoIntent.putExtra("Annotations",
		    currentStoryFragment.getAnnotations());
	    startActivity(annoIntent);
	    return true;

	    // when user selects "add annotations" icon in action bar
	case R.id.action_add_annotations:

	    // a popup menu asks to choose a picture from gallery or camera
	    showPopup();
	    return true;
	default:
	    return super.onOptionsItemSelected(item);
	}
    }

    public void fillChoice(ArrayList<Choice> cList) {

	// add headers to background list view
	lv.addHeaderView(headerGallery);
	lv.addHeaderView(headerText);
	ChoiceAdapter adapter = new ChoiceAdapter(this, android.R.id.list,
		cList);

	// populate the listview with choices
	lv.setAdapter(adapter);

    }

    public void showPopup() {

	// root view of popup menu
	View popUpItemView = findViewById(R.id.action_add_annotations);

	// instantiate the popup menu
	PopupMenu popupMenu = new PopupMenu(this, popUpItemView);
	MenuInflater inflater = popupMenu.getMenuInflater();
	inflater.inflate(R.menu.annotation_view, popupMenu.getMenu());

	// setup popmenu's click listener
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
			    // take the user to their chosen image selection app
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

	// show the popup menu
	popupMenu.show();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	if (resultCode == RESULT_OK) {

	    // the returned picture URI
	    Uri pickedUri = data.getData();

	    // declare the bitmap
	    Bitmap pic = null;

	    // declare the path string
	    String imgPath = "";

	    // retrieve the string using media data
	    String[] medData = { MediaStore.Images.Media.DATA };

	    // query the data
	    Cursor picCursor = managedQuery(pickedUri, medData, null, null,
		    null);
	    if (picCursor != null) {

		// get the path string
		int index = picCursor
			.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		picCursor.moveToFirst();
		imgPath = picCursor.getString(index);
	    } else
		imgPath = pickedUri.getPath();

	    // if we have a new URI attempt to decode the image bitmap
	    if (pickedUri != null) {

		/*
		 * set the width and height we want to use as maximum display
		 */
		int targetWidth = 200;
		int targetHeight = 150;

		// create bitmap options to calculate and use sample size
		BitmapFactory.Options bmpOptions = new BitmapFactory.Options();

		/*
		 * first decode image dimensions only - not the image bitmap
		 * itself
		 */
		bmpOptions.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(imgPath, bmpOptions);

		/*
		 * work out what the sample size should be image width and
		 * height before sampling
		 */
		int currHeight = bmpOptions.outHeight;
		int currWidth = bmpOptions.outWidth;

		// variable to store new sample size
		int sampleSize = 1;

		/*
		 * calculate the sample size if the existing size is larger than
		 * target size
		 */
		if (currHeight > targetHeight || currWidth > targetWidth) {
		    // use either width or height
		    if (currWidth > currHeight)
			sampleSize = Math.round((float) currHeight
				/ (float) targetHeight);
		    else
			sampleSize = Math.round((float) currWidth
				/ (float) targetWidth);
		}

		// use the new sample size
		bmpOptions.inSampleSize = sampleSize;

		// now decode the bitmap using sample options
		bmpOptions.inJustDecodeBounds = false;

		// get the file as a bitmap
		pic = BitmapFactory.decodeFile(imgPath, bmpOptions);

		// set the new file name
		String fileName = "Image"
			+ Integer.toString(currentStoryId)
			+ "Fragment"
			+ Integer.toString(currentStoryFragment
				.getStoryFragmentId())
			+ "Annotation"
			+ Integer.toString(currentStoryFragment
				.getAnnotations().size() + 1) + ".png";

		// save the new image file
		try {
		    FileOutputStream fos = openFileOutput(fileName,
			    Context.MODE_PRIVATE);
		    pic.compress(CompressFormat.PNG, 90, fos);
		} catch (FileNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}

		// compose the new annotation with the new image
		Annotation add = new Annotation();
		add.setAnnotationID(currentStoryFragment.getAnnotations()
			.size() + 1);
		add.setPhoto(fileName);
		ArrayList<Annotation> temp = currentStoryFragment
			.getAnnotations();
		temp.add(add);
		currentStoryFragment.setAnnotations(temp);

		// update the current story with the new story fragment
		currentStory.getStoryFragments().set(currentStoryFragmentIndex,
			currentStoryFragment);

		/*
		 * if the user is reading a downloaded story, then we update the
		 * local file of the story and update the story in web server.
		 * else if the user is reading an online story, we directly update
		 *  the online story.
		 */
		if (mode == MODE_OFFLINE) {
		    try {
			fHelper.updateOfflineStory(currentStory);
			currentStory = fHelper.getOfflineStory(currentStoryId);
			esHelper.addOrUpdateOnlineStory(currentStory);
		    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		} else if (mode == MODE_ONLINE) {
		    esHelper.addOrUpdateOnlineStory(currentStory);
		}
		
		// pop up a message to inform user that annotation is added
		Toast.makeText(getApplicationContext(),
			"New annotation is uploaded successfully",
			Toast.LENGTH_LONG).show();
	    }
	}

	// superclass method
	super.onActivityResult(requestCode, resultCode, data);
    }

}
