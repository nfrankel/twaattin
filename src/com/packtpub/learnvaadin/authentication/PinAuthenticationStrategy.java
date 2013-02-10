package com.packtpub.learnvaadin.authentication;

import java.security.Principal;

public interface PinAuthenticationStrategy {

	/**
	 * Authenticate using the PIN.
	 * 
	 * @param pin
	 *            PIN
	 * @return Security principal identifying this user
	 */
	Principal authenticate(String pin) throws AuthenticationException;
}
