package org.dpppt.switzerland.backend.sdk.config.ws.config.mock;

import java.util.List;
import org.dpppt.switzerland.backend.sdk.config.ws.controller.GaenConfigController;
import org.dpppt.switzerland.backend.sdk.config.ws.model.ConfigResponse;
import org.dpppt.switzerland.backend.sdk.config.ws.poeditor.Messages;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;

import ch.ubique.openapi.docannotations.Documentation;

@Configuration
@Profile("mock-forceupdate")
public class MockForceUpdateConfig {

	@Value("${ws.interops.countrycodes:}")
	List<String> interOpsCountryCodes;

	@Bean
	@Primary
	public GaenConfigController gaenConfigController(Messages messages) {
		return new MockForceUpdateController(messages);
	}
	
	public class MockForceUpdateController extends GaenConfigController {

		public MockForceUpdateController(Messages messages) {
			super(messages, interOpsCountryCodes, false);
		}
		
		@Override
		public @Documentation(description = "Echo endpoint", responses = "200 => Hello from DP3T Config WS") String hello() {
			return super.hello() + " (mock-forceupdate)";
		}

		@Override
		public @Documentation(description = "Read latest configuration and messages, depending on the version of the phone and the app.", responses = "200 => ConfigResponse structure with eventual notifications and epidemic parameters") ResponseEntity<ConfigResponse> getConfig(
				@Documentation(description = "Version of the App installed", example = "ios-1.0.7") String appversion,
				@Documentation(description = "Version of the OS", example = "ios13.6") String osversion,
				@Documentation(description = "Build number of the app", example = "ios-200619.2333.175") String buildnr) {
			ResponseEntity<ConfigResponse> response = super.getConfig(appversion, osversion, buildnr);
			response.getBody().setForceUpdate(true);;
			return response;
		}

	}
}
