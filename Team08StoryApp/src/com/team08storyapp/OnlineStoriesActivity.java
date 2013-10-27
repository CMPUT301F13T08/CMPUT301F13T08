package com.team08storyapp;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class OnlineStoriesActivity extends ListActivity {
	
	private static final int DOWNLOAD_ID = 1;
	private static final int READ_ID = 2;
	public int position;
	public AdapterContextMenuInfo info;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_online_stories);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.online_stories, menu);
		return true;
	}
	
	
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, DOWNLOAD_ID, 0, R.string.download_menu);
		menu.add(0,READ_ID, 0, R.string.read_menu);
	}

	public boolean onContextItemSelected(MenuItem item){
		info = (AdapterContextMenuInfo) item.getMenuInfo();
		position = info.position;
		switch(item.getItemId()){
		case DOWNLOAD_ID:
			
			
		case READ_ID:
			
			default:
				return super.onContextItemSelected(item);
		}
	}
}
