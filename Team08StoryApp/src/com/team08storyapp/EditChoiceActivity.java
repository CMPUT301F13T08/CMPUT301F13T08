package com.team08storyapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class EditChoiceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_choice);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_choice, menu);
		return true;
	}

	
	public void returnEditFragmentActivity(View view) {
		Intent intent = new Intent(EditChoiceActivity.this,
			EditFragmentActivity.class);
		
		/* Extras will have to be added here to make sure the correct story fragment is opened.
		 */
		startActivity(intent);
	}
	
	public void toSelectFragmentActivity(View view) {
	    Intent intent = new Intent(EditChoiceActivity.this, SelectFragmentActivity.class);
	    startActivity(intent);
	}
	
}
