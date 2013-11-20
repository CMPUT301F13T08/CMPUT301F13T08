/*
AUTHORS
========
Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen.

LICENSE
=======
Copyright  ���  2013 Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen,  
Free Software Foundation, Inc., Marky Mark  License GPLv3+: GNU
GPL version 3 or later <http://gnu.org/licenses/gpl.html>.
This program is free software: you can redistribute it and/or modify it under the terms of 
the GNU General Public License as published by the Free Software Foundation, either 
version 3 of the License, or (at your option) any later version. This program is distributed 
in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 
warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public 
License for more details. You should have received a copy of the GNU General Public License 
along with this program.  If not, see <http://www.gnu.org/licenses/>.
              
3rd Party Libraries
=============
Retrieved Oct. 27, 2013 - https://github.com/rayzhangcl/ESDemo
-This demo was used to help with JSON and ESHelper which is under the CC0 licenses

Retrieved Oct. 29, 2013  - http://hc.apache.org/downloads.cgi
-This is for the fluent library which is licensed under apache V2

Retrieved Oct. 29, 2013 
- https://code.google.com/p/google-gson/downloads/detail?name=google-gson-2.2.4-release.zip&can=2&q=
-This is for JSON which is licensed under apache V2
 */

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
	setTitle(R.string.create_a_new_story);
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
	firstFragment.setStoryText("Please Edit your story text here.");
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
		StoryFragmentListActivity.class);
	intent.putExtra("story", newStory);
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
