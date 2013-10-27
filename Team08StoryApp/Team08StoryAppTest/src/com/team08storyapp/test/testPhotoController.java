package com.team08storyapp.test;

import junit.framework.TestCase;

import com.team08storyapp.Photo;
import com.team08storyapp.PhotoController;

// test cases for methods of the PhotoController class
public class testPhotoController extends TestCase
{
	
// relevant for use cases 6, 11, and 15
// test the method TakePhoto. If a photo is successfully taken, then the picture parameter
// of the photo object should contain the picture (not be null)
public void testTakePhoto(){
		
	
		Photo photo = PhotoController.takePhoto();
		
		assertNotNull(photo.getPicture());

	}
	
	
// relevant for use cases 6, 11, 14, and 15
// uploading a photo into a photo object should set the picture parameter to the uploaded image.
// the picture should not be null
public void testUploadPhoto(){
	
	
	Photo photo = PhotoController.uploadPhoto();
	
	assertNotNull(photo.getPicture());
	
}
	
}
