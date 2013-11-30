/*
AUTHORS
========
Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen.

LICENSE
=======
Copyright  ���  2013 Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen,  
Free Software Foundation, Inc., Marky Mark  License GPLv3+: GNU
GPL version 3 or later <http://gnu.org/licenses/gpl.html>.
This program is free software: you can redistribute it and/or modify it under the terms of 
the GNU General Public License as published by the Free Software Foundation, either 
version 3 of the License, or (at your option) any later version. This program is distributed 
in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 
warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public 
License for more details. You should have received a copy of the GNU General Public License 
along with this program.  If not, see <http://www.gnu.org/licenses/>.
              
3rd Party Libraries
=============
Retrieved Oct. 27, 2013 - https://github.com/rayzhangcl/ESDemo
-This demo was used to help with JSON and ESHelper which is under the CC0 licenses

Retrieved Oct. 29, 2013  - http://hc.apache.org/downloads.cgi
-This is for the fluent library which is licensed under apache V2

Retrieved Oct. 29, 2013 
- https://code.google.com/p/google-gson/downloads/detail?name=google-gson-2.2.4-release.zip&can=2&q=
-This is for JSON which is licensed under apache V2
 */

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
import com.team08storyapp.MultimediaController;
import com.team08storyapp.Video;
import com.team08storyapp.Audio;
import com.team08storyapp.MultimediaController;
import com.team08storyapp.FileHelper;
import com.team08storyapp.MyStoriesActivity;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;
import com.team08storyapp.StoryFragmentActivity;

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
	mMController = new MultimediaController(testActivity, testContext,
		testCurrentStory, testCurrentStoryFragment, 0, testFHelper);
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
	mMController.setCurrentStoryFragment(testCurrentStoryFragment);
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
