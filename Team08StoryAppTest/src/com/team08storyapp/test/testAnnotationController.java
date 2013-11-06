package com.team08storyapp.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import android.app.Activity;
import android.content.Context;

import com.team08storyapp.AnnotationController;
import com.team08storyapp.ESHelper;
import com.team08storyapp.FileHelper;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;

public class testAnnotationController extends TestCase {

    private AnnotationController anController;
    private Activity testActivity;
    private Context testContext;
    private Story testStory;
    private StoryFragment testStoryFragment;
    private int testCurrentStoryFragmentIndex;
    private FileHelper testFHelper;
    private ESHelper testESHelper;
    
    public testAnnotationController() {
	super();
    }

    @Before
    public void setUp() throws FileNotFoundException, IOException {
	testCurrentStoryFragmentIndex = 1;
	
	anController = new AnnotationController(testActivity, testContext,
		testStory, testStoryFragment, testCurrentStoryFragmentIndex,
		testFHelper, testESHelper);

    }

    @Test
    public void test() {
	fail("Not yet implemented");
    }

}
