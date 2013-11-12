package com.team08storyapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class SelectFragmentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_fragment);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select, menu);
		return true;
	}
	
	/*Extras will have to be passed here so that the new fragment created
	 * is linked as a choice the last choice from EditChoiceActivity.
	 */
	public void newEditFragmentActivity(View view) {
		Intent intent = new Intent(SelectFragmentActivity.this,
			EditFragmentActivity.class);
		startActivity(intent);
	    }

}
