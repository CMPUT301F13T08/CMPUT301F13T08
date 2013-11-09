package com.team08storyapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class EditFragmentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_fragment);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_fragment, menu);
		return true;
	}

	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.camIllus:
	            return true;
	            
	        case R.id.camGallery:
	            return true;
	            
	        case R.id.addChoice:
	        	return true;
	        	
	        case R.id.save:
	        	return true;
	        	
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
