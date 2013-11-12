package com.team08storyapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

public class SelectFragmentActivity extends Activity {

    private ListView lv;
    private ArrayList<StoryFragment> sfList;
    private StoryFragment currentStoryFragment;
    private Story currentStory;


    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_select_fragment);
	lv = (ListView) findViewById(android.R.id.list);
	Intent passedIntent = getIntent();
	sfList = (ArrayList<StoryFragment>) passedIntent
		.getSerializableExtra("storyFragments");
	currentStory = (Story) passedIntent.getSerializableExtra("story");
	lv.setAdapter(new StoryFragmentAdapter(this, android.R.id.list, sfList));	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.select, menu);
	return true;
    }

    /*
     * Extras will have to be passed here so that the new fragment created is
     * linked as a choice the last choice from EditChoiceActivity.
     */
    public void newEditFragmentActivity(View view) {
	StoryFragment newStoryFragment = new StoryFragment(currentStory.getStoryFragments().size()+1);
	currentStory.getStoryFragments().add(newStoryFragment);
	int newStoryFragmentId = newStoryFragment.getStoryFragmentId();
	Intent intent = new Intent(SelectFragmentActivity.this,
		EditFragmentActivity.class);
	intent.putExtra("storyFragmentId", newStoryFragmentId);
	intent.putExtra("story", currentStory);
	intent.putExtra("mode", 1);
	startActivity(intent);
    }

}
