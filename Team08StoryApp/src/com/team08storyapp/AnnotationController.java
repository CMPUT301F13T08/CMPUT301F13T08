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

/**
 * AnnotationController is a controller class that is responsible for resizing
 * (if needed), saving annotations and update the current story and current
 * story fragment.
 * <p>
 * In current stage, AnnotationController only provides a public method:
 * <ul>
 * <li>savePhoto(Uri pickedUri)
 * </ul>
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 2.0 December 3, 2013
 * @since 2.0
 * 
 */
public class AnnotationController {

    private Activity activity;
    private Context context;
    private Story currentStory;
    private StoryFragment currentStoryFragment;
    private int currentStoryFragmentIndex;
    private FileHelper fHelper;
    private ESHelper esHelper;
    private static final int MODE_OFFLINE = 1;
    private static final int MODE_MY = 2;

    /**
     * Constructor of the AnnotationController takes in all the variables it
     * needs and set them to corresponding variables to its own fields.
     * 
     * @param activity
     *            an activity object
     * @param context
     *            a context object for writing files
     * @param currentStory
     *            currentStory, which will be updated after an image is added
     * @param currentStoryFragment
     *            the story fragment that needs to be updated
     * @param currentStoryFragmentIndex
     *            This is an index reference of the currentStoryFragment when
     *            updating. Since ArrayList.indexOf didn't work so well.
     * @param fHelper
     *            The FileHelper object that helps with saving images.
     * @param esHelper
     *            The ESHelper object that helps with uploading and saving
     *            images online.
     */
    public AnnotationController(Activity activity, Story currentStory,
	    StoryFragment currentStoryFragment, int currentStoryFragmentIndex,
	    FileHelper fHelper, ESHelper esHelper) {
	this.activity = activity;
	this.context = activity.getApplicationContext();
	this.currentStory = currentStory;
	this.currentStoryFragment = currentStoryFragment;
	this.currentStoryFragmentIndex = currentStoryFragmentIndex;
	this.fHelper = fHelper;
	this.esHelper = esHelper;
    }

    /**
     * SavePhoto is the function where resizing, saving the illustration, and
     * updating the current fragment are performed in order.
     * <ul>
     * <li>resizing the annotation to 200 * 150 if it's larger than this
     * dimension.
     * <li>saving the image based on the information of its fragmentId storyId
     * and current photo Id.
     * <li>updating the story in local file system.
     * </ul>
     * 
     * @param pickedUri
     *            Uri reference to the image
     * @param mode
     *            An integer value to indicate whether the annotation should be
     *            also saved to downloaded story besides upload it to the online
     *            story
     * @return Bitmap object that is decoded from the Uri
     * 
     */

    public Bitmap savePhoto(Uri pickedUri, int mode) {

	// create the fileName for the image
	String fileName = createFileName();

	/* declare the bitmap */
	Bitmap pic = null;

	// declare the path string
	String imgPath = getImagePath(pickedUri);

	/* if we have a new URI attempt to decode the image bitmap */
	if (pickedUri != null) {

	    /* create bitmap options to calculate and use sample size */
	    BitmapFactory.Options bmpOptions = createBitmapOptions(imgPath);

	    /* get the file as a bitmap */
	    pic = BitmapFactory.decodeFile(imgPath, bmpOptions);
	    try {
		FileOutputStream fos = context.openFileOutput(fileName,
			Context.MODE_PRIVATE);
		pic.compress(CompressFormat.PNG, 90, fos);
	    } catch (Exception e1) {
		e1.printStackTrace();
	    }
	} else {

	    /*
	     * if anything wrong happen during the saving process, a toast
	     * message will popup.
	     */

	    Toast.makeText(activity, "Save File Error", Toast.LENGTH_LONG)
		    .show();
	}

	/*
	 * add the annotation to online story and/or downloaded story depends on
	 * the mode
	 */
	addAnnotation(fileName, mode);
	return pic;
    }

