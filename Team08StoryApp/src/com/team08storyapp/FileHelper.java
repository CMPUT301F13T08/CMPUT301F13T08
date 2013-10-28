package com.team08storyapp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
	
	/* a context variable provides access to application-specific resources and 
	 * classes, as well as up-calls for application-level operations such as launching 
	 * activities, broadcasting and receiving intents, etc.
	 * In this case, it provides the directory of internal storage to store our files.
	 */
	private Context fileContext;	
	private Gson gson = new Gson();
	
	/*
	 * Initialize the fileContext with passed context.
	 */
	public FileHelper(Context context){
		fileContext = context;
	}
	
	
	/*
	 *  function: addOfflineStory
	 *  input : Story story
	 *  ouput: boolean value
	 *  
	 *  description: 
	 *  Create a file named after the story's Id (since it's unique), and write the story
	 *  content into the file.
	 *  If a story is successfully written into the file, true will be returned. Otherwise,
	 *  function will return false.
	 */
	public boolean addOfflineStory(Story story) throws FileNotFoundException, IOException {
		try{
			String fileName = Integer.toString(story.getStoryId()); // create the file name
			String context = gson.toJson(story);	// translate the story context to Json
			FileOutputStream ops = fileContext.openFileOutput(fileName, Context.MODE_PRIVATE);
			ops.write(context.getBytes());	
			ops.close();	
			//System.out.println("WRITTING DONE");
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/*
	 * function: updateOfflineStory
	 * input: Stroy story
	 * output: boolean value
	 * 
	 * description:
	 * Update process is done by deleting the original file and write a new file. So this function
	 * deletes the original file named by the story id and creates a new file and writes the updated
	 * content into the new file, then saves it.
	 * When update is successful, true will be returned, otherwise false will be returned.
	 */
	public boolean updateOfflineStory(Story story)throws FileNotFoundException, IOException{
		try{
			String fileName = Integer.toString(story.getStoryId());
			fileContext.deleteFile(fileName); // delete original file
			addOfflineStory(story);		// add new file
			return true;
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/*
	 * function: getOfflineStory
	 * input: int storyId
	 * output: Story story
	 * 
	 * description:
	 * Retrieve the file by the passed story id, and get the file. Finally return it.
	 * If any error occurs, a null object will be returned.
	 */
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
	/*
	 * function: getOfflineStories
	 * input: N/A
	 * output: ArrayList<Story> storyList;
	 * 
	 * description:
	 * First the function creates a file which contains the directory of all files.
	 * Then file.listFiles() function returns a list of files represented by the directory in the file.
	 * And the function will get a list of stories in Json and put them into ArrayList<Story> sList.
	 * On success, the sList will be returned with a list of stories. Otherwise sList will be null.
	 */
	public ArrayList<Story> getOfflineStories() throws FileNotFoundException, IOException{
		File file = fileContext.getFilesDir();
		File[] fileList= file.listFiles();
		int length = fileList.length;
		ArrayList<Story> sList = new ArrayList<Story>();
		
		for(int i = 0; i < length; i++){
			// ReadIn process
			InputStream is = new BufferedInputStream(new FileInputStream(fileList[i]));
			if (is != null){
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader br = new BufferedReader(isr);
				String temp = "";
				StringBuilder stringBuilder = new StringBuilder();
				while ( (temp = br.readLine()) != null ) {
	                stringBuilder.append(temp);
	            }
	            is.close();
	            // Translation process
	            Type storyType = new TypeToken<Story>(){}.getType();
	            Story story = gson.fromJson(stringBuilder.toString(), storyType);
	            //System.out.println(story.toString());	
	            // Add process
	            sList.add(story);
			}			
		}
		return sList;
	}

	public ArrayList<Story> searchOfflineStories(String searchText) {
		searchText = searchText.toLowerCase();
		
		try{
			
		ArrayList<Story> allList = getOfflineStories();
		ArrayList<Story> resultList = new ArrayList<Story>();
		
		for(int i = 0; i < allList.size(); i++){
			String title = allList.get(i).getTitle().toLowerCase();
			String author = allList.get(i).getAuthor().toLowerCase();
			if(author.contains(searchText) || title.contains(searchText)){
				resultList.add(allList.get(i));
			}
		}
		return resultList;
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
	
	
	
	

