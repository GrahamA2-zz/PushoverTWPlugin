package io.waterworx.pushover;

import push.AppInjector;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.thingworx.metadata.annotations.ThingworxServiceDefinition;
import com.thingworx.metadata.annotations.ThingworxServiceParameter;
import com.thingworx.metadata.annotations.ThingworxServiceResult;
import com.thingworx.things.Thing;

public class PushoverConnector extends Thing{

	private static final long serialVersionUID = 1L;
	private final PushoverService pushover;
	private String apiToken;
	
	public PushoverConnector(){
		Injector injector = Guice.createInjector(new AppInjector());
		pushover = injector.getInstance(PushoverService.class);
	}
	
	
	@Override
	protected void initializeThing() throws Exception {
		//May need to move pushover here 
		this.apiToken = ((String) getConfigurationData().getValue("APIToken", "PushoverConnector"));
	}
	
	@ThingworxServiceDefinition(name = "GetFileList", description = "Push a notification to a user or group")
	@ThingworxServiceResult(name = "Result", description = " ", baseType = "String")
	public String PushMessage( @ThingworxServiceParameter( name = "user",
	                                                          description = "the user/group key (not e-mail address) of your user (or you)",
	                                                          baseType = "STRING", aspects = { "defaultValue:" }) String userID,
								  @ThingworxServiceParameter( name = "message",
								                              description = "your message",
								                              baseType = "STRING",
								                              aspects = { "defaultValue:" }) String message) throws Exception {
		String status = pushover.push(apiToken, userID, message);
		return status;
	}
	
	public String PushMessageWithTitleAndURL( @ThingworxServiceParameter( name = "user",
            															  description = "the user/group key (not e-mail address) of your user (or you)",
            															  baseType = "STRING", aspects = { "defaultValue:" }) String userID,
            								  @ThingworxServiceParameter( name = "message",
            															  description = "your message",
            															  baseType = "STRING",
            															  aspects = { "defaultValue:" }) String message) throws Exception {
			String status = pushover.push(apiToken, userID, message);
			return status;
	}
}
