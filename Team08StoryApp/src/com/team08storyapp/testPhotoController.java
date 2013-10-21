package com.team08storyapp;

import android.provider.ContactsContract.CommonDataKinds.Photo;

import com.team08storyapp.Story;


public class testPhotoController extends TestCase
{

	private Picture picture;
	
public void testTakePhoto(){
		
		picture = new Picture();
	
		Photo photo = PhotoController.takePhoto();
		
		assertNotNull(photo);
		assertEquals(photo.getPicture(), picture);

	}
	
public void testUploadPhoto(){
	
	picture = new Picture();
	
	Photo photo = PhotoController.uploadPhoto();
	
	assertNotNull(photo);
	assertEquals(photo.getPicture(), picture);
	
}
	
}