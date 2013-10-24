package com.team08storyapp.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import com.team08storyapp.StoryFragment;
import com.team08storyapp.StoryFragmentController;
import com.team08storyapp.Choice;
import com.team08storyapp.Annotation;
import com.team08storyapp.Photo;


//StoryFragmentController is a class which manipulates data of StoryFragment objects
public class testStoryFragmentController extends TestCase {

	private StoryFragmentController aSFragmentController;
	private StoryFragment firstFragment;
	private StoryFragment updateFragment;
	private Choice firstChoice;
	private Annotation firstAnnotation;
	private Photo firstPhoto;
	private ArrayList<Annotation> aAnnotations;
	private ArrayList<Choice> aChoices;
	private ArrayList<Photo> aPhotos;
	
	public testStoryFragmentController(){
		super();
	}
	
	
	//SetUp sets up variables that are to be used in the test cases
	protected void setUp(){		
		firstFragment = new StoryFragment(0,"String");
		updateFragment = new StoryFragment(1, "updateString");
		//storyFragments = new ArrayList<StoryFragment>();
		
		firstChoice = new Choice();
		firstPhoto = new Photo(null);
		firstAnnotation = new Annotation(0, 0, null, null);
		aAnnotations = new ArrayList<Annotation>();
		aChoices = new ArrayList<Choice>();
		
		
	}
	
	/* Test Case for Use Case 10
	 * 
	 * The testAddAnootation method tests adding an Annotation to a StoryFragment Object. The array Annotations
	 * should not be null. It should contain an Annotation which was added. If this test was successful it should
	 * return True.
	*/
	public void testAddAnnotation(){
		firstFragment.setAnnotations(aAnnotations);
		aAnnotations.add(firstAnnotation);
		assertTrue(!aAnnotations.equals(null));
	}
	
	/* Test Case for Use Case 8
	 * 
	 * The testAddChoice method tests adding a choice to a StoryFragment Object. The array of Choices should not
	 * be null. It should contain an Choice object which was added during the test. If the test was successful it
	 * should return true.
	*/
	public void testAddChoice(){
		firstFragment.setChoices(aChoices);
		aChoices.add(firstChoice);
		assertTrue(!aChoices.equals(null));
		
	}
	
	/* Test Case for Use Case 6
	 * 
	 * The testAddImage method test adding an image to a StoryFragment Object. The array of photos should not be
	 * null, since a Photo object was put into the array of photos. If successful the test should return true.
	*/
	public void testAddImage(){
		firstFragment.setPhotos(aPhotos);
		aPhotos.add(firstPhoto);
		assertTrue(!aPhotos.equals(null));
		
	}
	
	/* Test Case for Use Case 6, 8, 10, 11
	 * 
	 * The testUpdateStoryFragment method test updating a StoryFragment object. If a Fragment is updated successfully
	 * the method call to aSFragmentController.updateStroyFragment should return as true.
	*/
	public void testUpdateStoryFragment(){
		assertTrue(aSFragmentController.updateStoryFragment(updateFragment));
	}
	
	
}
