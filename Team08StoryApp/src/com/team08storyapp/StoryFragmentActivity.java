package com.team08storyapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
//variable for selection intent
//variable to store the currently selected image
//adapter for gallery view
//gallery object
//image view for larger display
/**
     * instantiate the interactive gallery
     */
public class StoryFragmentActivity extends Activity { 
	

	private ListView lv;
    private View headerText;
    private View headerGallery;
    private Story currentStory;
	private int currentStoryFragmentId;
	private StoryFragment currentStoryFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	//  set up background layout
    	setContentView(R.layout.activity_story_list);
    			
    	ArrayList<Choice> choiceList = new ArrayList<Choice>();
    	choiceList.add(new Choice(1, 1, "Go to the fragment 1"));
    	choiceList.add(new Choice(2, 5, "Go to the fragment 5"));
    	choiceList.add(new Choice(3, 2, "Go to the fragment 2"));
    	        
    	lv = (ListView) findViewById(android.R.id.list);
    			
    	// set up text header
    	headerText = getLayoutInflater().inflate(R.layout.header_text, null);
    	TextView textSection = (TextView) headerText.findViewById(R.id.headerText);
    	textSection.setOnTouchListener(new View.OnTouchListener() {
    				
    		@Override
    		public boolean onTouch(View arg0, MotionEvent arg1) {
    			// TODO Auto-generated method stub
    			//
    			//View.setMovementMethod(new ScrollingMovementMethod());
    			System.out.println("TOUCHING");
    			return false;
    				}
    			}
    					
    					);
    			textSection.setMovementMethod(new ScrollingMovementMethod());
    			
    	        // Get the intent - passed either by Online/OfflineStoriesActivity or by StoryFragmentActivity
    	        Intent storyFragment = getIntent();
    	        
    	        // Get the story object from the intent
    	        currentStory = (Story) storyFragment.getSerializableExtra("story");
    	        // Get the current story fragment id from the intent - the fragment to display
    	        storyFragment.getIntExtra("storyFragmentId", currentStoryFragmentId);
    	        
    	        
    	        // The current story fragment object - get from the current story list fragment, by id
    	        currentStoryFragment = StoryController.readStoryFragment(currentStory.getStoryFragments(), currentStoryFragmentId);
    	        
    	       
    			textSection.setText("Please place your test text here.");
    			fillChoice(choiceList);
    				
    		
    		}
    
    
	public void fillChoice(ArrayList<Choice> cList){
		System.out.println("ADAPTER PREPAE!!");
		lv.addHeaderView(headerText);							
		ChoiceAdapter adapter = new ChoiceAdapter(this, android.R.id.list, cList);
		lv.setAdapter(adapter);		
		System.out.println("ADAPTER CLEAR!!");
    	
    }
 
}