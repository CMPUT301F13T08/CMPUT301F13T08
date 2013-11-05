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
     * StoryFragment is a model class representing a Story Fragment in a story.
     * A StoryFragment has the following properties:
     * <ul>
     * <li>Story Fragment Id is an integer which uniquely identify a story
     * fragment.
     * <li>Story text is a string which contains the words for the storyline.
     * <li>Photos is an ArrayList of photo objects.
     * <li>Choices is an ArrayList of choice objects.
     * <li>Annotation is an ArrayList of annotation object.
     * </ul>
     * These properties are able to be accessed through the constructor or
     * through public getters/setters.
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

    private static final long serialVersionUID = 1L;
    private int storyFragmentId;
    private String storyText;
    private ArrayList<Photo> photos;
    private ArrayList<Choice> choices;
    private ArrayList<Annotation> annotations;

    /**
     * The default constructor for creating a StoryFragment object. It will have
     * none of it's properties set, except it will have ArrayLists ready for
     * choices photos and annotations.
     */

    public StoryFragment() {

	choices = new ArrayList<Choice>();
	photos = new ArrayList<Photo>();
	annotations = new ArrayList<Annotation>();
    }

    /**
     * A constructor for creating a StoryFragment object, when the following
     * properties are know:
     * <ul>
     * <li>StoryFragmentId
     * </ul>
     * 
     * @param storyFragmentId
     *            The Id is used to uniquely identify a storyFragment
     */

    public StoryFragment(int storyFragmentId) {
	this.storyFragmentId = storyFragmentId;
	choices = new ArrayList<Choice>();
	photos = new ArrayList<Photo>();
	annotations = new ArrayList<Annotation>();
    }

    /**
     * A constructor for creating a StoryFragment object, when the following
     * properties are know:
     * <ul>
     * <li>StoryFragmentId
     * <li>StoryText
     * </ul>
     * 
     * @param storyFragmentId
     *            The Id is used to uniquely identify a storyFragment object.
     * @param storyText
     *            The textual component of the story
     */

    public StoryFragment(int storyFragmentId, String storyText) {
	this.storyFragmentId = storyFragmentId;
	this.storyText = storyText;
	choices = new ArrayList<Choice>();
	photos = new ArrayList<Photo>();
	annotations = new ArrayList<Annotation>();
    }

    /**
     * A constructor for creating a StoryFragment object, when the following
     * properties are know:
     * <ul>
     * <li>StoryText
     * </ul>
     * 
     * @param storyText
     *            The textual component of the story.
     */

    public StoryFragment(String storyText) {
	this.storyText = storyText;
	choices = new ArrayList<Choice>();
	photos = new ArrayList<Photo>();
	annotations = new ArrayList<Annotation>();
    }

    /**
     * A constructor for creating a StoryFragment object,, when the following
     * properties are known:
     * <ul>
     * <li>StoryFragment Id
     * <li>StoryText
     * <li>ArrayList of Photo
     * <li>Arraylist of Choice
     * <li>Arraylist of annotation
     * </ul>
     * 
     * @param storyFragmentId
     *            The Id used to uniquely identify an Annotation.
     * @param storyText
     *            The textual part of the story.
     * @param photos
     *            An ArrayList which contains photo objects
     * @param choices
     *            An ArrayList which contains choice objects
     * @param annotations
     *            An ArrrayList which contains annotation objects
     */

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

    /**
     * @return the Story Fragment Id
     */
    public int getStoryFragmentId() {
	return storyFragmentId;
    }

    /**
     * @param storyFragmentId
     *            Sets the Story Fragment Id
     */
    public void setStoryFragmentId(int storyFragmentId) {
	this.storyFragmentId = storyFragmentId;
    }

    /**
     * @return The text for the story.
     */

    public String getStoryText() {
	return storyText;
    }

    /**
     * @param storyText
     *            Sets the story text.
     */
    public void setStoryText(String storyText) {
	this.storyText = storyText;
    }

    /**
     * @return an ArrayList of photo objects.
     */
    public ArrayList<Photo> getPhotos() {
	return photos;
    }

    /**
     * @param photos
     *            Sets the ArrayList of photo.
     */
    public void setPhotos(ArrayList<Photo> photos) {
	this.photos = photos;
    }

    /**
     * @return an ArrayList of Choice objects.
     */

    public ArrayList<Choice> getChoices() {
	return choices;
    }
    
    /**
     * @param choices
     * 		Sets an ArrayList of Choice objects.
     */

    public void setChoices(ArrayList<Choice> choices) {
	this.choices = choices;
    }
    
    /**
     * @return an ArrayList of Annotation objects
     */

    public ArrayList<Annotation> getAnnotations() {
	return annotations;
    }
    
    /**
     * @param annotations
     * 		Sets an ArrayList of Annotation objects.
     */

    public void setAnnotations(ArrayList<Annotation> annotations) {
	this.annotations = annotations;
    }
    
    /**
     * @return a string with StoryFragmentId, StoryText, Photos, Choices, and Annotations.
     */

    @Override
    public String toString() {
	return "StoryFragment [storyFragmentId=" + storyFragmentId
		+ ", storyText=" + storyText + ", photos=" + photos
		+ ", choices=" + choices + ", annotations=" + annotations + "]";
    }

}
