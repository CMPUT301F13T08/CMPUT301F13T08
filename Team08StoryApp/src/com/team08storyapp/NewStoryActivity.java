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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * NewStoryActivity is a view class that is activated when the author needs to
 * create a new story. NewStoryActivity provides two editText views for author
 * to enter the title of the story and the name of author. When the creating
 * process is finished, save button will lead the author to create the first
 * story fragment of his story.
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 1.0 November 8, 2013
 * @since 1.0
 */
public class NewStoryActivity extends Activity {

    private EditText authorField;
    private EditText titleField;
    private Story newStory;
    private FileHelper fHelper;
    private String title;
    private String author;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	/* Handle item selection */
	switch (item.getItemId()) {
	case R.id.action_mainmenu:
	    Intent mainIntent = new Intent(getApplicationContext(),
		    MainActivity.class);
	    mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
		    | Intent.FLAG_ACTIVITY_CLEAR_TASK);
	    startActivity(mainIntent);
	case R.id.help:
	    /*
	     * Help option was selected by the user, display the popup dialog
	     * for the current activity.
	     */
	    BuiltInHelp.showDialog(NewStoryActivity.this,
		    getString(R.string.new_story_help_title),
		    getString(R.string.new_story_help_text));
	    return true;
	default:
	    return super.onOptionsItemSelected(item);
	}
    }

    /**
     * <ul>
     * toEditFragmentActivity is linked to the button "save", which saves the
     * information of new story and directs the author to edit the first
     * fragment of the story. So this function is called when the save button is
     * hit.
     * </ul>
     * <ul>
     * Mainly, this function checks if author and title are blank first. If both
     * of them are blank, the function will toast a message and inform the
     * author that at least one of the fields can't be blank. If only author is
     * blank, function will automatically assign "Anonymous" to author fields,
     * and if only the title is blank, the function will write the title as
     * "Untitled".
     * </ul>
     * <ul>
     * After handling the information of the story, a new story and its first
     * fragment are created. FileHelper will add the story to file system and
     * assign it an off-line id. Then start the activity of EditFragment.
     * </ul>
     * 
     * @param view
     *            the current view of NewStoryActivity
     */
    public void toEditFragmentActivity(View view) {

	if (createNewStory()) {
	    StoryFragment firstFragment = new StoryFragment(1);
	    newStory.getStoryFragments().add(firstFragment);

	    /* add the story to file system */
	    try {
		fHelper.addOfflineStory(newStory);
	    } catch (Exception e) {
		e.printStackTrace();
	    }

	    /* start the EditFragmentActivity */
	    Intent intent = new Intent(NewStoryActivity.this,
		    EditFragmentActivity.class);
	    intent.putExtra("story", newStory);
	    intent.putExtra("storyFragmentId", 1);
	    startActivity(intent);
	}
    }

    private boolean createNewStory() {
	if (setStoryInfo()) {
	    setNewStoryInfo(author, title);
	    return true;
	}
	return false;
    }

    private void setNewStoryInfo(String author, String title) {
	newStory = new Story(title, author);
	newStory.setFirstStoryFragmentId(1);
    }

    /*
     * This method is used to check that the author and title fields are not
     * blank before being able to save.
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

    private boolean setStoryInfo() {

	/* find views of authorfield and titlefield */
	authorField = (EditText) findViewById(R.id.enterAuthor);
	titleField = (EditText) findViewById(R.id.enterTitle);

	/* read the string input from two views */
	author = authorField.getText().toString();
	title = titleField.getText().toString();

	/* check if both of them are blank */
	if (isBlank(author) && isBlank(title)) {
	    Toast.makeText(getApplicationContext(),
		    "Sorry. Please make sure title and author are not empty",
		    Toast.LENGTH_LONG).show();
	    return false;
	} else {
	    if (isBlank(author) && !isBlank(title)) {
		author = "Anonymous";
	    } else if (isBlank(title) && !isBlank(author)) {
		title = "Untitled";
	    }
	}
	return true;
    }
}
