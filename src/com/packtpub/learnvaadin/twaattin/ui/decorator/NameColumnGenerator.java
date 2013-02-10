package com.packtpub.learnvaadin.twaattin.ui.decorator;

import twitter4j.User;

import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class NameColumnGenerator extends AbstractUserColumnGenerator {

	/**
	 * @return User name of the underlying {@link User}
	 */
	@Override
	public Object generateCell(Table source, Object itemId, Object columnId) {

		User user = getUser(source, itemId);

		return user.getName();
	}
}
