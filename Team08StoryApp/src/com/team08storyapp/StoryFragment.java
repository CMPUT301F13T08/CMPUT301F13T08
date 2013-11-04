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

public class StoryFragment implements Serializable {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private int storyFragmentId;
    private String storyText;
    private ArrayList<Photo> photos;
    private ArrayList<Choice> choices;
    private ArrayList<Annotation> annotations;

    public StoryFragment() {

	choices = new ArrayList<Choice>();
	photos = new ArrayList<Photo>();
	annotations = new ArrayList<Annotation>();
    }

    public StoryFragment(int storyFragmentId) {
	this.storyFragmentId = storyFragmentId;
	choices = new ArrayList<Choice>();
	photos = new ArrayList<Photo>();
	annotations = new ArrayList<Annotation>();
    }

    public StoryFragment(int storyFragmentId, String storyText) {
	this.storyFragmentId = storyFragmentId;
	this.storyText = storyText;
	choices = new ArrayList<Choice>();
	photos = new ArrayList<Photo>();
	annotations = new ArrayList<Annotation>();
    }

    public StoryFragment(String storyText) {
	this.storyText = storyText;
	choices = new ArrayList<Choice>();
	photos = new ArrayList<Photo>();
	annotations = new ArrayList<Annotation>();
    }

    public StoryFragment(int storyFragmentId, String storyText,
	    ArrayList<Photo> photos, ArrayList<Choice> choices,
	    ArrayList<Annotation> annotations) {
	super();
	this.storyFragmentId = storyFragmentId;
	this.storyText = storyText;
	this.photos = photos;
	this.choices = choices;
	this.annotations = annotations;
	choices = new ArrayList<Choice>();
	photos = new ArrayList<Photo>();
	annotations = new ArrayList<Annotation>();
    }

    public int getStoryFragmentId() {
	return storyFragmentId;
    }

    public void setStoryFragmentId(int storyFragmentId) {
	this.storyFragmentId = storyFragmentId;
    }

    public String getStoryText() {
	return storyText;
    }

    public void setStoryText(String storyText) {
	this.storyText = storyText;
    }

    public ArrayList<Photo> getPhotos() {
	return photos;
    }

    public void setPhotos(ArrayList<Photo> photos) {
	this.photos = photos;
    }

    public ArrayList<Choice> getChoices() {
	return choices;
    }

    public void setChoices(ArrayList<Choice> choices) {
	this.choices = choices;
    }

    public ArrayList<Annotation> getAnnotations() {
	return annotations;
    }

    public void setAnnotations(ArrayList<Annotation> annotations) {
	this.annotations = annotations;
    }

    @Override
    public String toString() {
	return "StoryFragment [storyFragmentId=" + storyFragmentId
		+ ", storyText=" + storyText + ", photos=" + photos
		+ ", choices=" + choices + ", annotations=" + annotations + "]";
    }


}
