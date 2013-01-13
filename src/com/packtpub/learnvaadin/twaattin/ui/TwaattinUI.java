package com.packtpub.learnvaadin.twaattin.ui;

import static com.vaadin.server.Sizeable.Unit.PIXELS;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.UI;

public class TwaattinUI extends UI {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(VaadinRequest request) {

		HorizontalSplitPanel panel = new HorizontalSplitPanel();

		panel.setFirstComponent(new LoginScreen());
		panel.setSecondComponent(new TimelineScreen());

		panel.setSplitPosition(300, PIXELS);

		setContent(panel);
	}
}
