package io.waterworx.pushover;

import push.AppInjector;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.thingworx.metadata.annotations.ThingworxConfigurationTableDefinitions;
import com.thingworx.metadata.annotations.ThingworxServiceDefinition;
import com.thingworx.metadata.annotations.ThingworxServiceParameter;
import com.thingworx.metadata.annotations.ThingworxServiceResult;
import com.thingworx.things.Thing;

//@formatter:off
@ThingworxConfigurationTableDefinitions( tables = { @com.thingworx.metadata.annotations.ThingworxConfigurationTableDefinition(
				name = "ConnectionInfo",
				description = "Pushover Connector Application Parameters", 
				isMultiRow = false,
				ordinal = 0,
				dataShape = @com.thingworx.metadata.annotations.ThingworxDataShapeDefinition(
						fields = { @com.thingworx.metadata.annotations.ThingworxFieldDefinition( ordinal = 0,
																						 		 name = "apiToken",
																								 description = "Application API Token/Key",
																								 baseType = "STRING", 
																								 aspects = { "defaultValue:", "friendlyName:Application Token" })
}))})


public class PushoverConnector extends Thing{

	private static final long serialVersionUID = 1L;
	private final PushoverService pushover;
	private String apiToken = "";
	
	public PushoverConnector(){
		Injector injector = Guice.createInjector(new AppInjector());
		pushover = injector.getInstance(PushoverService.class);
	}
	
	@Override
	protected void initializeThing() throws Exception {
		//May need to move pushover here 
		this.apiToken = ((String) getConfigurationData().getValue("APIToken", "PushoverConnector"));
	}
	
	@ThingworxServiceDefinition(name = "PushMessage", description = "Push a notification to a user or group")
	@ThingworxServiceResult(name = "Result", description = "Result", baseType = "STRING")
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
	
	@ThingworxServiceDefinition(name = "PushMessageWithTitleAndURL", description = "Push a notification to a user or group includeing a title and URL")
	@ThingworxServiceResult(name = "Result", description = "Result", baseType = "STRING")
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
//@formatter:on
