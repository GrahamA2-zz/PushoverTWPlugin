package push;

import net.pushover.client.PushoverClient;
import net.pushover.client.PushoverException;
import net.pushover.client.PushoverMessage;
import net.pushover.client.PushoverRestClient;


public class push {


	public static void main(String[] args) throws PushoverException {
		PushoverClient client = new PushoverRestClient();        

		client.pushMessage(PushoverMessage.builderWithApiToken("MY_APP_API_TOKEN")
		        .setUserId("USER_ID_TOKEN")
		        .setMessage("testing!")
		        .build());

	}

}
