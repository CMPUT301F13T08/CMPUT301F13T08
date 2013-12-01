package com.team08storyapp.test;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import com.team08storyapp.Audio;
import com.team08storyapp.MainActivity;

import android.test.ActivityInstrumentationTestCase2;

@Ignore("Future Implementation")
public class testAudio extends ActivityInstrumentationTestCase2<MainActivity>{

    public testAudio(){
        super(MainActivity.class);
    }
    
    @Test
    public void setup(){
        
    }
    
    /*
     * Constructor Test for Audio object. Set parameters audioId, audioName,
     * encodedAudio, and permissions.
     */
    @Test
    public void testConstructorAudio(){
        
        Audio audio = new Audio();
        
        audio.setAudioID(1);
        audio.setAudioName("audio");
        audio.setEncodedAudio("encoded");
        audio.setAudioPermission(1);
        
        //Fails
        assertEquals(1, audio.getAudioID());
        assertEquals("audio", audio.getAudioName());
        assertEquals("encoded", audio.getEncodedAudio());
        assertEquals(1, audio.getAudioPermission());
        audio.setAudioPermission(0);
        assertEquals(0, audio.getAudioPermission());
   }



}
