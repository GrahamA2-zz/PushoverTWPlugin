package push;

import io.waterworx.pushover.PushoverService;

import com.google.inject.Guice;
import com.google.inject.Injector;

import net.pushover.client.PushoverClient;
import net.pushover.client.PushoverException;
import net.pushover.client.PushoverMessage;
import net.pushover.client.PushoverRestClient;


public class push {

	private static final String API_TOKEN = "aS4s9HiwMkq6vjqvemDYbAqZQaJ8Nk";

	public static void main(String[] args) throws PushoverException {
		Injector injector = Guice.createInjector(new AppInjector());
		PushoverService pushover = injector.getInstance(PushoverService.class);
		
		String userID = "uQgzsYM37Di4AK4e8cRg2KEWQCGYMn";
		pushover.push(API_TOKEN, userID, "Hello World " + System.currentTimeMillis());
	}

}
