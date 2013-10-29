package com.team08storyapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import com.example.filehelperv0.FileHelper;
import com.example.filehelperv0.R;
import com.example.filehelperv0.Story;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class OnlineStoriesActivity extends ListActivity {

	// make sure to assign the "menu" thing with menu's value, otherwise
	// contextmenu won't respond.
	// private FileHelper fHelper; should be ESHelper
	
	public int position;
	public AdapterContextMenuInfo info;
	
	private static final int DOWNLOAD_ID = Menu.FIRST;
	private static final int READ_ID = Menu.FIRST+1;
	private static final boolean onUpdate = true;
	private static final boolean onCreate = false;
	
	private ESHelper esHelper;
	private View header;
	private String searchText;
	private EditText et;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		esHelper = new ESHelper();
		header = getLayoutInflater().inflate(R.layout.header_search, null);
		
		try {
			fillData(esHelper.getOnlineStories(), onCreate);
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
					ArrayList<Story> result = esHelper.searchOnlineStories(searchText);
					fillData(result, onUpdate);
				}else{
					try {
						fillData(esHelper.getOnlineStories(), onUpdate);
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