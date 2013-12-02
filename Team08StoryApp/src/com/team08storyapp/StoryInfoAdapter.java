/*
AUTHORS
========
Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen.

LICENSE
=======
Copyright  ��  2013 Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen,  
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
 * This StoryInfoAdapter class is a subclass of ArrayAdapter<T>. It is a
 * customized array adapter which populates a story's author and title into two
 * views.
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 1.0 November 8, 2013
 * @since 1.0
 */
public class StoryInfoAdapter extends ArrayAdapter<Story> {

    private ArrayList<Story> infos;
    private Activity activity;

    /**
     * Constructor of StoryInfoAdapter. It takes in the current activity to get
     * system service, and the view that will be converted to the desired view,
     * and a list of stories as well.
     * 
     * @param activity
     *            an activity object that provides LAYOUT_INFLATER_SERVICE
     * @param textViewResourceId
     *            the int value of id of the view
     * @param infos
     *            a list of stories
     */
    public StoryInfoAdapter(Activity activity, int textViewResourceId,
	    ArrayList<Story> infos) {
	super(activity, textViewResourceId, infos);
	this.infos = infos;
	this.activity = activity;

    }

    /**
     * Method will inflate the layout first and populate the data to
     * corresponding fields. (Title to item1, Author to item2)
     * 
     * @param position
     *            the index of the selected photo in the adapter
     * @param convertView
     *            the view that is going to be converted to user's desire
     * @param parent
     *            a ViewGroup object
     * @return the converted view for the Story List
     */
    public View getView(int position, View convertView, ViewGroup parent) {
	ViewHolder holder = holder(convertView);
	View v = convertView;

	/* if the view is not empty populate with the given layout */
	if (v == null) {
	    LayoutInflater vi = (LayoutInflater) activity
		    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    v = vi.inflate(R.layout.stories_row, null);
	    holder.item1 = (TextView) v.findViewById(R.id.big);
	    holder.item2 = (TextView) v.findViewById(R.id.small);
	    v.setTag(holder);
	}

	if (infos == null) {
	    return v;
	}

	final Story story = infos.get(position);

	/* set Title and Author to the right places. */
	if (story != null) {
	    holder.item1.setText(story.getTitle());
	    holder.item2.setText(story.getAuthor());
	}
	return v;
    }

    /*
     * holder is a function that returns an initialized holder depends on the
     * view in parameter. If the passed view is not null, then return the tagged
     * view, otherwise initialize a new ViewHolder object
     */
    private ViewHolder holder(View convertView) {
	View v = convertView;
	ViewHolder holder;
	if (v == null) {
	    holder = new ViewHolder();
	} else {
	    holder = (ViewHolder) v.getTag();
	}
	return holder;
    }

    /**
     * This innerclass ViewHolder holds two TextViews.
     * 
     * @author Sue Smith
     * @author Michele Paulichuk
     * @author Alice Wu
     * @author Ana Marcu
     * @author Jarrett Toll
     * @author Jiawei Shen
     * @version 1.0 November 8, 2013
     * @since 1.0
     * 
     */
    public static class ViewHolder {
	public TextView item1;
	public TextView item2;
    }
}
