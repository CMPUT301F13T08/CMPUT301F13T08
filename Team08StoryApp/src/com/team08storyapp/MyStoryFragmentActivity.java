package com.team08storyapp;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
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
public class MyStoryFragmentActivity extends Activity {

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

    private final int PICKER = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {

	super.onCreate(savedInstanceState);
	fHelper = new FileHelper(this, 1);
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

	picGallery.setOnItemLongClickListener(new OnItemLongClickListener() {
	    // handle long clicks
	    public boolean onItemLongClick(AdapterView<?> parent, View v,
		    int position, long id) {
		// update the currently selected position so that we assign the
		// imported bitmap to correct item
		currentPic = position;
		// take the user to their chosen image selection app (gallery or
		// file manager)
		Intent pickIntent = new Intent();
		pickIntent.setType("image/*");
		pickIntent.setAction(Intent.ACTION_GET_CONTENT);
		// we will handle the returned data in onActivityResult
		startActivityForResult(
			Intent.createChooser(pickIntent, "Select Picture"), 1);
		return true;
	    }
	});

	// Get the intent - passed either by Online/OfflineStoriesActivity or by
	// StoryFragmentActivity

	Intent storyFragment = getIntent();

	// Get the story object from the intent
	currentStory = (Story) storyFragment.getSerializableExtra("story");
	// Get the story fragment id from the intent - the fragment to display
	System.out.println(currentStory.toString());
	currentStoryFragmentId = storyFragment
		.getIntExtra("storyFragmentId", 0);
	currentStoryId = currentStory.getOfflineStoryId();
	
	for( int i = 0; i < currentStory.getStoryFragments().size();i++){
	    if(currentStory.getStoryFragments().get(i).getStoryFragmentId() == currentStoryFragmentId){
		currentStoryFragmentIndex = i;
	    }
	}
	System.out.println("storyFragment index: "  + currentStoryFragmentIndex);

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
	imgAdapt = new PicAdapter(this, illustrationList);
	// set the gallery adapter
	picGallery.setAdapter(imgAdapt);
	System.out.print("******ADAPTER DONE*******");

	fillChoice(storyFragmentChoices);

	lv.setOnItemClickListener(new OnItemClickListener() {
	    // handle clicks
	    public void onItemClick(AdapterView<?> parent, View v,
		    int position, long id) {

		Intent nextStoryFragment = new Intent(getApplicationContext(),
			MyStoryFragmentActivity.class);
		nextStoryFragment.putExtra("story", currentStory);

		Choice nextChoice = (Choice) lv.getAdapter().getItem(position);
		int nextStoryFragmentId = nextChoice.getStoryFragmentID();

		nextStoryFragment.putExtra("storyFragmentId",
			nextStoryFragmentId);

		startActivity(nextStoryFragment);

	    }
	});

    }

    public void fillChoice(ArrayList<Choice> cList) {
	lv.addHeaderView(headerGallery);
	lv.addHeaderView(headerText);
	ChoiceAdapter adapter = new ChoiceAdapter(this, android.R.id.list,
		cList);
	lv.setAdapter(adapter);

    }

    public class PicAdapter extends BaseAdapter {

	// use the default gallery background image
	int defaultItemBackground;

	// gallery context
	private Context galleryContext;

	// array to store bitmaps to display
	private Bitmap[] imageBitmaps;
	// placeholder bitmap for empty spaces in gallery
	Bitmap placeholder;

