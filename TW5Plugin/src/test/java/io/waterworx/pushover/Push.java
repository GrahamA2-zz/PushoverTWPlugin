package io.waterworx.pushover;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import net.pushover.client.PushoverException;
import push.AppInjector;

import com.google.inject.Guice;
import com.google.inject.Injector;


public class Push {

	private static PushoverService pushover;

	public static void main(String[] args) throws PushoverException, IOException {
		Injector injector = Guice.createInjector(new AppInjector());
		pushover = injector.getInstance(PushoverService.class);

		Push p = new Push();
		p.sendMessage();
	}

	private Properties prop = new Properties();

	private void sendMessage() {
		String apiToken = prop.getProperty("apiToken");
		String userID = prop.getProperty("userID");
		try{
			String status = pushover.push(apiToken, "ut2BN71hqXPQwJ1v6vK2c5Xqg7LkRV", "Critical Message");
			System.out.println(status);
		} catch (Exception e){
			System.err.println(e);
		}

	}
	
	public Push() throws IOException{
		String propFileName = "config.properties";
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

		if (inputStream != null) {
			prop.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		}		
	}


}
