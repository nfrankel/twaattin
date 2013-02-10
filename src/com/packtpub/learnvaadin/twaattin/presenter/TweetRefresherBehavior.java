package com.packtpub.learnvaadin.twaattin.presenter;

import java.util.List;

import twitter4j.Status;
import twitter4j.TwitterException;

import com.packtpub.learnvaadin.service.TwitterService;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents.ComponentAttachEvent;
import com.vaadin.ui.HasComponents.ComponentAttachListener;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class TweetRefresherBehavior implements ComponentAttachListener {

	@Override
	public void componentAttachedToContainer(ComponentAttachEvent event) {

		Component component = event.getAttachedComponent();

		if (component instanceof Table) {

			Table table = (Table) component;

			try {

				List<Status> statuses = TwitterService.get().getTweets();

				BeanItemContainer<Status> container = new BeanItemContainer<Status>(
						Status.class);

				container.addAll(statuses);

				table.setContainerDataSource(container);

			} catch (TwitterException e) {

				throw new RuntimeException(e);
			}
		}
	}
}
