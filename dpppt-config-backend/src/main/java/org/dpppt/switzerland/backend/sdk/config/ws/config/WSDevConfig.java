/*
 * Created by Ubique Innovation AG
 * https://www.ubique.ch
 * Copyright (c) 2020. All rights reserved.
 */

package org.dpppt.switzerland.backend.sdk.config.ws.config;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@Profile("cloud-dev")
public class WSDevConfig extends WSBaseConfig {
	
	@Value("${vcap.services.ecdsa_cs_dev.credentials.privateKey}")
	private String privateKey;
	@Value("${vcap.services.ecdsa_cs_dev.credentials.publicKey}")
    public String publicKey;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

	}
    
    @Override
    String getPrivateKey() {
        return new String(Base64.getDecoder().decode(privateKey));
    }
    @Override
    String getPublicKey() {
        return new String(Base64.getDecoder().decode(publicKey));
    }

}
