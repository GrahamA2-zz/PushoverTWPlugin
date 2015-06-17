package io.waterworx.pushover;

import net.pushover.client.PushoverClient;
import net.pushover.client.PushoverException;
import net.pushover.client.PushoverMessage;
import net.pushover.client.PushoverRestClient;
import net.pushover.client.Status;

public class PushoverUtils {

	private final PushoverClient client;
	
	public PushoverUtils(){
		client = new PushoverRestClient();  
	}
	
	public String push(String userID, String message ) throws PushoverException{
			      
		Status status = client.pushMessage(PushoverMessage.builderWithApiToken("aS4s9HiwMkq6vjqvemDYbAqZQaJ8Nk")
		        .setUserId(userID)
		        .setMessage(message)
		        .build());
		return status.toString();
	}

	
}
