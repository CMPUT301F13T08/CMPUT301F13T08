package com.team08storyapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class AnnotationViewActivity extends Activity {
    private ListView lv;
    private ArrayList<Annotation> aList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_story_list);
	// REQUIRE INTENT TO HAVE AN ARRAYLIST OF ANNOTATIONS
	Intent passedIntent = getIntent();
	aList = (ArrayList<Annotation>) passedIntent.getSerializableExtra("annotationList");
	lv = (ListView) findViewById(android.R.id.list);
	lv.setAdapter(new AnnotationAdapter(this, android.R.id.list, aList));	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.annotation_view, menu);
	return true;
    }

}
