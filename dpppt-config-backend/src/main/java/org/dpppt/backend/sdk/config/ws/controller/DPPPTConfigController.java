package org.dpppt.backend.sdk.config.ws.controller;

import org.dpppt.backend.sdk.config.ws.model.ConfigResponse;
import org.dpppt.backend.sdk.config.ws.model.GhettoBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
		return ResponseEntity.ok(new ConfigResponse());
	}
	@CrossOrigin(origins = { "https://editor.swagger.io" })
	@GetMapping(value = "/testghettobox/config")
	public @ResponseBody ResponseEntity<ConfigResponse> getGhettoboxConfig(@RequestParam(required = true) String appversion,
			@RequestParam(required = true) String osversion) {
		ConfigResponse body = new ConfigResponse();
		GhettoBox ghettoBox = new GhettoBox();
		ghettoBox.setMsg("Hier steht ein Text. Das kann ein Hinweis sein. Je länger umso mehr Platz");
		ghettoBox.setTitle("Hinweis");
		ghettoBox.setUrlTitle("Und ein externer Link");
		ghettoBox.setUrl("https://www.bag.admin.ch/");
		body.setGhettoBox(ghettoBox);
		return ResponseEntity.ok(body);
	}
}
