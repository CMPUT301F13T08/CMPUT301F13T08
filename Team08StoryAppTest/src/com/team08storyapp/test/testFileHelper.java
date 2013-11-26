package com.team08storyapp.test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
import android.util.Base64;

import com.team08storyapp.FileHelper;
import com.team08storyapp.MainActivity;
import com.team08storyapp.Photo;
import com.team08storyapp.R;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;

public class testFileHelper extends
	ActivityInstrumentationTestCase2<MainActivity> {

    private FileHelper fHelper;

    private Story s1;
    private int storyCount;

    private Story s2;

    private StoryFragment fragment1;
    private ArrayList<StoryFragment> sfList;
    private ArrayList<StoryFragment> sfList1;
    private Context context;

    private String content;

    public testFileHelper() {
	super(MainActivity.class);
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
	context = this.getInstrumentation().getTargetContext();
	fHelper = new FileHelper(context, 0);

	storyCount = fHelper.getOfflineStories().size() + 2;

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

    /*
     * Test case for Use Case 9
     * 
     * Taking the story that is cached by ESHelper.getOnlineStory(StoryId),
     * addOfflineStory(story) will store the story in local file. So if a story
     * is successfully added, the test function testAddOfflineStory() will
     * return true.
     */
    public void testAddOfflineStory() throws FileNotFoundException, IOException {
	assertTrue(fHelper.addOfflineStory(s1));
    }

    /*
     * Test case for Use Case 5, 6, 7, 8
     * 
     * UpdateOfflineStory() updates an offline story. If the change is
     * successfully saved and applied to the story, the test method
     * testUpdateOfflineStory() should return true.
     * 
     * ADDRESSING FEEDBACK: NOTE TO JOSH: Sorry we didn't see any difference
     * between use case 6 and use case 11 right now. Hope we can find out in
     * part 4 in order to improve our test cases.
     */
    public void testUpdateOfflineStory() throws FileNotFoundException,
	    IOException {
	sfList.add(fragment1);
	s1.setStoryFragments(sfList);
	assertTrue(fHelper.updateOfflineStory(s1));
    }

    /*
     * Test case for Use Case 1
     * 
     * testGetOfflineStory tests getting a story from local file with a given
     * id. A story should be returned with that given id. And the basic
     * information of that story(title, author, id) should not be null. And we
     * also check the length of fragment list to make sure no fragment is lost.
     */
    public void testGetOfflineStory() throws FileNotFoundException, IOException {
	Story story = fHelper.getOfflineStory(12);

	/* make sure we get the right story */
	assertNotNull(story);
	assertNotNull(story.getAuthor());
	assertNotNull(story.getTitle());
	assertEquals(story.getStoryFragments().size(), sfList.size());
	assertEquals(story.getOfflineStoryId(), 12);

	/* test we are reading the same text as we wrote into the content string */
	assertEquals(story.getStoryFragments().get(0).getStoryText(), content);

	/* test we are reading the same photo */
	assertEquals(story.getStoryFragments().get(0).getPhotos().get(0)
		.getPictureName(), "Image12Fragment1Photo1.png");

	/* make sure the story we read has no choices */
	assertTrue(story.getStoryFragments().get(0).getChoices().isEmpty());
    }

    /*
     * Test Case for Use Case 2
     * 
     * The testGetStories method tests retrieval of all the stories stored in
     * local file. The local file will contain at least one story for this test.
     * The list returned from the method call fHelper.getStories should be the
     * same size as the one stored locally.
     */
    public void testGetOfflineStories() throws FileNotFoundException,
	    IOException {
	assertEquals(fHelper.getOfflineStories().size(), storyCount);
    }

    /*
     * Test Case for Use Case 4
     * 
     * The testSearchForStory method tests the search functionality of the
     * stories on thelocal file. Given a string to search the method call
     * fHelper.searchForStory should return a list of the size we are expecting
     * for the given search text.
     * 
     * ADDRESSING FEEDBACK: Since we still feel like there's need for search
     * offline stories, we keep the function. And to be a more comprehensive
     * test, I did add another story, and make sure the returned story is what
     * we want by testing the author, title, as suggested in the feedback:
     * "create a relevant story and irrelevant story and check that the relevant
     * story is returned and the irrelevant one is not"
     */
    public void testSearchOfflineStories() {

	/* test the result of search should only have 1 item */
	assertEquals(fHelper.searchOfflineStories("Morroco likoko").size(), 1);
	/*
	 * and the only item should be exactly the story has "Morroco likoko" as
	 * title and Alice Wu as the author.
	 */
	assertEquals(fHelper.searchOfflineStories("co").get(0).getTitle(),
		"Morroco likoko");
	assertEquals(fHelper.searchOfflineStories("co").get(0).getAuthor(),
		"Alice Wu");

	/*
	 * test " we don't carry out search on whitespace or newline character"
	 * is true
	 */
	assertEquals(fHelper.searchOfflineStories("    ").size(), storyCount);
	assertEquals(fHelper.searchOfflineStories("\n").size(), storyCount);

	// No author or title has string "DAT". The result should be 0.
	assertEquals(fHelper
		.searchOfflineStories("WHO DAT WHO DAT WHO DAT DAT").size(), 0);
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
	Story encodedStory = fHelper.encodeStory(s1);

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

    /*
     * Test case for Use Case #9, #12, #16
     * 
     * Since decodeStory() function is used before saving the downloaded story
     * into local file system, this test is a part for UC#3, 12, 10, 14.
     * testDecodeStory() tests the decoding functionality of decodeStory().
     */
    public void testDecodeStory() throws Exception {
	Story encodedStory = fHelper.encodeStory(s1);

	// assert the correctness of fileName
	assertEquals(fHelper.decodeStory(encodedStory, 1).getStoryFragments()
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
	context.deleteFile("Download13");
    }

}
