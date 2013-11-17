/*
AUTHORS
========
Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen.

LICENSE
=======
Copyright  ���  2013 Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen,  
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
import android.provider.MediaStore;
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
    private int currentPic;

    /* declare variables for UI setup */
    private PicAdapter imgAdapt;
    private Gallery picGallery;
    private ListView lv;
    private ImageView picView;
    private EditText textSection;

    private Story currentStory;
    private StoryFragment currentStoryFragment;
    private FileHelper fHelper;
    private Uri imageFileUri;
    private PhotoController pc;
    private ChoiceAdapter adapter;
    private ArrayList<Choice> storyFragmentChoices;

    private final static int REQUEST_CHOICE = 0;
    private final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_edit_fragment);

	fHelper = new FileHelper(this, 1);
	
	lv = (ListView) findViewById(android.R.id.list);
	textSection = (EditText) findViewById(R.id.headerDialogue);

	/*
	 * Create a touch listener to handle scrolling of the text description
	 * section of the Edit Fragment Screen.
	 */
	textSection.setOnTouchListener(new View.OnTouchListener() {
	    @Override
	    public boolean onTouch(View arg0, MotionEvent arg1) {
		return false;
	    }
	});

	// headerGallery = getLayoutInflater().inflate(R.layout.header_gallery,
	// null);
	picView = (ImageView) findViewById(R.id.picture);
	picGallery = (Gallery) findViewById(R.id.gallery);

	/*
	 * Create a click listener to handle when the user clicks on the gallery
	 * to add a photo.
	 */
	picGallery.setOnItemClickListener(new OnItemClickListener() {
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
	 * Retrieve the Story Fragment Id passed from the Story Fragment List
	 * that the user is wishing to edit, along with the Story it is from in
	 * order to save any changes made back to the Story.
	 */
	Intent storyFragment = getIntent();
	currentStory = (Story) storyFragment.getSerializableExtra("story");
	currentStoryId = currentStory.getOfflineStoryId();
	currentStoryFragmentId = storyFragment
		.getIntExtra("storyFragmentId", 0);

	populateScreen();
    }

    private void populateScreen() {
	/*
	 * Set the index position of the Story Fragment in the Story's Array
	 * List of Story Fragments to be able to update the information at that
	 * location when finished editing the Story Fragment.
	 */
	currentStoryFragmentIndex = currentStoryFragmentId - 1;

	/*
	 * If the Story Fragment Id is less than or equal to the size of the
	 * array list of Story Fragments, then we are dealing with an editing an
	 * existing Story Fragment. Thus retrieve the Story Fragment to display
	 * the current data for editing.
	 */
	if (currentStoryFragmentId <= currentStory.getStoryFragments().size()) {
	    currentStoryFragment = StoryController.readStoryFragment(
		    currentStory.getStoryFragments(), currentStoryFragmentId);

	    /*
	     * Display the existing Story Fragment text, choices, and
	     * illustrations.
	     */
	    textSection.setText(currentStoryFragment.getStoryText());
	    storyFragmentChoices = currentStoryFragment.getChoices();
	    ArrayList<Photo> illustrationList = currentStoryFragment
		    .getPhotos();

	    pc = new PhotoController(this, getApplicationContext(),
		    currentStory, currentStoryFragment,
		    currentStoryFragmentIndex, fHelper);

	    imgAdapt = new PicAdapter(this, illustrationList, currentStoryId,
		    currentStoryFragmentId);

	    picGallery.setAdapter(imgAdapt);
	    fillChoice(storyFragmentChoices);
	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	getMenuInflater().inflate(R.menu.edit_fragment, menu);
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	// Handle item selection
	switch (item.getItemId()) {
	case R.id.camIllus:
	    Intent intent1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    intent1.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
	    startActivityForResult(intent1, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	    return true;

	case R.id.camGallery:
	    Intent pickIntent = new Intent();
	    pickIntent.setType("image/*");
	    pickIntent.setAction(Intent.ACTION_GET_CONTENT);
	    startActivityForResult(
		    Intent.createChooser(pickIntent, "Select Picture"), 200);
	    return true;

	case R.id.addChoice:
	    Intent intent = new Intent(EditFragmentActivity.this,
		    EditChoiceActivity.class);
	    intent.putExtra("story", currentStory);
	    intent.putExtra("storyFragmentIndex", currentStoryFragmentIndex);
	    startActivityForResult(intent, REQUEST_CHOICE);
	    return true;

	case R.id.save:
	    try {
		String dialogue = textSection.getText().toString();
		currentStoryFragment.setStoryText(dialogue);
		/*
		 * Replace the current fragment in the story object, to include
		 * changes made to it's text or illustrations
		 */currentStory.getStoryFragments().set(
			currentStoryFragmentIndex, currentStoryFragment);
		/*
		 * Update the current story on the file system. Permanent chenge
		 * to the current fragment
		 */
		fHelper.updateOfflineStory(currentStory);
		fHelper.appendUpdateQueue(currentStory.getOfflineStoryId());
		Toast.makeText(getApplicationContext(), "Save Successfully",
			Toast.LENGTH_SHORT).show();
		finish();
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
	adapter = new ChoiceAdapter(this, android.R.id.list, cList);
	lv.setAdapter(adapter);

    }

    protected void onResume() {
	System.out.println("On RESUME!");
	try {
	    currentStory = fHelper.getOfflineStory(currentStoryId);
	    currentStoryFragment = currentStory.getStoryFragments().get(
		    currentStoryFragmentIndex);
	    adapter.clear();
	    adapter.addAll(currentStoryFragment.getChoices());
	    adapter.notifyDataSetChanged();

	} catch (Exception e) {
	    e.printStackTrace();
	}
	super.onResume();

    }

    protected void onPause() {
	try {
	    String dialogue = textSection.getText().toString();
	    if (!dialogue.isEmpty() && dialogue != null) {
		currentStoryFragment.setStoryText(dialogue);
	    }
	    currentStory.getStoryFragments().set(currentStoryFragmentIndex,
		    currentStoryFragment);
	    fHelper.updateOfflineStory(currentStory);
	    fHelper.appendUpdateQueue(currentStory.getOfflineStoryId());
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	super.onPause();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	if (resultCode == RESULT_OK) {
	    if (requestCode == REQUEST_CHOICE) {

		currentStory = (Story) data.getSerializableExtra("story");
		currentStoryFragmentId = data.getIntExtra("storyFragmentId", 0);
		currentStoryFragment = currentStory.getStoryFragments().get(
			currentStoryFragmentIndex);
		storyFragmentChoices = currentStory.getStoryFragments()
			.get(currentStoryFragmentIndex).getChoices();

		try {
		    fHelper.updateOfflineStory(currentStory);

		} catch (Exception e) {
		    e.printStackTrace();
		}
		fHelper.appendUpdateQueue(currentStory.getOfflineStoryId());
		return;
	    }
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
		fHelper.appendUpdateQueue(currentStory.getOfflineStoryId());
	    }
	} else {
	    super.onActivityResult(requestCode, resultCode, data);
	}
    }
}
