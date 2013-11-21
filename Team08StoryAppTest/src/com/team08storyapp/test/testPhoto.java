package com.team08storyapp.test;


import android.test.ActivityInstrumentationTestCase2;


import com.team08storyapp.MainActivity;
import com.team08storyapp.Photo;


public class testPhoto extends
ActivityInstrumentationTestCase2<MainActivity>{


    
    public testPhoto() {
	super(MainActivity.class);
    }

    public void setUp(){
	
    }
    
    /*
     * Constructor Test for Photo object. Set parameters photoId, pictureName,
     * and encodedPicture.
     */
    public void testConstructorPhoto() {

	Photo photo = new Photo();

	photo.setPhotoID(1);
	photo.setPictureName("photo");
	photo.setEncodedPicture("encoded");

	assertEquals(1, photo.getPhotoID());
	assertEquals("photo", photo.getPictureName());
	assertEquals("encoded", photo.getEncodedPicture());

    }
}
