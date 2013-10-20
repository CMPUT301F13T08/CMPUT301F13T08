import java.util.ArrayList;

import com.team08storyapp.Choice;
import com.team08storyapp.Photo;
import com.team08storyapp.SQLiteHelper;
import com.team08storyapp.Story;
import com.team08storyapp.StoryFragment;

import junit.framework.TestCase;

public class testStory extends TestCase {

private SQLiteHelper SHelper;
	
	private int storyId;
	private int downloadStoryId;
	private Story addStory;
	private Story updateStory;
	private int storyListSize;
	private Story downloadStory;
	
	private int storyFragmentId;
	private StoryFragment addStoryFragment;
	private StoryFragment updateStoryFragment;
	private StoryFragment firstFragment;
	private ArrayList<StoryFragment> storyFragments;
	private int storyFragmentListSize;
	
	private Choice addChoice;
	private Choice updateChoice;
	private int choiceListSize;
	
	private Photo addStoryPhoto;
	private Photo addAnnotationPhoto;
	private int photoListSize;

	private String searchText;
	private int searchListSize;
	
	
	
	// The setUp method is used to setup variables for use in the test cases.
		protected void setUp(){
			SHelper = new SQLiteHelper();
			
			storyId = 1;
			addStory = new Story("title", "author");
			updateStory = new Story(1, "updatedTitle", "updatedAuthor");
			storyListSize = 1;
			downloadStory = new Story("title2", "author");
			downloadStory.setStoryId(2);
			
			storyFragmentId = 1;
			updateStoryFragment = new StoryFragment(1);
			updateStoryFragment.setStoryText("story");	
			firstFragment = new StoryFragment("first fragment");
			storyFragments = new ArrayList<StoryFragment>();
			storyFragments.add(firstFragment);
			storyFragmentListSize = 1;
			addStoryFragment = new StoryFragment(1, "text");
			
			addChoice = new Choice();
			updateChoice = new Choice();
			choiceListSize = 1;
			
			addStoryPhoto = new Photo();
			addAnnotationPhoto = new Photo();
			photoListSize = 1;
			
			searchText = "title or author";
			searchListSize = 1;
		}
	
	/* The testDownloadStory method tests downloaded a story from the server. If 
	 * the story is successfully downloaded, story should not be null, downloadStoryId and the fragment size should match.
	 */
	public void testDownloadStory(){
		
		
		Story story = Story.downloadStory(downloadStoryId);
		
		assertNotNull(story);
		assertEquals(story.getStoryId(), downloadStoryId);
		assertEquals(story.getStoryFragments().size(), storyFragmentListSize);
		
		
	}
	/*The testReadStory method tests reading a story. If the story can be successfully read the
	 * method call to story.readStory should return true.
	 */
	public void testReadStory(){
		downloadStory.setFirstStoryFragment(firstFragment);
		downloadStory.setStoryFragments(storyFragments);
		assertTrue(Story.readStory(downloadStory));
	}
	
	public void testBrowseStories(){
		
	}
	
	
	public void testPublish(){
		
	}
	public void testSeach(){
		
	}
	
	
	/* The testAddStoryFragment method tests adding a story fragment to the server. If 
	 * the story fragment is added successfully the method call to esHelper.addStoryFragment 
	 * should return true.
	 **/
	public void testAddStoryFragment(){
		assertTrue(Story.addStoryFragment(addStoryFragment));
	}
	
	
	
}