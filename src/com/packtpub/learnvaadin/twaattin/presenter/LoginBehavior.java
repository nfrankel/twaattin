package com.packtpub.learnvaadin.twaattin.presenter;

import static com.vaadin.ui.Notification.Type.ERROR_MESSAGE;

import java.security.Principal;

import com.packtpub.learnvaadin.authentication.AuthenticationException;
import com.packtpub.learnvaadin.authentication.TwitterAuthenticationStrategy;
import com.packtpub.learnvaadin.twaattin.ui.TimelineScreen;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

/**
 * Login behavior delegates to a predefined authentication strategy.
 */
public class LoginBehavior implements ClickListener {

	private static final long serialVersionUID = 1L;

	private final TextField pinField;

	public LoginBehavior(TextField loginField) {

		this.pinField = loginField;
	}

	@Override
	public void buttonClick(ClickEvent event) {

		try {

			String pin = pinField.getValue();

			Principal user = new TwitterAuthenticationStrategy()
					.authenticate(pin);

			VaadinSession.getCurrent().setAttribute(Principal.class, user);

			UI.getCurrent().setContent(new TimelineScreen());

			Notification.show("You're authenticated into Twaattin");

		} catch (AuthenticationException e) {

			Notification.show(e.getMessage(), ERROR_MESSAGE);
		}
	}
}
