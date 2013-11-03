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
	public PicAdapter(Context c, ArrayList<Photo> photoList, int currentStoryId, int currentStoryFragmentId) {

	    // instantiate context
	    galleryContext = c;

	    // create bitmap array
	    imageBitmaps = new Bitmap[10];
	    // decode the placeholder image
	    placeholder = BitmapFactory.decodeResource(galleryContext.getResources(),
		    R.drawable.ic_launcher);

	    System.out.println(photoList.size());

	    // decode the placeholder image
	    placeholder = BitmapFactory.decodeResource(galleryContext.getResources(),
		    R.drawable.ic_launcher);

	    // set placeholder as all thumbnail images in the gallery initially
	    for (int i = 0; i < imageBitmaps.length; i++)
		imageBitmaps[i] = placeholder;

	    if (photoList.size() > 0) {
		File file = galleryContext.getFilesDir();
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
	public void addPic(int currentPic, Bitmap newPic) {
	    // set at currently selected index
	    imageBitmaps[currentPic] = newPic;
	}

	// return bitmap at specified position for larger display
	public Bitmap getPic(int posn) {
	    // return bitmap at posn index
	    return imageBitmaps[posn];
	}
}  