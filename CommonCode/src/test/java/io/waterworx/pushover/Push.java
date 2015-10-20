package io.waterworx.pushover;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
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
		p.sendMessageLong();
	}

	private Properties prop = new Properties();

	private void sendMessageShort() {
		//aWj3fiqfdeLLqsQUFTSHGLQ1sqFhVq -- Shift change
		//amtNSeeqpyi6NFX5A8f8zG3VPJb38u -- Alarm
		//ada2JVK8ydZy21wQ8JhDavbYoeJdFF -- SWB
		String apiToken =  "ada2JVK8ydZy21wQ8JhDavbYoeJdFF"; prop.getProperty("apiToken");
		String userID = prop.getProperty("userID");
		try{
			System.out.println("userID:" + userID);
			System.out.println("apiToken:" + apiToken);
			String status = pushover.push(apiToken, userID, "SWB: Go to site x");
			System.out.println(status);
		} catch (Exception e){
			System.err.println(e);
		}

	}
	
	private void sendMessageLong() {
		//aWj3fiqfdeLLqsQUFTSHGLQ1sqFhVq -- Shift change
		//amtNSeeqpyi6NFX5A8f8zG3VPJb38u -- Alarm
		//ada2JVK8ydZy21wQ8JhDavbYoeJdFF -- SWB
		String apiToken =  "ada2JVK8ydZy21wQ8JhDavbYoeJdFF"; prop.getProperty("apiToken");
		String userID = prop.getProperty("userID");
		try{
			String message = "Test";
			String title = "A title";
			String url = "http://www.aquamatix.net";
			String urlTitle = "Aquamatix";
			Integer priority = 1;
			Date now = new Date();  	
			Long longTime = new Long(now.getTime()/1000);
			Long timestamp = longTime;
			String sound = "siren";
			String device = "";
			String status = pushover.push(apiToken, userID, message, device, title, url,urlTitle,priority,timestamp,sound);
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