    /*
     * This method adds the Annotation to the Story Fragment and saves the
     * changes to the Story to the webservice, so that it is available online to
     * all users.
     */
    private void addAnnotation(String fileName, int mode) {

	/*
	 * Make up a new annotation object and put related information(fileName,
	 * id) into the object and update the current story
	 */
	Annotation add = new Annotation();
	add.setAnnotationID(currentStoryFragment.getAnnotations().size() + 1);
	add.setPhoto(fileName);
	ArrayList<Annotation> temp = currentStoryFragment.getAnnotations();
	temp.add(add);
	currentStoryFragment.setAnnotations(temp);

	/* Update the current story with the new story fragment */
	currentStory.getStoryFragments().set(currentStoryFragmentIndex,
		currentStoryFragment);

	/*
	 * If the user adds the annotation in the downloaded story, the local
	 * story file will need to be updated.
	 */
	if (mode == MODE_OFFLINE) {
	    try {
		fHelper.updateOfflineStory(currentStory);
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	} else if (mode == MODE_MY) {
	    try {
		fHelper.updateOfflineStory(currentStory);
		UpdateFileRecorder.appendUpdateQueue(
			currentStory.getOfflineStoryId(), context);
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    Toast.makeText(context, "New annotation is saved successfully",
		    Toast.LENGTH_LONG).show();
	    return;

	}

	/*
	 * Else simply save the new story to the web server. On success a toast
	 * message will popup to inform the user that the annotation is added
	 * successfully
	 */
	try {

	    /* encode the story */
	    Encoder encoder = new Encoder(context);
	    Story encodedStory = encoder.encodeStory(currentStory);

	    /* make sure the annotation is uploaded and rewrites the right file. */
	    if (esHelper.addOrUpdateOnlineStory(encodedStory) == encodedStory
		    .getOnlineStoryId()) {

		/* pop up a message to inform user that annotation is added */
		Toast.makeText(context,
			"New annotation is uploaded successfully",
			Toast.LENGTH_LONG).show();
	    } else {
		Toast.makeText(context, "Network problem, please try again.",
			Toast.LENGTH_LONG).show();
	    }

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /*
     * This method resizes the Annotation to fit properly within the Annotation
     * view.
     */
    private int resize(BitmapFactory.Options bmpOptions) {

	/* set the target image size */
	int targetWidth = 200;
	int targetHeight = 150;

	/* image width and height before sampling */
	int currHeight = bmpOptions.outHeight;
	int currWidth = bmpOptions.outWidth;

	/*
	 * calculate the sample size if the existing size is larger than target
	 * size
	 */
	if (currHeight > targetHeight || currWidth > targetWidth) {
	    // use either width or height
	    if (currWidth > currHeight)
		return Math.round((float) currHeight / (float) targetHeight);
	    else
		return Math.round((float) currWidth / (float) targetWidth);
	} else {
	    return 1;
	}

    }

    /*
     * This method is used to setup Bitmap options for displaying the
     * Annotations.
     */
    private BitmapFactory.Options createBitmapOptions(String imgPath) {
	/* create bitmap options to calculate and use sample size */
	BitmapFactory.Options bmpOptions = new BitmapFactory.Options();

	/* first decode image dimensions only */
	bmpOptions.inJustDecodeBounds = true;

	BitmapFactory.decodeFile(imgPath, bmpOptions);

	/* use the new sample size */
	bmpOptions.inSampleSize = resize(bmpOptions);

	/* now decode the bitmap using sample options */
	bmpOptions.inJustDecodeBounds = false;

	return bmpOptions;
    }

    /*
     * This method finds the path of the image the user selected for an
     * Annotation.
     */
    private String getImagePath(Uri pickedUri) {

	String imgPath = "";
	/* query the data */
	Cursor picCursor = getPicCursor(pickedUri);

	if (picCursor != null) {

	    /* get the path string */
	    int index = picCursor
		    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	    picCursor.moveToFirst();
	    imgPath = picCursor.getString(index);
	    picCursor.close();
	} else {
	    imgPath = pickedUri.getPath();
	}

	return imgPath;
    }

    /*
     * This method sets up the fil
     */
    private String createFileName() {
	return "Image"
		+ Integer.toString(currentStory.getOfflineStoryId())
		+ "Fragment"
		+ Integer.toString(currentStoryFragment.getStoryFragmentId())
		+ "Annotation"
		+ Integer
			.toString(currentStoryFragment.getAnnotations().size() + 1)
		+ ".png";
    }

    private Cursor getPicCursor(Uri pickedUri) {

	/* retrieve the string using media data */
	String[] medData = { MediaStore.Images.Media.DATA };
	try {
	    return activity.getContentResolver().query(pickedUri, medData,
		    null, null, null);
	} catch (Exception e) {
	    return null;
	}
    }

}
