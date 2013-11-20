package com.team08storyapp.test;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.team08storyapp.Annotation;
import com.team08storyapp.AnnotationAdapter;
import com.team08storyapp.AnnotationViewActivity;

public class testAnnotationAdapter extends
	ActivityInstrumentationTestCase2<AnnotationViewActivity> {
    
    private Activity activity;
    private ArrayList<Annotation> annoList;
    private AnnotationAdapter adapter;
    
    public testAnnotationAdapter(){
	super(AnnotationViewActivity.class);
    }
    
    public void setUp(){
	annoList = new ArrayList<Annotation>();
	Annotation a1 = new Annotation();
	a1.setAnnotationID(1);
	a1.setText("This is the end");
	annoList.add(a1);
	
	Annotation a2 = new Annotation();
	a2.setAnnotationID(2);
	a2.setText("In our bedroom after the war");
	annoList.add(a2);
	
	Annotation a3 = new Annotation();
	a3.setAnnotationID(3);
	a3.setText("The darkest hour of human");
	annoList.add(a3);
	
	Intent intent = new Intent();
	intent.putExtra("Annotations", annoList);
	super.setActivityIntent(intent);
	activity = super.getActivity();
	adapter = new AnnotationAdapter(activity, android.R.id.list, annoList);
    }

    public void testGetView() {

	/* Test getView() function */
	ViewGroup parent = new LinearLayout(activity.getApplicationContext());
	adapter.getView(1, null, parent);

	/* Make sure all 3 story fragments in sfList are in adapter */
	assertEquals(adapter.getCount(), 3);

	/*
	 * Make sure right place has the right fragment. Index(Position) 0: sf
	 * Index(Position) 1: sf1 Index(Position) 2: sf2
	 */
	assertEquals(adapter.getItem(0).getAnnotationID(), 1);
	assertEquals(adapter.getItem(0).getText(),
		"This is the end");
	adapter.getView(2, null, parent);

	assertEquals(adapter.getItem(1).getAnnotationID(), 2);
	assertEquals(adapter.getItem(1).getText(),
		"In our bedroom after the war");

	assertEquals(adapter.getItem(2).getAnnotationID(), 3);
	assertEquals(adapter.getItem(2).getText(),
		"The darkest hour of human");
    }
}
