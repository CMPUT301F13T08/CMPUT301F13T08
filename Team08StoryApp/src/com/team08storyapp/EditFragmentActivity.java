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
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class EditFragmentActivity extends Activity {
   

    private int currentStoryFragmentId;
    private int currentStoryFragmentIndex;
    private int currentStoryId;
    private int mode;
    private int currentPic;

    /* declare variables for UI setup */
    private PicAdapter imgAdapt;
    private Gallery picGallery;
    private ListView lv;
    private View headerGallery;
    private ImageView picView;
    private EditText textSection;

    private Story currentStory;
    private StoryFragment currentStoryFragment;
    private FileHelper fHelper;
    private ESHelper esHelper;
    private Uri imageFileUri;
    private PhotoController pc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_edit_fragment);
	fHelper = new FileHelper(this, 1);

	/* set up the choice listView */
	lv = (ListView) findViewById(android.R.id.list);

	/* set up the EditText Dialogue */
	textSection = (EditText) findViewById(R.id.headerDialogue);
	textSection.setOnTouchListener(new View.OnTouchListener() {
	    @Override
	    public boolean onTouch(View arg0, MotionEvent arg1) {
		return false;
	    }
	});

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

	Intent storyFragment = getIntent();

	/* Get the story object from the intent */
	currentStory = (Story) storyFragment.getSerializableExtra("story");

	/* Get the story fragment id from the intent - the fragment to display */
	currentStoryFragmentId = storyFragment
		.getIntExtra("storyFragmentId", 0);
	currentStoryId = currentStory.getOfflineStoryId();

	currentStoryFragmentIndex = 0; 
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

	/* Display the current fragment text */
	textSection.setText(currentStoryFragment.getStoryText());

	ArrayList<Choice> storyFragmentChoices = currentStoryFragment
		.getChoices();
	ArrayList<Photo> illustrationList = currentStoryFragment.getPhotos();

	pc = new PhotoController(this, getApplicationContext(), currentStory,
		currentStoryFragment, currentStoryFragmentIndex, fHelper);

	/* create a new adapter */
	imgAdapt = new PicAdapter(this, illustrationList, currentStoryId,
		currentStoryFragmentId);

	/* set the gallery adapter */
	picGallery.setAdapter(imgAdapt);
	fillChoice(storyFragmentChoices);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.edit_fragment, menu);
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	// Handle item selection
	switch (item.getItemId()) {
	case R.id.camIllus:
	    return true;

	case R.id.camGallery:
	    return true;

	case R.id.addChoice:
	    Intent intent = new Intent(EditFragmentActivity.this,
		    EditChoiceActivity.class);
	    /* pass the currentStory to intent */
	    intent.putExtra("story", currentStory);

	    /* retrieve the selected choice object */
	    //not needed
	    //int newChoiceId = currentStoryFragment.getChoices().size()+1;

	    /* put the id in the intent */
	    //not needed
	    //intent.putExtra("choiceId", newChoiceId);
	    intent.putExtra("storyFragmentIndex", currentStoryFragmentIndex);
	    startActivity(intent);
	    return true;

	case R.id.save:
	    try {
		String dialogue = textSection.getText().toString();
		currentStoryFragment.setStoryText(dialogue);
		currentStory.getStoryFragments().set(currentStoryFragmentIndex,
			currentStoryFragment);
		System.out.println(currentStory.getOfflineStoryId());
		fHelper.updateOfflineStory(currentStory);
		Toast.makeText(getApplicationContext(), "Save Successfully",
			Toast.LENGTH_LONG).show();
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    return true;

	default:
	    return super.onOptionsItemSelected(item);
	}
    }

    private void fillChoice(ArrayList<Choice> cList) {
	lv.addHeaderView(headerGallery);
	ChoiceAdapter adapter = new ChoiceAdapter(this, android.R.id.list,
		cList);
	lv.setAdapter(adapter);

    }

    protected void onResume() {
	try {
	    currentStory = fHelper.getOfflineStory(currentStoryId);
	    currentStoryFragment = currentStory.getStoryFragments().get(
		    currentStoryFragmentIndex);

	} catch (Exception e) {
	    e.printStackTrace();
	}
	super.onResume();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	if (resultCode == RESULT_OK) {
	    Uri pickedUri = data.getData();
	    Bitmap pic = pc.savePhoto(pickedUri);
	    if (pic != null) {
		currentPic = pc.currentPosition();
		if (currentPic > 4) {
		    currentPic = (currentPic % 5);
		}
		imgAdapt.addPic(currentPic, pic);
		picGallery.setAdapter(imgAdapt);
		picView.setImageBitmap(pic);
		picView.setScaleType(ImageView.ScaleType.FIT_CENTER);
	    }
	    try {
		currentStory = fHelper.getOfflineStory(currentStoryId);
		currentStoryFragment = currentStory.getStoryFragments().get(
			currentStoryFragmentIndex);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	} else {
	    super.onActivityResult(requestCode, resultCode, data);
	}
    }

}
