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
		
		// MADE UP BOOKS FOR TESTING VIEWS
		try {
			Story s1 = new Story(1, "From Hell", "Alan Moore");		
			Story s2 = new Story(2, "Voice of Fiew", "Alan Moore");
			Story s3 = new Story(3, "V for Vendetta", "Alan Moore");
			Story s4 = new Story(4, "1984", "George Owell");
			Story s5 = new Story(5, "The art of computer programming", "Donald Knuth");
			Story s6 = new Story(6, "Alice in Wonderland", "Lewis Carroll");
			Story s7 = new Story(7, "Hamlet", "William Shakespeare");
			Story s8 = new Story(8, "A midsummer night's dream", "William Shakespeare");
			Story s9 = new Story(9, "The Fall of the House of Usher", "Edgar Allan Poe");
			Story s10 = new Story(10, "The Masque of the Red Death", "Edgar Allan Poe");
			Story[] slist = {s1,s2,s3,s4,s5,s6,s7,s8,s9,s10};
			for(Story s: slist){
				fHelper.addOfflineStory(s);				
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
        // following 4 lines will display the information on selected item.
        
        StoryInfo selectedValue = (StoryInfo) lv.getAdapter().getItem(position);		
		System.out.println(selectedValue.getTitle());
		System.out.println(selectedValue.getAuthor());
		System.out.println(selectedValue.getId());
        
        // TODO: read the story - initialize currentStory to story object
        
         /* 
     // create intent to pass the selected story object and the first story fragment id to the StoryFragmentActivity
        Intent firstStoryFragment = new Intent(getApplicationContext(), StoryFragmentActivity.class);		            
    
        // send the story object through the intent
        firstStoryFragment.putExtra("story", (Serializable)currentStory);
        // send the first story fragment id through the intent
        firstStoryFragment.putExtra("storyFragmentId", currentStory.getFirstStoryFragment());
   
  //      startActivity(firstFragment);
   * */
   
    }

	
	
	public void fillData(ArrayList<Story> sList, boolean update){
		ArrayList<StoryInfo> lList = new ArrayList<StoryInfo>();
		
		for(int i = 0; i < sList.size(); i++){
			String title = sList.get(i).getTitle();
			String author = sList.get(i).getAuthor();
			int id = sList.get(i).getStoryId();
			StoryInfo si = new StoryInfo(title, author, id);
			lList.add(si);
			
			//lList.add(new StoryInfo(sList.get(i).getTitle(), sList.get(i).getAuthor(), 
			//		sList.get(i).getStoryId()));
		}
		
		if(!update){
			lv.addHeaderView(header);	
		}	
		lv.setAdapter(new StoryInfoAdapter(this, android.R.id.list, lList));	
	}


	}


