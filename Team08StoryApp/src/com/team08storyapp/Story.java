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

public class Story implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private int onlineStoryId;
    private int offlineStoryId;
    private String title;
    private String author;
    private ArrayList<StoryFragment> storyFragments;
    private int firstStoryFragmentId;

    public Story() {
	storyFragments = new ArrayList<StoryFragment>();
    }

    public Story(String title, String author) {
	this.title = title;
	this.author = author;
	storyFragments = new ArrayList<StoryFragment>();
    }

    public Story(int offlineStoryId, String title, String author) {
	this.offlineStoryId = offlineStoryId;
	this.title = title;
	this.author = author;
	storyFragments = new ArrayList<StoryFragment>();
    }

    public Story(int offlineStoryId, String title, String author,
	    ArrayList<StoryFragment> storyFragments, int firstStoryFragmentId) {
	super();
	this.offlineStoryId = offlineStoryId;
	this.title = title;
	this.author = author;
	storyFragments = new ArrayList<StoryFragment>();
    }

    public int getOnlineStoryId() {
	return onlineStoryId;
    }

    public void setOnlineStoryId(int onlineStoryId) {
	this.onlineStoryId = onlineStoryId;
    }

    public String getTitle() {
	return title;
    }

    public String getAuthor() {
	return author;
    }

    public ArrayList<StoryFragment> getStoryFragments() {
	return storyFragments;
    }

    public void setStoryFragments(ArrayList<StoryFragment> storyFragments) {
	this.storyFragments = storyFragments;
    }

    public int getFirstStoryFragment() {
	return firstStoryFragmentId;
    }

    public void setFirstStoryFragment(int firstStoryFragmentId) {
	this.firstStoryFragmentId = firstStoryFragmentId;
    }

    @Override
    public String toString() {
	return "Story [offlineStoryId=" + offlineStoryId+ "olineStoryId= " + onlineStoryId + "title=" + title + ", author="
		+ author + ", storyFragments=" + storyFragments
		+ ", firstStoryFragmentId=" + firstStoryFragmentId + "]";
    }

    public int getOfflineStoryId() {
	return offlineStoryId;
    }

    public void setOfflineStoryId(int offlineStoryId) {
	this.offlineStoryId = offlineStoryId;
    }
}
