package com.team08storyapp.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.test.AndroidTestCase;

import com.team08storyapp.FileHelper;
import com.team08storyapp.PhotoController;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;

public class testPhotoController extends AndroidTestCase {

    private PhotoController phController;

    private Activity testActivity;
    private Context testContext;
    private Story testCurrentStory;
    private StoryFragment testCurrentStoryFragment;
    private int testCurrentStoryFragmentIndex;
    private FileHelper testFHelper;

   /* public testPhotoController() {
	super();
    }*/

    @Before
    public void setUp() throws FileNotFoundException, IOException {
	phController = new PhotoController(testActivity, testContext,
		testCurrentStory, testCurrentStoryFragment,
		testCurrentStoryFragmentIndex, testFHelper);
    }
    
    // TODO: write test case
    public void testSavePhoto(){
	
    }

    // TODO: write test case
    public void testCurrentPosition() {
	
    }
    
    // TODO: write test case
    private void testAddIllustration(){
	
    }
    
    
    
   /* @Test
    public void test() {
	fail("Not yet implemented");
    }
*/
}
