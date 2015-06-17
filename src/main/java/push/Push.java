package push;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.waterworx.pushover.PushoverService;

import com.google.inject.Guice;
import com.google.inject.Injector;

import net.pushover.client.PushoverClient;
import net.pushover.client.PushoverException;
import net.pushover.client.PushoverMessage;
import net.pushover.client.PushoverRestClient;


public class Push {


	private static PushoverService pushover;
	
	public static void main(String[] args) throws PushoverException, IOException {
		Injector injector = Guice.createInjector(new AppInjector());
		pushover = injector.getInstance(PushoverService.class);
		Push p = new Push();
		p.sendMessage();
	}

	private void sendMessage() throws IOException, PushoverException{
			Properties prop = new Properties();
			String propFileName = "config.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			String apiToken = prop.getProperty("apiToken");
			String userID = prop.getProperty("userID");
			pushover.push(apiToken, userID, "Hello World " + System.currentTimeMillis());
			
}
}
