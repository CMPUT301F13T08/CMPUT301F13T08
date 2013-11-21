package com.team08storyapp.test;


import android.test.ActivityInstrumentationTestCase2;

import com.team08storyapp.Annotation;
import com.team08storyapp.MainActivity;


public class testAnnotation extends
ActivityInstrumentationTestCase2<MainActivity>{
    
    public testAnnotation() {
	super(MainActivity.class);
    }

    public void setUp(){
	
    }
    
    /*
     * Constructor Test for Annotation object. Set parameters annotationId,
     * encodedAnnotation, photo, storyFragmentId, and text.
     */
    public void testConstructorAnnotation() {

	Annotation annotation = new Annotation();

	annotation.setAnnotationID(1);
	annotation.setEncodedAnnotation("encoded");
	annotation.setPhoto("photo");
	annotation.setStoryFragmentID(2);
	annotation.setText("text");

	assertEquals(1, annotation.getAnnotationID());
	assertEquals("encoded", annotation.getEncodedAnnotation());
	assertEquals("photo", annotation.getPhoto());
	assertEquals(2, annotation.getStoryFragmentID());
	assertEquals("text", annotation.getText());
    }
    
}
