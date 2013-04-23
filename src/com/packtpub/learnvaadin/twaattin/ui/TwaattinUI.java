package com.packtpub.learnvaadin.twaattin.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Theme("twaattin")
public class TwaattinUI extends UI {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(VaadinRequest request) {

		setContent(new LoginScreen());
	}
}
