package com.team08storyapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class OfflineStoriesActivity extends ListActivity {
	
	public int position;
	public AdapterContextMenuInfo info;
	private static final boolean onUpdate = true;
	private static final boolean onCreate = false;
	
	private FileHelper fHelper;
	private View header;
	private String searchText;
	private EditText et;
	
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		fHelper = new FileHelper(this, 0);
		header = getLayoutInflater().inflate(R.layout.header_search, null);
		
		try {
			fillData(fHelper.getOfflineStories(), onCreate);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Button searchButton = (Button) header.findViewById(R.id.searchButton);
		et = (EditText) header.findViewById(R.id.searchText);
		
		searchButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				searchText = et.getText().toString();			
				if(searchText != null && searchText!= ""){
					System.out.println(searchText);
					ArrayList<Story> result = fHelper.searchOfflineStories(searchText);
					fillData(result, onUpdate);
				}else{
					try {
						fillData(fHelper.getOfflineStories(), onUpdate);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		});
		
		registerForContextMenu(getListView());
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.offline_stories, menu);
		return true;
	}
	
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);       
        // TODO: read the story
    }

	
	
	public void fillData(ArrayList<Story> sList, boolean update){
		ArrayList<String> lList = new ArrayList<String>();
		for(int i = 0; i < sList.size(); i++){
			lList.add(sList.get(i).getTitle() + " \nBy: "+ sList.get(i).getAuthor());
		}
		String[] lArray = new String[lList.size()];
		lArray = lList.toArray(lArray);
		ListView lv = getListView();
		if(!update){
			lv.addHeaderView(header);	
		}
		setListAdapter(new ArrayAdapter<String>(this,R.layout.stories_row, lArray));


	}


}
