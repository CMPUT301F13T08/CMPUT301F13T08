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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

/**
 * PicAdapter is a customized Adapter based on BaseAdapter
 * 
 * @author Sue Smith
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 1.0 November 8, 2013
 * @since 1.0
 * 
 */
public class PicAdapter extends BaseAdapter {

    // use the default gallery background image
    int defaultItemBackground;

    // gallery context
    private Context galleryContext;

    // array to store bitmaps to display
    private Bitmap[] imageBitmaps;
    // placeholder bitmap for empty spaces in gallery
    Bitmap placeholder;

    /**
     * This constructor of PicAdapter will populate all images from photoList
     * into a gallery with customized getView function.
     * 
     * 
     * @param context
     * @param photoList
     * @param currentStoryId
     * @param currentStoryFragmentId
     */
    public PicAdapter(Context context, ArrayList<Photo> photoList,
	    int currentStoryId, int currentStoryFragmentId) {

	// instantiate context
	galleryContext = context;

	// create bitmap array
	imageBitmaps = new Bitmap[5];
	// decode the placeholder image
	placeholder = BitmapFactory.decodeResource(
		galleryContext.getResources(), R.drawable.ic_launcher);

	// decode the placeholder image
	placeholder = BitmapFactory.decodeResource(
		galleryContext.getResources(), R.drawable.ic_launcher);

	// set placeholder as all thumbnail images in the gallery initially
	for (int i = 0; i < imageBitmaps.length; i++)
	    imageBitmaps[i] = placeholder;
	if (photoList.size() > 0) {
	    File file = galleryContext.getFilesDir();
	    File[] fileList = file.listFiles();
	    ArrayList<File> prefixFileList = new ArrayList<File>();
	    for (int i = 0; i < fileList.length; i++) {
		if (fileList[i].getName().startsWith(
			"Image" + Integer.toString(currentStoryId) + "Fragment"
				+ Integer.toString(currentStoryFragmentId))) {
		    prefixFileList.add(fileList[i]);
		}
	    }
	    for (int i = 0; i < Math.min(imageBitmaps.length, photoList.size()); i++) {
		String path = prefixFileList.get(i).getAbsolutePath();
		placeholder = BitmapFactory.decodeFile(path);
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		placeholder.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte[] bytePicture = stream.toByteArray();
		imageBitmaps[i] = BitmapFactory.decodeByteArray(bytePicture, 0,
			bytePicture.length);
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

    /**
     * addPic method is a helper method to add a bitmap to the gallery when the
     * user chooses one
     * 
     * @param currentPic
     * @param newPic
     */
    public void addPic(int currentPic, Bitmap newPic) {
	// set at currently selected index
	imageBitmaps[currentPic] = newPic;
    }

    /**
     * getPic returns bitmap at specified position for larger display
     * 
     * @param position
     * @return
     */

    public Bitmap getPic(int position) {
	// return bitmap at position index
	return imageBitmaps[position];
    }
}