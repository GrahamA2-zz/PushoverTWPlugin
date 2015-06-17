package push;

import net.pushover.client.PushoverClient;
import net.pushover.client.PushoverException;
import net.pushover.client.PushoverMessage;
import net.pushover.client.PushoverRestClient;


public class push {


	public static void main(String[] args) throws PushoverException {
		PushoverClient client = new PushoverRestClient();        

		client.pushMessage(PushoverMessage.builderWithApiToken("aS4s9HiwMkq6vjqvemDYbAqZQaJ8Nk")
		        .setUserId("uQgzsYM37Di4AK4e8cRg2KEWQCGYMn")
		        .setMessage("testing!")
		        .build());

	}

}
