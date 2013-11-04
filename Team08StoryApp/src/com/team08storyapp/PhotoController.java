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
-This demo was used to help with JSON and ESHelper

Retrieved Oct. 29, 2013  - http://hc.apache.org/downloads.cgi
-This is for the fluent library which is licenced under apache V2

Retrieved Oct. 29, 2013 
- https://code.google.com/p/google-gson/downloads/detail?name=google-gson-2.2.4-release.zip&can=2&q=
-This is for JSON which is licenced under apache V2
 */

package com.team08storyapp;

/*
 * PhotoController is a controller class which allows users to choose from gallery
 * or take a photo through intent in order to add a photo to an annotation or 
 * a story fragment or an annotation.
 * 
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;

public class PhotoController extends Activity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    Uri imageFileUri;

    /**
     * Method takePhoto invokes an intent action which is sent to the camera
     * application to capture an image and save it.
     * 
     */

    public void takePhoto() {
	Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
	startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    /**
     * Method chooseFromGallery starts an intent which allows one to choose
     * images from the gallery.
     * 
     */

    public void chooseFromGallery() {
	Intent pickIntent = new Intent();
	pickIntent.setType("image/*");
	pickIntent.setAction(Intent.ACTION_GET_CONTENT);
	startActivityForResult(
		Intent.createChooser(pickIntent, "Select Picture"), 1);
    }

    public String saveToLocal(Uri pickedUri, int currentStoryId, StoryFragment currentStoryFragment) {

	// declare the bitmap
	Bitmap pic = null;

	// declare the path string
	String imgPath = "";

	// retrieve the string using media data
	String[] medData = { MediaStore.Images.Media.DATA };

	// query the data
	Cursor picCursor = managedQuery(pickedUri, medData, null, null, null);
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
	     * first decode image dimensions only - not the image bitmap itself
	     */
	    bmpOptions.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(imgPath, bmpOptions);

	    /*
	     * work out what the sample size should be image width and height
	     * before sampling
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
		    + Integer.toString(currentStoryFragment.getAnnotations()
			    .size() + 1) + ".png";

	    // save the new image file
	    try {
		FileOutputStream fos = openFileOutput(fileName,
			Context.MODE_PRIVATE);
		pic.compress(CompressFormat.PNG, 90, fos);
	    } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

	    }
	    return fileName;
	}
	return null;
    }
    
}
