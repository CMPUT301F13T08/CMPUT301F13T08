package com.team08storyapp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class FileHelper{

	private Context fileContext;	
	private Gson gson = new Gson();
	
	public FileHelper(Context context){
		fileContext = context;
	}
	
	public boolean addOfflineStory(Story story) throws FileNotFoundException, IOException {
		try{
			String fileName = Integer.toString(story.getStoryId());
			String context = gson.toJson(story);
			FileOutputStream ops = fileContext.openFileOutput(fileName, Context.MODE_PRIVATE);
			ops.write(context.getBytes());
			ops.close();
			System.out.println("WRITTING DONE");
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateOfflineStory(Story story)throws FileNotFoundException, IOException{
		try{
			String fileName = Integer.toString(story.getStoryId());
			fileContext.deleteFile(fileName);
			addOfflineStory(story);
			return true;
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Story getOfflineStory(int storyId) throws FileNotFoundException, IOException {
		try{
			String fileName = Integer.toString(storyId);
			InputStream is = fileContext.openFileInput(fileName);
			
			if ( is != null){
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String temp = "";
				StringBuilder stringBuilder = new StringBuilder();
				while ( (temp = br.readLine()) != null ) {
	                stringBuilder.append(temp);
	            }

	            is.close();
	            Type storyType = new TypeToken<Story>(){}.getType();
	            Story story = gson.fromJson(stringBuilder.toString(), storyType);
	            System.out.println(story.toString());
	            return story;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<StoryFragment> getOfflineStories() throws FileNotFoundException, IOException{
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<StoryFragment> searchOfflineStories(String searchText) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
