package com.packtpub.learnvaadin.twaattin.ui;

import static com.vaadin.server.Sizeable.Unit.PERCENTAGE;
import static com.vaadin.ui.Alignment.MIDDLE_RIGHT;
import static java.util.Locale.ENGLISH;

import java.security.Principal;

import twitter4j.Status;

import com.packtpub.learnvaadin.twaattin.presenter.LogoutBehavior;
import com.packtpub.learnvaadin.twaattin.presenter.TweetRefresherBehavior;
import com.packtpub.learnvaadin.twaattin.ui.decorator.NameColumnGenerator;
import com.packtpub.learnvaadin.twaattin.ui.decorator.ProfileImageColumnGenerator;
import com.packtpub.learnvaadin.twaattin.ui.decorator.ScreenNameColumnGenerator;
import com.packtpub.learnvaadin.twaattin.ui.decorator.SourceColumnDecorator;
import com.packtpub.learnvaadin.twaattin.ui.decorator.TweetColumnDecorator;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class TimelineScreen extends VerticalLayout {

	private static final long serialVersionUID = 1L;

	public TimelineScreen() {

		setMargin(true);

		Label label = new Label(VaadinSession.getCurrent()
				.getAttribute(Principal.class).getName());

		Button button = new Button("Logout");

		button.addClickListener(new LogoutBehavior());

		HorizontalLayout menuBar = new HorizontalLayout(label, button);

		menuBar.setWidth(100, PERCENTAGE);
		menuBar.setComponentAlignment(button, MIDDLE_RIGHT);

		addComponent(menuBar);

		TweetRefresherBehavior behavior = TwaattinUI.getCurrent().getTweetRefresherBehavior();

		addComponentAttachListener(behavior);

		Table table = new Table();

		BeanItemContainer<Status> container = new BeanItemContainer<Status>(Status.class);

		table.setContainerDataSource(container);

		table.setLocale(ENGLISH);

		addComponent(table);

		table.addGeneratedColumn("source", new SourceColumnDecorator());
		table.addGeneratedColumn("screenName", new ScreenNameColumnGenerator());
		table.addGeneratedColumn("name", new NameColumnGenerator());
		table.addGeneratedColumn("profileImage",
				new ProfileImageColumnGenerator());
		table.addGeneratedColumn("text", new TweetColumnDecorator());

		table.setColumnHeader("source", "via");
		table.setColumnHeader("screenName", "Screen name");
		table.setColumnHeader("profileImage", "");
		table.setColumnHeader("text", "Tweet");

		table.setVisibleColumns(new Object[] { "text", "name", "screenName",
				"profileImage", "createdAt", "source" });
	}
}
