/*
 * Copyright (c) 2020 Ubique Innovation AG <https://www.ubique.ch>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */

package org.dpppt.switzerland.backend.sdk.config.ws.controller;

import java.time.Duration;

import org.dpppt.switzerland.backend.sdk.config.ws.model.ConfigResponse;
import org.dpppt.switzerland.backend.sdk.config.ws.model.InfoBox;
import org.dpppt.switzerland.backend.sdk.config.ws.model.InfoBoxCollection;
import org.dpppt.switzerland.backend.sdk.config.ws.model.SDKConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/v1")
public class DPPPTConfigController {

	private static final Logger logger = LoggerFactory.getLogger(DPPPTConfigController.class);
	private final String tweakCode;

	public DPPPTConfigController(String tweakCode) {
		this.tweakCode = tweakCode;
	}

	@CrossOrigin(origins = { "https://editor.swagger.io" })
	@GetMapping(value = "")
	public @ResponseBody String hello() {
		return "Hello from DP3T Config WS";
	}

	@CrossOrigin(origins = { "https://editor.swagger.io" })
	@GetMapping(value = "/config")
	public @ResponseBody ResponseEntity<ConfigResponse> getConfig(@RequestParam(required = true) String appversion,
			@RequestParam(required = true) String osversion, @RequestParam(required = true) String buildnr) {
		ConfigResponse config = new ConfigResponse();
		// Build nr of the initial iOS pilot test app. Contains bug, that factors are
		// not used correctly in contact calculations. Set factorHigh to 0.0 for
		// improving the calculation.
		if (buildnr.equals("ios-200524.1316.87")) {
			config.getiOSGaenSdkConfig().setFactorHigh(0.0d);
		}
		config.setTweakCode(this.tweakCode);
		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(Duration.ofMinutes(5))).body(config);
	}

	@CrossOrigin(origins = { "https://editor.swagger.io" })
	@GetMapping(value = "/testinfobox/config")
	public @ResponseBody ResponseEntity<ConfigResponse> getGhettoboxConfig(
			@RequestParam(required = true) String appversion, @RequestParam(required = true) String osversion,
			@RequestParam(required = true) String buildnr) {
		ConfigResponse body = mockConfigResponseWithInfoBox();
		return ResponseEntity.ok(body);
	}

	private ConfigResponse mockConfigResponseWithInfoBox() {
		ConfigResponse configResponse = new ConfigResponse();

		InfoBox infoBoxde = new InfoBox();
		infoBoxde.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz DE");
		infoBoxde.setTitle("Hinweis DE");
		infoBoxde.setUrlTitle("Und ein externer Link DE");
		infoBoxde.setUrl("https://www.bag.admin.ch/bag/de/home.html");
		InfoBox infoBoxfr = new InfoBox();
		infoBoxfr.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz FR");
		infoBoxfr.setTitle("Hinweis FR");
		infoBoxfr.setUrlTitle("Und ein externer Link FR");
		infoBoxfr.setUrl("https://www.bag.admin.ch/bag/fr/home.html");
		InfoBox infoBoxit = new InfoBox();
		infoBoxit.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz IT");
		infoBoxit.setTitle("Hinweis IT");
		infoBoxit.setUrlTitle("Und ein externer Link IT");
		infoBoxit.setUrl("https://www.bag.admin.ch/bag/it/home.html");
		InfoBox infoBoxen = new InfoBox();
		infoBoxen.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz EN");
		infoBoxen.setTitle("Hinweis EN");
		infoBoxen.setUrlTitle("Und ein externer Link EN");
		infoBoxen.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		InfoBox infoBoxpt = new InfoBox();
		infoBoxpt.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz PT");
		infoBoxpt.setTitle("Hinweis PT");
		infoBoxpt.setUrlTitle("Und ein externer Link PT");
		infoBoxpt.setUrl("https://www.bag.admin.ch/bag/pt/home.html");
		InfoBox infoBoxes = new InfoBox();
		infoBoxes.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz ES");
		infoBoxes.setTitle("Hinweis ES");
		infoBoxes.setUrlTitle("Und ein externer Link ES");
		infoBoxes.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		InfoBox infoBoxsq = new InfoBox();
		infoBoxsq.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz SQ");
		infoBoxsq.setTitle("Hinweis SQ");
		infoBoxsq.setUrlTitle("Und ein externer Link SQ");
		infoBoxsq.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		InfoBox infoBoxbs = new InfoBox();
		infoBoxbs.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz BS");
		infoBoxbs.setTitle("Hinweis BS");
		infoBoxbs.setUrlTitle("Und ein externer Link BS");
		infoBoxbs.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		InfoBox infoBoxhr = new InfoBox();
		infoBoxhr.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz HR");
		infoBoxhr.setTitle("Hinweis HR");
		infoBoxhr.setUrlTitle("Und ein externer Link HR");
		infoBoxhr.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		InfoBox infoBoxrm = new InfoBox();
		infoBoxrm.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz RM");
		infoBoxrm.setTitle("Hinweis RM");
		infoBoxrm.setUrlTitle("Und ein externer Link RM");
		infoBoxrm.setUrl("https://www.bag.admin.ch/bag/en/home.html");
		InfoBox infoBoxsr = new InfoBox();
		infoBoxsr.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz SR");
		infoBoxsr.setTitle("Hinweis SR");
		infoBoxsr.setUrlTitle("Und ein externer Link SR");
		infoBoxsr.setUrl("https://www.bag.admin.ch/bag/en/home.html");

		InfoBoxCollection collection = new InfoBoxCollection();
		collection.setDeInfoBox(infoBoxde);
		collection.setEnInfoBox(infoBoxen);
		collection.setFrInfoBox(infoBoxfr);
		collection.setItInfoBox(infoBoxit);
		collection.setPtInfoBox(infoBoxpt);
		collection.setEsInfoBox(infoBoxes);
		collection.setSqInfoBox(infoBoxsq);
		collection.setHrInfoBox(infoBoxhr);
		collection.setBsInfoBox(infoBoxbs);
		collection.setRmInfoBox(infoBoxrm);
		collection.setSrInfoBox(infoBoxsr);
		configResponse.setInfoBox(collection);

		SDKConfig config = new SDKConfig();
		configResponse.setSdkConfig(config);
		configResponse.setTweakCode(this.tweakCode);
		return configResponse;
	}
	
	public ConfigResponse mockConfigResponseWithForceUpdate() {
		ConfigResponse configResponse = new ConfigResponse();
		configResponse.setForceUpdate(true);
		configResponse.setTweakCode(this.tweakCode);
		return configResponse;
	}
}
