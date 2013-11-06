package com.team08storyapp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

public class AnnotationController {

    private Activity activity;
    private Context context;
    private Story currentStory;
    private StoryFragment currentStoryFragment;
    private int currentStoryFragmentIndex;
    private FileHelper fHelper;
    private ESHelper esHelper;
    private static final int MODE_OFFLINE = 1;

    public AnnotationController(Activity a, Context context,
	    Story currentStory, StoryFragment currentStoryFragment,
	    int currentStoryFragmentIndex, FileHelper fHelper, ESHelper esHelper) {
	this.activity = a;
	this.context = context;
	this.currentStory = currentStory;
	this.currentStoryFragment = currentStoryFragment;
	this.currentStoryFragmentIndex = currentStoryFragmentIndex;
	this.fHelper = fHelper;
	this.esHelper = esHelper;
    }

    public Bitmap savePhoto(Uri pickedUri, int mode) {

	String fileName = "Image"
		+ Integer.toString(currentStory.getOfflineStoryId())
		+ "Fragment"
		+ Integer.toString(currentStoryFragment.getStoryFragmentId())
		+ "Annotation"
		+ Integer
			.toString(currentStoryFragment.getAnnotations().size() + 1)
		+ ".png";

	// declare the bitmap
	Bitmap pic = null;
	// declare the path string
	String imgPath = "";

	// retrieve the string using media data
	String[] medData = { MediaStore.Images.Media.DATA };

	// query the data
	Cursor picCursor = activity.getContentResolver().query(pickedUri,
		medData, null, null, null);
	if (picCursor != null) {

	    // get the path string
	    int index = picCursor
		    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	    picCursor.moveToFirst();
	    imgPath = picCursor.getString(index);
	} else
	    imgPath = pickedUri.getPath();
	picCursor.close();

	// if we have a new URI attempt to decode the image bitmap
	if (pickedUri != null) {

	    int targetWidth = 200;
	    int targetHeight = 150;

	    // create bitmap options to calculate and use sample size
	    BitmapFactory.Options bmpOptions = new BitmapFactory.Options();

	    // first decode image dimensions only
	    bmpOptions.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(imgPath, bmpOptions);

	    // image width and height before sampling
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

	    System.out.println("New image: " + fileName);

	    try {
		FileOutputStream fos = context.openFileOutput(fileName,
			Context.MODE_PRIVATE);
		pic.compress(CompressFormat.PNG, 90, fos);
	    } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	} else {
	    Toast.makeText(activity, "Save File Error", Toast.LENGTH_LONG)
		    .show();
	}
	addAnnotation(fileName, mode);
	return pic;
    }

    private void addAnnotation(String fileName, int mode) {
	Annotation add = new Annotation();
	add.setAnnotationID(currentStoryFragment.getAnnotations().size() + 1);
	add.setPhoto(fileName);
	ArrayList<Annotation> temp = currentStoryFragment.getAnnotations();
	temp.add(add);
	currentStoryFragment.setAnnotations(temp);

	// update the current story with the new story fragment
	currentStory.getStoryFragments().set(currentStoryFragmentIndex,
		currentStoryFragment);

	if (mode == MODE_OFFLINE) {
	    try {
		fHelper.updateOfflineStory(currentStory);
	    } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	try {
	    Story encodedStory = fHelper.encodeStory(currentStory);
	    if (esHelper.addOrUpdateOnlineStory(encodedStory) == encodedStory
		    .getOnlineStoryId()) {
		// pop up a message to inform user that annotation
		// is added
		Toast.makeText(context,
			"New annotation is uploaded successfully",
			Toast.LENGTH_LONG).show();
	    } else {
		Toast.makeText(context, "Network problem, please try again.",
			Toast.LENGTH_LONG).show();
	    }

	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}