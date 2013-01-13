package com.packtpub.learnvaadin.twaattin.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class LoginScreen extends FormLayout {

	private static final long serialVersionUID = 1L;

	private TextField loginField = new TextField("Login", "packtpub");
	private PasswordField passwordField = new PasswordField("Password");
	private Button submitButton = new Button("Submit");

	public LoginScreen() {

		setMargin(true);

		addComponent(loginField);
		addComponent(passwordField);
		addComponent(submitButton);
	}
}
