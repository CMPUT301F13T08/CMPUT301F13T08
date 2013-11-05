/*
AUTHORS
========
Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen.

LICENSE
=======
Copyright  �  2013 Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen,  
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

/**
 * Annotation is a model class representing a User's Annotations on a Particular
 * Story Fragment. An Annotation has the following properties:
 * <ul>
 * <li>Annotation Id to uniquely identify the Annotation.
 * <li>Story Fragment Id the Id identified with the Story Fragment the Annotation belongs to.
 * <li>Photo the photo the reader set in the Annotation.
 * <li>Text the text the reader set in the Annotation.
 * </ul>
 * These properties are able to be accessed through the constructor or through
 * public getters/setters.
 * 
 * @see StoryFragment
 * @see Photo
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
public class Annotation implements Serializable {
    @Override
    public String toString() {
	return "Annotation [annotationID=" + annotationID + ", text=" + text
		+ ", storyFragmentID=" + storyFragmentID + ", photo=" + photo
		+ ", encodedAnnotation=" + encodedAnnotation + "]";
    }

    private static final long serialVersionUID = 1L;
    private int annotationID;
    private String text;
    private int storyFragmentID;
    private String photo;
    private String encodedAnnotation;

    /**
     * The default constructor for creating an Annotation object. It will have
     * none of it's properties set, requiring the class instantiating the object
     * to set it's properties.
     */
    public Annotation() {
    }

    /**
     * A constructor for creating an Annotation object, when the following
     * properties are known:
     * <ul>
     * <li>Annotation Id
     * <li>Story Fragment Id
     * <li>Photo
     * <li>Text
     * </ul>
     * 
     * @param annotationID
     *            The Id used to uniquely identify an Annotation.
     * @param storyFragmentID
     *            The Id used to identify which story fragment the Annotation
     *            belongs to.
     * @param photo
     *            The image or illustration component of the Annotation.
     * @param text
     *            The textual component of the Annotation.
     */
    public Annotation(int annotationID, int storyFragmentID, String photo,
	    String text) {
    	super();
		this.annotationID = annotationID;
		this.storyFragmentID = storyFragmentID;
		this.photo = photo;
		this.text = text;
    }

    /**
     * @return The text of an Annotation
     */
    public String getText() {
    	return text;
    }

    /**
     * @param text
     *            the text to set
     */
    public void setText(String text) {
    	this.text = text;
    }

    /**
     * @return The storyFragmentID of the Story Fragment an the Annotation
     *         belongs to.
     */
    public int getStoryFragmentID() {
    	return storyFragmentID;
    }

    /**
     * @param storyFragmentID
     *            The storyFragmentID to set for the Annotation.
     */
    public void setStoryFragmentID(int storyFragmentID) {
    	this.storyFragmentID = storyFragmentID;
    }

    /**
     * @return The photo set in the Annotation but represented in as an encoded
     *         string.
     */
    public String getPhoto() {
    	return photo;
    }

    /**
     * @param photo
     *            The photo to set for the Annotation.
     */
    public void setPhoto(String photo) {
    	this.photo = photo;
    }

    /**
     * Retrieving the encoded Annotation is used for storing the Annotation
     * either on the webservice or local file system as photos need to be stored
     * as encoded byte arrays.
     * 
     * @return The encoded Annotation.
     */
    public String getEncodedAnnotation() {
    	return encodedAnnotation;
    }

    /**
     * The Annotation will be encoded from without the class and then set in the
     * Annotation object to for future use for the Annotation to be stored
     * outside the application.
     * 
     * @param encodedAnnotation
     *            The encodedAnnotation to set representing the Annotation.
     */
    public void setEncodedAnnotation(String encodedAnnotation) {
    	this.encodedAnnotation = encodedAnnotation;
    }

    /**
     * @return The annotationID used to uniquely identify the Annotation.
     */
    public int getAnnotationID() {
    	return annotationID;
    }

    /**
     * @param annotationID
     *            The annotationID to set for the Annotation.
     */
    public void setAnnotationID(int annotationID) {
    	this.annotationID = annotationID;
    }
}
