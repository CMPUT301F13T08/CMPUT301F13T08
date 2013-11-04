/*
AUTHORS
========
Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen.

LICENSE
=======
Copyright  ©  2013 Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen,  
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
-This demo was used to help with JSON and ESHelper

Retrieved Oct. 29, 2013  - http://hc.apache.org/downloads.cgi
-This is for the fluent library which is licenced under apache V2

Retrieved Oct. 29, 2013 
- https://code.google.com/p/google-gson/downloads/detail?name=google-gson-2.2.4-release.zip&can=2&q=
-This is for JSON which is licenced under apache V2
 */

package com.team08storyapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Story is a model class representing a User's Story. A story has the following
 * properties:
 * <ul>
 * <li>OnlineStoryId to uniquely identify the Stories online.
 * <li>OfflineStoryId to uniquely identify the Stories offline.
 * <li>Title, to represent the title of a story.
 * <li>Author, to identify who wrote the story.
 * <li>StoryFragments, which is an array that contains all the storyfragments related to a story.
 * <li>FirstStoryFragmentId is an integer which points towards the ID of the first fragment related to the story.
 * </ul>
 * These properties are able to be accessed through the constructor or through
 * public getters/setters.
 * 
 * @see StoryFragment
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 1.0 November 8, 2013
 * @since 1.0
 * 
 */

public class Story implements Serializable {

    private static final long serialVersionUID = 1L;
    private int onlineStoryId;
    private int offlineStoryId;
    private String title;
    private String author;
    private ArrayList<StoryFragment> storyFragments;
    private int firstStoryFragmentId;

    /**
     * The default constructor for creating a Story object. It will have no properties
     * set except it creates a new ArrayList of storyFragment objects
     */
    
    public Story() {
    	storyFragments = new ArrayList<StoryFragment>();
    }
    
    /**
     * A constructor for creating a Story object, when the following
     * properties are know:
     * <ul>
     * <li>Title
     * <li>Author
     * </ul>
     * 
     * @param title
     * 				The text which the story is called.
     * @param author
     * 				The text with the name of the author
     */

    public Story(String title, String author) {
    	this.title = title;
    	this.author = author;
		storyFragments = new ArrayList<StoryFragment>();
    }

    /**
     * A constructor for creating a Story object, when the following
     * properties are know:
     * <ul>
     * <li>OfflineStoryId
     * <li>Title
     * <li>Author
     * </ul>
     * 
     * @param offlineStoryId
     * 				The integer uniquely identifies this story offline.
     * @param title
     * 				The text which the story is called.
     * @param author
     * 				The text with the name of the author
     */
    
    public Story(int offlineStoryId, String title, String author) {
    	this.offlineStoryId = offlineStoryId;
    	this.title = title;
    	this.author = author;
    	storyFragments = new ArrayList<StoryFragment>();
    }

    /**
     * A constructor for creating a Story object, when the following
     * properties are know:
     * <ul>
     * <li>OfflineStoryId
     * <li>Title
     * <li>Author
     * <li>StoryFragments
     * <li>FirstStoryFragmentId
     * </ul>
     * 
     * @param offlineStoryId
     * 				The integer uniquely identifies this story offline.
     * @param title
     * 				The text which the story is called.
     * @param author
     * 				The text with the name of the author.
     * @param storyFragments
     * 				A preset arraylist of story fragments.
     * @param firstStoryFragmentId
     * 				An integer which points to the first fragment of the story.
     */
    
    public Story(int offlineStoryId, String title, String author,
	    ArrayList<StoryFragment> storyFragments, int firstStoryFragmentId) {
    	super();
    	this.offlineStoryId = offlineStoryId;
    	this.title = title;
    	this.author = author;
    	storyFragments = new ArrayList<StoryFragment>();
    }
    /**
     * @return onlineStoryId
     */

    public int getOnlineStoryId() {
    	return onlineStoryId;
    }

    /**
     * @param onlineStoryId
     * 			the onlineStoryId to set.
     */
    public void setOnlineStoryId(int onlineStoryId) {
    	this.onlineStoryId = onlineStoryId;
    }

    /**
     * @return title
     */
    
    public String getTitle() {
    	return title;
    }

    /**
     * @return author
     */

    public String getAuthor() {
    	return author;
    }

    /**
     * @return ArrayList of storyFragments
     */
    
    public ArrayList<StoryFragment> getStoryFragments() {
    	return storyFragments;
    }

    /**
     * @param ArrayList of storyFragments
     * 			sets the arraylist of storyFragments.
     */
    public void setStoryFragments(ArrayList<StoryFragment> storyFragments) {
    	this.storyFragments = storyFragments;
    }
    
    /**
     * @return firstStoryFragmentId
     */

    public int getFirstStoryFragment() {
    	return firstStoryFragmentId;
    }

    /**
     * @param firstStoryFragmentId
     * 				Sets the first storyFragment Id
     */
    
    public void setFirstStoryFragment(int firstStoryFragmentId) {
	this.firstStoryFragmentId = firstStoryFragmentId;
    }

    @Override
    public String toString() {
	return "Story [offlineStoryId=" + offlineStoryId+ "olineStoryId= " + onlineStoryId + "title=" + title + ", author="
		+ author + ", storyFragments=" + storyFragments
		+ ", firstStoryFragmentId=" + firstStoryFragmentId + "]";
    }
    
    /**
     * @return offlineStoryId
     */

    public int getOfflineStoryId() {
	return offlineStoryId;
    }

    /**
     * @param offlineStoryId
     * 			sets offlineStoryId
     */
    public void setOfflineStoryId(int offlineStoryId) {
	this.offlineStoryId = offlineStoryId;
    }
}
