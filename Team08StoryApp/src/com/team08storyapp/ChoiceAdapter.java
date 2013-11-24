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

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * ChoiceAdapter is a customized ArrayAdapter, which will populate only selected
 * information of a list of Choice objects into a listView by overriding the
 * getView method.
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 1.0 November 8, 2013
 * @since 1.0
 * 
 */
public class ChoiceAdapter extends ArrayAdapter<Choice> {

    private ArrayList<Choice> infos;
    private Activity activity;

    public static class ViewHolder {
	public TextView choiceText;
    }

    /**
     * Constructor of a ChoiceAdapter requires an activity object, an int value
     * of resourceView, and an ArrayList of Choice objects as well.
     * 
     * @param activity
     *            an activity object that provides LAYOUT_INFLATER_SERVICE
     * @param textViewResourceId
     *            the int value of the id of the view
     * @param infos
     *            a list of choice objects
     */
    public ChoiceAdapter(Activity activity, int textViewResourceId,
	    ArrayList<Choice> infos) {
	super(activity, textViewResourceId, infos);
	this.infos = infos;
	this.activity = activity;

    }

    /**
     * Public method getView overrides the getView method from ArrayAdapter for
     * custom purpose. It will populate only the text in a Choice object into
     * the TextView in the passed convertedView.
     * 
     * @param position
     *            the index of the selected photo in the adapter
     * @param convertView
     *            the view that is going to be converted to user's desire
     * @param parent
     *            a ViewGroup object
     */
    public View getView(int position, View convertView, ViewGroup parent) {
	View v = convertView;
	ViewHolder holder = new ViewHolder();

	/* if the given view is null, populate it with desired layout. */
	if (v == null) {
	    LayoutInflater vi = (LayoutInflater) activity
		    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    v = vi.inflate(R.layout.choices_row, null);
	    holder.choiceText = (TextView) v.findViewById(R.id.choiceText);
	    v.setTag(holder);

	} else {
	    holder = (ViewHolder) v.getTag();
	}
	final Choice choice = infos.get(position);

	if (choice != null) {
	    holder.choiceText.setText("  --" + choice.getText());
	}
	return v;

    }

}
