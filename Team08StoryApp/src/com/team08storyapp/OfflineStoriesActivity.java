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
	
	// just to test:
	private Story initializeSampleStory() {
		Story sampleStory = new Story("The Walk", "Michele Paulichuk");
		sampleStory.setFirstStoryFragment(1);

		ArrayList<StoryFragment> storyFragmentList = new ArrayList<StoryFragment>();
		ArrayList<Choice> choices = new ArrayList<Choice>();

		// Story Fragment 1
		sampleStory.setFirstStoryFragment(1);
		StoryFragment storyFragment1 = new StoryFragment(1);
		storyFragment1
				.setStoryText("Like any other day, Amara decided to take her dog out for a walk. She left the house and exited the yard.");
		choices.add(new Choice(2, 1,
				"Amara turned south heading for her friend's house."));
		choices.add(new Choice(7, 2,
				"Amara turned north heading for the store."));
		choices.add(new Choice(3, 3,
				"Amara turned east heading for the river valley."));
		storyFragment1.setChoices(choices);
		storyFragmentList.add(storyFragment1);

		// Story Fragment 2
		StoryFragment storyFragment2 = new StoryFragment(1);
		storyFragment2
				.setStoryText("As Amara walked toward her friend's house her dog started acting up. It pulled this way and that way. Then it stopped and started barking at a bush.");
		choices.clear();
		choices.add(new Choice(4, 1, "In the bush, Amara found a small boy."));
		choices.add(new Choice(5, 2,
				"In the bush, Amara found a hundred dollar bill."));
		storyFragment2.setChoices(choices);
		storyFragmentList.add(storyFragment2);

		// Story Fragment 3
		StoryFragment storyFragment3 = new StoryFragment(1);
		storyFragment3
				.setStoryText("Amara gets to the river valley and decides to take a path she has yet to explore. As Amara walks along she comes across a cave in the side of the valley.");
		choices.clear();
		choices.add(new Choice(8, 1,
				"Amara decides to take a peek in the cave."));
		choices.add(new Choice(9, 2,
				"Amara thinks the cave is creepy and continues walking."));
		storyFragment3.setChoices(choices);
		storyFragmentList.add(storyFragment3);

		// Story Fragment 4
		StoryFragment storyFragment4 = new StoryFragment(1);
		storyFragment4
				.setStoryText("At first Amara was startled by the boy. Then she realized he was crying softly. So she asked the boy what�s wrong. The boy was lost.");
		choices.clear();
		choices.add(new Choice(10, 1,
				"Amara decides to help the boy find his way home."));
		choices.add(new Choice(11, 2, "Amara is uncertain what to do."));
		storyFragment4.setChoices(choices);
		storyFragmentList.add(storyFragment4);

		// Story Fragment 5
		StoryFragment storyFragment5 = new StoryFragment(1);
		storyFragment5
				.setStoryText("Amara picked up the bill and looked around. There was no one around and therefore anyone to claim the money. So Amara decided to keep the bill and continue on to her friend�s house.");
		choices.clear();
		choices.add(new Choice(6, 1, "Continue"));
		storyFragment5.setChoices(choices);
		storyFragmentList.add(storyFragment5);

		// Story Fragment 6
		StoryFragment storyFragment6 = new StoryFragment(1);
		storyFragment6
				.setStoryText("When Amara got to her friend�s house she explained her excitement at finding one hundred dollars in a bush, all thanks to her dog. Her friend suggests she should reward her dog a treat with part of the money.");
		choices.clear();
		choices.add(new Choice(7, 1, "Continue"));
		storyFragment6.setChoices(choices);
		storyFragmentList.add(storyFragment6);

		// Story Fragment 7
		StoryFragment storyFragment7 = new StoryFragment(1);
		storyFragment7
				.setStoryText("Amara heads to the store and buys her dog a big juicy bone. After which, they head home for a nap. THE END");
		storyFragmentList.add(storyFragment7);

		// Story Fragment 8
		StoryFragment storyFragment8 = new StoryFragment(1);
		storyFragment8
				.setStoryText("Amara steps towards the cave, as a little boy crawls out of it.");
		choices.clear();
		choices.add(new Choice(4, 1, "Continue"));
		storyFragment8.setChoices(choices);
		storyFragmentList.add(storyFragment8);

		// Story Fragment 9
		StoryFragment storyFragment9 = new StoryFragment(1);
		storyFragment9
				.setStoryText("Amara walks past the cave in a hurry. She notices the clouds have formed into rain clouds and decides it�s time to head home. THE END");
		storyFragmentList.add(storyFragment9);

		// Story Fragment 10
		StoryFragment storyFragment10 = new StoryFragment(1);
		storyFragment10
				.setStoryText("The boy says he knows his phone number and his mom is home but has no way to call her. Amara pulls out her cell and dials the number. She explains she found the boy and where she is.");
		choices.clear();
		choices.add(new Choice(12, 1, "Continue"));
		storyFragment10.setChoices(choices);
		storyFragmentList.add(storyFragment10);

		// Story Fragment 11
		StoryFragment storyFragment11 = new StoryFragment(1);
		storyFragment11
				.setStoryText("As Amara and the boy stand there, they hear a voice calling the name Timothy. The boy stops his crying and starts yelling Momma. The boy�s mother comes rushing up and is relieved to find her missing son. You decide you have enough excitement for one walk and turn to head home for a nap. THE END");
		storyFragmentList.add(storyFragment11);

		// Story Fragment 12
		StoryFragment storyFragment12 = new StoryFragment(1);
		storyFragment12
				.setStoryText("The boy's mother arrives shortly and is relieved to find her son. As a reward for finding her son, she invites to take you out for ice cream with them. You decide to abandon your walk and take her offer up. THE END");
		storyFragmentList.add(storyFragment12);

		sampleStory.setStoryFragments(storyFragmentList);
		return sampleStory;
	}
		
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
        
		// just to test:
		currentStory = initializeSampleStory();
		if (currentStory == null){
			System.out.println("NO STORYYYY here");
		}
     // create intent to pass the selected story object and the first story fragment id to the StoryFragmentActivity
        Intent firstStoryFragment = new Intent(getApplicationContext(), StoryFragmentActivity.class);		            
    
        // send the story object through the intent
        firstStoryFragment.putExtra("story", currentStory);
        // send the first story fragment id through the intent
        int nextStoryFragmentId = currentStory.getFirstStoryFragment();
        firstStoryFragment.putExtra("storyFragmentId", nextStoryFragmentId);
   
        startActivity(firstStoryFragment);
   
   
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
