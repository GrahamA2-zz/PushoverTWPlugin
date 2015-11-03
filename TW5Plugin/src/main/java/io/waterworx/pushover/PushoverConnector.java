package io.waterworx.pushover;

import push.AppInjector;

import java.util.Date;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.thingworx.metadata.annotations.ThingworxConfigurationTableDefinitions;
import com.thingworx.metadata.annotations.ThingworxServiceDefinition;
import com.thingworx.metadata.annotations.ThingworxServiceParameter;
import com.thingworx.metadata.annotations.ThingworxServiceResult;
import com.thingworx.things.Thing;

//@formatter:off
@ThingworxConfigurationTableDefinitions(tables = {
		@com.thingworx.metadata.annotations.ThingworxConfigurationTableDefinition(name = "PushoverConnector", description = "Pushover Application Connection Parameters", isMultiRow = false, ordinal = 0, dataShape = @com.thingworx.metadata.annotations.ThingworxDataShapeDefinition(fields = {
				@com.thingworx.metadata.annotations.ThingworxFieldDefinition(ordinal = 0, name = "APIToken", description = "your application's API token", baseType = "STRING", aspects = {
						"defaultValue:your.token", "friendlyName:API Token" }),

		}) ) })

public class PushoverConnector extends Thing {

	private static final long serialVersionUID = 1L;
	private final PushoverService pushover;
	private String apiToken = "";

	public PushoverConnector() {
		Injector injector = Guice.createInjector(new AppInjector());
		pushover = injector.getInstance(PushoverService.class);
	}

	@Override
	protected void initializeThing() throws Exception {
		// May need to move pushover here
		this.apiToken = ((String) getConfigurationData().getValue("PushoverConnector", "APIToken"));
	}

	@ThingworxServiceDefinition(name = "PushMessageShort", description = "Push a notification to a user or group")
	@ThingworxServiceResult(name = "Result", description = "Result", baseType = "STRING")
	public String PushMessageShort(
			@ThingworxServiceParameter(name = "user", description = "the user/group key (not e-mail address) of your user (or you)", baseType = "STRING", aspects = {
					"defaultValue:" }) String userID,
			@ThingworxServiceParameter(name = "message", description = "your message", baseType = "STRING", aspects = {
					"defaultValue:" }) String message) throws Exception {

		String status = pushover.push(apiToken, userID, message);
		return status;
	}

	@ThingworxServiceDefinition(name = "PushMessageLong", description = "Push a notification to a user or group includeing a title and URL")
	@ThingworxServiceResult(name = "Result", description = "Result", baseType = "STRING")
	public String PushMessageLong(
			@ThingworxServiceParameter(name = "user", description = "the user/group key (not e-mail address) of your user (or you)", baseType = "STRING", aspects = {
					"defaultValue:" }) String userID,
			@ThingworxServiceParameter(name = "message", description = "Your message", baseType = "STRING", aspects = {
					"defaultValue:" }) String message,
			@ThingworxServiceParameter(name = "device", description = "Your user's device name to send the message directly to that device, rather than all of the user's devices (multiple devices may be separated by a comma)", baseType = "STRING", aspects = {
					"defaultValue:" }) String device,
			@ThingworxServiceParameter(name = "title", description = "Your message's title, otherwise your app's name is used", baseType = "STRING", aspects = {
					"defaultValue:" }) String title,
			@ThingworxServiceParameter(name = "url", description = "A supplementary URL to show with your message", baseType = "STRING", aspects = {
					"defaultValue:" }) String url,
			@ThingworxServiceParameter(name = "url_title", description = "A title for your supplementary URL, otherwise just the URL is shown", baseType = "STRING", aspects = {
					"defaultValue:" }) String url_title,
			@ThingworxServiceParameter(name = "priority", description = "Send as -2 to generate no notification/alert, -1 to always send as a quiet notification, 1 to display as high-priority and bypass the user's quiet hours, or 2 to also require confirmation from the user", baseType = "STRING", aspects = {
					"defaultValue:", "0" }) String priority,
			@ThingworxServiceParameter(name = "timestamp", description = "A Unix timestamp of your message's date and time to display to the user, rather than the time your message is received by our API", baseType = "STRING", aspects = {
					"defaultValue:" }) String timestamp,
			@ThingworxServiceParameter(name = "sound", description = "The name of one of the sounds supported by device clients to override the user's default sound choice", baseType = "STRING", aspects = {
					"defaultValue:", "pushover" }) String sound) throws Exception {
		Integer vPriorty = Integer.parseInt(priority);
		Long vTimestamp = Long.parseLong(timestamp);
		if (vTimestamp == 0) {
			Date now = new Date();
			vTimestamp = new Long(now.getTime() / 1000);
		}
		String status = pushover.push(apiToken, userID, message,device,title,url,url_title,vPriorty,vTimestamp,sound);
		return status;
	}
}
// @formatter:on
