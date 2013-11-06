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
 * PhotoController is a controller class that is responsible for resizing (if
 * needed), saving illustrations and update the current story and current story
 * fragment.
 * <p>
 * In current stage, PhotoController only provides a public method:
 * <ul>
 * <li>savePhoto(Uri pickedUri)
 * </ul>
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 1.0 November 8, 2013
 * @since 1.0
 * 
 */
public class PhotoController {

    private Activity activity;
    private Context context;
    private Story currentStory;
    private StoryFragment currentStoryFragment;
    private int currentStoryFragmentIndex;
    private FileHelper fHelper;

    /**
     * Constructor of the PhotoController takes in all the variables it needs
     * and set them to corresponding variables to its own fields.
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
     */
    public PhotoController(Activity activity, Context context,
	    Story currentStory, StoryFragment currentStoryFragment,
	    int currentStoryFragmentIndex, FileHelper fHelper) {
	this.activity = activity;
	this.context = context;
	this.currentStory = currentStory;
	this.currentStoryFragment = currentStoryFragment;
	this.currentStoryFragmentIndex = currentStoryFragmentIndex;
	this.fHelper = fHelper;
    }

    /**
     * savePhoto is the function where resizing, saving the illustration, and
     * updating the current fragment are performed in order.
     * <ul>
     * <li>resizing the illustration to 200 * 150 if it's larger than this
     * dimension.
     * <li>saving the image based on the information of its fragmentId storyId
     * and current photo Id.
     * <li>updating the story in local file system.
     * </ul>
     * 
     * @param pickedUri
     *            Uri reference to the image
     * @return Bitmap object that is decoded from the Uri
     */
    public Bitmap savePhoto(Uri pickedUri) {
	String fileName = "Image"
		+ Integer.toString(currentStory.getOfflineStoryId())
		+ "Fragment"
		+ Integer.toString(currentStoryFragment.getStoryFragmentId())
		+ "Photo"
		+ Integer.toString(currentStoryFragment.getPhotos().size() + 1)
		+ ".png";

	/* declare the bitmap */
	Bitmap pic = null;
	/* declare the path string */
	String imgPath = "";

	/* retrieve the string using media data */
	String[] medData = { MediaStore.Images.Media.DATA };

	/* query the data */
	Cursor picCursor = activity.getContentResolver().query(pickedUri,
		medData, null, null, null);
	if (picCursor != null) {

	    /* get the path string */
	    int index = picCursor
		    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	    picCursor.moveToFirst();
	    imgPath = picCursor.getString(index);
	} else
	    imgPath = pickedUri.getPath();
	picCursor.close();

	/* if we have a new URI attempt to decode the image bitmap */
	if (pickedUri != null) {

	    int targetWidth = 200;
	    int targetHeight = 150;

	    /* create bitmap options to calculate and use sample size */
	    BitmapFactory.Options bmpOptions = new BitmapFactory.Options();

	    /* first decode image dimensions only */
	    bmpOptions.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(imgPath, bmpOptions);

	    /* image width and height before sampling */
	    int currHeight = bmpOptions.outHeight;
	    int currWidth = bmpOptions.outWidth;

	    /* variable to store new sample size */
	    int sampleSize = 1;

	    /*
	     * calculate the sample size if the existing size is larger than
	     * target size
	     */
	    if (currHeight > targetHeight || currWidth > targetWidth) {
		/* use either width or height */
		if (currWidth > currHeight)
		    sampleSize = Math.round((float) currHeight
			    / (float) targetHeight);
		else
		    sampleSize = Math.round((float) currWidth
			    / (float) targetWidth);
	    }

	    /* use the new sample size */
	    bmpOptions.inSampleSize = sampleSize;

	    /* now decode the bitmap using sample options */
	    bmpOptions.inJustDecodeBounds = false;

	    /* get the file as a bitmap */
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

	addIllustration(fileName);

	return pic;
    }

    /**
     * currentPosition function will return the index of the last photo in the
     * gallery
     * 
     * @return an integer value that represents the index of the last photo in
     *         the gallery
     */
    public int currentPosition() {
	return currentStoryFragment.getPhotos().size() - 1;

    }

    /*
     * Add Illustration is a function that updates a story with newly added
     * illustrations.
     */
    private void addIllustration(String fileName) {
	
	// create a new photo object based on id and fileName
	Photo add = new Photo();
	add.setPhotoID(currentStoryFragment.getPhotos().size() + 1);
	add.setPictureName(fileName);

	// add new Photo to photoList
	ArrayList<Photo> temp = currentStoryFragment.getPhotos();
	temp.add(add);
	currentStoryFragment.setPhotos(temp);
	
	// update the story
	currentStory.getStoryFragments().set(currentStoryFragmentIndex,
		currentStoryFragment);

	// update the story in local file system
	try {
	    fHelper.updateOfflineStory(currentStory);
	    Toast.makeText(activity, "Illustration is added successfully",
		    Toast.LENGTH_LONG).show();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

}
