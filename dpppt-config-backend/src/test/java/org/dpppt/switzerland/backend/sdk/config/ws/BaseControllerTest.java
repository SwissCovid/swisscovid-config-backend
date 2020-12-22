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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.PublicKey;
import java.util.List;
import java.util.Map;

import org.dpppt.switzerland.backend.sdk.config.ws.filter.ResponseWrapperFilter;
import org.dpppt.switzerland.backend.sdk.config.ws.model.ConfigResponse;
import org.dpppt.switzerland.backend.sdk.config.ws.model.WhatToDoPositiveTestTextsCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	private void assertIsNoForceUpdate(MockHttpServletResponse result) throws Exception {
		ConfigResponse resp = objectMapper.readValue(result.getContentAsString(Charset.forName("utf-8")), ConfigResponse.class);
		assertFalse(resp.isForceUpdate());
	}
	
	private void assertThresholds(MockHttpServletResponse result) throws JsonMappingException, JsonProcessingException, UnsupportedEncodingException {
		ConfigResponse resp = objectMapper.readValue(result.getContentAsString(Charset.forName("utf-8")), ConfigResponse.class);
		assertEquals(63, (int) resp.getiOSGaenSdkConfig().getHigherThreshold());
		assertEquals(55, (int) resp.getiOSGaenSdkConfig().getLowerThreshold());
		assertEquals(63, (int) resp.getAndroidGaenSdkConfig().getHigherThreshold());
		assertEquals(55, (int) resp.getAndroidGaenSdkConfig().getLowerThreshold());
	}

	private void assertWhatToDoPositiveTestInfoBox(MockHttpServletResponse result) throws Exception {
		ConfigResponse resp =
				objectMapper.readValue(
						result.getContentAsString(Charset.forName("utf-8")), ConfigResponse.class);
		assertNotNull(resp.getWhatToDoPositiveTestTexts().getDe().getInfoBox());
		assertNotNull(resp.getWhatToDoPositiveTestTexts().getFr().getInfoBox());
		assertNotNull(resp.getWhatToDoPositiveTestTexts().getIt().getInfoBox());
		assertNotNull(resp.getWhatToDoPositiveTestTexts().getEn().getInfoBox());
		assertNotNull(resp.getWhatToDoPositiveTestTexts().getPt().getInfoBox());
		assertNotNull(resp.getWhatToDoPositiveTestTexts().getEs().getInfoBox());
		assertNotNull(resp.getWhatToDoPositiveTestTexts().getSq().getInfoBox());
		assertNotNull(resp.getWhatToDoPositiveTestTexts().getBs().getInfoBox());
		assertNotNull(resp.getWhatToDoPositiveTestTexts().getHr().getInfoBox());
		assertNotNull(resp.getWhatToDoPositiveTestTexts().getSr().getInfoBox());
		assertNotNull(resp.getWhatToDoPositiveTestTexts().getRm().getInfoBox());
		assertNotNull(resp.getWhatToDoPositiveTestTexts().getTr().getInfoBox());
		assertNotNull(resp.getWhatToDoPositiveTestTexts().getTi().getInfoBox());
	}

	private void assertEnterCovidcodeBoxText(MockHttpServletResponse result) throws Exception {
		ConfigResponse resp =
				objectMapper.readValue(
						result.getContentAsString(Charset.forName("utf-8")), ConfigResponse.class);
		assertNotEquals("", resp.getWhatToDoPositiveTestTexts().getDe().getEnterCovidcodeBoxText());
		assertNotEquals("", resp.getWhatToDoPositiveTestTexts().getFr().getEnterCovidcodeBoxText());
		assertNotEquals("", resp.getWhatToDoPositiveTestTexts().getIt().getEnterCovidcodeBoxText());
		assertNotEquals("", resp.getWhatToDoPositiveTestTexts().getEn().getEnterCovidcodeBoxText());
		assertNotEquals("", resp.getWhatToDoPositiveTestTexts().getPt().getEnterCovidcodeBoxText());
		assertNotEquals("", resp.getWhatToDoPositiveTestTexts().getEs().getEnterCovidcodeBoxText());
		assertNotEquals("", resp.getWhatToDoPositiveTestTexts().getSq().getEnterCovidcodeBoxText());
		assertNotEquals("", resp.getWhatToDoPositiveTestTexts().getBs().getEnterCovidcodeBoxText());
		assertNotEquals("", resp.getWhatToDoPositiveTestTexts().getHr().getEnterCovidcodeBoxText());
		assertNotEquals("", resp.getWhatToDoPositiveTestTexts().getSr().getEnterCovidcodeBoxText());
		assertNotEquals("", resp.getWhatToDoPositiveTestTexts().getRm().getEnterCovidcodeBoxText());
		assertNotEquals("", resp.getWhatToDoPositiveTestTexts().getTr().getEnterCovidcodeBoxText());
		assertNotEquals("", resp.getWhatToDoPositiveTestTexts().getTi().getEnterCovidcodeBoxText());
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
				get("/v1/config").param("osversion", "ios12").param("appversion", "ios-1.0.9").param("buildnr", "ios-2020.0145asdfa34"))
				.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
			assertTestNoUpdate(result);
		result = mockMvc.perform(
				get("/v1/config").param("osversion", "ios12").param("appversion", "android-1.0.1").param("buildnr", "ios-2020.0145asdfa34"))
				.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
			assertTestNoUpdate(result);
		result = mockMvc.perform(
			get("/v1/config").param("osversion", "ios12").param("appversion", "ios-1.0.8").param("buildnr", "ios-2020.0145asdfa34"))
			.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
			assertTestNormalUpdate(result);
		result = mockMvc.perform(
			get("/v1/config").param("osversion", "ios12").param("appversion", "android-1.0").param("buildnr", "ios-2020.0145asdfa34"))
			.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
			assertTestNoUpdate(result);
		result = mockMvc.perform(
			get("/v1/config").param("osversion", "ios12").param("appversion", "1.0.3").param("buildnr", "ios-2020.0145asdfa34"))
			.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
			assertTestNoUpdate(result);
		result = mockMvc.perform(
			get("/v1/config").param("osversion", "ios12").param("appversion", "android-1.0.4").param("buildnr", "ios-2020.0145asdfa34"))
			.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
			assertTestNoUpdate(result);
		result = mockMvc.perform(
			get("/v1/config").param("osversion", "ios12").param("appversion", "1.0.5").param("buildnr", "ios-2020.0145asdfa34"))
			.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
			assertTestNoUpdate(result);
		result = mockMvc.perform(
			get("/v1/config").param("osversion", "ios12").param("appversion", "ios-1.0.6").param("buildnr", "ios-2020.0145asdfa34"))
			.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
			assertTestNormalUpdate(result);
		
		result = mockMvc.perform(
				get("/v1/config").param("osversion", "ios12").param("appversion", "ios-1.0.7").param("buildnr", "ios-2020.0145asdfa34"))
				.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
			assertTestNormalUpdate(result);				
	
		result = mockMvc.perform(
				get("/v1/config").param("osversion", "ios13.7").param("appversion", "ios-1.0.9").param("buildnr", "ios-2020.0145asdfa34"))
				.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
			assertTestNoUpdate(result);
		result = mockMvc.perform(
					get("/v1/config").param("osversion", "ios14").param("appversion", "ios-1.0.9").param("buildnr", "ios-2020.0145asdfa34"))
					.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
				assertTestNoUpdate(result);			
	}
	
	@Test
	public void testNoForceUpdate() throws Exception {
		MockHttpServletResponse result = mockMvc.perform(
				get("/v1/config").param("osversion", "ios14.0").param("appversion", "ios-1.0.8").param("buildnr", "ios-2020.0145asdfa34"))
				.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
			assertIsNoForceUpdate(result);	
			
		result = mockMvc.perform(
					get("/v1/config").param("osversion", "ios13.7").param("appversion", "ios-1.0.7").param("buildnr", "ios-2020.0145asdfa34"))
					.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
				assertIsNoForceUpdate(result);			
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
				get("/v1/config").param("osversion", "ios12").param("appversion", "1.0.7").param("buildnr", "ios-200521.2320.80"))
				.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
		assertTestNoUpdate(result);
	}
	
	@Test
	public void testThreshold() throws Exception {
		MockHttpServletResponse result = mockMvc.perform(
				get("/v1/config").param("osversion", "ios12").param("appversion", "ios-1.0.9").param("buildnr", "ios-2020.0145asdfa34"))
				.andExpect(status().is2xxSuccessful()).andReturn().getResponse();
		
		assertThresholds(result);
	}

	@Test
	public void testWhatToDoPositiveTestTextLanguage() throws Exception {
		MockHttpServletResponse result = mockMvc.perform(
				get("/v1/config").param("osversion", "ios12").param("appversion", "ios-1.0.9").param("buildnr", "ios-2020.0145asdfa34"))
				.andExpect(status().is2xxSuccessful()).andReturn().getResponse();

		ConfigResponse resp = objectMapper.readValue(result.getContentAsString(Charset.forName("utf-8")), ConfigResponse.class);

		WhatToDoPositiveTestTextsCollection whatToDoPositiveTestTexts = resp.getWhatToDoPositiveTestTexts();
		assertEquals(
				"Mit dem Covidcode...",
				whatToDoPositiveTestTexts.getDe().getEnterCovidcodeBoxSupertitle());
		assertEquals(
				"Avec le code COVID…",
				whatToDoPositiveTestTexts.getFr().getEnterCovidcodeBoxSupertitle());
		assertEquals(
				"Con il codice Covid...",
				whatToDoPositiveTestTexts.getIt().getEnterCovidcodeBoxSupertitle());
		assertEquals(
				"With the Covidcode",
				whatToDoPositiveTestTexts.getEn().getEnterCovidcodeBoxSupertitle());
		assertEquals(
				"Com o código COVID...",
				whatToDoPositiveTestTexts.getPt().getEnterCovidcodeBoxSupertitle());
		assertEquals(
				"Con el código Covid…",
				whatToDoPositiveTestTexts.getEs().getEnterCovidcodeBoxSupertitle());
		assertEquals(
				"Me kodin Covid...",
				whatToDoPositiveTestTexts.getSq().getEnterCovidcodeBoxSupertitle());
		assertEquals(
				"Sa Covid šifrom...",
				whatToDoPositiveTestTexts.getBs().getEnterCovidcodeBoxSupertitle());
		assertEquals(
				"Sa Covid šifrom...",
				whatToDoPositiveTestTexts.getHr().getEnterCovidcodeBoxSupertitle());
		assertEquals(
				"Sa Covid šifrom...",
				whatToDoPositiveTestTexts.getSr().getEnterCovidcodeBoxSupertitle());
		assertEquals(
				"Cun il code covid...",
				whatToDoPositiveTestTexts.getRm().getEnterCovidcodeBoxSupertitle());
		assertEquals(
				"Kovid kodu ile...",
				whatToDoPositiveTestTexts.getTr().getEnterCovidcodeBoxSupertitle());
		assertEquals("ብኮቪድኮድ", whatToDoPositiveTestTexts.getTi().getEnterCovidcodeBoxSupertitle());
	}

	@Test
	public void testiOSInfoBoxWorkaround() throws Exception {
		MockHttpServletResponse result =
				mockMvc
						.perform(
								get("/v1/config")
										.param("osversion", "ios14.0")
										.param("appversion", "ios-1.1.2")
										.param("buildnr", "ios-2020.0145asdfa34"))
						.andExpect(status().is2xxSuccessful())
						.andReturn()
						.getResponse();
		assertWhatToDoPositiveTestInfoBox(result);

		result =
				mockMvc
						.perform(
								get("/v1/config")
										.param("osversion", "ios13.7")
										.param("appversion", "ios-1.1.1")
										.param("buildnr", "ios-2020.0145asdfa34"))
						.andExpect(status().is2xxSuccessful())
						.andReturn()
						.getResponse();
		assertEnterCovidcodeBoxText(result);
	}
	
	@Test
	public void testTestLocations() throws Exception {
		MockHttpServletResponse result = mockMvc
				.perform(get("/v1/config").param("osversion", "ios14.2").param("appversion", "ios-1.1.2")
						.param("buildnr", "ios-2020.0145asdfa34"))
				.andExpect(status().is2xxSuccessful()).andReturn().getResponse();

		ConfigResponse resp = objectMapper.readValue(result.getContentAsString(Charset.forName("utf-8")),
				ConfigResponse.class);
		// all cantons plus liechtenstein
		assertEquals(27, resp.getTestLocations().getBs().size());
		assertEquals(27, resp.getTestLocations().getDe().size());
		assertEquals(27, resp.getTestLocations().getEn().size());
		assertEquals(27, resp.getTestLocations().getEs().size());
		assertEquals(27, resp.getTestLocations().getFr().size());
		assertEquals(27, resp.getTestLocations().getHr().size());
		assertEquals(27, resp.getTestLocations().getIt().size());
		assertEquals(27, resp.getTestLocations().getPt().size());
		assertEquals(27, resp.getTestLocations().getRm().size());
		assertEquals(27, resp.getTestLocations().getSq().size());
		assertEquals(27, resp.getTestLocations().getSr().size());
		assertEquals(27, resp.getTestLocations().getTi().size());
		assertEquals(27, resp.getTestLocations().getTr().size());

		List<String> allCantonsAndLiechtenstein = List.of("canton_aargau", "canton_appenzell_ausserrhoden",
				"canton_appenzell_innerrhoden", "canton_basel_country", "canton_basel_city", "canton_berne",
				"canton_fribourg", "canton_geneva", "canton_glarus", "canton_graubuenden", "canton_jura",
				"canton_lucerne", "canton_neuchatel", "canton_nidwalden", "canton_obwalden", "canton_st_gallen",
				"canton_schaffhausen", "canton_schwyz", "canton_solothurn", "canton_thurgovia", "canton_ticino",
				"canton_uri", "canton_valais", "canton_vaud", "canton_zug", "canton_zurich", "country_liechtenstein");

		// check if all keys are included in order
		for (int i = 0; i < allCantonsAndLiechtenstein.size(); i++) {
			assertEquals(resp.getTestLocations().getRm().get(i).getRegion(), allCantonsAndLiechtenstein.get(i));
		}
	}

}
