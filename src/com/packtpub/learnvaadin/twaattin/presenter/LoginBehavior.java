package com.packtpub.learnvaadin.twaattin.presenter;

import static com.vaadin.ui.Notification.Type.ERROR_MESSAGE;

import java.security.Principal;

import com.packtpub.learnvaadin.authentication.AuthenticationException;
import com.packtpub.learnvaadin.authentication.SimpleUserPasswordAuthenticationStrategy;
import com.packtpub.learnvaadin.authentication.UserPasswordAuthenticationStrategy;
import com.packtpub.learnvaadin.twaattin.ui.TimelineScreen;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

/**
 * Login behavior delegates to a predefined
 * {@link UserPasswordAuthenticationStrategy}.
 */
public class LoginBehavior implements ClickListener {

	private static final long serialVersionUID = 1L;

	private final TextField loginField;
	private final PasswordField passwordField;

	public LoginBehavior(TextField loginField, PasswordField passwordField) {

		this.loginField = loginField;
		this.passwordField = passwordField;
	}

	@Override
	public void buttonClick(ClickEvent event) {

		try {

			String login = loginField.getValue();
			String password = passwordField.getValue();

			Principal user = new SimpleUserPasswordAuthenticationStrategy()
					.authenticate(login, password);

			VaadinSession.getCurrent().setAttribute(Principal.class, user);

			UI.getCurrent().setContent(new TimelineScreen());

			Notification.show("You're authenticated into Twaattin");

		} catch (AuthenticationException e) {

			Notification.show(e.getMessage(), ERROR_MESSAGE);
		}
	}
}
