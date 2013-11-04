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
 * AnnotationAdapter is a class that is responsible for populating
 * an annotation array list into a list view.
 * 
 * @author Alice Wu
 *
 */
public class AnnotationAdapter extends ArrayAdapter<Annotation> {
    private ArrayList<Annotation> annoList;
    private Activity activity;

    /**
     * constructor of an annotationAdapter.
     * 
     * @param Activity a
     * @param textViewResourceId
     * @param annoList
     */
    public AnnotationAdapter(Activity a, int textViewResourceId,
	    ArrayList<Annotation> annoList) {
	super(a, textViewResourceId, annoList);
	this.annoList = annoList;
	this.activity = a;
    }

    /**
     * The view that we are going to put information of annotations
     * in.
     * In this class, there are two fields: an ImageView for image annotation
     * and a TextView for text annotation.
     * @author Alice Wu
     *
     */
    public static class ViewHolder {
	public ImageView annoImage;
	public TextView annoText;
    }
    
    /**
     * Overrides getView method in ArrayAdapter.
     * 
     * @param: position
     * @param: convertView
     * @param: parent;
     * @return: View
     */
    public View getView(int position, View convertView, ViewGroup parent) {
	View v = convertView;
	ViewHolder holder;

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

	if (anno != null) {
	    
	    // set the annotation photo if it has
	    if (anno.getPhoto() != null && !anno.getPhoto().isEmpty()) {
		File file = activity.getFilesDir();
		File[] fileList = file.listFiles();
		File annoFile;
		for (int i = 0; i < fileList.length; i++) {
		    if (fileList[i].getName().equals(anno.getPhoto())) {
			annoFile = fileList[i];
			String path = annoFile.getAbsolutePath();
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

	    // set the text annotation if the annotation has
	    if (anno.getText() != null && !anno.getText().isEmpty()) {
		holder.annoText.setText(anno.getText());
	    } else {
		holder.annoText.setText("No Text Annotations.");
	    }
	}

	return v;
    }

}
