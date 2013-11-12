package com.team08storyapp;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NewStoryActivity extends Activity {

    private EditText authorField;
    private EditText titleField;
    private Story newStory;
    private FileHelper fHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	fHelper = new FileHelper(this, 1);
	setContentView(R.layout.activity_new_story);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.new_story, menu);
	return true;
    }

    public void toEditFragmentActivity(View view) {
	authorField = (EditText) findViewById(R.id.enterAuthor);
	titleField = (EditText) findViewById(R.id.enterTitle);
	
	String author = authorField.getText().toString();
	String title = titleField.getText().toString();
	
	if (isBlank(author) && isBlank(title)) {
	    Toast.makeText(getApplicationContext(),
		    "Sorry. Please make sure title and author are not empty",
		    Toast.LENGTH_LONG).show();
	    return;
	}else{
	    if(isBlank(author) && !isBlank(title)){
		author = "Anonymous";
	    }else if (isBlank(title) && !isBlank(author)){
		title = "Untitled";
	    }
	}
	
	newStory = new Story(title, author);
	StoryFragment firstFragment = new StoryFragment(1);
	newStory.getStoryFragments().add(firstFragment);
	newStory.setFirstStoryFragmentId(1);
	
	try {
	    fHelper.addOfflineStory(newStory);
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	Intent intent = new Intent(NewStoryActivity.this,
		EditFragmentActivity.class);
	intent.putExtra("story", newStory);
	int nextStoryFragmentId = newStory.getFirstStoryFragmentId();
	intent.putExtra("storyFragmentId", nextStoryFragmentId);
	startActivity(intent);
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
