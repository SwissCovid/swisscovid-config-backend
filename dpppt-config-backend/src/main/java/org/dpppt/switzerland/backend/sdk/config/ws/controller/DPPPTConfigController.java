/*
 * Created by Ubique Innovation AG
 * https://www.ubique.ch
 * Copyright (c) 2020. All rights reserved.
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

	@CrossOrigin(origins = { "https://editor.swagger.io" })
	@GetMapping(value = "")
	public @ResponseBody String hello() {
		return "Hello from DP3T Config WS";
	}

	@CrossOrigin(origins = { "https://editor.swagger.io" })
	@GetMapping(value = "/config")
	public @ResponseBody ResponseEntity<ConfigResponse> getConfig(@RequestParam(required = true) String appversion,
			@RequestParam(required = true) String osversion) {
		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(Duration.ofMinutes(30))).body(new ConfigResponse());
	}
	@CrossOrigin(origins = { "https://editor.swagger.io" })
	@GetMapping(value = "/testinfobox/config")
	public @ResponseBody ResponseEntity<ConfigResponse> getGhettoboxConfig(@RequestParam(required = true) String appversion,
			@RequestParam(required = true) String osversion) {
		ConfigResponse body = new ConfigResponse();

		InfoBox infoBoxde = new InfoBox();
		infoBoxde.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz");
		infoBoxde.setTitle("Hinweis");
		infoBoxde.setUrlTitle("Und ein externer Link");
		infoBoxde.setUrl("https://www.bag.admin.ch/");
		InfoBox infoBoxfr = new InfoBox();
		infoBoxfr.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz");
		infoBoxfr.setTitle("Hinweis");
		infoBoxfr.setUrlTitle("Und ein externer Link");
		infoBoxfr.setUrl("https://www.bag.admin.ch/");
		InfoBox infoBoxit = new InfoBox();
		infoBoxit.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz");
		infoBoxit.setTitle("Hinweis");
		infoBoxit.setUrlTitle("Und ein externer Link");
		infoBoxit.setUrl("https://www.bag.admin.ch/");
		InfoBox infoBoxen = new InfoBox();
		infoBoxen.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz");
		infoBoxen.setTitle("Hinweis");
		infoBoxen.setUrlTitle("Und ein externer Link");
		infoBoxen.setUrl("https://www.bag.admin.ch/");
		InfoBoxCollection collection = new InfoBoxCollection();
		collection.setDeInfoBox(infoBoxde);
		collection.setEnInfoBox(infoBoxen);
		collection.setFrInfoBox(infoBoxfr);
		collection.setItInfoBox(infoBoxit);
		body.setInfoBox(collection);

		SDKConfig config = new SDKConfig();
		body.setSdkConfig(config);
		
		return ResponseEntity.ok(body);
	}
}
