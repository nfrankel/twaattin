package com.packtpub.learnvaadin.service;

import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.UserStreamListener;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import com.vaadin.ui.UI;

public class TwitterService {

	private TwitterStream streamTwitter = TwitterStreamFactory.getSingleton();

	private static TwitterService singleton = new TwitterService();

	private RequestToken requestToken;

	private AccessToken accessToken;

	private TwitterService() {

		streamTwitter.addListener((UserStreamListener) UI.getCurrent());
	}

	public static TwitterService get() {

		return singleton;
	}

	public String getAuthenticationUrl() throws TwitterException {

		streamTwitter.setOAuthAccessToken(null);

		requestToken = streamTwitter.getOAuthRequestToken();

		return requestToken.getAuthenticationURL();
	}

	public String authenticate(String pin) throws TwitterException {

		accessToken = streamTwitter.getOAuthAccessToken(requestToken, pin);

		requestToken = null;

		streamTwitter.setOAuthAccessToken(accessToken);

		streamTwitter.user();

		return streamTwitter.getScreenName();
	}
}
