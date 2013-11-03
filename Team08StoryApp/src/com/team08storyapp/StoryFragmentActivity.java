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
//variable for selection intent
//variable to store the currently selected image
//adapter for gallery view
//gallery object
//image view for larger display

/**
 * instantiate the interactive gallery
 */
public class StoryFragmentActivity extends Activity {
    private static final int SELECT_PHOTO = 100;
    private final static int VIEW_ANNOTATION = Menu.FIRST;
    private final static int ADD_ANNOTATION = Menu.FIRST + 1;
    private final static int MAIN_MENU = Menu.FIRST + 2;

    private int currentStoryFragmentId;
    private int currentStoryFragmentIndex;
    private int currentStoryId;

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

    private final int PICKER = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {

	super.onCreate(savedInstanceState);
	esHelper = new ESHelper();
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

	// Get the intent - passed either by Online/OfflineStoriesActivity or by
	// StoryFragmentActivity

	Intent storyFragment = getIntent();

	// Get the story object from the intent
	// See source [7]
	currentStory = (Story) storyFragment.getSerializableExtra("story");
	// Get the story fragment id from the intent - the fragment to display
	System.out.println(currentStory.toString());
	currentStoryFragmentId = storyFragment
		.getIntExtra("storyFragmentId", 0);
	currentStoryId = currentStory.getOfflineStoryId();

	for (int i = 0; i < currentStory.getStoryFragments().size(); i++) {
	    if (currentStory.getStoryFragments().get(i).getStoryFragmentId() == currentStoryFragmentId) {
		currentStoryFragmentIndex = i;
	    }
	}
	System.out.println("storyFragment index: " + currentStoryFragmentIndex);

	// The current story fragment object - from the story fragment list, by
	// id
	currentStoryFragment = StoryController.readStoryFragment(
		currentStory.getStoryFragments(), currentStoryFragmentId);

	// Display the current fragment text
	textSection.setText(currentStoryFragment.getStoryText());

	// The list of choices from the current fragment
	ArrayList<Choice> storyFragmentChoices = currentStoryFragment
		.getChoices();

	// Populate choices listview with the go to choices from the current
	// fragment
	ArrayList<Photo> illustrationList = currentStoryFragment.getPhotos();
	// create a new adapter
	imgAdapt = new PicAdapter(this, illustrationList, currentStoryId,
		currentStoryFragmentId);
	// set the gallery adapter
	picGallery.setAdapter(imgAdapt);
	System.out.print("******ADAPTER DONE*******");

	fillChoice(storyFragmentChoices);

	lv.setOnItemClickListener(new OnItemClickListener() {
	    // handle clicks
	    public void onItemClick(AdapterView<?> parent, View v,
		    int position, long id) {

		Intent nextStoryFragment = new Intent(getApplicationContext(),
			StoryFragmentActivity.class);
		nextStoryFragment.putExtra("story", currentStory);

		Choice nextChoice = (Choice) lv.getAdapter().getItem(position);

		int nextStoryFragmentId = nextChoice.getStoryFragmentID();

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
	case R.id.view_annotations:
	    Intent annoIntent = new Intent(getApplicationContext(),
		    AnnotationViewActivity.class);
	    annoIntent.putExtra("Annotations",
		    currentStoryFragment.getAnnotations());
	    startActivity(annoIntent);
	    return true;
	case R.id.action_add_annotations:
	    showPopup();
	    return true;

	default:
	    return super.onOptionsItemSelected(item);
	}
    }

    public void fillChoice(ArrayList<Choice> cList) {
	lv.addHeaderView(headerGallery);
	lv.addHeaderView(headerText);
	ChoiceAdapter adapter = new ChoiceAdapter(this, android.R.id.list,
		cList);
	lv.setAdapter(adapter);

    }

    public void showPopup() {
	View popUpItemView = findViewById(R.id.action_add_annotations);
	PopupMenu popupMenu = new PopupMenu(this, popUpItemView);
	MenuInflater inflater = popupMenu.getMenuInflater();
	// popupMenu.inflate(R.menu.annotation_view);
	inflater.inflate(R.menu.annotation_view, popupMenu.getMenu());
	
	popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){	   
	    public boolean onMenuItemClick(MenuItem item) {
		System.out.println("MenuItemSetUp");
		switch (item.getItemId()) {
		case R.id.add_anno_camera:
		    // archive(item);
		    return true;
		case R.id.add_anno_gallery:
		    // take the user to their chosen image selection app (gallery or
		    // file manager)
		    System.out.println("GallerySelected!");
		    Intent pickIntent = new Intent();
		    pickIntent.setType("image/*");
		    pickIntent.setAction(Intent.ACTION_GET_CONTENT);
		    startActivityForResult(
				Intent.createChooser(pickIntent, "Select Picture"), 1);
		    return true;
		default:
		    return false;
		}
	    }
	});
	popupMenu.show();
	
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	if (resultCode == RESULT_OK) {
	    // check if we are returning from picture selection
	    if (requestCode == PICKER) {
		
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

		// if and else handle both choosing from gallery and from file
		// manager

		// if we have a new URI attempt to decode the image bitmap
		if (pickedUri != null) {

		    // set the width and height we want to use as maximum
		    // display
		    int targetWidth = 200;
		    int targetHeight = 150;

		    // sample the incoming image to save on memory resources

		    // create bitmap options to calculate and use sample size
		    BitmapFactory.Options bmpOptions = new BitmapFactory.Options();

		    // first decode image dimensions only - not the image bitmap
		    // itself
		    bmpOptions.inJustDecodeBounds = true;
		    BitmapFactory.decodeFile(imgPath, bmpOptions);

		    // work out what the sample size should be

		    // image width and height before sampling
		    int currHeight = bmpOptions.outHeight;
		    int currWidth = bmpOptions.outWidth;

		    // variable to store new sample size
		    int sampleSize = 1;

		    // calculate the sample size if the existing size is larger
		    // than target size
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

		    String fileName = "Image"
			    + Integer.toString(currentStoryId)
			    + "Fragment"
			    + Integer.toString(currentStoryFragment
				    .getStoryFragmentId())
			    + "Annotation"
			    + Integer.toString(currentStoryFragment
				    .getAnnotations().size() + 1) + ".png";
		    System.out.println("New image: " + fileName);

		    try {
			FileOutputStream fos = openFileOutput(fileName,
				Context.MODE_PRIVATE);
			pic.compress(CompressFormat.PNG, 90, fos);
		    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		    System.out.println(currentStoryFragment.toString());
		    System.out.println(currentStory.getStoryFragments()
			    .toString());
		    System.out.println("TEST ID " + currentStoryFragmentIndex);
		    Annotation add = new Annotation();
		    add.setAnnotationID(currentStoryFragment.getAnnotations()
			    .size() + 1);
		    add.setPhoto(fileName);
		    ArrayList<Annotation> temp = currentStoryFragment
			    .getAnnotations();
		    temp.add(add);
		    System.out.println("Annotation MAKE DONE");
		    currentStoryFragment.setAnnotations(temp);
		    currentStory.getStoryFragments().set(
			    currentStoryFragmentIndex, currentStoryFragment);
		    System.out.println("SWAP FRAGMENT DONE");
		    try {
			fHelper.updateOfflineStory(currentStory);
			System.out.println("Test currentStoryId:"
				+ currentStoryId);
			currentStory = fHelper.getOfflineStory(currentStoryId);
			esHelper.addOrUpdateOnlineStory(currentStory);
			Toast.makeText(getApplicationContext(),
				"New annotation is uploaded successfully",
				Toast.LENGTH_LONG).show();
			System.out
				.println("================================================CLEAR==================================");
		    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }

		}
	    }

	    // superclass method
	    super.onActivityResult(requestCode, resultCode, data);
	}

    }

}
