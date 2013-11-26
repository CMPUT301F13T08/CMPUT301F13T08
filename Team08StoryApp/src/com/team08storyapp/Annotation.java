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
-This demo was used to help with JSON and ESHelper which is under the CC0 licenses

Retrieved Oct. 29, 2013  - http://hc.apache.org/downloads.cgi
-This is for the fluent library which is licensed under apache V2

Retrieved Oct. 29, 2013 
- https://code.google.com/p/google-gson/downloads/detail?name=google-gson-2.2.4-release.zip&can=2&q=
-This is for JSON which is licensed under apache V2
 */

package com.team08storyapp;

import java.io.Serializable;

/**
 * Annotation is a model class representing a reader's Annotations on a
 * Particular Story Fragment. An Annotation has the following properties:
 * <ul>
 * <li>Annotation Id to uniquely identify the Annotation.
 * <li>Story Fragment Id the Id identified with the Story Fragment the
 * Annotation belongs to.
 * <li>Photo the photo the reader set in the Annotation.
 * <li>Encoded Annotation is the Annotation encoded for storing it in a file or
 * on a webservice.
 * </ul>
 * These properties are able to be accessed through the constructor or through
 * public getters/setters.
 * 
 * A user while reading a Story online or offline has the option to view or add
 * Annotations to each Story Fragment they read. The Annotation is created in
 * the form of a illustration or photo. The user can either add this by taking a
 * picture or uploading it from their phones gallery.
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

    private static final long serialVersionUID = 1L;
    private int annotationID;
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
     * This getter method gets the unique identifier Story Fragment that the
     * Annotation belongs to.
     * 
     * @return The storyFragmentID of the Story Fragment the Annotation belongs
     *         to.
     */
    public int getStoryFragmentID() {
	return storyFragmentID;
    }

    /**
     * This setter method sets the unique identifier Story Fragment that the
     * Annotation belongs to.
     * 
     * @param storyFragmentID
     *            The storyFragmentID to set for the Annotation.
     */
    public void setStoryFragmentID(int storyFragmentID) {
	this.storyFragmentID = storyFragmentID;
    }

    /**
     * This getter method gets the photo that the user chose for the Annotation.
     * 
     * @return The photo set in the Annotation but represented in as an encoded
     *         string.
     */
    public String getPhoto() {
	return photo;
    }

    /**
     * This setter method sets the photo that the user chose for the Annotation.
     * 
     * @param photo
     *            The photo to set for the Annotation.
     */
    public void setPhoto(String photo) {
	this.photo = photo;
    }

    /**
     * This getter method retrieves the encoded Annotation used for storing the
     * Annotation either on the webservice or local file system as photos need
     * to be stored as encoded byte arrays.
     * 
     * @return The encoded Annotation.
     */
    public String getEncodedAnnotation() {
	return encodedAnnotation;
    }

    /**
     * Annotation will be encoded from without the class and then this setter
     * method sets it in the Annotation object to for future use for the
     * Annotation to be stored outside the application.
     * 
     * @param encodedAnnotation
     *            The encodedAnnotation to set representing the Annotation.
     */
    public void setEncodedAnnotation(String encodedAnnotation) {
	this.encodedAnnotation = encodedAnnotation;
    }

    /**
     * This getter method gets the unique identifier for the Annotation.
     * 
     * @return The annotationID used to uniquely identify the Annotation.
     */
    public int getAnnotationID() {
	return annotationID;
    }

    /**
     * This setter method sets the unique identifier for the Annotation.
     * 
     * @param annotationID
     *            The annotationID to set for the Annotation.
     */
    public void setAnnotationID(int annotationID) {
	this.annotationID = annotationID;
    }
}
