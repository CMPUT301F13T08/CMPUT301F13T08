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
package com.team08storyapp.test;

import static org.junit.Assert.*;

import org.junit.Test;
import com.team08storyapp.MainActivity;
import com.team08storyapp.Audio;

import android.test.ActivityInstrumentationTestCase2;


public class testAudio extends ActivityInstrumentationTestCase2<MainActivity>{

    public testAudio(){
        super(MainActivity.class);
    }
    
    public void setup(){
        
    }
    
    /*
     * Constructor Test for Audio object. Set parameters audioId, audioName,
     * encodedAudio, and permissions.
     */
    public void testConstructorAudio(){
        
        Audio audio = new Audio();
        
        audio.setAudioID(1);
        audio.setAudioName("audio");
        audio.setEncodedAudio("encoded");
        audio.setAudioPermission(1);
        
        //Fails
        asserEquals(1, audio.getAudioID());
        assertEquals("audio", audio.getAudioName());
        assertEquals("encoded", audio.getEncodedAudio());
        assertEquals(1, audio.getAudioPermission());
        audio.setAudioPermission(0);
        assertEquals(0, audio.getAudioPermission());
   }



}
