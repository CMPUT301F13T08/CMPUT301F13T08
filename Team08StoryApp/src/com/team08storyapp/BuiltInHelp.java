package com.team08storyapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContextWrapper;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BuiltInHelp {

    private String helpTitle;
    private String helpText;
    private Activity activity;

    public BuiltInHelp(Activity activity) {
	this.activity = activity;
	if (activity.getComponentName().getClassName()
		.contentEquals("com.team08storyapp.MainActivity")) {
	    setMainMenuHelp();
	}
    }

    public void showDialog() {
	final Dialog helpDialog = new Dialog(activity);
	helpDialog.setContentView(R.layout.help_dialog);
	TextView helptext = (TextView) helpDialog
		.findViewById(R.id.helptextView);
	helpDialog.setTitle(helpTitle);
	helptext.setText(Html.fromHtml(helpText));
	Button okayButton = (Button) helpDialog.findViewById(R.id.okayButton);
	okayButton.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View view) {
		helpDialog.dismiss();
	    }

	});
	helpDialog.show();
    }

    private void setMainMenuHelp() {
	this.helpTitle = "Main Menu Help";
	this.helpText = "<B>Read Online Stories Button</B>"
		+ "<br>Click to display the list of Stories available to read from the online webservice.<br>"
		+ "<br><B>Read Downloaded Stories Button</B>"
		+ "<br>Click to display the list of Stories available to read that are local stored on the device.<br>"
		+ "<br><B>My Stories Button</B>"
		+ "<br>Click to display the list of Stories you are creating.";
    }

}
