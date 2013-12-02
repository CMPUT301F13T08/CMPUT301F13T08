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
 * Video is a model class representing an soundtrack or recording that can be
 * used in an Annotation on a StoryFragment in a Story or as an illustration on
 * a StoryFragment in a Story. An photo has the following properties:
 * <ul>
 * <li>Video Id is to uniquely identify the Video.
 * <li>Video Name is the name the user gave to the Video track.
 * <li>Video the Video the reader set in Annotation.
 * <li>Encoded Video is the video encoded for storing it in a file or on a
 * webservice.
 * <li>Video Permissions is the track's permission for read only
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

public class Video implements Serializable{

    private static final long serialVersionUID = 1L;
    private int videoID;
    private String videoName;
    private String encodedVideo;
    private int videoPermission;
    
    /**
     * ++++++ Used for future implementation ++++++
     * 
     * Fake constructor for creating a Video object.
     * Will be implemented in the future.
     */
    public Video(){
    }
    
    /**
     * ++++++ Used for future implementation ++++++
     * 
     * This getter method gets the unique identifier used for to identify the video.
     * 
     * @return The videoID used to uniquely identify the Video
     */
    public int getVideoID() {
	return videoID;
    }
    
    /**
     * ++++++ Used for future implementation ++++++
     * 
     * This setter method sets the unique identifier used to identify the Video.
     * 
     * @param videoID
     * 		the videoID to set the video for identification.
     */
    
    public void setVideoID(int videoID) {
	this.videoID = videoID;
    }
    
    /**
     * ++++++ Used for future implementation ++++++
     * 
     * This getter method gets the file Name for the Video
     * 
     * @return The videoName the file Name for the Video
     */
    
    public String getVideoName(){
	return videoName;
    }
    
    /**
     * ++++++ Used for future implementation ++++++
     * 
     * This setter method sets the file Name for the Video
     * 
     * @param videoName
     * 		The VideoName to set the Video to.
     */
    
    public void setVideoName(String videoName){
	this.videoName = videoName;
    }
    
    
    /**
     * ++++++ Used for future implementation ++++++
     * 
     * Retrieving the encoded Video is used for storing the Video either on the
     * webservice or local file system as Videos need to be stored as encoded
     * byte arrays.
     * 
     * @return The encodedvideo video
     */
    public String getEncodedVideo(){
	return encodedVideo;
    }
    
    /**
     * ++++++ Used for future implementation ++++++
     * 
     * The Video will be encoded from without the class and then set in the
     * Video object to for future use for the Video to be stored outside the
     * application.
     * 
     * @param encodedVideo
     *            The encodedVideo to set representing the Video.
     */
    
    public void setEncodedVideo(String encodedVideo){
	this.encodedVideo = encodedVideo;
    }
    
    /**
     * ++++++ Used for future implementation ++++++
     * 
     * This getter method gets the video file's permission.
     * 
     * @return The videoPermission of the video track.
     */
    public int getVideoPermission(){
	return videoPermission;
    }
    
    /**
     * ++++++ Used for future implementation ++++++
     * 
     * This setter method sets the Video's permission to be read only or not.
     * 
     * @param VideoPermission
     *            The VideoPermission to set the Video permission to.
     */
    public void setVideoPermission(int videoPermission){
	this.videoPermission = videoPermission;
    }
}
