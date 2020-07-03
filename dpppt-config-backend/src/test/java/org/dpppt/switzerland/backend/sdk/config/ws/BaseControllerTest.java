/*
 * Copyright (c) 2020 Ubique Innovation AG <https://www.ubique.ch>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */

package org.dpppt.switzerland.backend.sdk.config.ws;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.dpppt.switzerland.backend.sdk.config.ws.filter.ResponseWrapperFilter;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.security.PublicKey;

import javax.servlet.Filter;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
	"ws.security.secretForVerificationCodeGeneration=1234556"
} )
@ActiveProfiles({ "cloud-dev" })
public abstract class BaseControllerTest {

	protected MockMvc mockMvc;
	protected ObjectMapper objectMapper;
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private Filter springSecurityFilterChain;

	@Autowired
	private ResponseWrapperFilter filter;
	protected PublicKey publicKey;

	@Before
	public void setup() throws Exception {
		this.publicKey = filter.getPublicKey();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(springSecurityFilterChain).addFilter(filter, "/*").build();
		this.objectMapper = new ObjectMapper(new JsonFactory());
		this.objectMapper.registerModule(new JavaTimeModule());
		this.objectMapper.registerModule(new JodaModule());
		// this makes sure, that the objectmapper does not fail, when a filter is not provided.
		this.objectMapper.setFilterProvider(new SimpleFilterProvider().setFailOnUnknownId(false));
	}

	protected String json(Object o) throws IOException {
		return objectMapper.writeValueAsString(o);
	}

}
