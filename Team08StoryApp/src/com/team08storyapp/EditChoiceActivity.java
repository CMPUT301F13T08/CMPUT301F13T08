package com.team08storyapp;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class EditChoiceActivity extends Activity {
    private Story currentStory;
    private int currentStoryFragmentIndex;
    private int choiceId;
    private Choice currentChoice;
    private FileHelper fHelper;

    private EditText tv;
    private static final int LINKED_FRAGMENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_edit_choice);
	fHelper = new FileHelper(this, 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.edit_choice, menu);
	Intent choiceIntent = getIntent();
	currentStory = (Story) choiceIntent.getSerializableExtra("story");
	currentStoryFragmentIndex = choiceIntent.getIntExtra(
		"storyFragmentIndex", 0);
	choiceId = choiceIntent.getIntExtra("choiceId", 1);
	currentChoice = new Choice();
	currentChoice.setChoiceId(choiceId);
	return true;
    }

    public void returnEditFragmentActivity(View view) {
	tv = (EditText) findViewById(R.id.editChoiceText);
	String text = tv.getText().toString();
	if (isBlank(text)) {
	    Toast.makeText(getApplicationContext(),
		    "Please enter choice text.", Toast.LENGTH_LONG).show();
	    return;
	}
	currentChoice.setText(text);
	if (currentChoice.getStoryFragmentID() < 1) {
	    Toast.makeText(getApplicationContext(),
		    "Please link a story fragment to current choice.",
		    Toast.LENGTH_LONG).show();
	    return;
	}
	currentStory.getStoryFragments().get(currentStoryFragmentIndex)
		.getChoices().add(currentChoice);
	try {
	    fHelper.updateOfflineStory(currentStory);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	/*
	 * Intent intent = new Intent(EditChoiceActivity.this,
	 * EditFragmentActivity.class);
	 * 
	 * /* Extras will have to be added here to make sure the correct story
	 * fragment is opened.
	 */
	/*
	 * startActivity(intent);
	 */	
	finish();

    }

    public void toSelectFragmentActivity(View view) {
	Intent intent = new Intent(EditChoiceActivity.this,
		SelectFragmentActivity.class);
	startActivityForResult(intent, LINKED_FRAGMENT);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	if (resultCode == RESULT_OK && requestCode == LINKED_FRAGMENT) {
	    int nextFragmentId = data.getIntExtra("nextStoryFragmentId", 1);
	    currentChoice.setStoryFragmentID(nextFragmentId);
	} else {
	    super.onActivityResult(requestCode, resultCode, data);
	}
    }

    private boolean isBlank(String str) {
	int strLen;
	if ((str == null) || ((strLen = str.length()) == 0))
	    return true;
	for (int i = 0; i < strLen; ++i) {
	    if (!(Character.isWhitespace(str.charAt(i)))) {
		return false;
	    }
	}
	return true;
    }

}
