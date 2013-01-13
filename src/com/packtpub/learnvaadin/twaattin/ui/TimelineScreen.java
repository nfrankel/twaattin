package com.packtpub.learnvaadin.twaattin.ui;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class TimelineScreen extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public TimelineScreen() {

		setMargin(true);

		fillTweets();
	}

	public void fillTweets() {

		for (int i = 0; i < 10; i++) {

			Label label = new Label();

			label.setValue("Lorem ipsum dolor sit amet, consectetur "
					+ "adipisicing elit, sed do eiusmod tempor incididunt "
					+ "ut labore et dolore magna aliqua. Ut enim ad minim "
					+ "veniam, quis nostrud exercitation ullamco laboris "
					+ "nisi ut aliquip ex ea commodo consequat.");

			addComponent(label);
		}
	}
}
