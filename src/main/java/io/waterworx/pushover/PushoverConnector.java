package io.waterworx.pushover;

import push.AppInjector;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.thingworx.things.Thing;

public class PushoverConnector extends Thing{

	private final PushoverService pushover;
	
	public PushoverConnector(){
		Injector injector = Guice.createInjector(new AppInjector());
		pushover = injector.getInstance(PushoverService.class);
	}
	
	
	@Override
	protected void initializeThing() throws Exception {
		//May need to move pushover here 

	}
	
}
