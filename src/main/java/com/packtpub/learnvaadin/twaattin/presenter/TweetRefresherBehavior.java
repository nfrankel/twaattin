package com.packtpub.learnvaadin.twaattin.presenter;

import twitter4j.Status;

import com.packtpub.learnvaadin.twaattin.ui.StatusComponent;
import com.packtpub.learnvaadin.twaattin.ui.convert.StatusConverter;
import com.packtpub.learnvaadin.twaattin.ui.convert.StatusDto;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents.ComponentAttachEvent;
import com.vaadin.ui.HasComponents.ComponentAttachListener;
import com.vaadin.ui.Layout;

@SuppressWarnings("serial")
public class TweetRefresherBehavior implements ComponentAttachListener {

	private Layout layout;

	public void updatedStatus(Status status) {

		if (layout != null) {
		
			StatusConverter converter = new StatusConverter();

			StatusDto dto = converter.convert(status);

			StatusComponent statusComponent = new StatusComponent(dto);

			layout.addComponent(statusComponent);
		}
	}

	@Override
	public void componentAttachedToContainer(ComponentAttachEvent event) {

		Component component = event.getAttachedComponent();

		if (component instanceof Layout) {

			layout = (Layout) component;
		}
	}
}