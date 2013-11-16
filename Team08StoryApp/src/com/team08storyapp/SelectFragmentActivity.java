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
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SelectFragmentActivity extends Activity {

    private ListView lv;
    private ArrayList<StoryFragment> sfList;


    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_select_fragment);
	lv = (ListView) findViewById(android.R.id.list);
	Intent passedIntent = getIntent();
	sfList = (ArrayList<StoryFragment>) passedIntent
		.getSerializableExtra("storyFragments");

	lv.setAdapter(new StoryFragmentAdapter(this, android.R.id.list, sfList));
	
	lv.setOnItemClickListener(new OnItemClickListener(){
	    @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		StoryFragment sf = (StoryFragment)parent.getItemAtPosition(position);
		int storyFragmentId = sf.getStoryFragmentId();		
		Intent intent = new Intent(SelectFragmentActivity.this,
			EditChoiceActivity.class);
		intent.putExtra("nextStoryFragmentId", storyFragmentId);	
		intent.putExtra("mode", 1);
		setResult(RESULT_OK, intent);
		finish();
	    }
	});
    }




}
