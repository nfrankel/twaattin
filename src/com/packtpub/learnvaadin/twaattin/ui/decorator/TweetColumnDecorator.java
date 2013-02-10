package com.packtpub.learnvaadin.twaattin.ui.decorator;

import static com.vaadin.shared.ui.label.ContentMode.HTML;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.URLEntity;
import twitter4j.UserMentionEntity;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;

@SuppressWarnings("serial")
public class TweetColumnDecorator implements ColumnGenerator {

	private static final String TWITTER_SEARCH_URL = "http://twitter.com/search/";
	private static final String TWITTER_USER_URL = "https://twitter.com/";

	private List<TweetFragment> fragments;

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

	@Override
	@SuppressWarnings("unchecked")
	public Object generateCell(Table source, Object itemId, Object columnId) {

		fragments = new ArrayList<TweetFragment>();

		Item item = source.getItem(itemId);

		@SuppressWarnings("rawtypes")
		BeanItem<Status> beanItem = (BeanItem) item;

		Status status = beanItem.getBean();

		createFragmentsWithUrl(status.getURLEntities());
		createFragmentsWithTag(status.getHashtagEntities());
		createFragmentsWithMention(status.getUserMentionEntities());

		Collections.sort(fragments);

		StringBuilder builder = new StringBuilder(status.getText());

		int offset = 0;

		for (TweetFragment fragment : fragments) {

			builder.replace(fragment.getStart() + offset, fragment.getEnd()
					+ offset, fragment.getReplacement());

			offset += fragment.getOffset();
		}

		return new Label(builder.toString(), HTML);
	}

	void createFragmentsWithUrl(URLEntity[] urls) {

		if (urls != null) {

			for (URLEntity url : urls) {

				String expandedUrl = url.getExpandedURL();

				int start = url.getStart();
				int end = url.getEnd();

				String href = "<a href='" + expandedUrl
						+ "' target='_blank' + title='" + expandedUrl + "'>";

				TweetFragment fragment = new TweetFragment(start, end, href
						+ url.getURL() + "</a>");

				fragments.add(fragment);
			}
		}
	}

	void createFragmentsWithTag(HashtagEntity[] tags) {

		if (tags != null) {

			for (HashtagEntity tag : tags) {

				int start = tag.getStart();
				int end = tag.getEnd();

				try {

					String encodedUrl = TWITTER_SEARCH_URL
							+ URLEncoder.encode('#' + tag.getText(), "UTF-8");

					String href = "<a href='" + encodedUrl
							+ "' target='search'>";

					TweetFragment fragment = new TweetFragment(start, end, href
							+ '#' + tag.getText() + "</a>");

					fragments.add(fragment);

				} catch (UnsupportedEncodingException e) {

					e.printStackTrace();
				}
			}
		}
	}

	void createFragmentsWithMention(UserMentionEntity[] mentions) {

		if (mentions != null) {

			for (UserMentionEntity mention : mentions) {

				int start = mention.getStart();
				int end = mention.getEnd();

				String screenName = mention.getScreenName();

				String url = TWITTER_USER_URL + screenName;

				String href = "<a href='" + url + "' target='" + screenName
						+ "'>";

				TweetFragment fragment = new TweetFragment(start, end, href
						+ '@' + screenName + "</a>");

				fragments.add(fragment);
			}
		}
	}
}
