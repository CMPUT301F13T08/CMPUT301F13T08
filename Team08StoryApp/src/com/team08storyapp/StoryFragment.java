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
-This demo was used to help with JSON and ESHelper which is under the CC0 licenses

Retrieved Oct. 29, 2013  - http://hc.apache.org/downloads.cgi
-This is for the fluent library which is licensed under apache V2

Retrieved Oct. 29, 2013 
- https://code.google.com/p/google-gson/downloads/detail?name=google-gson-2.2.4-release.zip&can=2&q=
-This is for JSON which is licensed under apache V2
 */

package com.team08storyapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * StoryFragment is a model class representing the pages of a Chose Your Own
 * Adventure Story. An StoryFragment has the following properties:
 * <ul>
 * <li>Story Fragment Id to uniquely identify the Story Fragment within a Story.
 * <li>Story Text that makes up the body text of a Story Page.
 * <li>Photos that illustrate what is happening in this page of the Story.
 * <li>Choices that allow the reader to decide what happens next in the Story
 * resulting in a different page being next.
 * <li>Annotations that users added to contribute there opinions on the Story.
 * </ul>
 * These properties are able to be accessed through the constructor or through
 * public getters/setters.
 * 
 * @see Story
 * @see Photo
 * @see Choice
 * @see Annotation
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
public class StoryFragment implements Serializable {

    private static final long serialVersionUID = 1L;
    private int storyFragmentId;
    private String storyText;
    private ArrayList<Photo> photos;
    private ArrayList<Choice> choices;
    private ArrayList<Annotation> annotations;

    /**
     * A constructor for creating a Story Fragment object used when initializing
     * a sample story for testing. The following properties must be know:
     * <ul>
     * <li>Story Fragment Id
     * </ul>
     * 
     * @param storyFragmentId
     *            The unique identifier used to identify the current Story
     *            Fragment.
     */
    public StoryFragment(int storyFragmentId) {
	this.storyFragmentId = storyFragmentId;
	choices = new ArrayList<Choice>();
	photos = new ArrayList<Photo>();
	annotations = new ArrayList<Annotation>();
    }

    /**
     * A constructor for creating a Story Fragment object used to help setup the
     * pages to display for a Story to be read or for saving to when an Author
     * is writing a Story. The following properties must be known:
     * <ul>
     * <li>Story Fragment Id
     * <li>Story Text
     * </ul>
     * 
     * @param storyFragmentId
     *            The unique identifier used to identify the current Story
     *            Fragment.
     * @param storyText
     *            The text that makes up the body of the Story Fragment.
     */
    public StoryFragment(int storyFragmentId, String storyText) {
	this.storyFragmentId = storyFragmentId;
	this.storyText = storyText;
	choices = new ArrayList<Choice>();
	photos = new ArrayList<Photo>();
	annotations = new ArrayList<Annotation>();
    }

    /**
     * @return The storyFragmentId used to uniquely identify the Story Fragment.
     */
    public int getStoryFragmentId() {
	return storyFragmentId;
    }

    /**
     * @param storyFragmentId
     *            The storyFragmentId to set for the Story Fragment.
     */
    public void setStoryFragmentId(int storyFragmentId) {
	this.storyFragmentId = storyFragmentId;
    }

    /**
     * @return The storyText that makes up the body of the Story Fragment.
     */
    public String getStoryText() {
	return storyText;
    }

    /**
     * @param storyText
     *            The storyText to set for the Story Fragment's body.
     */
    public void setStoryText(String storyText) {
	this.storyText = storyText;
    }

    /**
     * @return The photos that illustrate the Story being told in the Story
     *         Fragment.
     */
    public ArrayList<Photo> getPhotos() {
	return photos;
    }

    /**
     * @param photos
     *            The photos to set in the Story Fragment for the Story.
     */
    public void setPhotos(ArrayList<Photo> photos) {
	this.photos = photos;
    }

    /**
     * @return The choices a reader has in moving the Story along from the
     *         current Story Fragment.
     */
    public ArrayList<Choice> getChoices() {
	return choices;
    }

    /**
     * @param choices
     *            The choices to set for that will be available for this Story
     *            Fragment.
     */
    public void setChoices(ArrayList<Choice> choices) {
	this.choices = choices;
    }

    /**
     * @return The annotations readers have provided for this Story Fragment.
     */
    public ArrayList<Annotation> getAnnotations() {
	return annotations;
    }

    /**
     * @param annotations
     *            the annotations to set that readers have given for this Story
     *            Fragment.
     */
    public void setAnnotations(ArrayList<Annotation> annotations) {
	this.annotations = annotations;
    }

}
