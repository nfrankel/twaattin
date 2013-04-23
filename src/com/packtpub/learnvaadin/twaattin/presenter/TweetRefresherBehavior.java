package com.packtpub.learnvaadin.twaattin.presenter;

import java.util.List;

import twitter4j.Status;
import twitter4j.TwitterException;

import com.packtpub.learnvaadin.service.TwitterService;
import com.packtpub.learnvaadin.twaattin.ui.StatusComponent;
import com.packtpub.learnvaadin.twaattin.ui.convert.StatusConverter;
import com.packtpub.learnvaadin.twaattin.ui.convert.StatusDto;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents.ComponentAttachEvent;
import com.vaadin.ui.HasComponents.ComponentAttachListener;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class TweetRefresherBehavior implements ComponentAttachListener {

	@Override
	public void componentAttachedToContainer(ComponentAttachEvent event) {

		Component component = event.getAttachedComponent();

		if (component instanceof VerticalLayout) {

			VerticalLayout layout = (VerticalLayout) component;

			try {

				List<Status> statuses = TwitterService.get().getTweets();

				StatusConverter converter = new StatusConverter();

				for (Status status : statuses) {

					StatusDto dto = converter.convert(status);

					StatusComponent statusComponent = new StatusComponent(dto);
					
					layout.addComponent(statusComponent);
				}

			} catch (TwitterException e) {

				throw new RuntimeException(e);
			}
		}
	}
}
