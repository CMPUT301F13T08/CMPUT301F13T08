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

import android.annotation.SuppressLint;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * FileHelper is a helper class that mainly manages retrieving, saving stories
 * or images files in local, sometimes assists ESHelper by encoding or decoding
 * a story when uploading or downloading a story
 * 
 * <p>
 * In order to manage files, FileHelper provides the following methods:
 * <ul>
 * <li>Add an offline story with a given story.
 * <li>Get an offline story with a given story Id.
 * <li>Retrieve a list of all available offline stories.
 * <li>Update an offline story with a given story Id.
 * <li>search for offline stories with given search string. (Note: it doesn't
 * perform search on "\n" and whitespace )
 * </ul>
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 1.0 November 8, 2013
 * @since 1.0
 */

@SuppressLint("DefaultLocale")
public class FileHelper {
    /*
     * a context variable provides access to application-specific resources and
     * classes, as well as up-calls for application-level operations such as
     * launching activities, broadcasting and receiving intents, etc. In this
     * case, it provides the directory of internal storage to store our files.
     */
    private Context fileContext;
    private Gson gson = new Gson();
    private static final int Download = 0;
    private static final int My = 1;
    private String prefix;

    /**
     * The Constructor initializes the fileContext with passed context. Since we
     * design to store author's own stories and downloaded stories in different
     * directories in internal storage, we need to differentiate them. (Actually
     * I haven't come up with a better idea than using a third party library
     * called DirectoryPicker when saving files in different directory in
     * internal storage. But I think the "prefix" can do the job as well. So I
     * may stick to it.)
     * 
     * @param context
     *            an Context object
     * @param mode
     *            integer,to access dowaloaded stories: mode == 0; to access
     *            authored stories mode == 1
     */
    public FileHelper(Context context, int mode) {
	fileContext = context;
	switch (mode) {
	case Download:
	    prefix = "Download";
	    break;
	case My:
	    prefix = "My";
	    break;
	}
    }

