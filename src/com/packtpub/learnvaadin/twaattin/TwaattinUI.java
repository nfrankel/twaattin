package com.packtpub.learnvaadin.twaattin;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

public class TwaattinUI extends UI {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(VaadinRequest request) {
		Label label = new Label("Hello Vaadin user");
		setContent(label);
	}
}
