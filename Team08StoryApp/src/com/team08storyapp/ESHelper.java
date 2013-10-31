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
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author Michele's Netbook
 * 
 */
public class ESHelper {
    // Http Connector
    private HttpClient httpclient = new DefaultHttpClient();

    // JSON Utilities
    private Gson gson = new Gson();

    private static final String TAG = "ESHelper";

    /**
     * Consumes the POST/Insert operation of the service
     * 
     * @throws IOException
     * @throws IllegalStateException
     */

    public int addOnlineStory(Story story) {
	if (story.getOnlineStoryId() == 0) {
	    ArrayList<Story> stories = getOnlineStories();
	    int nextId = stories.size() + 1;
	    HttpPost httpPost = new HttpPost(
		    "http://cmput301.softwareprocess.es:8080/cmput301f13t08/stories/"
			    + nextId);
	    story.setOnlineStoryId(nextId);
	    StringEntity stringentity = null;
	    try {
		stringentity = new StringEntity(gson.toJson(story));
	    } catch (UnsupportedEncodingException e) {
		Log.d(TAG, e.getLocalizedMessage());
		return 0;
	    }
	    httpPost.setHeader("Accept", "application/json");

	    httpPost.setEntity(stringentity);
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

	    try {
		EntityUtils.consume(entity);
	    } catch (IOException e) {
		Log.d(TAG, e.getLocalizedMessage());
		return 0;
	    }
	    httpPost.releaseConnection();
	}

	return story.getOnlineStoryId();
    }

    public boolean updateOnlineStory(Story story) {
	// TODO Auto-generated method stub
	return false;
    }

    public Story getOnlineStory(int storyId) {
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
	    getRequest.releaseConnection();

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

	    postRequest.releaseConnection();

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
	ArrayList<Story> stories = new ArrayList<Story>();
	try {
	    HttpPost searchRequest = new HttpPost(
		    "http://cmput301.softwareprocess.es:8080/cmput301f13t08/_search?pretty=1");
	    String query = "{\"query\" : {\"multi_match\" : {\"fields\" : [\"title\", \"author\"],\"query\" : \""
		    + searchString + "\"}}}";
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
		searchRequest.releaseConnection();
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
