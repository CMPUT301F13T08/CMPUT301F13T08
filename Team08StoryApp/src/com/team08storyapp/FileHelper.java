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
import java.io.ByteArrayOutputStream;
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
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.util.Base64;

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
    private static final int Save = 1;
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
     * appendUpdateQueue function is called whenever a local authored story has
     * been changed since last sync. This function will append the story's
     * offline id into a file named "updateQueue" if it's not in the
     * updateQueue. And the "updateQueue" file is used for keeping records of
     * locally authored stories that have been edited since the last sync.
     * 
     * @param storyId
     *            the offline id of the story that needs to be synced to
     *            webserver
     */
    public void appendUpdateQueue(int storyId) {
	try {

	    /* create the updateQueue file or open it if it exists */
	    String filename = "updateQueue";
	    FileOutputStream fos = fileContext.openFileOutput(filename,
		    Context.MODE_APPEND);

	    /* write the offline id of story that is need to be uploaded */
	    fos.write((Integer.toString(storyId) + "\n").getBytes());
	    fos.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * This function is usually called under condition of the network is
     * connected and check if a sync is need to be performed. getUpdateFilesIds
     * returns a list of ids that are read from the file "updateQueue". Those
     * ids are the offline ids of stories that need to be synced.
     * 
     * 
     * @return updateId It's an ArrayList of Strings, which represent the
     *         offline ids of the stories that need to be synced to webserver.
     */
    public ArrayList<String> getUpdateFilesIds() {
	ArrayList<String> updateId = new ArrayList<String>();
	try {

	    /* open the "updateQueue" file */
	    File dir = fileContext.getFilesDir();
	    File updateQueue = new File(dir, "updateQueue");

	    /* read in the ids of stories that need to be uploaded */
	    InputStream is = new BufferedInputStream(new FileInputStream(
		    updateQueue));
	    if (is != null) {
		InputStreamReader isr = new InputStreamReader(is);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(isr);
		String temp = "";
		while ((temp = br.readLine()) != null) {
		    if (!updateId.contains(temp)) {
			updateId.add(temp);
		    }
		}
	    }
	    is.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return updateId;
    }

    /**
     * clearUpdateQueue deletes the file "updateQueue", which keeps record of
     * stories that need to be uploaded, when a sync/upload is done
     * successfully.
     */
    public void clearUpdateQueue() {
	fileContext.deleteFile("updateQueue");
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

    /**
     * Function takes in a story object and encodes all the image files related
     * to the story into a Base64 string for further use. (Convert to Json
     * Object and upload to webserver)
     * 
     * @param s
     *            a story that needs to be encoded for uploading to webserver
     * @return s an encoded story object
     * @throws IOException
     */
    public Story encodeStory(Story s) throws IOException {

	/* get all fragments */
	ArrayList<StoryFragment> sfList = s.getStoryFragments();

	/* for each fragment, get it's photolist and annotation list */
	for (int i = 0; i < sfList.size(); i++) {
	    ArrayList<Photo> photos = sfList.get(i).getPhotos();
	    ArrayList<Annotation> annotations = sfList.get(i).getAnnotations();

	    /* set picture in each Photo object to empty after encoding. */
	    for (int m = 0; m < photos.size(); m++) {
		try {
		    InputStream is = fileContext.openFileInput(photos.get(m)
			    .getPictureName());
		    ByteArrayOutputStream bos = new ByteArrayOutputStream();
		    byte[] b = new byte[1024];
		    int bytesRead = 0;
		    while ((bytesRead = is.read(b)) != -1) {
			bos.write(b, 0, bytesRead);
		    }
		    byte[] bytes = bos.toByteArray();

		    photos.get(m).setEncodedPicture(
			    Base64.encodeToString(bytes, Base64.DEFAULT));
		} catch (Exception e) {
		    continue;
		}
	    }

	    /* Encode the photo annotation object first and clear the photo. */
	    for (int n = 0; n < annotations.size(); n++) {
		try {
		    InputStream is = fileContext.openFileInput(annotations.get(
			    n).getPhoto());
		    ByteArrayOutputStream bos = new ByteArrayOutputStream();
		    byte[] b = new byte[1024];
		    int bytesRead = 0;
		    while ((bytesRead = is.read(b)) != -1) {
			bos.write(b, 0, bytesRead);
		    }
		    byte[] bytes = bos.toByteArray();
		    annotations.get(n).setEncodedAnnotation(
			    Base64.encodeToString(bytes, Base64.DEFAULT));
		} catch (Exception e) {
		    continue;
		}
	    }

	    /* update the annotations list and photos list of a story */
	    sfList.get(i).setAnnotations(annotations);
	    sfList.get(i).setPhotos(photos);
	}
	return s;

    }

    /**
     * This function decodes a story's images files from encoded strings to
     * byteArray and save them to local before passing the story to
     * addOfflineStory(Story story) for adding to local
     * 
     * @param story
     *            a story downdloaed from webserver that needs to be decoded
     * @param mode
     *            the mode:
     * @return story the decoded Story
     * @throws Exception
     * @throws IOException
     */
    public Story decodeStory(Story story, int mode) throws Exception,
	    IOException {

	/* get a story */
	int storyId;
	if (story.getOfflineStoryId() < 1) {
	    storyId = getOfflineStories().size() + 1;
	    story.setOfflineStoryId(storyId);
	} else {
	    storyId = story.getOfflineStoryId();
	}
	ArrayList<StoryFragment> sfList = story.getStoryFragments();
	for (int i = 0; i < sfList.size(); i++) {
	    ArrayList<Photo> photos = sfList.get(i).getPhotos();
	    ArrayList<Annotation> annotations = sfList.get(i).getAnnotations();
	    for (int m = 0; m < photos.size(); m++) {
		try {
		    byte[] photoByte = Base64.decode(photos.get(m)
			    .getEncodedPicture(), Base64.DEFAULT);
		    Bitmap photoBM = BitmapFactory.decodeByteArray(photoByte,
			    0, photoByte.length);

		    /*
		     * clear the encoded string to avoid conflicts with
		     * encodeStory and save spaces.
		     */
		    photos.get(m).setEncodedPicture("");
		    String fileName = "";
		    if (photos.get(m).getPictureName().isEmpty()) {
			if (mode == Save) {
			    fileName = "Image"
				    + Integer.toString(storyId)
				    + "Fragment"
				    + Integer.toString(sfList.get(i)
					    .getStoryFragmentId()) + "Photo"
				    + Integer.toString(m + 1) + ".png";
			}
		    } else {
			fileName = photos.get(m).getPictureName();
		    }
		    try {
			FileOutputStream fos = fileContext.openFileOutput(
				fileName, Context.MODE_PRIVATE);
			photoBM.compress(CompressFormat.PNG, 90, fos);
		    } catch (FileNotFoundException e) {
			e.printStackTrace();
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		    continue;
		}
	    }
	    for (int n = 0; n < annotations.size(); n++) {
		try {
		    byte[] annotationByte = Base64.decode(annotations.get(n)
			    .getEncodedAnnotation(), Base64.DEFAULT);
		    Bitmap annotationBM = BitmapFactory.decodeByteArray(
			    annotationByte, 0, annotationByte.length);
		    annotations.get(n).setEncodedAnnotation("");
		    String fileName = createFileName(storyId, i, annotations, n);
		    /*
		     * write the file to local system based on the fileName
		     * generated by the above code
		     */
		    try {
			FileOutputStream fos = fileContext.openFileOutput(
				fileName, Context.MODE_PRIVATE);
			annotationBM.compress(CompressFormat.PNG, 90, fos);
		    } catch (FileNotFoundException e) {
			e.printStackTrace();
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		    continue;
		}
	    }
	    sfList.get(i).setAnnotations(annotations);
	    sfList.get(i).setPhotos(photos);
	}
	return story;
    }

    /**
     * This function creates the file name for the annotation
     * 
     * @param storyId
     *            the id of selected story
     * @param i
     *            the index of storyfragment in storyFragments list
     * @param annotations
     *            list of annotations
     * @param n
     *            the index of annotation in annotation list
     * @return the string of file name
     */
    private String createFileName(int storyId, int i,
	    ArrayList<Annotation> annotations, int n) {
	String fileName;
	if (annotations.get(n).getPhoto().isEmpty()) {
	    fileName = "Image" + Integer.toString(storyId) + "Fragment"
		    + Integer.toString(i + 1) + "Annotation"
		    + Integer.toString(n + 1) + ".png";
	} else {
	    fileName = annotations.get(n).getPhoto();
	}
	return fileName;
    }

}
