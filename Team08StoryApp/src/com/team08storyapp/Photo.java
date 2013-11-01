package com.team08storyapp;

import java.io.Serializable;

import android.graphics.Bitmap;

public class Photo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int photoID;
	private byte[] picture;
	private String encodedPicture;

	public String getEncodedPicture() {
	    return encodedPicture;
	}

	public void setEncodedPicture(String encodedPicture) {
	    this.encodedPicture = encodedPicture;
	}

	public Photo() {

	}

	public Photo(byte[] picture) {
		this.picture = picture;
	}

	public Photo(int photoID, byte[] picture) {
		super();
		this.photoID = photoID;
		this.picture = picture;
	}

	public void setPhotoID(int photoID) {
		this.photoID = photoID;
	}

	public int getPhotoID() {
		return photoID;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public byte[] getPicture() {
		return picture;
	}

	@Override
	public String toString() {
		return "Photo [photoID=" + photoID + ", picture=" + picture + "]";
	}
}
