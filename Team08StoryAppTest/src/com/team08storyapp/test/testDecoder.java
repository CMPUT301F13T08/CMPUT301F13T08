package com.team08storyapp.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.test.ActivityInstrumentationTestCase2;

import com.team08storyapp.Decoder;
import com.team08storyapp.Encoder;
import com.team08storyapp.FileHelper;
import com.team08storyapp.MainActivity;
import com.team08storyapp.Photo;
import com.team08storyapp.R;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;

public class testDecoder extends ActivityInstrumentationTestCase2<MainActivity> {
    private FileHelper fHelper;
    private Context context;
    private Story s1;
    private ArrayList<StoryFragment> sfList;
    private String content;

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
	context = this.getInstrumentation().getTargetContext();
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

    }
    
    public testDecoder(){
	super(MainActivity.class);
    }

    /*
     * Test case for Use Case #9, #12, #16
     * 
     * Since decodeStory() function is used before saving the downloaded story
     * into local file system, this test is a part for UC#3, 12, 10, 14.
     * testDecodeStory() tests the decoding functionality of decodeStory().
     */
    public void testDecodeStory() throws Exception {
	Encoder encoder = new Encoder(context);
	Story encodedStory = encoder.encodeStory(s1);

	// assert the correctness of fileName
	Decoder decoder = new Decoder(context);
	assertEquals(decoder.decodeStory(encodedStory, 1).getStoryFragments()
		.get(0).getPhotos().get(0).getPictureName(),
		"Image12Fragment1Photo1.png");
	FileInputStream fos = context
		.openFileInput("Image12Fragment1Photo1.png");
	Bitmap photo = BitmapFactory.decodeStream(fos);
	assertNotNull(photo);

    }
    
    /* Delete data */
    protected void tearDown() {
	context.deleteFile("Download12");
    }
}
