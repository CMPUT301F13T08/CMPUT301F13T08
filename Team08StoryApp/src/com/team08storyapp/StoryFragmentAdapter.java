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
 * StoryFragmentAdapter is a customized ArrayAdapter that is designed for
 * populating the images and text (if applicable) into right views in a
 * ListView. This class requires at least a list of story fragments as the
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
public class StoryFragmentAdapter extends ArrayAdapter<StoryFragment> {
    private ArrayList<StoryFragment> storyFragmentList;
    private Activity activity;

    /**
     * The constructor of an StoryFragmentAdapter requires at least a list of
     * story fragments as the information provider and an activity to get
     * LAYOUT_INFLATER_SERVICE.
     * 
     * @param activity
     *            an activity object that provides LAYOUT_INFLATER_SERVICE
     * @param textViewResourceId
     *            the int value of the id of the view
     * @param stroyFragmentList
     *            a list of StoryFragment objects
     */
    public StoryFragmentAdapter(Activity activity, int textViewResourceId,
	    ArrayList<StoryFragment> storyFragmentList) {
	super(activity, textViewResourceId, storyFragmentList);
	this.activity = activity;
	this.storyFragmentList = storyFragmentList;
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
	    v = vi.inflate(R.layout.storyfragments_row, null);
	    holder = new ViewHolder();
	    holder.fragmentImage = (ImageView) v.findViewById(R.id.thumbnail);
	    holder.fragmentText = (TextView) v.findViewById(R.id.fragmentText);
	    v.setTag(holder);
	} else {
	    holder = (ViewHolder) v.getTag();
	}

	if (storyFragmentList == null) {
	    return v;
	}

	final StoryFragment currentStoryFragment = storyFragmentList
		.get(position);

	/*
	 * If the annotation is not a null object and really has a photo then
	 * just read the photo as a byteArray and set it to the layout's Image
	 * view. Else set the default launch icon to the image view.
	 */
	if (currentStoryFragment != null) {
	    if (!currentStoryFragment.getPhotos().isEmpty()) {
		if (currentStoryFragment.getPhotos().get(0) != null) {
		    File file = activity.getFilesDir();
		    File[] fileList = file.listFiles();
		    File sfFile;
		    for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].getName().equals(
				currentStoryFragment.getPhotos().get(0)
					.getPictureName())) {
			    sfFile = fileList[i];
			    String path = sfFile.getAbsolutePath();

			    Bitmap placeholder = BitmapFactory.decodeFile(path);
			    ByteArrayOutputStream stream = new ByteArrayOutputStream();
			    placeholder.compress(Bitmap.CompressFormat.PNG, 80,
				    stream);
			    byte[] bytePicture = stream.toByteArray();
			    holder.fragmentImage.setImageBitmap((BitmapFactory
				    .decodeByteArray(bytePicture, 0,
					    bytePicture.length)));
			    break;
			}
		    }
		} else {
		    holder.fragmentImage.setImageBitmap(BitmapFactory
			    .decodeResource(activity.getResources(),
				    R.drawable.ic_launcher));
		}
	    } else {
		holder.fragmentImage.setImageBitmap(BitmapFactory
			.decodeResource(activity.getResources(),
				R.drawable.ic_launcher));
	    }

	    /*
	     * If the annotation has its own text then set the text to the
	     * textView else set the message to "No Text Annotation".
	     */

	    if (currentStoryFragment.getStoryText() != null
		    && !currentStoryFragment.getStoryText().isEmpty()) {
		if (currentStoryFragment.getStoryText().length() > 30) {
		    holder.fragmentText.setText(currentStoryFragment
			    .getStoryText().substring(0, 29) + "...");
		} else {
		    holder.fragmentText.setText(currentStoryFragment
			    .getStoryText());
		}
	    } else {
		holder.fragmentText.setText("No Text.");
	    }
	}

	return v;
    }

    /**
     * ViewHolder is a like a struct in C++. Only hold two view objects for the
     * population purpose of StoryFragmentAdapter
     */
    public static class ViewHolder {
	public ImageView fragmentImage;
	public TextView fragmentText;
    }

}
