package push;

import io.waterworx.pushover.PushoverService;
import io.waterworx.pushover.PushoverUtils;

import com.google.inject.AbstractModule;

public class AppInjector extends AbstractModule {

	@Override
	protected void configure() {
		bind(PushoverService.class).to(PushoverUtils.class);
	}

}
