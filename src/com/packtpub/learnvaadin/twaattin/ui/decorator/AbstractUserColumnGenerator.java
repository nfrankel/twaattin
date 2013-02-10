package com.packtpub.learnvaadin.twaattin.ui.decorator;

import twitter4j.User;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;

@SuppressWarnings("serial")
public abstract class AbstractUserColumnGenerator implements ColumnGenerator {

	/**
	 * @param source
	 *            Table component
	 * @param itemId
	 *            Item ID
	 * @return Tweet's underlying {@link User}
	 */
	protected User getUser(Table source, Object itemId) {
		
		Item item = source.getItem(itemId);

		@SuppressWarnings("unchecked")
		Property<User> property = item.getItemProperty("user");

		return property.getValue();
	}
}
