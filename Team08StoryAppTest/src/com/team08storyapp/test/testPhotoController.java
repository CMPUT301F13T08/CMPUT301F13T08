package com.team08storyapp.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.test.AndroidTestCase;

import com.team08storyapp.FileHelper;
import com.team08storyapp.Photo;
import com.team08storyapp.PhotoController;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;

public class testPhotoController extends AndroidTestCase {

    private PhotoController phController;

    private Activity testActivity;
    private Context testContext;
    private Story testCurrentStory;
    private StoryFragment testCurrentStoryFragment;
    private Photo testPhoto1;
    private Photo testPhoto2;
    ArrayList<StoryFragment> testStoryFragmentList;
    ArrayList<Photo> testPhotoList;
    private int testCurrentStoryFragmentIndex;
    private FileHelper testFHelper;
    private Uri testUri;

   /* public testPhotoController() {
	super();
    }*/

    @Before
    public void setUp() throws FileNotFoundException, IOException {
	testUri = Uri.parse("test");

	testCurrentStory = new Story(14,"Spaceman Spiff", "Calvin");
	testCurrentStoryFragment = new StoryFragment(1, "Test text.");
	//testPhoto1 = new Photo();
	//testPhoto2 = new Photo();
	//testPhotoList.add(testPhoto1);
	//testPhotoList.add(testPhoto2);
	testStoryFragmentList = new ArrayList<StoryFragment>();
	testStoryFragmentList.add(testCurrentStoryFragment);
	testCurrentStory.setStoryFragments(testStoryFragmentList);
	phController = new PhotoController(testActivity, testContext,
		testCurrentStory, testCurrentStoryFragment,
		testCurrentStoryFragmentIndex, testFHelper);
    }
    
    // TODO: write test case
    public void testSavePhoto(){

	assertNull(phController.savePhoto(testUri));
    }

    // TODO: write test case
    public void testCurrentPosition() {

    }
    
    
    
    
   /* @Test
    public void test() {
	fail("Not yet implemented");
    }
*/
}
