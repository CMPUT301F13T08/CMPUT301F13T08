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

public class Annotation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int annotationID;
	private String text;
	private int storyFragmentID;
	private String photo;
	private String encodedAnnotation;

	public String getEncodedAnnotation() {
	    return encodedAnnotation;
	}

	public void setEncodedAnnotation(String encodedAnnotation) {
	    this.encodedAnnotation = encodedAnnotation;
	}

	public Annotation() {

	}

	public Annotation(int storyFragmentID, String photo, String text) {
		this.storyFragmentID = storyFragmentID;
		this.photo = photo;
		this.text = text;
	}

	public Annotation(int annotationID, int storyFragmentID, String photo,
			String text) {
		super();
		this.annotationID = annotationID;
		this.storyFragmentID = storyFragmentID;
		this.photo = photo;
		this.text = text;
	}

	public void setAnnotationID(int annotationID) {
		this.annotationID = annotationID;
	}

	public int getAnnotationID() {
		return annotationID;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setStoryFragmentID(int storyFragmentID) {
		this.storyFragmentID = storyFragmentID;
	}

	public int getStoryFragmentID() {
		return storyFragmentID;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getPhoto() {
		return photo;
	}

	@Override
	public String toString() {
		return "Annotation [storyFragmentID=" + storyFragmentID
				+ ", annotationID=" + annotationID + ", text=" + text
				+ ", photo=" + photo + "]";
	}

}
