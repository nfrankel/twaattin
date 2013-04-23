package com.packtpub.learnvaadin.twaattin.ui.convert;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.URLEntity;
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

		dto.setName(status.getUser().getName());
		dto.setScreenName(status.getUser().getScreenName());
		dto.setProfileImage(status.getUser().getProfileImageURL());

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

	void createFragmentsWithUrl(List<TweetFragment> fragments, URLEntity[] urls) {

		if (urls != null) {

			for (URLEntity url : urls) {

				String expandedUrl = url.getExpandedURL();

				int start = url.getStart();
				int end = url.getEnd();

				String href = "<a href='" + expandedUrl + "' target='_blank' + title='" + expandedUrl + "'>";

				TweetFragment fragment = new TweetFragment(start, end, href + url.getURL() + "</a>");

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
