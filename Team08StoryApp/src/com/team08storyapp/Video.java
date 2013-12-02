/*
AUTHORS
========
Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen.

LICENSE
=======
Copyright  ���  2013 Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen,  
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
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * ++++++ This class is used for future implementations (Fake Class)++++++
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * 
 * Video is a model class that would be used for StoryFragments video
 * illustrations, or video annotations.
 * <ul>
 * <li>Video Id uniquely identifies the vidoe object.
 * <li>Video Name is the name the user gives the video.
 * <li>Encoded Video is the video encoded for storing it in a file or on a
 * webservice.
 * <li>Vudio Permission indicates whether the video illustration is read only.
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

public class Video implements Serializable {

    private static final long serialVersionUID = 1L;
    private int videoID;
    private String videoName;
    private String encodedVideo;
    private int videoPermission;

    /**
     * Fake constructor for creating a Video object. Will be implemented in the
     * future.
     */
    public Video() {
    }

    /**
     * This getter method gets the unique identifier of the Video.
     * 
     * @return The videoID used to uniquely identify the Video.
     */
    public int getVideoID() {
	return videoID;
    }

    /**
     * This setter method sets the unique identifier id of the Video.
     * 
     * @param vudiID
     *            the vudioID uniquely identifies the Video.
     */
    public void setVideoID(int videoID) {
	this.videoID = videoID;
    }

    /**
     * This getter method gets the name of the Video.
     * 
     * @return The videoName that is the file name of the Video.
     */
    public String getVideoName() {
	return videoName;
    }

    /**
     * This setter method sets the file name for the Video.
     * 
     * @param videoName
     *            The vudioName as the file of the Video.
     */
    public void setVideoName(String videoName) {
	this.videoName = videoName;
    }

    /**
     * Retrieving the encoded Video. It is used to store the Video either on the
     * webservice or local file system, as encoded byte arrays.
     * 
     * @return The encodedVideo
     */
    public String getEncodedVideo() {
	return encodedVideo;
    }

    /**
     * The Video will be encoded and stored on the local file system or on a
     * webservice.
     * 
     * @param encodedVideo
     *            The encodedVideo representing the Video
     */
    public void setEncodedVideo(String encodedVideo) {
	this.encodedVideo = encodedVideo;
    }

    /**
     * This getter method gets the video file's permission.
     * 
     * @return The vudioPermission of the Video.
     */
    public int getVideoPermission() {
	return videoPermission;
    }

    /**
     * This setter method sets the Video's permission to be read only or not.
     * 
     * @param videoPermission
     *            The videoPermission, 0 - read only, 1 - otherwise
     */
    public void setVideoPermission(int videoPermission) {
	this.videoPermission = videoPermission;
    }
}
