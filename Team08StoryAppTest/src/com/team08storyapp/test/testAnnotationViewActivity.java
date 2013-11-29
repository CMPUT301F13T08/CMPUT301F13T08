package com.team08storyapp.test;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ListView;

import com.team08storyapp.Annotation;
import com.team08storyapp.AnnotationViewActivity;

public class testAnnotationViewActivity extends
	ActivityInstrumentationTestCase2<AnnotationViewActivity> {

    private Activity activity;
    private ListView listView;
    private ArrayList<Annotation> Annotations;
    private Annotation annotation1;
    private Annotation annotation2;

    public testAnnotationViewActivity() {
	super(AnnotationViewActivity.class);
    }

    public void setUp() {

	annotation1 = new Annotation();
	annotation1.setStoryFragmentID(1);
	//annotation1.setText("TextOne");
	annotation2 = new Annotation();
	annotation2.setStoryFragmentID(2);
	//annotation2.setText("TextTwo");

	Annotations = new ArrayList<Annotation>();
	Annotations.add(annotation1);
	Annotations.add(annotation2);

	Intent intent = new Intent();
	intent.putExtra("Annotations", Annotations);
	setActivityIntent(intent);

	activity = getActivity();

	listView = (ListView) activity.findViewById(android.R.id.list);

    }

    public void testPreConditions() {

	assertNotNull(activity);
	assertNotNull(listView);

    }
    
    public void testListViewItem(){
	assertEquals(1, ((Annotation) listView.getItemAtPosition(0)).getStoryFragmentID());
	assertEquals(2, ((Annotation) listView.getItemAtPosition(1)).getStoryFragmentID());
    }
    
    public void tearDown() throws Exception{
	super.tearDown();
    }

}
