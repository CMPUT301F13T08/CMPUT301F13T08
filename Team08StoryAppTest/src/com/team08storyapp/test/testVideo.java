package com.team08storyapp.test;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import android.test.ActivityInstrumentationTestCase2;
import com.team08storyapp.MainActivity;
import com.team08storyapp.Video;

@Ignore
public class testVideo extends ActivityInstrumentationTestCase2<MainActivity>{
    
    public testVideo()
    {
        super(MainActivity.class);
    }
    @Test
    public void setUp(){
        
    }
    
    /*
     * Constructor Test for Video object. Set parameters videoId, videoName,
     * encodedVideo, and permissions.
     */
    @Test
    public void testConstructorVideo(){
        
        Video video = new Video();
        video.setVideoID(1);
        video.setVideoName("video");
        video.setEncodedVideo("encoded");
        video.setVideoPermission(1);
        
        //Fails
        assertEquals(1, video.getVideoID());
        assertEquals("audio", video.getVideoName());
        assertEquals("encoded", video.getEncodedVideo());
        assertEquals(1, video.getVideoPermission());
        video.setVideoPermission(0);
        assertEquals(0, video.getVideoPermission());
        
    }

}
