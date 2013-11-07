package com.team08storyapp.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

import org.junit.Before;
import org.junit.Test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.test.AndroidTestCase;

import com.team08storyapp.AnnotationController;
import com.team08storyapp.ESHelper;
import com.team08storyapp.FileHelper;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;

public class testAnnotationController extends AndroidTestCase {

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

	anController = new AnnotationController(testActivity, testContext,
		testStory, testStoryFragment, testCurrentStoryFragmentIndex,
		testFHelper, testESHelper);

	testUri = Uri.parse("teststring");

    }
    
   /* // TODO: write test case
    public void testSavePhoto() {
	testBitmap = anController.savePhoto(testUri, 1);
	assertNull(testBitmap);
    }*/
   


}
