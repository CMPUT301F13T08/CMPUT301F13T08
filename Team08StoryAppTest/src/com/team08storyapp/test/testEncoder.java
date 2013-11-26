package com.team08storyapp.test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Base64;

import com.team08storyapp.Encoder;
import com.team08storyapp.FileHelper;
import com.team08storyapp.MainActivity;
import com.team08storyapp.Photo;
import com.team08storyapp.R;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;

public class testEncoder extends ActivityInstrumentationTestCase2<MainActivity> {
    private FileHelper fHelper;

    private Story s1;
    private ArrayList<StoryFragment> sfList;
    private Context context;
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

    public testEncoder() {
	super(MainActivity.class);
    }

    /*
     * Test case for Use Case #3, #12, #10, #14
     * 
     * Since encodeStory() function is used before uploading/updating an online
     * story, this test is a part for UC#3, 12, 10, 14. testEncodeStory() tests
     * the encoding functionality of encodeStory, especially its ability and
     * correctness to encode an image.
     */
    public void testEncodeStory() throws FileNotFoundException, IOException {

	/* encode a story */
	Encoder encoder = new Encoder(context);
	Story encodedStory = encoder.encodeStory(s1);

	/* create the byte[] of lauch icon image */
	InputStream is = context.openFileInput(s1.getStoryFragments().get(0)
		.getPhotos().get(0).getPictureName());
	ByteArrayOutputStream bos = new ByteArrayOutputStream();
	byte[] b = new byte[1024];
	int bytesRead = 0;
	while ((bytesRead = is.read(b)) != -1) {
	    bos.write(b, 0, bytesRead);
	}
	byte[] bytes = bos.toByteArray();

	/* test the encodeStory() function does some "encoding" */
	assertNotNull(encodedStory.getStoryFragments().get(0).getPhotos()
		.get(0).getEncodedPicture());

	/* test the correctness of encoding */
	assertEquals(Base64.encodeToString(bytes, Base64.DEFAULT), encodedStory
		.getStoryFragments().get(0).getPhotos().get(0)
		.getEncodedPicture());

	/* test the file is not changed during the process */
	assertEquals(encodedStory.getStoryFragments().get(0).getPhotos().get(0)
		.getPictureName(), "Image12Fragment1Photo1.png");
    }

    /* Delete data */
    protected void tearDown() {
	context.deleteFile("Download12");
    }
}
