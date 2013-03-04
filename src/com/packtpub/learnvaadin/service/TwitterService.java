package com.packtpub.learnvaadin.service;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TwitterService {

	private Twitter twitter = TwitterFactory.getSingleton();

	private static TwitterService singleton = new TwitterService();

	private RequestToken requestToken;

	private AccessToken accessToken;

	public static TwitterService get() {

		return singleton;
	}

	public String getAuthenticationUrl() throws TwitterException {

		twitter.setOAuthAccessToken(null);

		requestToken = twitter.getOAuthRequestToken();

		return requestToken.getAuthenticationURL();
	}

	public String authenticate(String pin) throws TwitterException {

		accessToken = twitter.getOAuthAccessToken(requestToken, pin);

		requestToken = null;

		twitter.setOAuthAccessToken(accessToken);

		return twitter.getScreenName();
	}

	public List<Status> getTweets() throws TwitterException {

		return twitter.getUserTimeline();
	}
}
