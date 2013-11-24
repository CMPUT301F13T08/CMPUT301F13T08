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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * AnnotationAdapter is a customized ArrayAdapter that is designed for
 * populating the images and text (if applicable) into right views in a
 * ListView. This class requires at least a list of annotation as the
 * information provider and an activity to get LAYOUT_INFLATER_SERVICE.
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 1.0 November 8, 2013
 * @since 1.0
 */
public class AnnotationAdapter extends ArrayAdapter<Annotation> {
    private ArrayList<Annotation> annoList;
    private Activity activity;

    /**
     * The constructor of an AnnotationAdapter requires at least a list of
     * annotation as the information provider and an activity to get
     * LAYOUT_INFLATER_SERVICE.
     * 
     * @param activity
     *            an activity object that provides LAYOUT_INFLATER_SERVICE
     * @param textViewResourceId
     *            the int value of the id of the view
     * @param annoList
     *            a list of annotations
     */
    public AnnotationAdapter(Activity activity, int textViewResourceId,
	    ArrayList<Annotation> annoList) {
	super(activity, textViewResourceId, annoList);
	this.annoList = annoList;
	this.activity = activity;
    }

    /**
     * The customized getView will populate images and texts into the passed
     * ListView.
     * 
     * @param position
     *            the index of the selected photo in the adapter
     * @param convertView
     *            the view that is going to be converted to user's desire
     * @param parent
     *            a ViewGroup object
     * @return the converted view for the Annotation
     */
    public View getView(int position, View convertView, ViewGroup parent) {
	View v = convertView;
	ViewHolder holder;

	/*
	 * If the passed view is a valid view then inflate it with desired
	 * layout.
	 */
	if (v == null) {
	    LayoutInflater vi = (LayoutInflater) activity
		    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    v = vi.inflate(R.layout.annotation_row, null);
	    holder = new ViewHolder();
	    holder.annoImage = (ImageView) v.findViewById(R.id.annotationImage);
	    holder.annoText = (TextView) v.findViewById(R.id.annotationText);
	    v.setTag(holder);
	} else
	    holder = (ViewHolder) v.getTag();

	final Annotation anno = annoList.get(position);

	/*
	 * If the annotation is not a null object and really has a photo then
	 * just read the photo as a byteArray and set it to the layout's Image
	 * view. Else set the default launch icon to the image view.
	 */
	if (anno != null) {
	    if (anno.getPhoto() != null && !anno.getPhoto().isEmpty()) {
		File file = activity.getFilesDir();
		File[] fileList = file.listFiles();
		File annoFile;
		for (int i = 0; i < fileList.length; i++) {
		    if (fileList[i].getName().equals(anno.getPhoto())) {
			annoFile = fileList[i];
			String path = annoFile.getAbsolutePath();
			System.out.println(path);
			Bitmap placeholder = BitmapFactory.decodeFile(path);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			placeholder.compress(Bitmap.CompressFormat.PNG, 80,
				stream);
			byte[] bytePicture = stream.toByteArray();
			holder.annoImage.setImageBitmap((BitmapFactory
				.decodeByteArray(bytePicture, 0,
					bytePicture.length)));
			break;
		    }
		}
	    } else {
		holder.annoImage.setImageBitmap(BitmapFactory.decodeResource(
			activity.getResources(), R.drawable.ic_launcher));
	    }

	    /*
	     * If the annotation has its own text then set the text to the
	     * textView else set the message to "No Text Annotation".
	     */

	    if (anno.getText() != null && !anno.getText().isEmpty()) {
		holder.annoText.setText(anno.getText());
	    } else {
		holder.annoText.setText("No Text Annotations.");
	    }
	}

	return v;
    }

    /**
     * ViewHolder is a like a struct in C++. Only hold two view objects for the
     * population purpose of AnnotationAdapter
     */
    public static class ViewHolder {
	public ImageView annoImage;
	public TextView annoText;
    }

}
