package com.team08storyapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
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
	private ListView lv;
	private String searchText;
	private EditText et;	
	private Story currentStory;
	
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_story_list);
		lv = (ListView) findViewById(android.R.id.list);
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
					fillData(fHelper.searchOfflineStories(searchText), onUpdate);
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
        StoryInfo selectedValue = (StoryInfo) lv.getAdapter().getItem(position);		
		System.out.println(selectedValue.getTitle());
		System.out.println(selectedValue.getAuthor());
		System.out.println(selectedValue.getId());
        
        // TODO: read the story - initialize currentStory to story object
        
          
     // create intent to pass the selected story object and the first story fragment id to the StoryFragmentActivity
        Intent firstStoryFragment = new Intent(getApplicationContext(), StoryFragmentActivity.class);		            
    
        // send the story object through the intent
        firstStoryFragment.putExtra("story", (Serializable)currentStory);
        // send the first story fragment id through the intent
        firstStoryFragment.putExtra("storyFragmentId", currentStory.getFirstStoryFragment());
   
  //      startActivity(firstFragment);
    }

	
	
	public void fillData(ArrayList<Story> sList, boolean update){
		ArrayList<StoryInfo> lList = new ArrayList<StoryInfo>();
		for(int i = 0; i < sList.size(); i++){
			String title = sList.get(i).getTitle();
			String author = sList.get(i).getAuthor();
			int id = sList.get(i).getStoryId();
			StoryInfo si = new StoryInfo(title, author, id);
			lList.add(si);			
		}
		if(!update){
			lv.addHeaderView(header);	
		}
		StoryInfoAdapter adapter = new StoryInfoAdapter(this, android.R.id.list, lList);
		lv.setAdapter(adapter);	

	}


}
