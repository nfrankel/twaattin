package com.packtpub.learnvaadin.twaattin.ui;

import twitter4j.TwitterException;

import com.packtpub.learnvaadin.service.TwitterService;
import com.packtpub.learnvaadin.twaattin.presenter.LoginBehavior;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.SystemError;
import com.vaadin.ui.Button;
import com.vaadin.ui.Link;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class LoginScreen extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	private Link twitterLink = new Link();
	private TextField pinField = new TextField();
	private Button submitButton = new Button("Submit");

	public LoginScreen() {

		setMargin(true);
		setSpacing(true);

		twitterLink.setCaption("Get PIN");
		twitterLink.setTargetName("twitterauth");

		try {

			twitterLink.setResource(new ExternalResource(TwitterService.get().getAuthenticationUrl()));

		} catch (TwitterException e) {

			twitterLink.setComponentError(new SystemError(e));
		}

		pinField.setInputPrompt("PIN");

		addComponent(twitterLink);
		addComponent(pinField);
		addComponent(submitButton);

		submitButton.addClickListener(new LoginBehavior(pinField));
	}
}
