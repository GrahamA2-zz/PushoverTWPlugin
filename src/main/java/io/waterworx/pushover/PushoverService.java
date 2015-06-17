package io.waterworx.pushover;

import net.pushover.client.PushoverException;

public interface PushoverService {

	public abstract String push(String apiToken, String userID, String message) throws PushoverException;
	public abstract String push(String apiToken, String userID, String message, String title, String url, String urlTitle ) throws PushoverException;
}