package com.team08storyapp.test;

import java.util.ArrayList;

import android.test.ActivityInstrumentationTestCase2;

import com.team08storyapp.Annotation;
import com.team08storyapp.Choice;
import com.team08storyapp.MainActivity;
import com.team08storyapp.Photo;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;


public class testStory extends
ActivityInstrumentationTestCase2<MainActivity>{

   
    private StoryFragment firstStoryFragment;
    private StoryFragment secondStoryFragment;
    ArrayList<StoryFragment> storyFragmentList;
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
    
    
    public testStory() {
	super(MainActivity.class);
    }

    public void setUp(){
    
	storyFragmentList = new ArrayList<StoryFragment>();

	firstStoryFragment = new StoryFragment(1, "fragment text");
	secondStoryFragment = new StoryFragment(2, "another fragment text");

	storyFragmentList.add(firstStoryFragment);
	storyFragmentList.add(secondStoryFragment);
	
	
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
     * Constructor Test for Story object, with parameters title and author. Also
     * set firstStoryFragmentId, offlineStoryId, onlineStoryId, and
     * storyFragments.
     */
    public void testFirstConstructorStory() {
	Story story = new Story("title", "author");

	assertEquals("title", story.getTitle());
	assertEquals("author", story.getAuthor());

	story.setFirstStoryFragmentId(1);
	story.setOfflineStoryId(2);
	story.setOnlineStoryId(3);
	story.setStoryFragments(storyFragmentList);

	assertEquals(1, story.getFirstStoryFragmentId());
	assertEquals(2, story.getOfflineStoryId());
	assertEquals(3, story.getOnlineStoryId());
	assertEquals(1, story.getStoryFragments().get(0).getStoryFragmentId());
    }

    /*
     * Constructor Test for Story object, with parameters offlineStorId, title,
     * and author. Also set firstStoryFragmentId, onlineStoryId, and
     * storyFragments.
     */
    public void testSecondConstructorStory() {
	Story story = new Story(1, "title", "author");

	assertEquals(1, story.getOfflineStoryId());
	assertEquals("title", story.getTitle());
	assertEquals("author", story.getAuthor());

	story.setFirstStoryFragmentId(1);
	story.setOnlineStoryId(3);
	story.setStoryFragments(storyFragmentList);

	assertEquals(1, story.getFirstStoryFragmentId());
	assertEquals(3, story.getOnlineStoryId());
	assertEquals(1, story.getStoryFragments().get(0).getStoryFragmentId());

    }
    
    
}