    /**
     * Create a file named after the story's Id (since it's unique), and write
     * the story content into the file. If a story is successfully written into
     * the file, true will be returned. Otherwise, function will return false.
     * 
     * @param story
     *            the story that needs to be added to local file system
     * @return true: story is successfully added | false: writing file error
     *         during adding process
     * @throws FileNotFoundException
     * @throws IOException
     */
    public boolean addOfflineStory(Story story) throws FileNotFoundException,
	    IOException {
	try {
	    
	    /*
	     * If the offlineStoryId of added story is taken, and the offline
	     * story which has the same offline Id is not the same story as the
	     * story we are going to add. Then we assign a new offline Id for
	     * it. Else if the offlineStoryId is empty and our Offline stock is
	     * also empty, we simply assign the new story an 1 as offline Id.
	     */
	    if ((getOfflineStory(story.getOfflineStoryId()) != null && getOfflineStory(
		    story.getOfflineStoryId()).getOnlineStoryId() != story
		    .getOnlineStoryId()) || story.getOfflineStoryId() == 0) {
		int total = getOfflineStories().size();
		if (total == 0) {
		    story.setOfflineStoryId(1);
		} else {
		    story.setOfflineStoryId(Math.max(total - 1,
			    getOfflineStories().get(total - 1)
				    .getOfflineStoryId()) + 1);
		    System.out.println("NEW OFFLINE ID:"
			    + story.getOfflineStoryId());
		}
	    }
	    String fileName = prefix
		    + Integer.toString(story.getOfflineStoryId());
	    
	    /* translate the story context to Json */
	    String context = gson.toJson(story);
	    FileOutputStream ops = fileContext.openFileOutput(fileName,
		    Context.MODE_PRIVATE);
	    ops.write(context.getBytes());
	    ops.close();
	    return true;
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return false;
    }

    /**
     * Update process is done by deleting the original file and write a new
     * file. So this function deletes the original file named by the story id
     * and creates a new file and writes the updated content into the new file,
     * then saves it. When update is successful, true will be returned,
     * otherwise false will be returned.
     * 
     * @param story
     *            the story object that needs to be updated
     * @return true: story is successfully updated | false: writing file error
     *         during update process
     * @throws FileNotFoundException
     * @throws IOException
     */
    public boolean updateOfflineStory(Story story)
	    throws FileNotFoundException, IOException {
	try {
	    String fileName = prefix
		    + Integer.toString(story.getOfflineStoryId());

	    /* delete original file */
	    fileContext.deleteFile(fileName);

	    /* add new file */
	    String context = gson.toJson(story);
	    FileOutputStream ops = fileContext.openFileOutput(fileName,
		    Context.MODE_PRIVATE);
	    ops.write(context.getBytes());
	    ops.close();
	    return true;
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return false;
    }

    /**
     * Retrieve the file by the passed story id, and get the file.
     * 
     * @param storyId
     *            the offline id of the desired story
     * @return on success desired story object will be returned otherwise null
     *         will be returned
     * @throws FileNotFoundException
     * @throws IOException
     */
    public Story getOfflineStory(int storyId) throws FileNotFoundException,
	    IOException {
	try {

	    /* compose the fileName of the story based on storyId */
	    String fileName = prefix + Integer.toString(storyId);
	    InputStream is = fileContext.openFileInput(fileName);
	    if (is != null) {
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String temp = "";
		StringBuilder stringBuilder = new StringBuilder();
		while ((temp = br.readLine()) != null) {
		    stringBuilder.append(temp);
		}
		is.close();
		Type storyType = new TypeToken<Story>() {
		}.getType();
		Story story = gson
			.fromJson(stringBuilder.toString(), storyType);
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

    /**
     * First the function creates a file which contains the directory of all
     * files. Then file.listFiles() function returns a list of files represented
     * by the directory in the file. And the function will get a list of stories
     * in Json and put them into ArrayList<Story> sList.
     * 
     * @return On success, the sList will be returned with a list of stories.
     *         Otherwise sList will be null.
     * @throws FileNotFoundException
     * @throws IOException
     */
    @SuppressLint("DefaultLocale")
    public ArrayList<Story> getOfflineStories() throws FileNotFoundException,
	    IOException {

	/* get all the files */
	File file = fileContext.getFilesDir();
	File[] fileList = file.listFiles();
	ArrayList<File> prefixFileList = new ArrayList<File>();

	/* get all the files with specific prefix */
	for (int i = 0; i < fileList.length; i++) {
	    if (fileList[i].getName().startsWith(prefix)) {
		prefixFileList.add(fileList[i]);
	    }
	}

	ArrayList<Story> sList = new ArrayList<Story>();
	for (int i = 0; i < prefixFileList.size(); i++) {

	    /* ReadIn process */
	    InputStream is = new BufferedInputStream(new FileInputStream(
		    prefixFileList.get(i)));
	    if (is != null) {
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String temp = "";
		StringBuilder stringBuilder = new StringBuilder();
		while ((temp = br.readLine()) != null) {
		    stringBuilder.append(temp);
		}
		is.close();

		/* Translation process */
		Type storyType = new TypeToken<Story>() {
		}.getType();
		Story story = gson
			.fromJson(stringBuilder.toString(), storyType);

		/* Add process */
		sList.add(story);
	    }
	}
	return sList;
    }

    /**
     * First the function gets all the stories in the current directory. And
     * then compares each stories author and title with searchText to determine
     * if this story should be added into the result storyList.
     * 
     * @param searchText
     *            the string that user enters as search string, usually it's an
     *            author name or title
     * @return return an ArrayList<Story> storyList that contains the search
     *         text in author and/or title field;
     */

    public ArrayList<Story> searchOfflineStories(String searchText) {
	searchText = searchText.toLowerCase();
	try {

	    /*
	     * if the search text is all whitespace or has a newline character,
	     * the function won't perform search.
	     */
	    if (!searchText.matches(".*\\w.*") || searchText.contains("\n")) {
		return getOfflineStories();
	    }

	    ArrayList<Story> allList = getOfflineStories();
	    ArrayList<Story> resultList = new ArrayList<Story>();
	    for (int i = 0; i < allList.size(); i++) {
		String title = allList.get(i).getTitle().toLowerCase();
		String author = allList.get(i).getAuthor().toLowerCase();
		if (author.contains(searchText) || title.contains(searchText)) {
		    resultList.add(allList.get(i));
		}
	    }
	    return resultList;
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }

}
