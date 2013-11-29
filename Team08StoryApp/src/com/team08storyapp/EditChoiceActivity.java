/*
AUTHORS
========
Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen.

LICENSE
=======
Copyright  ���������  2013 Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen,  
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
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * EditChoiceActivity is a view class that provides author an opportunity to
 * edit the new choice's text and the fragment it links to.
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 1.0 November 8, 2013
 * @since 1.0
 */
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
	SyncManager.sync(this);
	Intent choiceIntent = getIntent();
	currentStory = (Story) choiceIntent.getSerializableExtra("story");
	currentStoryFragmentIndex = choiceIntent.getIntExtra(
		"storyFragmentIndex", 0);
	currentStoryFragment = currentStory.getStoryFragments().get(
		currentStoryFragmentIndex);

	availableStoryFragmentList = new ArrayList<StoryFragment>();
	ArrayList<StoryFragment> currentStoryFragments = currentStory
		.getStoryFragments();

	/*
	 * Remove the current fragment from the story fragment list that will be
	 * passes to SelectFragmentActivity. The current fragment will not be
	 * selected as a choice to itself
	 */
	for (StoryFragment sf : currentStoryFragments) {
	    if (sf.getStoryFragmentId() != currentStoryFragmentIndex + 1) {
		availableStoryFragmentList.add(sf);
	    }
	}
	tvFragment = (TextView) findViewById(R.id.choiceFragmentId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

	/*
	 * 
	 * Inflate the menu; this adds items to the action bar if they are
	 * present.
	 */
	getMenuInflater().inflate(R.menu.edit_choice, menu);
	return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	if (item.getItemId() == R.id.help) {

	    /*
	     * Help option was selected by the user, display the popup dialog
	     * for the current activity.
	     */
	    BuiltInHelp.showDialog(EditChoiceActivity.this,
		    getString(R.string.edit_choice_help_title),
		    getString(R.string.edit_choice_help_text));
	    return true;
	}
	return super.onOptionsItemSelected(item);
    }

    /**
     * After hitting the save button in current activity, this function is
     * automatically called to return to StoryFragmentListActivity. Before
     * returning, this function handles the information of the new choice.
     * <ul>
     * If choice text and/or linked fragment is not set when save button is
     * clicked, this function will terminate and inform author which field is
     * missing.
     * </ul>
     * <ul>
     * Else, the new choice will be added to current fragment's choice list and
     * update the current story.
     * </ul>
     * 
     * @param view
     *            It's the view where the save button is placed.
     */
    public void returnEditFragmentActivity(View view) {
	tv = (EditText) findViewById(R.id.editChoiceText);
	String choiceText = tv.getText().toString();
	if (isBlank(choiceText)) {

	    /*
	     * A choice must have a text before it's added
	     */Toast.makeText(getApplicationContext(),
		    "Please enter choice text.", Toast.LENGTH_LONG).show();
	    return;
	}

	if (nextFragmentId < 1) {

	    /*
	     * A choice must have a fragment selected before it's added
	     */Toast.makeText(getApplicationContext(),
		    "Please link a story fragment to current choice.",
		    Toast.LENGTH_LONG).show();
	    return;
	}

	/*
	 * Call addChoice to update the choice list of the current story
	 * fragment with the new choice
	 */
	currentStoryFragment = StoryController.addChoice(choiceText,
		currentStoryFragment, nextFragmentId);

	/*
	 * Update the current story object by replacing the fragment the user is
	 * on with the updated fragment that contains a new choice
	 */

	currentStory.getStoryFragments().set(currentStoryFragmentIndex,
		currentStoryFragment);

	/*
	 * Update the story object on the file system, later access will include
	 * the new choice for the current fragment
	 */
	FileHelper fHelper = new FileHelper(this, 1);
	try {
	    fHelper.updateOfflineStory(currentStory);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}

	SyncManager.sync(this);

	/*
	 * Return to the EditFragment activity Pass the updated story object,
	 * and the current story fragment id
	 */
	Intent intent = new Intent(EditChoiceActivity.this,
		EditFragmentActivity.class);
	intent.putExtra("story", currentStory);
	intent.putExtra("storyFragmentId",
		currentStoryFragment.getStoryFragmentId());
	setResult(RESULT_OK, intent);
	finish();

    }

    /**
     * This function is linked to the button "Link Fragment", which let the
     * author select an available story fragment for the new choice to go to.
     * 
     * 
     * @param view
     *            It's the view where the Link Fragment button is placed.
     */
    public void toSelectFragmentActivity(View view) {
	Intent intent = new Intent(EditChoiceActivity.this,
		SelectFragmentActivity.class);
	/*
	 * Pass the fragment list of the story, not including the current
	 * fragment (no choice link to itself)
	 */
	intent.putExtra("storyFragments", availableStoryFragmentList);
	startActivityForResult(intent, LINKED_FRAGMENT);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);

	if (resultCode == RESULT_OK) {
	    /*
	     * The selected fragment for a new choice is passed from the
	     * SelectFragmentActivity
	     */
	    nextFragmentId = data
		    .getIntExtra("nextStoryFragmentId", resultCode);
	    tvFragment.setText("The linked fragment id is: "
		    + Integer.toString(nextFragmentId));
	}
    }

    /*
     * if the input text for a choice is null, the user will be prompted for
     * input before saving in returnEditFragment activity method
     */
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
