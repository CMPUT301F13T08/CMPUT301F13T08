package com.team08storyapp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.util.Base64;

public class Encoder {

    private Context fileContext;

    public Encoder(Context context) {
	fileContext = context;
    }

    /**
     * Function takes in a story object and encodes all the image files related
     * to the story into a Base64 string for further use. (Convert to Json
     * Object and upload to webserver)
     * 
     * @param s
     *            a story that needs to be encoded for uploading to webserver
     * @return s an encoded story object
     * @throws IOException
     */
    public Story encodeStory(Story s) throws IOException {

	/* get all fragments */
	ArrayList<StoryFragment> sfList = s.getStoryFragments();

	/* for each fragment, get it's photolist and annotation list */
	for (int i = 0; i < sfList.size(); i++) {
	    ArrayList<Photo> photos = sfList.get(i).getPhotos();
	    ArrayList<Annotation> annotations = sfList.get(i).getAnnotations();

	    /* set picture in each Photo object to empty after encoding. */
	    for (int m = 0; m < photos.size(); m++) {
		try {
		    InputStream is = fileContext.openFileInput(photos.get(m)
			    .getPictureName());
		    ByteArrayOutputStream bos = new ByteArrayOutputStream();
		    byte[] b = new byte[1024];
		    int bytesRead = 0;
		    while ((bytesRead = is.read(b)) != -1) {
			bos.write(b, 0, bytesRead);
		    }
		    byte[] bytes = bos.toByteArray();

		    photos.get(m).setEncodedPicture(
			    Base64.encodeToString(bytes, Base64.DEFAULT));
		} catch (Exception e) {
		    continue;
		}
	    }

	    /* Encode the photo annotation object first and clear the photo. */
	    for (int n = 0; n < annotations.size(); n++) {
		try {
		    InputStream is = fileContext.openFileInput(annotations.get(
			    n).getPhoto());
		    ByteArrayOutputStream bos = new ByteArrayOutputStream();
		    byte[] b = new byte[1024];
		    int bytesRead = 0;
		    while ((bytesRead = is.read(b)) != -1) {
			bos.write(b, 0, bytesRead);
		    }
		    byte[] bytes = bos.toByteArray();
		    annotations.get(n).setEncodedAnnotation(
			    Base64.encodeToString(bytes, Base64.DEFAULT));
		} catch (Exception e) {
		    continue;
		}
	    }

	    /* update the annotations list and photos list of a story */
	    sfList.get(i).setAnnotations(annotations);
	    sfList.get(i).setPhotos(photos);
	}
	return s;

    }

}
