package com.team08storyapp;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class StoryFragmentListActivity extends Activity {
    private ListView lv;
    private ArrayList<StoryFragment> sfList;
    private StoryFragment currentStoryFragment;
    private Story currentStory;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_story_fragment_list);
	lv = (ListView) findViewById(android.R.id.list);
	Intent passedIntent = getIntent();
	sfList = (ArrayList<StoryFragment>) passedIntent
		.getSerializableExtra("storyFragments");
	currentStory = (Story) passedIntent.getSerializableExtra("story");

	lv.setAdapter(new StoryFragmentAdapter(this, android.R.id.list, sfList));

	lv.setOnItemClickListener(new OnItemClickListener() {
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view,
		    int position, long id) {
		StoryFragment sf = (StoryFragment) parent
			.getItemAtPosition(position);
		int storyFragmentId = sf.getStoryFragmentId();

		Intent intent = new Intent(StoryFragmentListActivity.this,
			EditFragmentActivity.class);
		intent.putExtra("storyFragmentId", storyFragmentId);
		intent.putExtra("story", currentStory);
		intent.putExtra("mode", 0);
		startActivity(intent);
	    }
	});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.select, menu);
	return true;
    }

    public void toEditFragment(View view) {
	Intent intent = new Intent(StoryFragmentListActivity.this,
		EditFragmentActivity.class);
	int newStoryFragmentId = currentStory.getStoryFragments().size() + 1;
	intent.putExtra("storyFragmentId", newStoryFragmentId);
	intent.putExtra("story", currentStory);
	intent.putExtra("mode", 0);
	startActivity(intent);
    }
}
