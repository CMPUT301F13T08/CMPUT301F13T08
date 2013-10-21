package src.com.team08storyapp.test;

import android.provider.ContactsContract.CommonDataKinds.Photo;

import com.team08storyapp.Story;


public class testPhotoController extends TestCase
{

	
public void testTakePhoto(){
		
	
		Photo photo = PhotoController.takePhoto();
		
		assertNotNull(photo.picture);

	}
	
public void testUploadPhoto(){
	
	
	Photo photo = PhotoController.uploadPhoto();
	
	assertNotNull(photo.picture);
	
}
	
}
