package com.team08storyapp.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import junit.framework.TestCase;

import com.team08storyapp.Choice;
import com.team08storyapp.Photo;
import com.team08storyapp.SQLiteHelper;
import com.team08storyapp.Story;
import com.team08storyapp.StoryController;
import com.team08storyapp.StoryFragment;
import com.team08storyapp.Annotation;

public class testStoryFragment extends TestCase{

	private String storyAuthor;
	private ArrayList<java.lang.annotation.Annotation> fragAnnotations;
	private ArrayList<Choice> fragChoices;
	private ArrayList<Photo> fragPhotos;
	private int fragmentID;
	
	protected void setUp(){
		fragmentID = 1;
		storyAuthor = "Authour";
		fragAnnotations = new ArrayList<Annotation>();
		fragChoices = new ArrayList<Choice>();
		fragPhoto = new ArrayList<Photo>();
		
	}
	
	
	public void test() {
		fail("Not yet implemented");
	}

}
