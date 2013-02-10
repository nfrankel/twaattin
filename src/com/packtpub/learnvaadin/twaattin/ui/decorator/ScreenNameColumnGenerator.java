package com.packtpub.learnvaadin.twaattin.ui.decorator;

import twitter4j.User;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class ScreenNameColumnGenerator extends AbstractUserColumnGenerator {

	private static final String TWITTER_USER_URL = "https://twitter.com/";

	/**
	 * @return Screen name of the underlying {@link User} as a {@link Link}
	 *         component.
	 */
	@Override
	public Object generateCell(Table source, Object itemId, Object columnId) {

		User user = getUser(source, itemId);

		ExternalResource resource = new ExternalResource(TWITTER_USER_URL
				+ user.getScreenName());

		Link link = new Link('@' + user.getScreenName(), resource);

		link.setTargetName("screenname");

		return link;
	}
}
