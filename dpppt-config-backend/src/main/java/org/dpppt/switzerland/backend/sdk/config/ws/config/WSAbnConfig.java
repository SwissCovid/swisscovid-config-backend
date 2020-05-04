/*
 * Copyright (c) 2020 Ubique Innovation AG <https://www.ubique.ch>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */

package org.dpppt.switzerland.backend.sdk.config.ws.config;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@Configuration
@Profile("cloud-abn")
public class WSAbnConfig extends WSBaseConfig {
    @Value("${vcap.services.ecdsa_cs_abn.credentials.privateKey}")
	private String privateKey;
	@Value("${vcap.services.ecdsa_cs_abn.credentials.publicKey}")
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