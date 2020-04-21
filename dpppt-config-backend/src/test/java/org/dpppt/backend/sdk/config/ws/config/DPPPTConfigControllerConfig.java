package org.dpppt.backend.sdk.config.ws.config;

import org.dpppt.backend.sdk.config.ws.controller.DPPPTConfigController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DPPPTConfigControllerConfig {
	@Bean
	public DPPPTConfigController dppptSDKController() {
		return new DPPPTConfigController();
	}
}
