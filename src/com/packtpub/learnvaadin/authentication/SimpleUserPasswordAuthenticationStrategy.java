package com.packtpub.learnvaadin.authentication;

import java.security.Principal;

public class SimpleUserPasswordAuthenticationStrategy implements
		UserPasswordAuthenticationStrategy {

	@Override
	public Principal authenticate(String login, String password)
			throws AuthenticationException {

		if (!"packtpub".equals(login)) {

			throw new AuthenticationException("Bad login/password pair");
		}

		return new User(login);
	}
}
