package com.team08storyapp.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import com.team08storyapp.Story;
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
	
	//addAnnotation adds an annotation to a StoryFragment Object.
	public void testAddAnnotation(){
		firstFragment.setAnnotations(aAnnotations);
		aAnnotations.add(firstAnnotation);
		assertTrue(!aAnnotations.equals(null));
	}
	
	//addChoice adds a choice to a StoryFragment Object.
	public void testAddChoice(){
		firstFragment.setChoices(aChoices);
		aChoices.add(firstChoice);
		assertTrue(!aChoices.equals(null));
		
	}
	
	//addImage adds an image to a StoryFragment Object.
	public void testAddImage(){
		firstFragment.setPhotos(aPhotos);
		aPhotos.add(firstPhoto);
		assertTrue(!aPhotos.equals(null));
		
	}
	
	//updateStoryFragment updates a fragment that has just been modified.
	public void testUpdateStoryFragment(){
		assertTrue(aSFragmentController.updateStoryfragment(updateFragment));
	}
	
	
}
