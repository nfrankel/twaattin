package com.packtpub.learnvaadin.twaattin.ui.convert;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.URLEntity;
import twitter4j.User;
import twitter4j.UserMentionEntity;

public class StatusConverter {

	private static final String TWITTER_SEARCH_URL = "http://twitter.com/search/";
	public static final String TWITTER_USER_URL = "https://twitter.com/";

	class TweetFragment implements Comparable<TweetFragment> {

		private final int start;
		private final int end;
		private final String replacement;

		public TweetFragment(int start, int end, String replacement) {

			this.start = start;
			this.end = end;
			this.replacement = replacement;
		}

		public int getStart() {
			return start;
		}

		public int getEnd() {
			return end;
		}

		public String getReplacement() {
			return replacement;
		}

		public int getOffset() {

			return replacement.length() - end + start;
		}

		@Override
		public int compareTo(TweetFragment o) {

			return start - o.start;
		}
	}

	public StatusDto convert(Status status) {

		List<TweetFragment> fragments = new ArrayList<>();

		StatusDto dto = new StatusDto();

		User user = null;
		
		if (status.isRetweet()) {
			
			user = status.getRetweetedStatus().getUser();

		} else {
			
			user = status.getUser();
		}
		
		dto.setName(user.getName());
		dto.setScreenName(user.getScreenName());
		dto.setProfileImage(user.getProfileImageURL());

		createFragmentsWithUrl(fragments, status.getURLEntities());
		createFragmentsWithTag(fragments, status.getHashtagEntities());
		createFragmentsWithMention(fragments, status.getUserMentionEntities());

		Collections.sort(fragments);

		StringBuilder builder = new StringBuilder(status.getText());

		int offset = 0;

		for (TweetFragment fragment : fragments) {

			builder.replace(fragment.getStart() + offset, fragment.getEnd() + offset, fragment.getReplacement());

			offset += fragment.getOffset();
		}

		dto.setText(builder.toString());

		return dto;
	}

	void createFragmentsWithUrl(List<TweetFragment> fragments, URLEntity[] entities) {

		if (entities != null) {

			for (URLEntity entity : entities) {

				String url = entity.getExpandedURL();

				int start = entity.getStart();
				int end = entity.getEnd();

				String protocollessUrl = null;
				
				if (url.startsWith("http://")) {
					
					protocollessUrl = url.substring(7);

				} else {
					
					protocollessUrl = url.substring(8);
				}
				
				String href = "<a href='" + url + "' target='_blank' + title='" + url + "'>";

				TweetFragment fragment = new TweetFragment(start, end, href + protocollessUrl + "</a>");

				fragments.add(fragment);
			}
		}
	}

	void createFragmentsWithTag(List<TweetFragment> fragments, HashtagEntity[] tags) {

		if (tags != null) {

			for (HashtagEntity tag : tags) {

				int start = tag.getStart();
				int end = tag.getEnd();

				try {

					String encodedUrl = TWITTER_SEARCH_URL + URLEncoder.encode('#' + tag.getText(), "UTF-8");

					String href = "<a href='" + encodedUrl + "' target='search'>";

					TweetFragment fragment = new TweetFragment(start, end, href + '#' + tag.getText() + "</a>");

					fragments.add(fragment);

				} catch (UnsupportedEncodingException e) {

					e.printStackTrace();
				}
			}
		}
	}

	void createFragmentsWithMention(List<TweetFragment> fragments, UserMentionEntity[] mentions) {

		if (mentions != null) {

			for (UserMentionEntity mention : mentions) {

				int start = mention.getStart();
				int end = mention.getEnd();

				String screenName = mention.getScreenName();

				String url = TWITTER_USER_URL + screenName;

				String href = "<a href='" + url + "' target='" + screenName + "'>";

				TweetFragment fragment = new TweetFragment(start, end, href + '@' + screenName + "</a>");

				fragments.add(fragment);
			}
		}
	}
}
