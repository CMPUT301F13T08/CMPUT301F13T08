package com.team08storyapp.test;

import junit.framework.TestCase;

import com.team08storyapp.SyncManager;

public class testSyncManager extends TestCase{

    public testSyncManager() {
    }
    
    public void testGetInstance(){
        SyncManager sm1 = SyncManager.getInstance();
        SyncManager sm2 = SyncManager.getInstance();
        assertEquals(sm1, sm2);
    }

}