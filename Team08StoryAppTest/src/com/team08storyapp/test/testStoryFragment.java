package com.team08storyapp.test;

import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import android.test.ActivityInstrumentationTestCase2;

import com.team08storyapp.Annotation;
import com.team08storyapp.Choice;
import com.team08storyapp.MainActivity;
import com.team08storyapp.Photo;
import com.team08storyapp.StoryFragment;

public class testStoryFragment extends
	ActivityInstrumentationTestCase2<MainActivity> {

    ArrayList<Choice> Choices;
    ArrayList<Annotation> Annotations;
    ArrayList<Photo> Photos;
    private Choice testChoice1;
    private Choice testChoice2;
    private Choice testChoice3;
    private Choice testChoice4;
    private Annotation testAnn1;
    private Annotation testAnn2;
    private Photo testPhoto1;
    private Photo testPhoto2;

    public testStoryFragment() {
	super(MainActivity.class);
    }

    public void setUp() {

	Choices = new ArrayList<Choice>();
	testChoice1 = new Choice(1, 1, "This is test 1");
	testChoice2 = new Choice(2, 2, "This is test 2");
	testChoice3 = new Choice(3, 3, "This is test 3");
	testChoice4 = new Choice(4, 4, "This is test 4");
	Choices.add(testChoice1);
	Choices.add(testChoice2);
	Choices.add(testChoice3);
	Choices.add(testChoice4);

	Annotations = new ArrayList<Annotation>();
	testAnn1 = new Annotation();
	testAnn1.setAnnotationID(1);
	testAnn2 = new Annotation();
	testAnn2.setAnnotationID(2);
	Annotations.add(testAnn1);
	Annotations.add(testAnn2);

	Photos = new ArrayList<Photo>();
	testPhoto1 = new Photo();
	testPhoto1.setPhotoID(1);
	testPhoto2 = new Photo();
	testPhoto2.setPhotoID(2);
	Photos.add(testPhoto1);
	Photos.add(testPhoto2);
    }

    /*
     * Constructor Test for StoryFragment object, with parameters
     * storyFragmentId. Also set storyText, choices, annotations, an photos.
     */
    public void testFirstConstructorStoryFragment() {
	StoryFragment storyFragment = new StoryFragment(1);

	assertEquals(1, storyFragment.getStoryFragmentId());

	storyFragment.setStoryText("text");
	storyFragment.setChoices(Choices);
	storyFragment.setAnnotations(Annotations);
	storyFragment.setPhotos(Photos);

	assertEquals("text", storyFragment.getStoryText());
	assertEquals("This is test 1", storyFragment.getChoices().get(0)
		.getText());
	assertEquals(2, storyFragment.getAnnotations().get(1).getAnnotationID());
	assertEquals(2, storyFragment.getPhotos().get(1).getPhotoID());
    }

    /*
     * Constructor Test for StoryFragment object, with parameters
     * storyFragmentId and storyText. Also set choices, annotations, photos
     */
    public void testSecondConstructorStoryFragment() {
	StoryFragment storyFragment = new StoryFragment(1, "text");

	assertEquals(1, storyFragment.getStoryFragmentId());
	assertEquals("text", storyFragment.getStoryText());

	storyFragment.setChoices(Choices);
	storyFragment.setAnnotations(Annotations);
	storyFragment.setPhotos(Photos);

	assertEquals("This is test 1", storyFragment.getChoices().get(0)
		.getText());
	assertEquals(2, storyFragment.getAnnotations().get(1).getAnnotationID());
	assertEquals(2, storyFragment.getPhotos().get(1).getPhotoID());

    }

    /*
     * Tests the setting of read only permissions of a story fragment object
     */
    @Ignore("Not ready yet")
    @Test
    public void testFragmentPermission() {
	StoryFragment storyFragment = new StoryFragment(1, "text");
	storyFragment.setChoices(Choices);
	storyFragment.setAnnotations(Annotations);
	storyFragment.setPhotos(Photos);
	storyFragment.setFragmentPermission(1);

	assertEquals(1, testFragmentPermission());
	storyFragment.setFragmentPermission(0);
	assertEquals(0, testFragmentPermission());



    }

}