package com.team08storyapp.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.test.ActivityTestCase;

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
    private ArrayList<StoryFragment> testStoryFragmentList;



    @Before
    public void setUp() throws FileNotFoundException, IOException {
	testCurrentStoryFragmentIndex = 1;

	testUri = Uri.parse("test");
	testActivity = super.getActivity();
	testContext = super.getInstrumentation().getContext();
	

	testStory = new Story(15,"newstory", "me");
	testFHelper = new FileHelper(testContext, 0);
	testESHelper = new ESHelper();
	
	testStoryFragment = new StoryFragment(1, "Test text.");
	testStoryFragmentList = new ArrayList<StoryFragment>();
	testStoryFragmentList.add(testStoryFragment);
	
	anController = new AnnotationController(testActivity, testContext,
		testStory, testStoryFragment, 0,
		testFHelper, testESHelper);

	

    }
    
    // TODO: write test case
    public void testSavePhoto() {
	testBitmap = anController.savePhoto(testUri, 0);
	assertNull(testBitmap);
    }
    
    @After
    public void tearDown(){
	testContext.deleteFile("Download15");
    }
   


}
