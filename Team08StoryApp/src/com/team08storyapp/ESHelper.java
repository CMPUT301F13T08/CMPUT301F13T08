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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.util.Log;

public class ESHelper {
	// Http Connector
	private HttpClient httpclient = new DefaultHttpClient();

	// JSON Utilities
	private Gson gson = new Gson();

	private static final String TAG = "ESHelper";

	// TODO: remove when methods for this class are working
	private Story initializeSampleStory() {
		Story sampleStory = new Story("The Walk", "Michele Paulichuk");
		sampleStory.setFirstStoryFragment(1);

		ArrayList<StoryFragment> storyFragmentList = new ArrayList<StoryFragment>();
		ArrayList<Choice> choices = new ArrayList<Choice>();

		// Story Fragment 1
		sampleStory.setFirstStoryFragment(1);
		StoryFragment storyFragment1 = new StoryFragment(1);
		storyFragment1
				.setStoryText("Like any other day, Amara decided to take her dog out for a walk. She left the house and exited the yard.");
		choices.add(new Choice(2, 1,
				"Amara turned south heading for her friend’s house."));
		choices.add(new Choice(7, 2,
				"Amara turned north heading for the store."));
		choices.add(new Choice(3, 3,
				"Amara turned east heading for the river valley."));
		storyFragment1.setChoices(choices);
		storyFragmentList.add(storyFragment1);

		// Story Fragment 2
		StoryFragment storyFragment2 = new StoryFragment(1);
		storyFragment2
				.setStoryText("As Amara walked toward her friend’s house her dog started acting up. It pulled this way and that way. Then it stopped and started barking at a bush.");
		choices.clear();
		choices.add(new Choice(4, 1, "In the bush, Amara found a small boy."));
		choices.add(new Choice(5, 2,
				"In the bush, Amara found a hundred dollar bill."));
		storyFragment2.setChoices(choices);
		storyFragmentList.add(storyFragment2);

		// Story Fragment 3
		StoryFragment storyFragment3 = new StoryFragment(1);
		storyFragment3
				.setStoryText("Amara gets to the river valley and decides to take a path she has yet to explore. As Amara walks along she comes across a cave in the side of the valley.");
		choices.clear();
		choices.add(new Choice(8, 1,
				"Amara decides to take a peek in the cave."));
		choices.add(new Choice(9, 2,
				"Amara thinks the cave is creepy and continues walking."));
		storyFragment3.setChoices(choices);
		storyFragmentList.add(storyFragment3);

		// Story Fragment 4
		StoryFragment storyFragment4 = new StoryFragment(1);
		storyFragment4
				.setStoryText("At first Amara was startled by the boy. Then she realized he was crying softly. So she asked the boy what’s wrong. The boy was lost.");
		choices.clear();
		choices.add(new Choice(10, 1,
				"Amara decides to help the boy find his way home."));
		choices.add(new Choice(11, 2, "Amara is uncertain what to do."));
		storyFragment4.setChoices(choices);
		storyFragmentList.add(storyFragment4);

		// Story Fragment 5
		StoryFragment storyFragment5 = new StoryFragment(1);
		storyFragment5
				.setStoryText("Amara picked up the bill and looked around. There was no one around and therefore anyone to claim the money. So Amara decided to keep the bill and continue on to her friend’s house.");
		choices.clear();
		choices.add(new Choice(6, 1, "Continue"));
		storyFragment5.setChoices(choices);
		storyFragmentList.add(storyFragment5);

		// Story Fragment 6
		StoryFragment storyFragment6 = new StoryFragment(1);
		storyFragment6
				.setStoryText("When Amara got to her friend’s house she explained her excitement at finding one hundred dollars in a bush, all thanks to her dog. Her friend suggests she should reward her dog a treat with part of the money.");
		choices.clear();
		choices.add(new Choice(7, 1, "Continue"));
		storyFragment6.setChoices(choices);
		storyFragmentList.add(storyFragment6);

		// Story Fragment 7
		StoryFragment storyFragment7 = new StoryFragment(1);
		storyFragment7
				.setStoryText("Amara heads to the store and buys her dog a big juicy bone. After which, they head home for a nap. THE END");
		storyFragmentList.add(storyFragment7);

		// Story Fragment 8
		StoryFragment storyFragment8 = new StoryFragment(1);
		storyFragment8
				.setStoryText("Amara steps towards the cave, as a little boy crawls out of it.");
		choices.clear();
		choices.add(new Choice(4, 1, "Continue"));
		storyFragment8.setChoices(choices);
		storyFragmentList.add(storyFragment8);

		// Story Fragment 9
		StoryFragment storyFragment9 = new StoryFragment(1);
		storyFragment9
				.setStoryText("Amara walks past the cave in a hurry. She notices the clouds have formed into rain clouds and decides it’s time to head home. THE END");
		storyFragmentList.add(storyFragment9);

		// Story Fragment 10
		StoryFragment storyFragment10 = new StoryFragment(1);
		storyFragment10
				.setStoryText("The boy says he knows his phone number and his mom is home but has no way to call her. Amara pulls out her cell and dials the number. She explains she found the boy and where she is.");
		choices.clear();
		choices.add(new Choice(12, 1, "Continue"));
		storyFragment10.setChoices(choices);
		storyFragmentList.add(storyFragment10);

		// Story Fragment 11
		StoryFragment storyFragment11 = new StoryFragment(1);
		storyFragment11
				.setStoryText("As Amara and the boy stand there, they hear a voice calling the name Timothy. The boy stops his crying and starts yelling Momma. The boy’s mother comes rushing up and is relieved to find her missing son. You decide you have enough excitement for one walk and turn to head home for a nap. THE END");
		storyFragmentList.add(storyFragment11);

		// Story Fragment 12
		StoryFragment storyFragment12 = new StoryFragment(1);
		storyFragment12
				.setStoryText("The boy’s mother arrives shortly and is relieved to find her son. As a reward for finding her son, she invites to take you out for ice cream with them. You decide to abandon your walk and take her offer up. THE END");
		storyFragmentList.add(storyFragment12);

		sampleStory.setStoryFragments(storyFragmentList);
		return sampleStory;
	}
	
