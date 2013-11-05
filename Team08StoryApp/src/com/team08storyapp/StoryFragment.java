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

    /**
     * @return the storyFragmentId
     */
    public int getStoryFragmentId() {
        return storyFragmentId;
    }

    /**
     * @param storyFragmentId the storyFragmentId to set
     */
    public void setStoryFragmentId(int storyFragmentId) {
        this.storyFragmentId = storyFragmentId;
    }

    /**
     * @return the storyText
     */
    public String getStoryText() {
        return storyText;
    }

    /**
     * @param storyText the storyText to set
     */
    public void setStoryText(String storyText) {
        this.storyText = storyText;
    }

    /**
     * @return the photos
     */
    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    /**
     * @param photos the photos to set
     */
    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }

    /**
     * @return the choices
     */
    public ArrayList<Choice> getChoices() {
        return choices;
    }

    /**
     * @param choices the choices to set
     */
    public void setChoices(ArrayList<Choice> choices) {
        this.choices = choices;
    }

    /**
     * @return the annotations
     */
    public ArrayList<Annotation> getAnnotations() {
        return annotations;
    }

    /**
     * @param annotations the annotations to set
     */
    public void setAnnotations(ArrayList<Annotation> annotations) {
        this.annotations = annotations;
    }

}
