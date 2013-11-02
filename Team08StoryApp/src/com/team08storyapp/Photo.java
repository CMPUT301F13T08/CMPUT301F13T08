package com.team08storyapp;

import java.io.Serializable;

import android.graphics.Bitmap;

public class Photo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int photoID;
	private String pictureName;
	private String encodedPicture;

	public String getEncodedPicture() {
	    return encodedPicture;
	}

	public void setEncodedPicture(String encodedPicture) {
	    this.encodedPicture = encodedPicture;
	}

	public Photo() {

	}

	public String getPictureName() {
	    return pictureName;
	}

	public void setPictureName(String pictureName) {
	    this.pictureName = pictureName;
	}

	public void setPhotoID(int photoID) {
		this.photoID = photoID;
	}

	public int getPhotoID() {
		return photoID;
	}

	@Override
	public String toString() {
		return "Photo [photoID=" + photoID + ", picture=" + pictureName + "]";
	}
}
