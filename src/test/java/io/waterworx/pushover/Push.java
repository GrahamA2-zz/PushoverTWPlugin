package io.waterworx.pushover;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import push.AppInjector;
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
	
	private Properties prop = new Properties();
	
	public Push() throws IOException{
		String propFileName = "config.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}		
	}

	private void sendMessage() {
			String apiToken = prop.getProperty("apiToken");
			String userID = prop.getProperty("userID");
			try{
				System.out.println(pushover.push(apiToken, userID, "Hello World"));
			} catch (Exception e){
				System.err.println(e);
			}
			
}
}
