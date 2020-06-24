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
import java.util.List;

import org.dpppt.switzerland.backend.sdk.config.ws.model.ConfigResponse;
import org.dpppt.switzerland.backend.sdk.config.ws.model.InfoBox;
import org.dpppt.switzerland.backend.sdk.config.ws.model.InfoBoxCollection;
import org.dpppt.switzerland.backend.sdk.config.ws.model.SDKConfig;
import org.dpppt.switzerland.backend.sdk.config.ws.semver.Version;
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
	private static final Version initialReleaseVersion = new Version("1.0.5");
	private static final Version latestVersion = new Version("1.0.5");
	private static final List<String> testflightVersions = List.of("ios-200619.2333.175", 
																   "ios-200612.2347.141",
																   "ios-200528.2230.100",
																   "ios-200524.1316.87",
																   "ios-200521.2320.79");

	public DPPPTConfigController() {
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
		//update message for various old builds
		var appVersion = new Version(appversion);
		if(!appVersion.isValid() || appVersion.isSmallerVersionThan(initialReleaseVersion)) {
			config = generalUpdateRelease1(appVersion.isIOS());
		}
		//if we have testflight builds suggest to switch to store version
		if(testflightVersions.contains(buildnr)) {
			config = testFlightUpdate();
		}
		// Build nr of the initial iOS pilot test app. Contains bug, that factors are
		// not used correctly in contact calculations. Set factorHigh to 0.0 for
		// improving the calculation.
		if (buildnr.equals("ios-200524.1316.87")) {
			config.getiOSGaenSdkConfig().setFactorHigh(0.0d);
		}
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

	private ConfigResponse testFlightUpdate(){
		ConfigResponse configResponse = new ConfigResponse();
		String iosURL = "https://apps.apple.com/ch/app/id1509275381";
		InfoBox infoBoxde = new InfoBox();
		infoBoxde.setMsg("Die App wird in Zukunft nicht mehr über Testflight verfügbar sein.");
		infoBoxde.setTitle("App-Update im App Store");
		infoBoxde.setUrlTitle("Aktualisieren");
		infoBoxde.setUrl(iosURL);
		InfoBox infoBoxfr = new InfoBox();
		infoBoxfr.setMsg("L'application ne sera plus disponible sur TestFlight.");
		infoBoxfr.setTitle("Mise à jour dans l'App Store");
		infoBoxfr.setUrlTitle("Mettre à jour");
		infoBoxfr.setUrl(iosURL);
		InfoBox infoBoxit = new InfoBox();
		infoBoxit.setMsg("In futuro l'app non sarà più disponibile tramite Testflight.");
		infoBoxit.setTitle("Aggiornamento dell'app nell'App Store");
		infoBoxit.setUrlTitle("Aggiorna");
		infoBoxit.setUrl(iosURL);
		InfoBox infoBoxen = new InfoBox();
		infoBoxen.setMsg("The app will no longer be available via Testflight.");
		infoBoxen.setTitle("App update in the App Store");
		infoBoxen.setUrlTitle("Update");
		infoBoxen.setUrl(iosURL);
		InfoBox infoBoxpt = new InfoBox();
		infoBoxpt.setMsg("Futuramente, a app deixará de estar disponível na Testflight.");
		infoBoxpt.setTitle("Atualização da app na App Store");
		infoBoxpt.setUrlTitle("Atualizar");
		infoBoxpt.setUrl(iosURL);
		InfoBox infoBoxes = new InfoBox();
		infoBoxes.setMsg("En el futuro la aplicación dejará de estar disponible a través de Textflight.");
		infoBoxes.setTitle("Actualización de la app en el App Store");
		infoBoxes.setUrlTitle("Actualizar");
		infoBoxes.setUrl(iosURL);
		InfoBox infoBoxsq = new InfoBox();
		infoBoxsq.setMsg("Në të ardhmen aplikacioni nuk do të jetë më i disponueshëm përmes Testflight.");
		infoBoxsq.setTitle("Update i aplikacionit në App Store");
		infoBoxsq.setUrlTitle("Përditësimi");
		infoBoxsq.setUrl(iosURL);
		InfoBox infoBoxbs = new InfoBox();
		infoBoxbs.setMsg("Aplikacija ubuduće više neće biti dostupna preko Testflight-a.");
		infoBoxbs.setTitle("Ažuriranje aplikacije u trgovini aplikacijama App Store");
		infoBoxbs.setUrlTitle("Ažuriraj");
		infoBoxbs.setUrl(iosURL);
		InfoBox infoBoxhr = new InfoBox();
		infoBoxhr.setMsg("Aplikacija ubuduće više neće biti dostupna preko Testflight-a.");
		infoBoxhr.setTitle("Ažuriranje aplikacije u trgovini aplikacijama App Store");
		infoBoxhr.setUrlTitle("Ažuriraj");
		infoBoxhr.setUrl(iosURL);
		InfoBox infoBoxrm = new InfoBox();
		infoBoxrm.setMsg("En il futur na vegn l'app betg pli ad esser disponibla via Testflight.");
		infoBoxrm.setTitle("Actualisaziun da l'app en l'App Store");
		infoBoxrm.setUrlTitle("Actualisar");
		infoBoxrm.setUrl(iosURL);
		InfoBox infoBoxsr = new InfoBox();
		infoBoxsr.setMsg("Aplikacija ubuduće više neće biti dostupna preko Testflight-a.");
		infoBoxsr.setTitle("Ažuriranje aplikacije u trgovini aplikacijama App Store");
		infoBoxsr.setUrlTitle("Ažuriraj");
		infoBoxsr.setUrl(iosURL);

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
		return configResponse;

	}
	private ConfigResponse generalUpdateRelease1(boolean isIos){
		ConfigResponse configResponse = new ConfigResponse();
		String appstoreUrl = isIos? "https://apps.apple.com/ch/app/id1509275381" : "https://play.google.com/store/apps/details?id=ch.admin.bag.dp3t";

		String store = isIos? "App Store" : "Play Store";
		String storeFr = isIos? "l'App Store" : "le Play Store";

		InfoBox infoBoxde = new InfoBox();
		infoBoxde.setMsg("Es ist eine neuere Version von SwissCovid verfügbar. Um die bestmögliche Funktionsweise der App zu erhalten, laden Sie die neuste Version vom " + store);
		infoBoxde.setTitle("App-Update verfügbar");
		infoBoxde.setUrlTitle("Aktualisieren");
		infoBoxde.setUrl(appstoreUrl);
		InfoBox infoBoxfr = new InfoBox();
		infoBoxfr.setMsg("Une nouvelle version de SwissCovid est disponible. Afin que l'application fonctionne au mieux, téléchargez la dernière version sur " + storeFr);
		infoBoxfr.setTitle("Mise à jour disponible");
		infoBoxfr.setUrlTitle("Mettre à jour");
		infoBoxfr.setUrl(appstoreUrl);
		InfoBox infoBoxit = new InfoBox();
		infoBoxit.setMsg("È disponibile una versione più recente di SwissCovid. Per ottimizzare la funzionalità dell'app, scarica l'ultima versione da " + store);
		infoBoxit.setTitle("È disponibile un aggiornamento dell'app");
		infoBoxit.setUrlTitle("Aggiorna");
		infoBoxit.setUrl(appstoreUrl);
		InfoBox infoBoxen = new InfoBox();
		infoBoxen.setMsg("An updated version of SwissCovid is available. To guarantee the app works as well as possible, download the latest version from the " + store);
		infoBoxen.setTitle("App update available");
		infoBoxen.setUrlTitle("Update");
		infoBoxen.setUrl(appstoreUrl);
		InfoBox infoBoxpt = new InfoBox();
		infoBoxpt.setMsg("Está disponível uma nova versão da SwissCovid. Para que a app trabalhe com toda a eficiência, carregue a versão mais recente a partir da " + store);
		infoBoxpt.setTitle("Atualização da app disponível");
		infoBoxpt.setUrlTitle("Atualizar");
		infoBoxpt.setUrl(appstoreUrl);
		InfoBox infoBoxes = new InfoBox();
		infoBoxes.setMsg("Hay una nueva versión de SwissCovid disponible. Para garantizar el mejor funcionamiento posible, descargue siempre la versión más nueva en el " + store);
		infoBoxes.setTitle("Actualización de la app disponible");
		infoBoxes.setUrlTitle("Actualizar");
		infoBoxes.setUrl(appstoreUrl);
		InfoBox infoBoxsq = new InfoBox();
		infoBoxsq.setMsg("Është i disponueshëm një version i ri nga SwissCovid. Për të marrë mënyrën më të mirë të mundshme të funksionit të aplikacionit, ngarkoni versionin më të ri nga " + store);
		infoBoxsq.setTitle("Update i aplikacionit i disponueshëm");
		infoBoxsq.setUrlTitle("Përditësimi");
		infoBoxsq.setUrl(appstoreUrl);
		InfoBox infoBoxbs = new InfoBox();
		infoBoxbs.setMsg("Dostupna je novija verzija aplikacije SwissCovid. Da biste održavali najbolju moguću funkcionalnost aplikacije, preuzmite najnoviju verziju iz trgovine aplikacijama " + store);
		infoBoxbs.setTitle("Dostupno ažuriranje aplikacije");
		infoBoxbs.setUrlTitle("Ažuriraj");
		infoBoxbs.setUrl(appstoreUrl);
		InfoBox infoBoxhr = new InfoBox();
		infoBoxhr.setMsg("Dostupna je novija verzija aplikacije SwissCovid. Da biste održavali najbolju moguću funkcionalnost aplikacije, preuzmite najnoviju verziju iz trgovine aplikacijama " + store);
		infoBoxhr.setTitle("Dostupno ažuriranje aplikacije");
		infoBoxhr.setUrlTitle("Ažuriraj");
		infoBoxhr.setUrl(appstoreUrl);
		InfoBox infoBoxrm = new InfoBox();
		infoBoxrm.setMsg("Ina versiun pli nova da SwissCovid è a disposiziun. Chargiai la novissima versiun da " + store + " per che l'app funcziunia il meglier pussaivel.");
		infoBoxrm.setTitle("Actualisaziun da l'app è disponibla");
		infoBoxrm.setUrlTitle("Actualisar");
		infoBoxrm.setUrl(appstoreUrl);
		InfoBox infoBoxsr = new InfoBox();
		infoBoxsr.setMsg("Dostupna je novija verzija aplikacije SwissCovid. Da biste održavali najbolju moguću funkcionalnost aplikacije, preuzmite najnoviju verziju iz trgovine aplikacijama " + store);
		infoBoxsr.setTitle("Dostupno ažuriranje aplikacije");
		infoBoxsr.setUrlTitle("Ažuriraj");
		infoBoxsr.setUrl(appstoreUrl);

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
		return configResponse;
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
		return configResponse;
	}
	
	public ConfigResponse mockConfigResponseWithForceUpdate() {
		ConfigResponse configResponse = new ConfigResponse();
		configResponse.setForceUpdate(true);
		return configResponse;
	}
}
