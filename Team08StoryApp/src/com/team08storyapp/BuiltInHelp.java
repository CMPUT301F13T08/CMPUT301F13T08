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
import android.graphics.Color;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * BuiltInHelp provides a pop up dialog to display help on the user interface
 * for the screen the user is currently on.
 * <p>
 * To display the help, call the BuiltInHelp's showDialog() method to display
 * the pop up dialog passing it the calling activity and it's title and text to
 * display in the pop up.
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

    /**
     * When called displays a pop up dialog to the user containing help for the
     * screen they are currently viewing.
     * 
     * @param activity
     *            The calling activity in order to set up the help text that is
     *            a appropriate for the UI screen.
     * @param helpTitle
     *            The title to display on the top of the Help pop up.
     * @param helpText
     *            The text describing the help for the UI screen.
     */
    public static void showDialog(Activity activity, String helpTitle,
	    String helpText) {
	/*
	 * Create a dialog for displaying help and set it's title and layout to
	 * display the help information for the current screen.
	 */
	final Dialog helpDialog = new Dialog(activity, R.style.cust_dialog);

	helpDialog.setContentView(R.layout.help_dialog);
	TextView helptext = (TextView) helpDialog
		.findViewById(R.id.helptextView);
	helpDialog.setTitle(helpTitle);
	helptext.setTextColor(Color.BLACK);
	helptext.setText(Html.fromHtml(helpText));
	helptext.setMovementMethod(new ScrollingMovementMethod());

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
}
