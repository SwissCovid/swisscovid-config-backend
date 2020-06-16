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

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;

import io.jsonwebtoken.Jwts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;


public class DPPPTConfigControllerTest extends BaseControllerTest {
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
}
