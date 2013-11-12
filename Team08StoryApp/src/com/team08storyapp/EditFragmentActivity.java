package com.team08storyapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
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

public class EditFragmentActivity extends Activity {

    private int currentStoryFragmentId;
    private int currentStoryFragmentIndex;
    private int currentStoryId;
    private int currentPic = 0;
    private PicAdapter imgAdapt;
    private Gallery picGallery;
    private ListView lv;
    private View headerText;
    private View headerGallery;
    private ImageView picView;
    private Story currentStory;
    private StoryFragment currentStoryFragment;
    private FileHelper fHelper;
    private Uri imageFileUri;
    private PhotoController pc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_story_list);
	
	/* set up the choice listView */
	lv = (ListView) findViewById(android.R.id.list);

	/* set up the EditText Dialogue */
	headerText = getLayoutInflater()
		.inflate(R.layout.header_dialogue, null);
	EditText textSection = (EditText) headerText
		.findViewById(R.id.headerDialogue);
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

	ArrayList<Choice> storyFragmentChoices = currentStoryFragment
		.getChoices();
	ArrayList<Photo> illustrationList = currentStoryFragment.getPhotos();

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
	    startActivity(intent);
	    return true;

	case R.id.save:
	    return true;

	default:
	    return super.onOptionsItemSelected(item);
	}
    }

    private void fillChoice(ArrayList<Choice> cList) {
	lv.addHeaderView(headerGallery);
	lv.addHeaderView(headerText);
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

}
