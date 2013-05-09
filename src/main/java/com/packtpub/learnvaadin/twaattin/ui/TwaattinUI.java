package com.packtpub.learnvaadin.twaattin.ui;

import twitter4j.DirectMessage;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.UserStreamListener;

import com.packtpub.learnvaadin.twaattin.presenter.TweetRefresherBehavior;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.server.SystemError;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Push
@Theme("twaattin")
public class TwaattinUI extends UI implements UserStreamListener {

	private static final long serialVersionUID = 1L;

	private TweetRefresherBehavior tweetRefresherBehavior = new TweetRefresherBehavior();

	@Override
	public void init(VaadinRequest request) {

		setContent(new LoginScreen());
	}

	public static TwaattinUI getCurrent() {

		return (TwaattinUI) UI.getCurrent();
	}

	public TweetRefresherBehavior getTweetRefresherBehavior() {

		return tweetRefresherBehavior;
	}

	@Override
	public void onException(Exception ex) {

		setComponentError(new SystemError(ex));
	}

	@Override
	public void onStatus(final Status status) {

		access(new Runnable() {

			@Override
			public void run() {
				tweetRefresherBehavior.updatedStatus(status);
			}
		});
	}

	@Override
	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScrubGeo(long userId, long upToStatusId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStallWarning(StallWarning warning) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDeletionNotice(long directMessageId, long userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFriendList(long[] friendIds) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFavorite(User source, User target, Status favoritedStatus) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnfavorite(User source, User target, Status unfavoritedStatus) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFollow(User source, User followedUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDirectMessage(DirectMessage directMessage) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserListMemberAddition(User addedMember, User listOwner, UserList list) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserListMemberDeletion(User deletedMember, User listOwner, UserList list) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserListSubscription(User subscriber, User listOwner, UserList list) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserListUnsubscription(User subscriber, User listOwner, UserList list) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserListCreation(User listOwner, UserList list) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserListUpdate(User listOwner, UserList list) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserListDeletion(User listOwner, UserList list) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUserProfileUpdate(User updatedUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onBlock(User source, User blockedUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnblock(User source, User unblockedUser) {
		// TODO Auto-generated method stub

	}
}
