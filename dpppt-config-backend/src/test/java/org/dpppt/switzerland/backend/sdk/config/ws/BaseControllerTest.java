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

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dpppt.switzerland.backend.sdk.config.ws.filter.ResponseWrapperFilter;
import org.dpppt.switzerland.backend.sdk.config.ws.model.ConfigResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.PublicKey;
import java.util.List;
import java.util.Map;

import io.jsonwebtoken.Jwts;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({ "cloud-dev" })
public abstract class BaseControllerTest {
	@Autowired
	protected ObjectMapper objectMapper;
	protected MockMvc mockMvc;
	@Autowired
	protected WebApplicationContext webApplicationContext;
	@Autowired
	protected ResponseWrapperFilter filter;
	protected PublicKey publicKey;


	protected String json(Object o) throws IOException {
		return objectMapper.writeValueAsString(o);
	}

	protected void assertTestNoUpdate(MockHttpServletResponse result) throws Exception {
		ConfigResponse resp = objectMapper.readValue(result.getContentAsString(Charset.forName("utf-8")), ConfigResponse.class);
		assertNull(resp.getInfoBox());
	}
	protected void assertTestNormalUpdate(MockHttpServletResponse result) throws Exception{
		ConfigResponse resp = objectMapper.readValue(result.getContentAsString(Charset.forName("utf-8")), ConfigResponse.class);
		assertNotNull(resp);
		assertNotNull(resp.getInfoBox());
		assertNotNull(resp.getInfoBox().getDeInfoBox());
		assertEquals("App-Update verfügbar", resp.getInfoBox().getDeInfoBox().getTitle());
	}
	protected void assertTestTestflightUpdate(MockHttpServletResponse result) throws Exception{
		ConfigResponse resp = objectMapper.readValue(result.getContentAsString(Charset.forName("utf-8")), ConfigResponse.class);
		assertNotNull(resp);
		assertNotNull(resp.getInfoBox());
		assertNotNull(resp.getInfoBox().getDeInfoBox());
		assertEquals("App-Update im App Store", resp.getInfoBox().getDeInfoBox().getTitle());
	}

	@Test
	public void testHello() throws Exception {
		final MockHttpServletResponse response = mockMvc.perform(get("/v1"))
				.andExpect(status().is2xxSuccessful()).andReturn().getResponse();

		assertNotNull(response);
		assertEquals("Hello from DP3T Config WS", response.getContentAsString());
	}

	@Test
	public void testGetConfig() throws Exception {
		mockMvc.perform(get("/v1/config"))
				.andExpect(status().is4xxClientError());
		final MockHttpServletResponse result = mockMvc.perform(
				get("/v1/config").param("osversion", "ios12").param("appversion", "1.0").param("buildnr", "2020.0145asdfa34"))
				.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
		Jwts.parserBuilder().setSigningKey(this.publicKey).build().parse(result.getHeader("Signature"));
	}
	private final Map<String, String> headers= Map.of("X-Content-Type-Options","nosniff", "X-Frame-Options", "DENY", "X-Xss-Protection", "1; mode=block");
	@Test
	public void testSecurityHeaders() throws Exception {
		final MockHttpServletResponse response = mockMvc.perform(get("/v1")).andExpect(status().is2xxSuccessful()).andReturn()
		.getResponse();
		for(final var header : headers.keySet()) {
			assertTrue(response.containsHeader(header));
			assertEquals(headers.get(header), response.getHeader(header));
		} 
		
	}

	@Test
	public void testForUpdateNote() throws Exception {
		MockHttpServletResponse result = mockMvc.perform(
				get("/v1/config").param("osversion", "ios12").param("appversion", "ios-1.0.0").param("buildnr", "ios-2020.0145asdfa34"))
				.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
		assertTestNormalUpdate(result);
		result = mockMvc.perform(
				get("/v1/config").param("osversion", "ios12").param("appversion", "android-1.0.1").param("buildnr", "ios-2020.0145asdfa34"))
				.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
		assertTestNormalUpdate(result);
		result = mockMvc.perform(
			get("/v1/config").param("osversion", "ios12").param("appversion", "ios-1.0.2").param("buildnr", "ios-2020.0145asdfa34"))
			.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
		assertTestNormalUpdate(result);
		result = mockMvc.perform(
			get("/v1/config").param("osversion", "ios12").param("appversion", "android-1.0").param("buildnr", "ios-2020.0145asdfa34"))
			.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
		assertTestNormalUpdate(result);
		result = mockMvc.perform(
			get("/v1/config").param("osversion", "ios12").param("appversion", "1.0.3").param("buildnr", "ios-2020.0145asdfa34"))
			.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
		assertTestNormalUpdate(result);
		result = mockMvc.perform(
			get("/v1/config").param("osversion", "ios12").param("appversion", "android-1.0.4").param("buildnr", "ios-2020.0145asdfa34"))
			.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
		assertTestNormalUpdate(result);
		result = mockMvc.perform(
			get("/v1/config").param("osversion", "ios12").param("appversion", "1.0.5").param("buildnr", "ios-2020.0145asdfa34"))
			.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
		assertTestNoUpdate(result);
		result = mockMvc.perform(
			get("/v1/config").param("osversion", "ios12").param("appversion", "ios-1.0.6").param("buildnr", "ios-2020.0145asdfa34"))
			.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
		assertTestNoUpdate(result);
	}
	@Test
	public void testForTestflight() throws Exception {
		final List<String> testflightVersions = List.of("ios-200619.2333.175", 
												"ios-200612.2347.141",
												"ios-200528.2230.100",
												"ios-200524.1316.87",
												"ios-200521.2320.79");
		for(final var buildnr : testflightVersions) {
			final MockHttpServletResponse result = mockMvc.perform(
				get("/v1/config").param("osversion", "ios12").param("appversion", "1.0.0").param("buildnr", buildnr))
				.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
			assertTestTestflightUpdate(result);
		}
		final MockHttpServletResponse result = mockMvc.perform(
				get("/v1/config").param("osversion", "ios12").param("appversion", "1.0.5").param("buildnr", "ios-200521.2320.80"))
				.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
		assertTestNoUpdate(result);
	}
}
