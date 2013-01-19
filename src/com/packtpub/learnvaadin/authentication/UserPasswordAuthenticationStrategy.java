package com.packtpub.learnvaadin.authentication;

import java.security.Principal;

public interface UserPasswordAuthenticationStrategy {

	/**
	 * Authenticate using the login / password pair.
	 * 
	 * @param login
	 *            Login
	 * @param password
	 *            Password
	 * @return Security principal identifying this user
	 */
	Principal authenticate(String login, String password)
			throws AuthenticationException;
}
