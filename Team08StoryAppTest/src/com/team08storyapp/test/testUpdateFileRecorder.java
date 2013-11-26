package com.team08storyapp.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.test.ActivityInstrumentationTestCase2;

import com.team08storyapp.FileHelper;
import com.team08storyapp.MainActivity;
import com.team08storyapp.Photo;
import com.team08storyapp.R;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;
import com.team08storyapp.UpdateFileRecorder;

public class testUpdateFileRecorder extends
	ActivityInstrumentationTestCase2<MainActivity> {

    private Story s1;
    private Story s2;
    private ArrayList<StoryFragment> sfList;
    private ArrayList<StoryFragment> sfList1;
    private String content;
    private Context context;
    private FileHelper fHelper;

    public testUpdateFileRecorder() {
	super(MainActivity.class);
	/*context = super.getActivity().getApplicationContext();
	fHelper = new FileHelper(context, 0);*/
    }

    /*
     * set up testing data for testing methods.
     * 
     * ADDRESSING FEEDBACK: I was trying to make adding stories into a function
     * annotated as a @BeforeClass as you suggested. However there is an issue
     * with context, which is "Cannot make a static reference to the non-static
     * method getContext() from the type AndroidTestCase". But I really need the
     * context to make the FileHelper. So that's why I still keep the setUp
     * function with these code.
     */
    public void setUp() throws FileNotFoundException, IOException {

	context = getActivity().getApplicationContext();
	fHelper = new FileHelper(context, 0);
	
	s1 = new Story(14, "Morroco likoko", "Alice Wu");
	s1.setOfflineStoryId(12);
	sfList = new ArrayList<StoryFragment>();
	content = "	This is the city, where sins grow, profits expand, people gets colder,"
		+ " and Michael"
		+ "De Santa, the retired criminal wanted to start his new life. "
		+ "\n    But this comes to an end, when he finds"
		+ "out his son, Jimmy, is set up in a credit card "
		+ "fraud by a local dealership. \n    The rage occupies "
		+ "him, leading him" + "to teach that manager a 'lesson'";
	StoryFragment sf1 = new StoryFragment(1, content);

	/* make a photo with launch icon, assign it to story fragment 1 */
	Photo p1 = new Photo();
	p1.setPhotoID(1);
	String fileName = "Image12Fragment1Photo1.png";
	p1.setPictureName(fileName);
	Bitmap photo = BitmapFactory.decodeResource(context.getResources(),
		R.drawable.ic_launcher);
	FileOutputStream fos = context.openFileOutput(fileName,
		Context.MODE_PRIVATE);
	photo.compress(CompressFormat.PNG, 90, fos);
	ArrayList<Photo> pList = new ArrayList<Photo>();
	pList.add(p1);
	sf1.setPhotos(pList);

	sfList.add(sf1);
	s1.setFirstStoryFragmentId(1);
	s1.setStoryFragments(sfList);
	fHelper.addOfflineStory(s1);

	s2 = new Story(13, "FA LA LA LA ", "FA FA LA LA");
	sfList1 = new ArrayList<StoryFragment>();
	s2.setStoryFragments(sfList1);
	fHelper.addOfflineStory(s2);
    }

    public void testAppendUpdateQueue() throws Exception {
	File dir = context.getFilesDir();
	File updateQueue = new File(dir, "updateQueue");
	updateQueue.createNewFile();
	UpdateFileRecorder.appendUpdateQueue(s1.getOfflineStoryId(), context);
	UpdateFileRecorder.appendUpdateQueue(s2.getOfflineStoryId(), context);
	InputStream in = context.openFileInput("updateQueue");
	InputStreamReader inputStreamReader = new InputStreamReader(in);
	BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	String line;
	line = bufferedReader.readLine();
	assertEquals(line, "12");
	line = bufferedReader.readLine();
	assertEquals(line, "13");
    }

    public void testClearUpdateQueue() throws Exception {
	File dir = context.getFilesDir();
	File updateQueue = new File(dir, "updateQueue");
	updateQueue.createNewFile();
	assertTrue(updateQueue.exists());
	UpdateFileRecorder.clearUpdateQueue(context);
	assertFalse(updateQueue.exists());
    }

    public void testGetUpdateFilesIds() throws Exception {
	File dir = context.getFilesDir();
	File updateQueue = new File(dir, "updateQueue");
	updateQueue.createNewFile();
	UpdateFileRecorder.appendUpdateQueue(s1.getOfflineStoryId(), context);
	UpdateFileRecorder.appendUpdateQueue(s2.getOfflineStoryId(), context);
	ArrayList<String> ids = UpdateFileRecorder.getUpdateFilesIds(context);
	assertEquals(ids.size(), 2);
	assertEquals(ids.get(0), "12");
	assertEquals(ids.get(1), "13");

    }

    /* Delete data */
    protected void tearDown() {
	context.deleteFile("Download12");
	context.deleteFile("Download13");
    }

}
