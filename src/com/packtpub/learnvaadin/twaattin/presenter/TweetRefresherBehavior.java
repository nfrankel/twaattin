package com.packtpub.learnvaadin.twaattin.presenter;

import twitter4j.Status;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.HasComponents.ComponentAttachEvent;
import com.vaadin.ui.HasComponents.ComponentAttachListener;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class TweetRefresherBehavior implements ComponentAttachListener {

	private BeanItemContainer<Status> container;

	public void updatedStatus(Status status) {

		if (container != null) {
		
			container.addBean(status);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void componentAttachedToContainer(ComponentAttachEvent event) {

		Component component = event.getAttachedComponent();

		if (component instanceof Table) {

			Table table = (Table) component;

			container = (BeanItemContainer<Status>) table.getContainerDataSource();
		}
	}
}
