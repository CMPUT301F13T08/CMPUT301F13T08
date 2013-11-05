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
 * Story is a model class representing a Story. A story has the following
 * properties:
 * <ul>
 * <li>Online Story Id to uniquely identify the Story from other Stories stored
 * online.
 * <li>OfflineStoryId to uniquely identify the Story from other Stories stored
 * offline.
 * <li>Title of the Story.
 * <li>Author of the Story.
 * <li>A list of the Story Fragments, which are the pages that make up the
 * Story.
 * <li>First Story Fragment Id of the first page of the Story.
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
     * A constructor for creating a Story object used when initializing a sample
     * story for testing. The following properties must be know:
     * <ul>
     * <li>Title
     * <li>Author
     * </ul>
     * 
     * @param title
     *            The title of the Story.
     * @param author
     *            The author of the Story.
     */

    public Story(String title, String author) {
	this.title = title;
	this.author = author;
	storyFragments = new ArrayList<StoryFragment>();
    }

    /**
     * A constructor for creating a Story object used when an Author is writing
     * a Story. The following properties must be know:
     * <ul>
     * <li>OfflineStoryId
     * <li>Title
     * <li>Author
     * </ul>
     * 
     * @param offlineStoryId
     *            The Id used to uniquely identify the Story offline.
     * @param title
     *            The title of the Story.
     * @param author
     *            The author of the Story.
     */

    public Story(int offlineStoryId, String title, String author) {
	this.offlineStoryId = offlineStoryId;
	this.title = title;
	this.author = author;
	storyFragments = new ArrayList<StoryFragment>();
    }

    /**
     * @return The onlineStoryId used to uniquely identify of the Story from
     *         online.
     */
    public int getOnlineStoryId() {
	return onlineStoryId;
    }

    /**
     * @param onlineStoryId
     *            The onlineStoryId to set the Story to.
     */
    public void setOnlineStoryId(int onlineStoryId) {
	this.onlineStoryId = onlineStoryId;
    }

    /**
     * @return The offlineStoryId used to uniquely identify of the Story when
     *         stored offline.
     */
    public int getOfflineStoryId() {
	return offlineStoryId;
    }

    /**
     * @param offlineStoryId
     *            The offlineStoryId to set the stored Story to.
     */
    public void setOfflineStoryId(int offlineStoryId) {
	this.offlineStoryId = offlineStoryId;
    }

    /**
     * @return The title of the Story.
     */
    public String getTitle() {
	return title;
    }

    /**
     * @param title
     *            The title to set the Story with.
     */
    public void setTitle(String title) {
	this.title = title;
    }

    /**
     * @return The author of the Story.
     */
    public String getAuthor() {
	return author;
    }

    /**
     * @param author
     *            The author to set for the Story.
     */
    public void setAuthor(String author) {
	this.author = author;
    }

    /**
     * @return The storyFragments, which are the pages of the Story.
     */
    public ArrayList<StoryFragment> getStoryFragments() {
	return storyFragments;
    }

    /**
     * @param storyFragments
     *            The storyFragments to set for the pages of the Story.
     */
    public void setStoryFragments(ArrayList<StoryFragment> storyFragments) {
	this.storyFragments = storyFragments;
    }

    /**
     * @return The firstStoryFragmentId, that is to uniquely identify the first
     *         page of the Story to being the Story with.
     */
    public int getFirstStoryFragmentId() {
	return firstStoryFragmentId;
    }

    /**
     * @param firstStoryFragmentId
     *            The firstStoryFragmentId to set for the Story`s first page.
     */
    public void setFirstStoryFragmentId(int firstStoryFragmentId) {
	this.firstStoryFragmentId = firstStoryFragmentId;
    }
}
