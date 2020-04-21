/*
 * Created by Ubique Innovation AG
 * https://www.ubique.ch
 * Copyright (c) 2020. All rights reserved.
 */

package org.dpppt.backend.sdk.config.ws.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@Profile("dev")
public class WSDevConfig extends WSBaseConfig {

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

	}

}
