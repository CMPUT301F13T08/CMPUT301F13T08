package com.team08storyapp.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.test.ActivityInstrumentationTestCase2;

import com.team08storyapp.AnnotationController;
import com.team08storyapp.ESHelper;
import com.team08storyapp.FileHelper;
import com.team08storyapp.Photo;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;
import com.team08storyapp.StoryFragmentActivity;

public class testAnnotationController extends
	ActivityInstrumentationTestCase2<StoryFragmentActivity> {

    public testAnnotationController() {
	super(StoryFragmentActivity.class);
    }

    private AnnotationController anController;
    private Activity testActivity;
    private Context testContext;
    private Story testStory;
    private StoryFragment testStoryFragment;
    private FileHelper testFHelper;
    private ESHelper testESHelper;
    private Bitmap testBitmap;
    private Uri testUri;
    private ArrayList<StoryFragment> testStoryFragmentList;

    public void setUp() throws FileNotFoundException, IOException {

	/*
	 * Initialize testUri with a mock String Initialize the activity and
	 * context as well
	 */
	testUri = Uri.parse("test");

	/*
	 * Initialize the setStory object, as well as the file helper and the es
	 * helper
	 */
	ArrayList<Photo> pList = new ArrayList<Photo>();
	testStory = new Story(15, "newstory", "me");
	testStory.setOnlineStoryId(100);
	testFHelper = new FileHelper(testContext, 0);
	testESHelper = new ESHelper();

	testStoryFragment = new StoryFragment(1, "Test text.");
	testStoryFragment.setPhotos(pList);
	testStoryFragmentList = new ArrayList<StoryFragment>();
	testStoryFragmentList.add(testStoryFragment);
	testStory.setStoryFragments(testStoryFragmentList);

	Intent intent = new Intent();
	intent.putExtra("story", testStory);
	intent.putExtra("storyFragmentId", 1);
	super.setActivityIntent(intent);
	testActivity = super.getActivity();
	testContext = super.getInstrumentation().getContext();

	/* The AnnotationController initialization */
	anController = new AnnotationController(testActivity, testContext,
		testStory, testStoryFragment, 0, testFHelper, testESHelper);

    }

    /*
     * Test Case for Use Cases 6, 11
     * 
     * The testSavePhoto method tests saving the illustrations and updating the
     * current fragment.
     * 
     * The test uses an annotation controller to save a photo. Then it tests if
     * the returned object is null. If it is not null than an Assertion Error is
     * thrown.
     * 
     * parameters: mock testUri and mode as 0 testSavePhoto should return a null
     * Bitmap object
     */
    public void testSavePhoto() {
	testBitmap = anController.savePhoto(testUri, 0);
	assertNull(testBitmap);
    }

    /* Delete the file used to test savePhoto() after the testcase has run */
    @SuppressWarnings("deprecation")
    public void tearDown() throws Exception{

	/* Delete the story added to online */
	@SuppressWarnings("resource")
	HttpClient httpclient = new DefaultHttpClient();
	HttpDelete httpDelete = new HttpDelete(
		"http://cmput301.softwareprocess.es:8080/cmput301f13t08/stories/"
			+ 100);
	httpDelete.addHeader("Accept", "application/json");

	HttpResponse response = httpclient.execute(httpDelete);

	String status = response.getStatusLine().toString();
	System.out.println(status);

	HttpEntity entity = response.getEntity();
	BufferedReader br = new BufferedReader(new InputStreamReader(
		entity.getContent()));
	String output;
	System.err.println("Output from Server -> ");
	while ((output = br.readLine()) != null) {
	    System.err.println(output);
	}
	testContext.deleteFile("Download15");
    }

}
