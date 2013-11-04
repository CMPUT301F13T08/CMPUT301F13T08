/*
AUTHORS
========
Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen.

LICENSE
=======
Copyright  ©  2013 Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen,  
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
-This demo was used to help with JSON and ESHelper

Retrieved Oct. 29, 2013  - http://hc.apache.org/downloads.cgi
-This is for the fluent library which is licenced under apache V2

Retrieved Oct. 29, 2013 
- https://code.google.com/p/google-gson/downloads/detail?name=google-gson-2.2.4-release.zip&can=2&q=
-This is for JSON which is licenced under apache V2
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

public class ChoiceAdapter extends ArrayAdapter<Choice>{

	private ArrayList<Choice> infos;
	private Activity activity;
	
	public static class ViewHolder{
		public TextView choiceText;
	}
	
	public ChoiceAdapter(Activity a, int textViewResourceId, ArrayList<Choice> infos){
		super(a, textViewResourceId, infos);
		this.infos = infos;
		this.activity = a;
		
	}
	
	public View getView(int position, View convertView, ViewGroup parent){
		View v = convertView;
		ViewHolder holder = new ViewHolder();
		
		if(v == null){
			LayoutInflater vi = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);		
			v = vi.inflate(R.layout.choices_row, null);
			holder.choiceText = (TextView) v.findViewById(R.id.choiceText);
			v.setTag(holder);
		
		}else{
			holder = (ViewHolder)v.getTag();
		}
		final Choice choice= infos.get(position);
		
		if(choice != null){
			holder.choiceText.setText("  --"+choice.getText());
		}
		return v;
		
	}

}
