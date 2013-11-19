package com.team08storyapp.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.test.ActivityInstrumentationTestCase2;

import com.team08storyapp.FileHelper;
import com.team08storyapp.MyStoriesActivity;
import com.team08storyapp.Photo;
import com.team08storyapp.PhotoController;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;
import com.team08storyapp.StoryFragmentActivity;

import dalvik.annotation.TestTargetClass;

@SuppressWarnings("deprecation")
@TestTargetClass(MyStoriesActivity.class)
public class testPhotoController extends
	ActivityInstrumentationTestCase2<StoryFragmentActivity> {

    private PhotoController phController;

    private Activity testActivity;
    private Context testContext;
    private Story testCurrentStory;
    private StoryFragment testCurrentStoryFragment;
    ArrayList<StoryFragment> testStoryFragmentList;
    ArrayList<Photo> testPhotoList;
    private FileHelper testFHelper;
    private Uri testUri;

    public testPhotoController() {
	super(StoryFragmentActivity.class);
    }

    public void setUp() throws FileNotFoundException, IOException {

	/*
	 * Initialize testUri with a mock string Initialize the activity and
	 * context, file helper, and test story objects
	 */
	testUri = Uri.parse("test");
	
	testFHelper = new FileHelper(testContext, 0);
	testCurrentStory = new Story(14, "Spaceman Spiff", "Calvin");

	testCurrentStoryFragment = new StoryFragment(1, "Test text.");
	testStoryFragmentList = new ArrayList<StoryFragment>();
	testStoryFragmentList.add(testCurrentStoryFragment);

	testCurrentStory.setStoryFragments(testStoryFragmentList);

	Intent intent = new Intent();
	intent.putExtra("story", testCurrentStory);
	intent.putExtra("storyFragmentId", 1);
	super.setActivityIntent(intent);
	testActivity = super.getActivity();
	testContext = super.getInstrumentation().getContext();

	
	/* The PhotoController initialization */
	phController = new PhotoController(testActivity, testContext,
		testCurrentStory, testCurrentStoryFragment, 0, testFHelper);
    }

    /*
     * Test Case for Use Cases 6, 11
     * 
     * The testSavePhoto method tests saving the illustrations and updating the
     * current fragment.
     * 
     * The test uses a photo controller to save a photo. Then it tests if the
     * returned object is null. If it is not null than an Assertion Error is
     * thrown.
     * 
     * Input parameters: a mock testUri The savePhoto() should return a null
     * Bitmap object
     */
    public void testSavePhoto() {
	assertNull(phController.savePhoto(testUri));
    }

    /*
     * testCurrentStoryFragment has no Photos, so currentPosition() should
     * return -1
     */
    public void testCurrentPosition() {
	phController.setCurrentStoryFragment(testCurrentStoryFragment);
	assertEquals(phController.currentPosition(), -1);
    }

    /* Delete the file used to test savePhoto() after the testcase has run */
    public void tearDown() {
	testContext.deleteFile("Download14");
    }

}