	/**
	 * Consumes the POST/Insert operation of the service
	 * 
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public boolean addOnlineStory(Story story) {
		HttpPost httpPost = new HttpPost(
				"http://cmput301.softwareprocess.es:8080/cmput301f13t08/"
						+ story.getStoryId());
		StringEntity stringentity = null;
		try {
			stringentity = new StringEntity(gson.toJson(story));
		} catch (UnsupportedEncodingException e) {
			Log.d(TAG, e.getLocalizedMessage());
			return false;
		}
		httpPost.setHeader("Accept", "application/json");

		httpPost.setEntity(stringentity);
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
		} catch (ClientProtocolException e) {
			Log.d(TAG, e.getLocalizedMessage());
			return false;
		} catch (IOException e) {
			Log.d(TAG, e.getLocalizedMessage());
			return false;
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
			return false;
		} catch (IOException e) {
			Log.d(TAG, e.getLocalizedMessage());
			return false;
		}

		try {
			EntityUtils.consume(entity);
		} catch (IOException e) {
			Log.d(TAG, e.getLocalizedMessage());
			return false;
		}
		httpPost.releaseConnection();

		return true;
	}

	public boolean updateOnlineStory(Story story) {
		// TODO Auto-generated method stub
		return false;
	}

	public Story getOnlineStory(int storyId) {
		Story story;
		/*try {
			HttpGet getRequest = new HttpGet(
					"http://cmput301.softwareprocess.es:8080/cmput301f13t08/"
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
		}*/
		
		story = initializeSampleStory();
		
		return story;
	}

	public ArrayList<Story> getOnlineStories() {
		ArrayList<Story> stories = new ArrayList<Story>();
		stories.add(initializeSampleStory());
		return stories;
	}

	public ArrayList<Story> searchOnlineStories(String searchString) {
		ArrayList<Story> stories = new ArrayList<Story>();
		/*try {
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
		}*/
		
		stories.add(initializeSampleStory());

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
