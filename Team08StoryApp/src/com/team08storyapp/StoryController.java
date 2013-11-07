/*
AUTHORS
========
Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen.

LICENSE
=======
Copyright  �  2013 Alice Wu, Ana Marcu, Michele Paulichuk, Jarrett Toll, Jiawei Shen,  
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
     * @return Whether the Story Fragment was added successful
     */
    public static boolean addStoryFragment(StoryFragment addStoryFragment,
	    Story story) {
	// TODO This will be completed in Project Part 4
	return false;
    }

    /**
     * Allows for an Author's creation of a Choice to be added to the Story
     * Fragment they are working on.
     * 
     * @param choice
     *            The Choice the Author has created for the Story Fragment.
     * @param storyFragment
     *            The Story Fragment that the Choice is being added to.
     * @param story
     *            The Story the Author is creating by adding Story Fragments to.
     * @return
     */
    public static boolean addChoice(Choice choice, StoryFragment storyFragment,
	    Story story) {

	return false;
    }

    /**
     * Takes two Story Fragments for a Story and connects them via the Choice.
     * 
     * @param storyFragment1 The first Story Fragment to connect with the second Story Fragment.
     * @param storyFragment2 The second Story Fragment to connect with the first Story Fragment.
     * @param story The Story Fragment that the Story Fragments are being connected in.
     * @param choice The choice the Story Fragments are being connected with.
     * @return
     */
    public static boolean connectStoryFragments(StoryFragment storyFragment1,
	    StoryFragment storyFragment2, Story story, Choice choice) {
	// TODO This will be completed in Project Part 4
	return false;
    }
}
