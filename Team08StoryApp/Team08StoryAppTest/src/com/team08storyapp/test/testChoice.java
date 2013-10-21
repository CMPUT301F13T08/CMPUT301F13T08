package com.team08storyapp.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

import com.team08storyapp.Choice;
import com.team08storyapp.Photo;
import com.team08storyapp.SQLiteHelper;
import com.team08storyapp.Story;
import com.team08storyapp.StoryController;
import com.team08storyapp.StoryFragment;

public class testChoice extends TestCase {

	private int choiceID;
	private String choiceTitle;
	private int fragmentID;
	
	
	protected void setup(){
		choiceID = 1;
		choiceTitle = "Choice";
		fragmentID = 1;
	}
	
	public void test() {
		fail("Not yet implemented");
	}

}
