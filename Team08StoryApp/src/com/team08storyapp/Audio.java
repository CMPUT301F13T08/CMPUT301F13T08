/*
AUTHORS
========
Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen.

LICENSE
=======
Copyright  ���������  2013 Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen,  
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

/**
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * ++++++ This class is used for future implementations (Fake Class)++++++
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * 
 * Audio is a model class representing an soundtrack or recording that can be
 * used in an Annotation on a StoryFragment in a Story or as an illustration on
 * a StoryFragment in a Story. An photo has the following properties:
 * <ul>
 * <li>Audio Id is to uniquely identify the Audio.
 * <li>Audio Name is the name the user gave to the audio track.
 * <li>Audio the audio the reader set in Annotation.
 * <li>Encoded audio is the audio encoded for storing it in a file or on a
 * webservice.
 * <li>Audio Permissions is the track's permission for read only
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

public class Audio {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;
    private int audioID;
    private String audioName;
    private String encodedAudio;
    private int audioPermission;
    
    /**
     * ++++++ Used for future implementation ++++++
     * 
     * Fake constructor for creating an Audio object. 
     * Will be implemented in the future.
     */
    public Audio() {
    }

    /**
     * ++++++ Used for future implementation ++++++
     * 
     * This getter method gets the unique identifier used for to identify the 
     * Audio.
     * 
     * @return The audioID used to uniquely identify the Audio.
     */
    public int getAudioID() {
	return audioID;
    }
    
    /**
     * ++++++ Used for future implementation ++++++
     * 
     * This setter method sets the unique identifier used to identify the Audio.
     * 
     * @param audioID
     *            the audioID to set to the audio for identification.
     */
    public void setAudioID(int audioID) {
	this.audioID = audioID;
    }

    /**
     * ++++++ Used for future implementation ++++++
     * 
     * This getter method gets the file Name for the Audio.
     * 
     * @return The audioName the file Name for the Audio.
     */
    public String getAudioName() {
	return audioName;
    }
    
    /**
     * ++++++ Used for future implementation ++++++
     * 
     * This setter method sets the file Name for the Audio.
     * 
     * @param AudioName
     *            The AudioName to set the Audio to.
     */
    public void setAudioName(String audioName) {
	this.audioName = audioName;
    }
    
    /**
     * ++++++ Used for future implementation ++++++
     * 
     * Retrieving the encoded Audio is used for storing the Audii either on the
     * webservice or local file system as Audios need to be stored as encoded
     * byte arrays.
     * 
     * @return The encodedAudio Audio
     */
    public String getEncodedAudio() {
	return encodedAudio;
    }
    
    /**
     * ++++++ Used for future implementation ++++++
     * 
     * The Audio will be encoded from without the class and then set in the
     * Audio object to for future use for the Audio to be stored outside the
     * application.
     * 
     * @param encodedAudio
     *            The encodedAudio to set representing the Audio.
     */
    public void setEncodedAudio(String encodedAudio) {
	this.encodedAudio = encodedAudio;
    }
    
    /**
     * ++++++ Used for future implementation ++++++
     * 
     * This setter method sets the Audio's permission to be read only or not.
     * 
     * @param AudioPermission
     *            The AudioPermission to set the Audio permission to.
     */
    public void setAudioPermission(int audioPermission) {
	this.audioPermission = audioPermission;
    }
    
    /**
     * ++++++ Used for future implementation ++++++
     * 
     * This getter method gets the audio file's permission.
     * 
     * @return The audioPermission of the audio track.
     */
    public int getAudioPermission() {
	return audioPermission;
    }

}
