package src.com.team08storyapp.test;

import android.provider.ContactsContract.CommonDataKinds.Photo;

import com.team08storyapp.Story;

// test cases for methods of the PhotoController class
public class testPhotoController extends TestCase
{

// test the method TakePhoto. If a photo is successfully taken, then the picture parameter
// of the photo object should contain the picture (not be null)
public void testTakePhoto(){
		
	
		Photo photo = PhotoController.takePhoto();
		
		assertNotNull(photo.picture);

	}

// uploading a photo into a photo object should set the picture parameter to the uploaded image.
// the picture should not be null
public void testUploadPhoto(){
	
	
	Photo photo = PhotoController.uploadPhoto();
	
	assertNotNull(photo.picture);
	
}
	
}
