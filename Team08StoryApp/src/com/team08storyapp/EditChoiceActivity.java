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
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditChoiceActivity extends Activity {
    private Story currentStory;
    private StoryFragment currentStoryFragment;
    private int currentStoryFragmentIndex;
    private int nextFragmentId = 0;
    private ArrayList<StoryFragment> availableStoryFragmentList;
    private EditText tv;
    private TextView tvFragment;
    private static final int LINKED_FRAGMENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_edit_choice);
	Intent choiceIntent = getIntent();
	currentStory = (Story) choiceIntent.getSerializableExtra("story");
	currentStoryFragmentIndex = choiceIntent.getIntExtra(
		"storyFragmentIndex", 0);
	currentStoryFragment = currentStory.getStoryFragments().get(
		currentStoryFragmentIndex);

	availableStoryFragmentList = new ArrayList<StoryFragment>();
	ArrayList<StoryFragment> currentStoryFragments = currentStory.getStoryFragments();
	for(StoryFragment sf: currentStoryFragments){
	    if(sf.getStoryFragmentId() != currentStoryFragmentIndex + 1){
		availableStoryFragmentList.add(sf);
	    }
	}
	tvFragment = (TextView) findViewById(R.id.choiceFragmentId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.edit_choice, menu);

	return true;
    }

    public void returnEditFragmentActivity(View view) {
	tv = (EditText) findViewById(R.id.editChoiceText);
	String choiceText = tv.getText().toString();
	if (isBlank(choiceText)) {
	    Toast.makeText(getApplicationContext(),
		    "Please enter choice text.", Toast.LENGTH_LONG).show();
	    return;
	}

	if (nextFragmentId < 0) {
	    Toast.makeText(getApplicationContext(),
		    "Please link a story fragment to current choice.",
		    Toast.LENGTH_LONG).show();
	    return;
	}
	currentStoryFragment = StoryController.addChoice(choiceText,
		currentStoryFragment, nextFragmentId);
	currentStory.getStoryFragments().set(currentStoryFragmentIndex, currentStoryFragment);
	FileHelper fHelper = new FileHelper(this, 1);
	try {
	    fHelper.updateOfflineStory(currentStory);
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	System.out.println("THIS IS THE NEW CHOICE??"+ currentStoryFragment.getChoices().get(currentStoryFragment.getChoices().size()-1).getText());
	Intent intent = new Intent(EditChoiceActivity.this,
		EditFragmentActivity.class);
	intent.putExtra("story", currentStory);
	intent.putExtra("storyFragmentId",
		currentStoryFragment.getStoryFragmentId());
	setResult(RESULT_OK, intent);
	finish();

    }

    public void toSelectFragmentActivity(View view) {
	Intent intent = new Intent(EditChoiceActivity.this,
		SelectFragmentActivity.class);
	intent.putExtra("storyFragments", availableStoryFragmentList);
	startActivityForResult(intent, LINKED_FRAGMENT);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);

	if (resultCode == RESULT_OK) {
	    nextFragmentId = data
		    .getIntExtra("nextStoryFragmentId", resultCode);
	    tvFragment.setText("The next storyFragment id is: "
		    + Integer.toString(nextFragmentId));

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
