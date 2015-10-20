package io.waterworx.pushover;

import static org.mockito.Mockito.mock;
import net.pushover.client.PushoverException;
import net.pushover.client.PushoverRestClient;
import net.pushover.client.Status;

import org.junit.Before;
import org.junit.Ignore;

public class PushoverUtilsTest {

	private PushoverService pushover;
	private static final String API_TOKEN = "mockToken";
	private static final String USER_ID = "user1";
	private PushoverRestClient client;
	
	@Before 
	public void setUp() throws Exception {
		client = mock(PushoverRestClient.class);
		pushover = new PushoverUtils(client);
	}

	@Ignore
	public void testPushWithMessage() throws PushoverException {
		
		Status status = mock(Status.class);
		//when(client.pushMessage(anyObject())).thenReturn(status);
		
		String userID = "user1";
		pushover.push(API_TOKEN, userID, "Hello World");
		//verify(client).pushMessage(anyObject());
		
	}
	
	@Ignore//(expected=IllegalArgumentException.class)
	public void testPushWithAMessageThatIsToLong() throws PushoverException {	
		String message = String.format("%1$"+251+ "s", " ");
		pushover.push(API_TOKEN, USER_ID, message);	
	}

	
}
