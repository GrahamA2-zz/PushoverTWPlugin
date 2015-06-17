package io.waterworx.pushover;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gson.annotations.Since;

import net.pushover.client.PushoverClient;
import net.pushover.client.PushoverException;
import net.pushover.client.PushoverMessage;
import net.pushover.client.PushoverRestClient;
import net.pushover.client.Status;

@Singleton
public class PushoverUtils implements PushoverService {

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
			      
		Status status = client.pushMessage(PushoverMessage.builderWithApiToken("aS4s9HiwMkq6vjqvemDYbAqZQaJ8Nk")
		        .setUserId(userID)
		        .setMessage(message)
		        .build());
		return status.toString();
	}

	
}