	// constructor
	public PicAdapter(Context c, ArrayList<Photo> photoList) {

	    // instantiate context
	    galleryContext = c;

	    // create bitmap array
	    imageBitmaps = new Bitmap[10];
	    // decode the placeholder image
	    placeholder = BitmapFactory.decodeResource(getResources(),
		    R.drawable.ic_launcher);

	    System.out.println(photoList.size());

	    // decode the placeholder image
	    placeholder = BitmapFactory.decodeResource(getResources(),
		    R.drawable.ic_launcher);

	    // set placeholder as all thumbnail images in the gallery initially
	    for (int i = 0; i < imageBitmaps.length; i++)
		imageBitmaps[i] = placeholder;

	    if (photoList.size() > 0) {
		File file = getFilesDir();
		File[] fileList = file.listFiles();
		ArrayList<File> prefixFileList = new ArrayList<File>();
		for (int i = 0; i < fileList.length; i++) {
		    System.out.println("FIND IMAGE: " + fileList[i].getName());
		    if (fileList[i].getName().startsWith(
			    "Image"+Integer.toString(currentStoryId)+"Fragment"
				    + Integer.toString(currentStoryFragmentId))) {
			System.out.println("USE IMAGE: " + fileList[i].getName());
			prefixFileList.add(fileList[i]);
		    }
		}
		for (int i = 0; i < Math.min(imageBitmaps.length,
			photoList.size()); i++) {
		    String path = prefixFileList.get(i).getAbsolutePath();
		    // String filePath = path.substring(0,
		    // path.lastIndexOf(File.separator));
		    placeholder = BitmapFactory.decodeFile(path);
		    ByteArrayOutputStream stream = new ByteArrayOutputStream();
		    placeholder
			    .compress(Bitmap.CompressFormat.PNG, 100, stream);
		    byte[] bytePicture = stream.toByteArray();

		    System.out.println("*****ByteArray Done******");

		    imageBitmaps[i] = BitmapFactory.decodeByteArray(
			    bytePicture, 0, bytePicture.length);

		}
	    }

	    // get the styling attributes - use default Andorid system resources
	    TypedArray styleAttrs = galleryContext
		    .obtainStyledAttributes(R.styleable.PicGallery);
	    // get the background resource
	    defaultItemBackground = styleAttrs.getResourceId(
		    R.styleable.PicGallery_android_galleryItemBackground, 0);
	    // recycle attributes
	    styleAttrs.recycle();

	}

	// BaseAdapter methods

	// return number of data items i.e. bitmap images
	public int getCount() {
	    return imageBitmaps.length;
	}

	// return item at specified position
	public Object getItem(int position) {
	    return position;
	}

	// return item ID at specified position
	public long getItemId(int position) {
	    return position;
	}

	// get view specifies layout and display options for each thumbnail in
	// the gallery
	public View getView(int position, View convertView, ViewGroup parent) {

	    // create the view
	    ImageView imageView = new ImageView(galleryContext);
	    // specify the bitmap at this position in the array
	    imageView.setImageBitmap(imageBitmaps[position]);
	    // set layout options
	    imageView.setLayoutParams(new Gallery.LayoutParams(300, 200));
	    // scale type within view area
	    imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
	    // set default gallery item background
	    imageView.setBackgroundResource(defaultItemBackground);
	    // return the view
	    return imageView;
	}

	// custom methods for this app

	// helper method to add a bitmap to the gallery when the user chooses
	// one
	public void addPic(Bitmap newPic) {
	    // set at currently selected index
	    imageBitmaps[currentPic] = newPic;
	}

	// return bitmap at specified position for larger display
	public Bitmap getPic(int posn) {
	    // return bitmap at posn index
	    return imageBitmaps[posn];
	}
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

		    String fileName = "Image"+Integer.toString(currentStoryId)+"Fragment"
			    + Integer.toString(currentStoryFragment
				    .getStoryFragmentId())
			    + "Photo"
			    + Integer.toString(currentStoryFragment.getPhotos()
				    .size() + 1) + ".png";
		    System.out.println("New image: "+fileName);

		    try {
			FileOutputStream fos = openFileOutput(fileName,
				Context.MODE_PRIVATE);
			pic.compress(CompressFormat.PNG, 90, fos);
		    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		    System.out.println(currentStoryFragment.toString());
		    System.out.println(currentStory.getStoryFragments().toString());
		    System.out.println("TEST ID "+currentStoryFragmentIndex);
		    Photo add = new Photo();
		    add.setPhotoID(currentStoryFragment.getPhotos().size() + 1);
		    add.setPictureName(fileName);
		    ArrayList<Photo> temp = currentStoryFragment.getPhotos();
		    temp.add(add);
		    System.out.println("PHOTO MAKE DONE");
		    currentStoryFragment.setPhotos(temp);
		    currentStory.getStoryFragments().set(
			    currentStoryFragmentIndex, currentStoryFragment);
		    System.out.println("SWAP FRAGMENT DONE");
		    try {
			fHelper.updateOfflineStory(currentStory);
			System.out.println("Test currentStoryId:" + currentStoryId);
			currentStory = fHelper.getOfflineStory(currentStoryId);
			System.out.println("================================================CLEAR==================================");
		    } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }

		    // pass bitmap to ImageAdapter to add to array
		    imgAdapt.addPic(pic);

		    // redraw the gallery thumbnails to reflect the new addition
		    picGallery.setAdapter(imgAdapt);

		    // display the newly selected image at larger size
		    picView.setImageBitmap(pic);
		    // scale options
		    picView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		}
	    }

	    // superclass method
	    super.onActivityResult(requestCode, resultCode, data);
	}

    }

}
