package com.team08storyapp.test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.test.ActivityInstrumentationTestCase2;

import com.team08storyapp.Photo;
import com.team08storyapp.FileHelper;
import com.team08storyapp.MyStoriesActivity;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;
import com.team08storyapp.StoryFragmentActivity;
import com.team08storyapp.MultimediaController;

import dalvik.annotation.TestTargetClass;

@Ignore
public class testMultimediaController extends
	ActivityInstrumentationTestCase2<StoryFragmentActivity> {
    private MultimediaController mMController;

    private Activity testActivity;
    private Context testContext;
    private Story testCurrentStory;
    private StoryFragment testCurrentStoryFragment;
    ArrayList<StoryFragment> testStoryFragmentList;
    ArrayList<Photo> testPhotoList;
    private FileHelper testFHelper;
    private Uri testAudioUri;
    private Uri testVideoUri;

    public testMultimediaController() {
	super(StoryFragmentActivity.class);
    }

    @Test
    public void setUp() throws FileNotFoundException, IOException {
	/*
	 * Initialize testUri with a mock string Initialize the activity and
	 * context, file helper, and test story objects
	 */
	testAudioUri = Uri.parse("Audiotest");
	testVideoUri = Uri.parse("Videotest");

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

	/* The MultimediaController initialization */
	mMController = new MultimediaController();
    }

    /*
     * Test Case for Use Cases
     * 
     * the testSaveAudio method tests saving the audio and updating the current
     * fragment.
     * 
     * The test uses a multimedia controller to save an audio. Then it tests if
     * the returned object is null. If it is not null than an Assertion error is
     * thrown.
     * 
     * Input parameters: a mock testAudioUri The future saveAudio() should
     * return a null object.
     */
    @Test
    public void testSaveAudio() {
	assertNull(mMController.saveAudio(testAudioUri));
    }

    /*
     * Test Case for Use Cases
     * 
     * the testSaveVideo method tests saving the video and updating the current
     * fragment.
     * 
     * The test uses a multimedia controller to save an video. Then it tests if
     * the returned object is null. If it is not null than an Assertion error is
     * thrown.
     * 
     * Input parameters: a mock testVideoUri The future saveVideo() should
     * return a null object.
     */
    @Test
    public void testSaveVideo() {
	assertNull(mMController.saveVideo(testVideoUri));
    }

    /*
     * testCurrentStoryFragment has no audio or video, so currentPosition()
     * should return -1
     */
    @Test
    public void testCurrentPosition() {
	mMController.setCurrentStoryFragment();
	assertEquals(mMController.currentPosition(), -1);
    }

    /*
     * Delete the file used to test saveAudio() and saveVideo() after the
     * testcase has run
     */
    @Test
    public void tearDown() {
	testContext.deleteFile("Download14");
    }

}
