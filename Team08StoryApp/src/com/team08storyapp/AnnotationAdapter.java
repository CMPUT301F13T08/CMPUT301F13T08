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

public class AnnotationAdapter extends ArrayAdapter<Annotation> {
    private ArrayList<Annotation> annoList;
    private Activity activity;

    public AnnotationAdapter(Activity a, int textViewResourceId,
	    ArrayList<Annotation> annoList) {
	super(a, textViewResourceId, annoList);
	this.annoList = annoList;
	this.activity = a;
    }

    public static class ViewHolder {
	public ImageView annoImage;
	public TextView annoText;
    }

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
	    if (!anno.getPhoto().isEmpty()) {

		File file = activity.getFilesDir();
		File[] fileList = file.listFiles();
		File annoFile;
		for (int i = 0; i < fileList.length; i++) {
		    if (fileList[i].getName() == anno.getPhoto()) {
			annoFile = fileList[i];
			String path = annoFile.getAbsolutePath();
			Bitmap placeholder = BitmapFactory.decodeFile(path);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			placeholder.compress(Bitmap.CompressFormat.PNG, 80,
				stream);
			byte[] bytePicture = stream.toByteArray();

			System.out.println("*****ByteArray Done******");

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

	    if (!anno.getText().isEmpty()) {
		holder.annoText.setText(anno.getText());
	    } else {
		holder.annoText.setText("");
	    }
	} else {
	    holder.annoText
		    .setText("No annotations for current story fragment.");
	    holder.annoImage.setImageBitmap(BitmapFactory.decodeResource(
		    activity.getResources(), R.drawable.ic_launcher));
	}

	return v;
    }

}
