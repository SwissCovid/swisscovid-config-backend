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

import org.dpppt.switzerland.backend.sdk.config.ws.model.ConfigResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;

import io.jsonwebtoken.Jwts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles({"actuator-security"})
@SpringBootTest(properties = {
	"ws.monitor.prometheus.user=prometheus",
	"vcap.services.ha_prometheus_dev.credentials.password=prometheus",
	"management.endpoints.enabled-by-default=true",
	"management.endpoints.web.exposure.include=*"
 })
public class DPPPTConfigControllerTest extends BaseControllerTest {
	@Autowired
	ObjectMapper objectMapper;
	@Test
	public void testHello() throws Exception {
		MockHttpServletResponse response = mockMvc.perform(get("/v1"))
				.andExpect(status().is2xxSuccessful()).andReturn().getResponse();

		assertNotNull(response);
		assertEquals("Hello from DP3T Config WS", response.getContentAsString());
	}

	@Test
	public void testGetConfig() throws Exception {
		mockMvc.perform(get("/v1/config"))
				.andExpect(status().is4xxClientError());
		MockHttpServletResponse result = mockMvc.perform(
				get("/v1/config").param("osversion", "ios12").param("appversion", "1.0").param("buildnr", "2020.0145asdfa34"))
				.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
		Jwts.parserBuilder().setSigningKey(this.publicKey).build().parse(result.getHeader("Signature"));
	}
	private Map<String, String> headers= Map.of("X-Content-Type-Options","nosniff", "X-Frame-Options", "DENY", "X-Xss-Protection", "1; mode=block");
	@Test
	public void testSecurityHeaders() throws Exception {
		MockHttpServletResponse response = mockMvc.perform(get("/v1")).andExpect(status().is2xxSuccessful()).andReturn()
		.getResponse();
		for(var header : headers.keySet()) {
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
		List<String> testflightVersions = List.of("ios-200619.2333.175", 
												"ios-200612.2347.141",
												"ios-200528.2230.100",
												"ios-200524.1316.87",
												"ios-200521.2320.79");
		for(var buildnr : testflightVersions) {
			MockHttpServletResponse result = mockMvc.perform(
				get("/v1/config").param("osversion", "ios12").param("appversion", "1.0.0").param("buildnr", buildnr))
				.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
			assertTestTestflightUpdate(result);
		}
		MockHttpServletResponse result = mockMvc.perform(
				get("/v1/config").param("osversion", "ios12").param("appversion", "1.0.5").param("buildnr", "ios-200521.2320.80"))
				.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
		assertTestNoUpdate(result);
	}

	private void assertTestNoUpdate(MockHttpServletResponse result) throws Exception {
		ConfigResponse resp = objectMapper.readValue(result.getContentAsString(Charset.forName("utf-8")), ConfigResponse.class);
		assertNull(resp.getInfoBox());
	}
	private void assertTestNormalUpdate(MockHttpServletResponse result) throws Exception{
		ConfigResponse resp = objectMapper.readValue(result.getContentAsString(Charset.forName("utf-8")), ConfigResponse.class);
		assertNotNull(resp);
		assertNotNull(resp.getInfoBox());
		assertNotNull(resp.getInfoBox().getDeInfoBox());
		assertEquals("App-Update verfügbar", resp.getInfoBox().getDeInfoBox().getTitle());
	}
	private void assertTestTestflightUpdate(MockHttpServletResponse result) throws Exception{
		ConfigResponse resp = objectMapper.readValue(result.getContentAsString(Charset.forName("utf-8")), ConfigResponse.class);
		assertNotNull(resp);
		assertNotNull(resp.getInfoBox());
		assertNotNull(resp.getInfoBox().getDeInfoBox());
		assertEquals("App-Update im App Store", resp.getInfoBox().getDeInfoBox().getTitle());
	}

	@Test
	public void testActuatorSecurity() throws Exception {
		var response = mockMvc.perform(get("/actuator/health")).andExpect(status().is2xxSuccessful()).andReturn()
				.getResponse();
		response = mockMvc.perform(get("/actuator/loggers")).andExpect(status().is(401)).andReturn()
		.getResponse();
		response = mockMvc.perform(get("/actuator/loggers").header("Authorization", "Basic cHJvbWV0aGV1czpwcm9tZXRoZXVz")).andExpect(status().isOk()).andReturn()
		.getResponse();
		response = mockMvc.perform(get("/actuator/prometheus")).andExpect(status().is(401)).andReturn()
		.getResponse();
		response = mockMvc.perform(get("/actuator/prometheus").header("Authorization", "Basic cHJvbWV0aGV1czpwcm9tZXRoZXVz")).andExpect(status().isOk()).andReturn()
		.getResponse();
	}
}
