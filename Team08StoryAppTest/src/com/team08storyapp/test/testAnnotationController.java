package com.team08storyapp.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.test.ActivityTestCase;
import android.test.AndroidTestCase;
import android.test.ApplicationTestCase;

import com.team08storyapp.AnnotationController;
import com.team08storyapp.ESHelper;
import com.team08storyapp.FileHelper;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;


public class testAnnotationController extends ActivityTestCase {



    private AnnotationController anController;
    private Activity testActivity;
    private Context testContext;
    private Story testStory;
    private StoryFragment testStoryFragment;
    private int testCurrentStoryFragmentIndex;
    private FileHelper testFHelper;
    private ESHelper testESHelper;
    private Bitmap testBitmap;
    private Uri testUri;



    @Before
    public void setUp() throws FileNotFoundException, IOException {
	testCurrentStoryFragmentIndex = 1;

	testUri = Uri.parse("test");
	testActivity = super.getActivity();
	testContext = super.getInstrumentation().getContext();
	

	testStory = new Story("newstory", "me");
	testFHelper = new FileHelper(testContext, testCurrentStoryFragmentIndex);
	testESHelper = new ESHelper();
	
	anController = new AnnotationController(testActivity, testContext,
		testStory, testStoryFragment, testCurrentStoryFragmentIndex,
		testFHelper, testESHelper);

	

    }
    
    // TODO: write test case
    public void testSavePhoto() {
	testBitmap = anController.savePhoto(testUri, 0);
	assertNull(testBitmap);
    }
   


}
