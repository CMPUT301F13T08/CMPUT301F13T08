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

import android.net.Uri;

/**
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * ++++++ This class is used for future implementations (Fake Class)++++++
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * 
 * MultimediaController is a controller class that is responsible for resizing
 * (if needed), saving multimedia and update the current story and current story
 * fragment.
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei
 * @version 1.0 November 8, 2013
 * @since 1.0
 * 
 */

public class MultimediaController {

    /**
     * ++++++ Used for future implementation ++++++
     * 
     * Constructor of the MultimediaController is a stub constructor. Will be
     * implemented in the future
     */
    public MultimediaController() {
    }
    
    /**
     * ++++++ Used for future implementation ++++++
     */
    public void setCurrentStoryFragment() {
    }

    /**
     * ++++++ Used for future implementation ++++++
     * 
     * @return 0
     */
    public int currentPosition() {
	return 0;
    }

    /**
     * ++++++ Used for future implementation ++++++
     * 
     * saveVideo is the function where saving the video and updating
     * the current fragment are performed in order. Will be used for future
     * implementation.
     * 
     * @param pickedUri
     *            Uri reference to the image
     * @return Null
     */
    public Object saveVideo(Uri pickedUri) {
	return null;
    }

    /**
     * ++++++ Used for future implementation ++++++
     * 
     * saveAudio is the function where saving the audio and updating
     * the current fragment are performed in order. Will be used for future
     * implementation.
     * 
     * @param pickedUri
     *            Uri reference to the image
     * @return Null
     */
    public Object saveAudio(Uri pickedUri) {
	return null;
    }
}
