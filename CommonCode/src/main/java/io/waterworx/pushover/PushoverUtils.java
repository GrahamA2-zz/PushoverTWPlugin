package io.waterworx.pushover;

import java.security.InvalidParameterException;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.pushover.client.MessagePriority;
import net.pushover.client.PushoverClient;
import net.pushover.client.PushoverException;
import net.pushover.client.PushoverMessage;
import net.pushover.client.PushoverRestClient;
import net.pushover.client.Status;

@Singleton
public class PushoverUtils implements PushoverService {

	private static final int MAX_MESSAGE_LENGTH = 250;
	
	private final PushoverClient client;
		
	@Inject
	public PushoverUtils(PushoverRestClient client ){
		this.client = client;  
	}
	
	/* (non-Javadoc)
	 * @see io.waterworx.pushover.PushoverService#push(java.lang.String, java.lang.String)
	 */
	@Override
	public String push(String apiToken, String userID, String message ) throws PushoverException{
		if ( message.length() > MAX_MESSAGE_LENGTH){
			throw new InvalidParameterException(String.format("Message length is to long (%s) expected %s",  message.length()));
		}		
		Status status = client.pushMessage(PushoverMessage.builderWithApiToken(apiToken)
		        .setUserId(userID)
		        .setMessage(message)
		        .build());
		return status.toString();
	}

	@Override
	public String push(String apiToken, String userID, String message, String device, String title, String url,
			String urlTitle,Integer priority,Long timestamp,String sound)
			throws PushoverException {

		if ( message.length() > MAX_MESSAGE_LENGTH){
			throw new InvalidParameterException(String.format("Message length is to long (%s) expected %s",  message.length()));
		}
		
		
		MessagePriority mPriority = MessagePriority.NORMAL;
		switch (priority) {
		case -1:
			mPriority = mPriority.QUIET;
			break;
		case 1:
			mPriority = mPriority.HIGH;
			break;
		}
		
		
		Status status = client.pushMessage(PushoverMessage.builderWithApiToken(apiToken)
		        .setTitleForURL(urlTitle)
		        .setUrl(url)
		        .setTitleForURL(urlTitle)
		        .setDevice(device)
		        .setTimestamp(timestamp)
		        .setTitle(title)
				.setUserId(userID)
		        .setMessage(message)
		        .setPriority(mPriority)
		        .setSound(sound)
		        .build());
		return status.toString();
	}

	
}
