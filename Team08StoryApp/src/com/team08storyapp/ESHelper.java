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
 * @author Michele Paulichuk
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
     * @param story
     * @return
     */
    public int addOrUpdateOnlineStory(Story story) {
	// set policy to allow for internet activity to happen within the
	// android application
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

    public Story getOnlineStory(int storyId) {
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
		.permitAll().build();
	StrictMode.setThreadPolicy(policy);
	Story story;
	try {
	    HttpGet getRequest = new HttpGet(
		    "http://cmput301.softwareprocess.es:8080/cmput301f13t08/stories/"
			    + storyId + "?pretty=1");

	    getRequest.addHeader("Accept", "application/json");

	    HttpResponse response = httpclient.execute(getRequest);

	    String status = response.getStatusLine().toString();
	    Log.d(TAG, status);

	    String json = getEntityContent(response);

	    // We have to tell GSON what type we expect
	    Type elasticSearchResponseType = new TypeToken<ElasticSearchResponse<Story>>() {
	    }.getType();
	    // Now we expect to get a Story response
	    ElasticSearchResponse<Story> esResponse = gson.fromJson(json,
		    elasticSearchResponseType);
	    // We get the Story from it!
	    story = esResponse.getSource();
	    Log.d(TAG, story.toString());

	} catch (ClientProtocolException e) {

	    Log.d(TAG, e.getLocalizedMessage());
	    return null;

	} catch (IOException e) {

	    Log.d(TAG, e.getLocalizedMessage());
	    return null;
	}

	return story;
    }

    public ArrayList<Story> getOnlineStories() {
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
		.permitAll().build();
	StrictMode.setThreadPolicy(policy);
	ArrayList<Story> stories = new ArrayList<Story>();
	try {
	    HttpPost postRequest = new HttpPost(
		    "http://cmput301.softwareprocess.es:8080/cmput301f13t08/stories/_search?pretty=1"); // 0yzbiRXsTHi9LQGTHWS-MA

	    postRequest.addHeader("Accept", "application/json");

	    HttpResponse response = httpclient.execute(postRequest);

	    String status = response.getStatusLine().toString();
	    Log.d(TAG, status);

	    String json = getEntityContent(response);

	    // We have to tell GSON what type we expect
	    Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Story>>() {
	    }.getType();
	    // Now we expect to get a Recipe response
	    ElasticSearchSearchResponse<Story> esResponse = gson.fromJson(json,
		    elasticSearchSearchResponseType);
	    // We get the recipe from it!
	    Log.d(TAG, esResponse.toString());
	    for (ElasticSearchResponse<Story> s : esResponse.getHits()) {
		Story story = s.getSource();
		Log.d(TAG, story.toString());
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

    public ArrayList<Story> searchOnlineStories(String searchString) {
	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
		.permitAll().build();
	StrictMode.setThreadPolicy(policy);
	ArrayList<Story> stories = new ArrayList<Story>();
	try {
	    HttpPost searchRequest = new HttpPost(
		    "http://cmput301.softwareprocess.es:8080/cmput301f13t08/_search?pretty=1");
		String query = "{\"query\": {\"query_string\" :{ \"fields\":[\"title\",\"author\"], \"query\":\""+ searchString + "\"}}}";
	    StringEntity stringentity = new StringEntity(query);

	    searchRequest.setHeader("Accept", "application/json");
	    searchRequest.setEntity(stringentity);

	    HttpResponse response = httpclient.execute(searchRequest);
	    String status = response.getStatusLine().toString();
	    Log.d(TAG, status);

	    String json = getEntityContent(response);

	    Type elasticSearchSearchResponseType = new TypeToken<ElasticSearchSearchResponse<Story>>() {
	    }.getType();
	    ElasticSearchSearchResponse<Story> esResponse = gson.fromJson(json,
		    elasticSearchSearchResponseType);
	    Log.d(TAG, esResponse.toString());
	    for (ElasticSearchResponse<Story> s : esResponse.getHits()) {
		Story story = s.getSource();
		stories.add(story);
		Log.d(TAG, story.toString());
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
     * get the http response and return json string
     */
    String getEntityContent(HttpResponse response) throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(
		(response.getEntity().getContent())));
	String output;
	Log.d(TAG, "Output from Server -> ");
	String json = "";
	while ((output = br.readLine()) != null) {
	    Log.d(TAG, output);
	    json += output;
	}
	Log.d(TAG, "JSON:" + json);
	return json;
    }
}
