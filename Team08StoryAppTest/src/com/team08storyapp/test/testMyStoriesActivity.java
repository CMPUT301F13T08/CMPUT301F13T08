package com.team08storyapp.test;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.team08storyapp.ESHelper;
import com.team08storyapp.FileHelper;
import com.team08storyapp.MyStoriesActivity;
import com.team08storyapp.R;

public class testMyStoriesActivity extends
ActivityInstrumentationTestCase2<MyStoriesActivity>{

    private Activity activity;
    private View header;
    private Button searchButton;
    private ESHelper esHelper;
    private FileHelper fHelper;
    private ListView listView;
    
    public testMyStoriesActivity() {
   	super(MyStoriesActivity.class);
       }
    
    public void setUp(){
	
	activity = getActivity();
	
	fHelper = new FileHelper(activity, 1);
	esHelper = new ESHelper();
	
	header = activity.getLayoutInflater().inflate(R.layout.header_search, null);
	searchButton = (Button) header.findViewById(R.id.searchButton);
	listView = (ListView) activity.findViewById(android.R.id.list);
	
    }
    
    public void testPreConditions(){
	
	assertNotNull(header);
	assertNotNull(searchButton);
	assertNotNull(listView);
	
    }
    
    
}
