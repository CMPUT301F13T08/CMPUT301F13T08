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
-This demo was used to help with JSON and ESHelper

Retrieved Oct. 29, 2013  - http://hc.apache.org/downloads.cgi
-This is for the fluent library which is licenced under apache V2

Retrieved Oct. 29, 2013 
- https://code.google.com/p/google-gson/downloads/detail?name=google-gson-2.2.4-release.zip&can=2&q=
-This is for JSON which is licenced under apache V2
 */

package com.team08storyapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.StrictMode;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * ESHelper is a helper class that helps the application communicate with a
 * webservice. The webservice is may allow posting, listing, deleting and
 * searching stories.
 * <p>
 * For the purpose of this application, this class allows the following
 * communications with the webservice:
 * <ul>
 * <li>Adding a local story to the webservice.
 * <li>Getting a specific story stored on the webservice using a provided ID.
 * <li>Getting all stories stored from the webservice.
 * <li>Updating a specific story stored on the webservice using a provided ID.
 * <li>Searching the webservice for all stories that contain a provided text
 * string within either it's author and/or title.
 * </ul>
 * 
 * @author Abram Hindle and Chenlei Zhang (@link
 *         https://github.com/rayzhangcl/ESDemo)
 * @author Michele Paulichuk
 * @author Alice Wu
 * @author Ana Marcu
 * @author Jarrett Toll
 * @author Jiawei Shen
 * @version 1.0 November 8, 2013
 * @since 1.0
 * 
 */
public class ESHelper {
    // Http Connector
    private HttpClient httpclient = new DefaultHttpClient();

    // JSON Utilities
    private Gson gson = new Gson();

    // tag for printing out to the log cat which class the log information is
    // coming from
    private static final String TAG = "ESHelper";

    /**
     * Adds or updates a story on the webservice. The method checks the Story
     * object passed in to see if it contains a onlineStoryId. If it does not it
     * finds the next possible Id to assigned to it and then adds the Story to
     * the webservice with that Id. If the Story object contains an
     * onlineStoryId then it calls the webservice with that Id and the story
     * object, updating the Story that was located at that Id.
     * 
     * @param story
     *            The Story that is being added or updated to the webservice.
     * @return The onlineStoryId of the Story that was added or updated.
     * @see Story
     */
    public int addOrUpdateOnlineStory(Story story) {

	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
		.permitAll().build();
	StrictMode.setThreadPolicy(policy);

	HttpPost httpPost;
	// check if there is an online story Id to determine if adding or
	// updating the story
	if (story.getOnlineStoryId() == 0) {
	    // find out how many stories are stored online in order to generate
	    // an appropriate online story Id
	    ArrayList<Story> stories = getOnlineStories();
	    int nextId = stories.size() + 1;

	    // create the httppost item with the webservice information and the
	    // new id to put the story under
	    httpPost = new HttpPost(
		    "http://cmput301.softwareprocess.es:8080/cmput301f13t08/stories/"
			    + nextId);
	    story.setOnlineStoryId(nextId);
	} else {
	    // create the httppost item with the webservice information and the
	    // story's online id so that it upates that item
	    httpPost = new HttpPost(
		    "http://cmput301.softwareprocess.es:8080/cmput301f13t08/stories/"
			    + story.getOnlineStoryId());
	}

	// convert the story object to JSON format
	StringEntity stringentity = null;
	try {
	    stringentity = new StringEntity(gson.toJson(story));
	} catch (UnsupportedEncodingException e) {
	    Log.d(TAG, e.getLocalizedMessage());
	    return 0;
	}

	// set the httppost so that it knows it is accepting a JSON formatted
	// object to add
	httpPost.setHeader("Accept", "application/json");
	// set the object to add into the httppost
	httpPost.setEntity(stringentity);

	// execute the httpclient to post the object to the webservice
	HttpResponse response = null;
	try {
	    response = httpclient.execute(httpPost);
	} catch (ClientProtocolException e) {
	    Log.d(TAG, e.getLocalizedMessage());
	    return 0;
	} catch (IOException e) {
	    Log.d(TAG, e.getLocalizedMessage());
	    return 0;
	}

	// Retrieve and print to the log cat the status result of the post
	String status = response.getStatusLine().toString();
	Log.d(TAG, status);

	HttpEntity entity = response.getEntity();
	try {
	    BufferedReader br = new BufferedReader(new InputStreamReader(
		    entity.getContent()));
	    String output;

	    Log.d(TAG, "Output from Server -> ");
	    while ((output = br.readLine()) != null) {
		Log.d(TAG, output);
	    }
	} catch (IllegalStateException e) {
	    Log.d(TAG, e.getLocalizedMessage());
	    return 0;
	} catch (IOException e) {
	    Log.d(TAG, e.getLocalizedMessage());
	    return 0;
	}

	// return back to the calling class the online story id for the story
	// object added or updated
	return story.getOnlineStoryId();
    }

    /**
     * Returns a Story object for a specific onlineStoryId contained on the
     * webservice. This Story object can be used to display a
     * "Chose Your Own Adventure story".
     * <p>
     * The method uses ElisticSearch (@link http://www.elasticsearch.org/guide/)
     * to retrieve the story from the webservice.
     * 
     * @param storyId
     *            The onlineStoryId of the Story to retrieve from the
     *            webservice.
     * @return The Story object for a specified onlineStoryId.
     * @see Story
     */
    public Story getOnlineStory(int storyId) {
	// set policy to allow for internet activity to happen within the
	// android application
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
		.permitAll().build();
	StrictMode.setThreadPolicy(policy);

	Story story;
	try {
	    // Create a HttpGet object with the onlineStoryId of the Story to
	    // retrieve from the webservice
	    HttpGet getRequest = new HttpGet(
		    "http://cmput301.softwareprocess.es:8080/cmput301f13t08/stories/"
			    + storyId + "?pretty=1");

	    // Set the HttpGet so that it knows it is retrieving a JSON
	    // formatted object
	    getRequest.addHeader("Accept", "application/json");

	    // Execute the httpclient to get the object from the webservice
	    HttpResponse response = httpclient.execute(getRequest);

	    // Retrieve and print to the log cat the status result of the post
	    String status = response.getStatusLine().toString();
	    Log.d(TAG, status);

	    // Retrieve the Story object in the form of a string to be converted
	    // from JSON
	    String json = getEntityContent(response);

	    // We have to tell GSON what type we expect
	    Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<Story>>() {
	    }.getType();

	    // Now we expect to get a Story response
	    ElasticSearchResponse<Story> esResponse = gson.fromJson(json,
		    elasticSearchResponseType);

	    // We get the Story from it!
	    story = esResponse.getSource();

	} catch (ClientProtocolException e) {
	    Log.d(TAG, e.getLocalizedMessage());
	    return null;

	} catch (IOException e) {
	    Log.d(TAG, e.getLocalizedMessage());
	    return null;
	}

	return story;
    }

    /**
     * Returns a list of all Story objects contained on the webservice. These
     * stories can be used to display a list of all possible stories to view
     * online.
     * 
     * @return The List of Stories from the webservice.
     * @see Story
     */
    public ArrayList<Story> getOnlineStories() {
	// set policy to allow for internet activity to happen within the
	// android application
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
		.permitAll().build();
	StrictMode.setThreadPolicy(policy);

	ArrayList<Story> stories = new ArrayList<Story>();
	try {
	    // Create a HttpPost object to retrieve the Stories from the
	    // webservice
	    HttpPost postRequest = new HttpPost(
		    "http://cmput301.softwareprocess.es:8080/cmput301f13t08/stories/_search?pretty=1");

	    // Set the httppost so that it knows it is retrieving a JSON
	    // formatted object
	    postRequest.addHeader("Accept", "application/json");

	    // Execute the httpclient to get the object from the webservice
	    HttpResponse response = httpclient.execute(postRequest);

	    // Retrieve and print to the log cat the status result of the post
	    String status = response.getStatusLine().toString();
	    Log.d(TAG, status);

	    // Retrieve the Story object in the form of a string to be converted
	    // from JSON
	    String json = getEntityContent(response);

	    // We have to tell GSON what type we expect
	    Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Story>>() {
	    }.getType();
	    // Now we expect to get a story response
	    ElasticSearchSearchResponse<Story> esResponse = gson.fromJson(json,
		    elasticSearchSearchResponseType);
	    // We get the story from it!
	    Log.d(TAG, esResponse.toString());
	    for (ElasticSearchResponse<Story> s : esResponse.getHits()) {
		Story story = s.getSource();
		stories.add(story);
	    }

	} catch (ClientProtocolException e) {
	    Log.d(TAG, e.getLocalizedMessage());
	    return null;

	} catch (IOException e) {
	    Log.d(TAG, e.getLocalizedMessage());
	    return null;
	}
	return stories;
    }

    /**
     * Returns a list of stories from the webservice that contain the searched
     * text in either their Author or Title fields.
     * <p>
     * The method uses ElisticSearch (@link http://www.elasticsearch.org/guide/)
     * to retrieve the story from the webservice.
     * 
     * @param searchString
     *            A text string containing the key set of words to use in
     *            searching the webservices Stories.
     * @return A list of stories that contain the text used in the search in
     *         either their Author or Title fields.
     */
    public ArrayList<Story> searchOnlineStories(String searchString) {
	searchString = searchString.toLowerCase();

	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
		.permitAll().build();
	StrictMode.setThreadPolicy(policy);

	ArrayList<Story> stories = new ArrayList<Story>();
	
	if (!searchString.matches(".*\\w.*") || searchString.contains("\n")) {
	    return getOnlineStories();
	}

	try {
	    // Create a HttpPost object to retrieve the Stories from the
	    // webservice
	    HttpPost searchRequest = new HttpPost(
		    "http://cmput301.softwareprocess.es:8080/cmput301f13t08/_search?pretty=1");

	    // Create the ElasticSearch query string to search the webservice
	    // for the search text
	    String query = "{\"query\": {\"query_string\" :{ \"fields\":[\"title\",\"author\"], \"query\":\""
		    + searchString + "\"}}}";
	    StringEntity stringentity = new StringEntity(query);

	    // Set the httppost so that it knows it is retrieving a JSON
	    // formatted object
	    searchRequest.setHeader("Accept", "application/json");
	    searchRequest.setEntity(stringentity);

	    // Execute the httpclient to get the object from the webservice
	    HttpResponse response = httpclient.execute(searchRequest);

	    // Retrieve and print to the log cat the status result of the post
	    String status = response.getStatusLine().toString();
	    Log.d(TAG, status);

	    // Retrieve the Story object in the form of a string to be converted
	    // from JSON
	    String json = getEntityContent(response);

	    // We have to tell GSON what type we expect
	    Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Story>>() {
	    }.getType();
	    // Now we expect to get a story response
	    ElasticSearchSearchResponse<Story> esResponse = gson.fromJson(json,
		    elasticSearchSearchResponseType);
	    Log.d(TAG, esResponse.toString());
	    // We get the story from it!
	    for (ElasticSearchResponse<Story> s : esResponse.getHits()) {
		Story story = s.getSource();
		stories.add(story);
	    }
	} catch (ClientProtocolException e) {
	    Log.d(TAG, e.getLocalizedMessage());
	    return null;
	} catch (IOException e) {
	    Log.d(TAG, e.getLocalizedMessage());
	    return null;
	}

	return stories;
    }

    /**
     * Returns a string containing the content from the Http request for all
     * webservice calls.
     * 
     * @param response
     *            The response provided from calling the webservice.
     * @return A string of the content from the webservice request.
     * @throws IOException
     */
    String getEntityContent(HttpResponse response) throws IOException {
	// Create a buffer reader to read the contents of response.
	BufferedReader br = new BufferedReader(new InputStreamReader(
		(response.getEntity().getContent())));
	StringBuilder stringBuilder = new StringBuilder();
	Log.d(TAG, "Output from Server -> ");
	String output = "";
	
	// Retrieve the contents of the response and append it in a string.
	while ((output = br.readLine()) != null) {
	    Log.d(TAG, output);
	    stringBuilder.append(output);
	}
	Log.d(TAG, "JSON:" + stringBuilder.toString());
	
	// Return the string of the response.
	return stringBuilder.toString();
    }
}
