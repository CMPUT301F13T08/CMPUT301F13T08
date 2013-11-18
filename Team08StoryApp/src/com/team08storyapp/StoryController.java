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

package com.team08storyapp;

import java.util.ArrayList;
import java.util.Random;

/**
 * StoryController is a controller class that manipulates data of story objects.
 * This class allows for us to read story, read the story fragments related to
 * that story and allows us to add story fragments on to a story.
 * <p>
 * For the purpose of this application, this class does the following:
 * <ul>
 * <li>Reading the story.
 * <li>Getting a specific Story Fragment by using Story Fragment Id.
 * <li>Adding a Story Fragment to a story.
 * <li>Adding a Choice to a Story Fragment.
 * <li>Connecting two Story Fragments together.
 * </ul>
 * 
 * @see Story
 * @see StoryFragment
 * @see Choice
 * 
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @Version 1.0 November 8, 2013
 * @since 1.0
 * 
 */

public class StoryController {

    /**
     * ReadStoryFragment method passing in an Arraylist of Story Fragment object
     * and a Story Fragment Id. It goes through the ArrayList finding the next
     * story fragment comparing the Story Fragment Id. The method then returns
     * the next story fragment in line.
     * 
     * @param storyFragments
     *            Array List of Story Fragment objects that relates to the
     *            current story.
     * @param storyFragmentId
     *            An integer which uniquely identifies a Story Fragment.
     * @return The Story Fragment that is supposed to be next in the story.
     */
    public static StoryFragment readStoryFragment(
	    ArrayList<StoryFragment> storyFragments, int storyFragmentId) {
	StoryFragment nextFragment = null;
	for (StoryFragment storyFragment : storyFragments) {
	    System.out.println("Looking for: " + storyFragmentId + "  Now is: "
		    + storyFragment.getStoryFragmentId());
	    if (storyFragment.getStoryFragmentId() == storyFragmentId) {
		nextFragment = storyFragment;
		break;
	    }
	}
	return nextFragment;
    }

    /**
     * Allows for an Authors creation of a StoryFragment to be added to the
     * Story they are working on.
     * 
     * @param addStoryFragment
     *            The Story Fragment that was created to add to the Story.
     * @param story
     *            The Story the Author is creating by adding Story Fragments to.
     * @return The Story now containing the new Story Fragment.
     */
    public static Story addStoryFragment(StoryFragment addStoryFragment,
	    Story story) {
	story.getStoryFragments().add(addStoryFragment);
	return story;
    }

    /**
     * Allows for an Author's creation of a Choice to be added to the Story
     * Fragment they are working on. By adding a Choice the Author is connecting
     * two Story Fragments together.
     * 
     * @param choice
     *            The Choice the Author has created for the Story Fragment.
     * @param storyFragment
     *            The Story Fragment that the Choice is being added to.
     * @param storyFragment2
     *            The Story Fragment that the Choice leads to.
     * @return The Story Fragment with the newly added Choice.
     */
    public static StoryFragment addChoice(String choiceText,
	    StoryFragment storyFragment1, int storyFragment2Id) {
	int choiceId = storyFragment1.getChoices().size() + 1;
	Choice choice = new Choice(storyFragment2Id, choiceId, choiceText);
	storyFragment1.getChoices().add(choice);
	return storyFragment1;
    }

    /**
     * This method takes the size of a list of Stories and returns a random
     * number that symbolizes the Story index of one of the Stories in the list.
     * This Story index is used to find and display a random Story for the user.
     * 
     * This method would be used with a "I'm Feeling Lucky!" button in a list of
     * Stories to read. The user presses the button which calls this method and
     * takes the size of the list of Stories displayed to the user. Then the
     * method returns to the calling code the Story Id of the Story to retrieve
     * and display to the user.
     * 
     * @param storyListSize
     *            The size of the List of Stories that the user is getting a
     *            random Story from.
     * @return The Story index for the random Story to display to the user from
     *         the list of Stories.
     */
    public static int feelingLucky(int storyListSize) {
	Random rand = new Random();
	return rand.nextInt(storyListSize);
    }

    /**
     * This method takes in an Array list of choices and returns a random story
     * fragment that the current story fragment might go to.
     * 
     * Example: If there is a fragment that has choices a,b,c, each of them
     * leading to different fragments through toGoToStoryFragmentID. This method
     * will then choose one of these possible choices at random and return that
     * fragment.
     * 
     */
    public static StoryFragment randomChoice(ArrayList<Choice> Choices) {
	StoryFragment randomFragment = null;

	return randomFragment;

    }
}
