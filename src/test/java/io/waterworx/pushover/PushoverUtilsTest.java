package io.waterworx.pushover;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import net.pushover.client.PushoverException;
import net.pushover.client.PushoverRestClient;
import net.pushover.client.Status;

import org.junit.Before;
import org.junit.Test;

public class PushoverUtilsTest {

	private PushoverService pushover;
	private static final String API_TOKEN = "mockToken";
	private PushoverRestClient client;
	
	@Before
	public void setUp() throws Exception {
		client = mock(PushoverRestClient.class);
		pushover = new PushoverUtils(client);
	}

	@Test
	public void test() throws PushoverException {
		
		Status status = mock(Status.class);
		when(client.pushMessage(anyObject())).thenReturn(status);
		
		String userID = "user1";
		pushover.push(API_TOKEN, userID, "Hello World");
		verify(client).pushMessage(anyObject());
		
	}

}
