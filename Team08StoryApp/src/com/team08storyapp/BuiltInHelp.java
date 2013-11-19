package com.team08storyapp;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BuiltInHelp {

    private String helpTitle;
    private String helpText;
    private Activity activity;

    public BuiltInHelp(Activity activity) {
	this.activity = activity;
	setMainMenuHelp();
    }

    public void showDialog(){
	final Dialog helpDialog = new Dialog(activity);
	    helpDialog.setContentView(R.layout.help_dialog);
	    TextView helptext = (TextView) helpDialog
		    .findViewById(R.id.helptextView);
	    helpDialog.setTitle(helpTitle);
	    helptext.setText(helpText);
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
	this.helpText = "<b>Read Online Stories</b><br>When clicked will lead the user to the Online Stories screen.";
    }
    
}
