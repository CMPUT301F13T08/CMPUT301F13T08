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

/**
 * Photo is a model class representing an image or illustration that can be used
 * in an Annotation on a Story Fragment in a Story or as an illustration on a
 * Story Fragment in a Story. An Photo has the following properties:
 * <ul>
 * <li>Photo Id to uniquely identify the Photo.
 * <li>Picture Name is the name the user gave to the Photo.
 * <li>Photo the photo the reader set in the Annotation.
 * <li>Encoded Picture is the Photo encoded for storing it in a file or on a
 * webservice.
 * </ul>
 * These properties are able to be accessed through the constructor or through
 * public getters/setters.
 * 
 * @see Story
 * @see StoryFragment
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
public class Photo implements Serializable {

    private static final long serialVersionUID = 1L;
    private int photoID;
    private String pictureName;
    private String encodedPicture;

    /**
     * The default constructor for creating an Photo object. It will have none
     * of it's properties set, requiring the class instantiating the object to
     * set it's properties.
     */
    public Photo() {
    }

    /**
     * @return The photoID used to uniquely identify the Photo.
     */
    public int getPhotoID() {
	return photoID;
    }

    /**
     * @param photoID
     *            the photoID to set to the Photo for identification.
     */
    public void setPhotoID(int photoID) {
	this.photoID = photoID;
    }

    /**
     * @return The pictureName that the user used to describe the Photo.
     */
    public String getPictureName() {
	return pictureName;
    }

    /**
     * @param pictureName
     *            The pictureName to set the Photo to.
     */
    public void setPictureName(String pictureName) {
	this.pictureName = pictureName;
    }

    /**
     * Retrieving the encoded Photo is used for storing the Photo either on the
     * webservice or local file system as photos need to be stored as encoded
     * byte arrays.
     * 
     * @return The encodedPicture Photo
     */
    public String getEncodedPicture() {
	return encodedPicture;
    }

    /**
     * The Photo will be encoded from without the class and then set in the
     * Photo object to for future use for the Photo to be stored outside the
     * application.
     * 
     * @param encodedPicture
     *            The encodedPicture to set representing the Photo.
     */
    public void setEncodedPicture(String encodedPicture) {
	this.encodedPicture = encodedPicture;
    }

}
