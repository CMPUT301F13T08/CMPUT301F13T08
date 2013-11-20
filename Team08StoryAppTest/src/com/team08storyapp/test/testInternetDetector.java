package com.team08storyapp.test;

import junit.framework.TestCase;

import com.team08storyapp.InternetDetector;

public class testInternetDetector extends TestCase {

    public testInternetDetector() {

    }

    public void testGetInstance() {
	InternetDetector id1 = InternetDetector.getInstance();
	InternetDetector id2 = InternetDetector.getInstance();
	assertEquals(id1, id2);

    }
}
