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
	private Story addStory;
	private Story updateStory;
	private int storyListSize;
	private Story downloadStory;
	
	private int storyFragmentId;
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
	
	private int storyId;
	
	// The setUp method is used to setup variables for use in the test cases.
		protected void setUp(){
			SHelper = new SQLiteHelper();
			
			storyId = 1;
			addStory = new Story("title", "author");
			updateStory = new Story(1, "updatedTitle", "updatedAuthor");
			storyListSize = 1;
			downloadStory = new Story(1);
			
			storyFragmentId = 1;
			updateStoryFragment = new StoryFragment(1);
			updateStoryFragment.setStoryText("story");	
			firstFragment = new StoryFragment("first fragment");
			storyFragments = new ArrayList<StoryFragment>();
			storyFragments.add(firstFragment);
			storyFragmentListSize = 1;
			
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
	 * the story is successfully downloaded the method call to story.downloadStory should
	 * return true. 
	 */
	public void testDownloadStory(){
		assertTrue(Story.downloadStory(1));
		
	}
	/*The testReadStory method tests reading a story. If the story can be successfully read the
	 * method call to story.readStory should return true.
	 */
	public void testReadStory(){
		addStory.setFirstStoryFragment(firstFragment);
		addStory.setStoryFragments(storyFragments);
		assertTrue(Story.readStory(addStory));
		
		
		
		
	}
	
	
	
}