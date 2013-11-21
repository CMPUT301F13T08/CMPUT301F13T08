/*
AUTHORS
========
Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen.

LICENSE
=======
Copyright  �  2013 Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen,  
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

import android.app.Dialog;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * BuiltInHelp provides a pop up dialog to display help on the user interface
 * for the screen the user is currently on.
 * <p>
 * It contains help for the following Android Activity's screens:
 * <ul>
 * <li>MainActivity
 * <li>OnlineStoriesActivity
 * </ul>
 * <p>
 * To display the help, instantiate an object of BuiltInHelp and pass it the
 * Android Activity for the help you wish to display. Then call the
 * BuiltInHelp's showDialog() method to display the pop up dialog.
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 2.0 December 3, 2013
 * @since 2.0
 */
public class BuiltInHelp {

    private String helpTitle;
    private String helpText;
    private Activity activity;

    /*
     * This enum contains all the activity classes that contain help. This is
     * used in a switch statement to help determine which help text to display
     * based on the activity set when instantiating the class.
     */
    private enum ActivityEnum {
	AnnotationViewActivity,
	EditChoiceActivity,
	EditFragmentActivity,
	MainActivity, 
	MyStoriesActivity, 
	NewStoryActivity,
	OfflineStoriesActivity, 
	OnlineStoriesActivity, 
	SelectFragmentActivity,
	StoryFragmentActivity,
	StoryFragmentListActivity
    }

    /**
     * Initializes the BuiltInHelp with the current Activity, setting up the
     * help to display for that Activity.
     * 
     * @param activity
     *            The current activity the user is viewing and needs the help
     *            for.
     */
    public BuiltInHelp(Activity activity) {
	this.activity = activity;

	/*
	 * Retrieve just the activity name from the activity component name for
	 * use in determining what help text to set up for displaying in the
	 * dialog.
	 */
	String activityName = activity.getComponentName().getClassName()
		.replace("com.team08storyapp.", "");

	/*
	 * Turn the string Activity name into an enum value in order to be used
	 * in a switch statement for comparison.
	 */
	ActivityEnum activityEnum = ActivityEnum.valueOf(activityName);

	switch (activityEnum) {
	case AnnotationViewActivity:
	    setAnnotationHelp();
	    break;
	case EditChoiceActivity:
	    setEditChoiceHelp();
	    break;
	case EditFragmentActivity:
	    setEditFragmentHelp();
	    break;
	case MainActivity:
	    setMainMenuHelp();
	    break;
	case MyStoriesActivity:
	    setMyStoriesHelp();
	    break;
	case NewStoryActivity:
	    setNewStoryHelp();
	    break;
	case OfflineStoriesActivity:
	    setOfflineStoryHelp();
	    break;
	case OnlineStoriesActivity:
	    setOnlineStoryHelp();
	    break;
	case SelectFragmentActivity:
	    setSelectFragmentHelp();
	    break;
	case StoryFragmentActivity:
	    setReadStoryHelp();
	    break;
	case StoryFragmentListActivity:
	    setStoryFragmentListHelp();
	    break;
	default:
	    break;
	}
    }

    /**
     * When called displays a pop up dialog to the user containing help for the
     * screen they are currently viewing.
     */
    public void showDialog() {
	/*
	 * Create a dialog for displaying help and set it's title and layout to
	 * display the help information for the current screen.
	 */
	final Dialog helpDialog = new Dialog(activity);
	helpDialog.setContentView(R.layout.help_dialog);
	TextView helptext = (TextView) helpDialog
		.findViewById(R.id.helptextView);
	helpDialog.setTitle(helpTitle);
	helptext.setText(Html.fromHtml(helpText));

	/*
	 * Retrieve the button from the dialog layout and assign it a click
	 * listener. This will allow the "Okay" button to be pressed by the user
	 * to dismiss the help dialog and return to the screen they were on
	 * before clicking help.
	 */
	Button okayButton = (Button) helpDialog.findViewById(R.id.okayButton);
	okayButton.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View view) {
		helpDialog.dismiss();
	    }

	});

	/* Display the help dialog pop up to the user. */
	helpDialog.show();
    }

    /* Sets up the help text to display for the Annotation screen. */
    private void setAnnotationHelp() {
	this.helpText = "Annotation Help";
	this.helpText = "";
    }
    
    /* Sets up the help text to display for the Edit Story Fragment screen. */
    private void setEditFragmentHelp(){
	this.helpText = "Edit Story Fragment Help";
	this.helpText = "";
    }
    
    /* Sets up the help text to display for the Edit Choice screen. */
    private void setEditChoiceHelp(){
	this.helpText = "Edit Choice Help";
	this.helpText = "";
    }

    /* Sets up the help text to display for the Main Menu screen. */
    private void setMainMenuHelp() {
	this.helpTitle = "Main Menu Help";
	this.helpText = "<B>Read Online Stories Button</B>"
		+ "<br>Click to display the list of Stories available to read from the online webservice.<br>"
		+ "<br><B>Read Downloaded Stories Button</B>"
		+ "<br>Click to display the list of Stories available to read that are local stored on the device.<br>"
		+ "<br><B>My Stories Button</B>"
		+ "<br>Click to display the list of Stories you are creating.<br>";
    }
    
    /* Sets up the help text to display for the My Stories screen.*/
    private void setMyStoriesHelp(){
	this.helpText = "My Stories Help";
	this.helpText = "";
    }
    
    /* Sets up the help text to display for the New Story screen.*/
    private void setNewStoryHelp(){
	this.helpText = "New Story Help";
	this.helpText = "";
    }
    
    /* Sets up the help text to display for the Offline Story Listing screen. */
    private void setOfflineStoryHelp() {
	this.helpText = "Downloaded Stories Help";
	this.helpText = "";
    }

    /* Sets up the help text to display for the Online Story Listing screen. */
    private void setOnlineStoryHelp() {
	this.helpTitle = "Online Stories Help";
	this.helpText = "<b>Search Function</b>"
		+ "<br>Finds stories where the title or author contain the text entered into the search area.<br>"
		+ "<br><b>List of Stories </b>"
		+ "<br>Displays all stories stored online or the stories resulting from the search."
		+ "<br><br>By long pressing on a story in the list, a popup menu appears with the following options:"
		+ "<br>	� <b>Download</b> - Select this to download the Story to the devices local memory."
		+ "<br>	� <b>Read</b> - Select this to start reading the Story.<br>"
		+ "<br><b>\"I'm Feeling Lucky\" Button</b>"
		+ "<br>Click to pick a story at random to start reading.<br>";
    }

    /* Sets up the help text to display for the Select Story Fragment screen. */
    private void setSelectFragmentHelp(){
	this.helpText = "Selecting Story Fragment Help";
	this.helpText = "";
    }
    
    /* Sets up the help text to display for the Reading of a Story. */
    private void setReadStoryHelp() {
	this.helpTitle = "Story Reading Help";
	this.helpText = "<b>Action Bar Options</b>"
		+ "<br>These are found at the top of the screen next to the title and consist of the following:<br>"
		+ "<br>	� <b>Annotations</b> - This is the icon that looks like a little picture. Click it to go to the annotation screen where you can view user annotations or provide your own."
		+ "<br>	� <b>Return to Main Menu</b> - This is located in the drop down menu and will return you to the main menu screen.";
    }
    
    /* Sets up the help text to display for the Story Fragment Listing screen. */
    private void setStoryFragmentListHelp(){
	this.helpText = "Story Fragment Listing Help";
	this.helpText = "";
    }
}