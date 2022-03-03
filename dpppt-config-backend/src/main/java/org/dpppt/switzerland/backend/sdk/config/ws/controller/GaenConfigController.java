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

import ch.ubique.openapi.docannotations.Documentation;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.dpppt.switzerland.backend.sdk.config.ws.helper.IOS136InfoBoxHelper;
import org.dpppt.switzerland.backend.sdk.config.ws.helper.MockHelper;
import org.dpppt.switzerland.backend.sdk.config.ws.helper.TestLocationHelper;
import org.dpppt.switzerland.backend.sdk.config.ws.helper.VaccinationInfoHelper;
import org.dpppt.switzerland.backend.sdk.config.ws.model.ConfigResponse;
import org.dpppt.switzerland.backend.sdk.config.ws.model.FaqEntry;
import org.dpppt.switzerland.backend.sdk.config.ws.model.InfoBox;
import org.dpppt.switzerland.backend.sdk.config.ws.model.InfoBoxCollection;
import org.dpppt.switzerland.backend.sdk.config.ws.model.Language;
import org.dpppt.switzerland.backend.sdk.config.ws.model.WhatToDoPositiveTestTexts;
import org.dpppt.switzerland.backend.sdk.config.ws.model.WhatToDoPositiveTestTextsCollection;
import org.dpppt.switzerland.backend.sdk.config.ws.poeditor.Messages;
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

/**
 * @CrossOrigin(origins = { "https://editor.swagger.io" }) @GetMapping(value = "")
 * public @ResponseBody String hello() { return "Hello from DP3T Config WS"; } @CrossOrigin(origins
 * = { "https://editor.swagger.io" }) @GetMapping(value = "/config") public @ResponseBody
 * ResponseEntity<ConfigResponse> getConfig( @RequestParam String osversion, @RequestParam String
 * appversion, @RequestParam String buildnr) { ConfigResponse config = new ConfigResponse(); // For
 * iOS 13.6 users with language DE show information about weekly // notification if
 * (osversion.equals(IOS_VERSION_DE_WEEKLY_NOTIFCATION_INFO)) { setInfoTextForiOS136DE(config); }
 *
 * <p>// if we have testflight builds suggest to switch to store version if
 * (TESTFLIGHT_VERSIONS.contains(buildnr)) { config = testFlightUpdate(); }
 *
 * <p>// Build nr of the initial iOS pilot test app. Contains bug, that factors are // not used
 * correctly in contact calculations. Set factorHigh to 0.0 for // improving the calculation. if
 * (buildnr.equals("ios-200524.1316.87")) { config.getiOSGaenSdkConfig().setFactorHigh(0.0d); }
 * return ResponseEntity.ok().cacheControl(CacheControl.maxAge(Duration.ofMinutes(5))).body(config);
 * } @Documentation(description = "Infobox testing endpoint", responses = {"200 => Infobox in all
 * languages"}) @CrossOrigin(origins = { "https://editor.swagger.io" }) @GetMapping(value =
 * "/testinfobox/config") public @ResponseBody ResponseEntity<ConfigResponse>
 * getGhettoboxConfig( @RequestParam String osversion, @RequestParam String
 * appversion, @RequestParam String buildnr) { ConfigResponse body =
 * mockConfigResponseWithInfoBox(); return ResponseEntity.ok(body); }
 */
@Controller
@RequestMapping("/v1")
public class GaenConfigController {

    private static final String IOS_VERSION_DE_WEEKLY_NOTIFCATION_INFO = "ios13.6";
    private static final List<String> TESTFLIGHT_VERSIONS =
            List.of(
                    "ios-200619.2333.175",
                    "ios-200612.2347.141",
                    "ios-200528.2230.100",
                    "ios-200524.1316.87",
                    "ios-200521.2320.79");
    private static final String IOS_VERSION_13_7 = "ios13.7";
    private static final String IOS_VERSION_14 = "ios14.0";
    private static final Version APP_VERSION_1_0_9 = new Version("ios-1.0.9");
    private static final Version IOS_APP_VERSION_1_1_2 = new Version("ios-1.1.2");
    private static final Version APP_VERSION_2_3_1 = new Version("ios-2.3.1");


    private static final Logger logger = LoggerFactory.getLogger(GaenConfigController.class);

    protected final Messages messages;
    private final List<String> interOpsCountryCodes;
    private final TestLocationHelper testLocationHelper;
    private final boolean checkInUpdateNotificationEnabled;
    private final VaccinationInfoHelper vaccinationInfoHelper;
    private final boolean showVaccinationInfo;
    private final boolean deactivate;

    public GaenConfigController(
            Messages messages,
            List<String> interOpsCountryCodes,
            boolean checkInUpdateNotificationEnabled,
            VaccinationInfoHelper vaccinationInfoHelper,
            boolean showVaccinationInfo,
            boolean deactivate) {
        this.messages = messages;
        this.interOpsCountryCodes = interOpsCountryCodes;
        this.testLocationHelper = new TestLocationHelper(messages);
        this.checkInUpdateNotificationEnabled = checkInUpdateNotificationEnabled;
        this.vaccinationInfoHelper = vaccinationInfoHelper;
        this.showVaccinationInfo = showVaccinationInfo;
        this.deactivate = deactivate;
    }

    @Documentation(
            description = "Echo endpoint",
            responses = {"200 => Hello from DP3T Config WS"})
    @CrossOrigin(origins = {"https://editor.swagger.io"})
    @GetMapping(value = "")
    public @ResponseBody String hello() {
        return "Hello from DP3T Config WS";
    }

    @Documentation(
            description =
                    "Read latest configuration and messages, depending on the version of the phone and the"
                            + " app.",
            responses = {
                "200 => ConfigResponse structure with eventual notifications and epidemic parameters"
            })
    @CrossOrigin(origins = {"https://editor.swagger.io"})
    @GetMapping(value = "/config")
    public @ResponseBody ResponseEntity<ConfigResponse> getConfig(
            @Documentation(description = "Version of the App installed", example = "ios-1.0.7")
                    @RequestParam
                    String appversion,
            @Documentation(description = "Version of the OS", example = "ios13.6") @RequestParam
                    String osversion,
            @Documentation(description = "Build number of the app", example = "ios-200619.2333.175")
                    @RequestParam
                    String buildnr) {
        Version userAppVersion = new Version(appversion);
        ConfigResponse config = new ConfigResponse();
        config.setCheckInUpdateNotificationEnabled(this.checkInUpdateNotificationEnabled);
        config.setInterOpsCountries(interOpsCountryCodes);
        config.setTestInformationUrls(testLocationHelper.getTestInfoUrls());
        config.setWhatToDoPositiveTestTexts(whatToDoPositiveTestTexts(userAppVersion, messages));
        config.setTestLocations(testLocationHelper.getTestLocations());

        config.setVaccinationBookingCantons(vaccinationInfoHelper.getVaccinationBookingCantons());
        config.setVaccinationBookingInfo(vaccinationInfoHelper.getVaccinationBookingInfo());
        config.setShowVaccinationInfo(showVaccinationInfo);
        config.setDeactivate(deactivate);

        //Check for version >2.3.1 (the deactivation update)
        if (userAppVersion.isLargerVersionThan(APP_VERSION_2_3_1)) {
           config.setDeactivationMessage(appDeactivationInfobox());
        } else {
           config.setInfoBox(appDeactivationInfobox());
        }

        // Work around a limitation of SwissCovid 1.1.2 on iOS which requires an InfoBox
        // to be set.
        // For this specific version, move the text above the "Enter CovidCode" button,
        // below into the
        // InfoBox if no other InfoBox is present.
        if (userAppVersion.isIOS() && IOS_APP_VERSION_1_1_2.isSameVersionAs(userAppVersion)) {
            moveEnterCovidcodeBoxTextToInfoBoxIfNecessary(
                    config.getWhatToDoPositiveTestTexts().getDe());
            moveEnterCovidcodeBoxTextToInfoBoxIfNecessary(
                    config.getWhatToDoPositiveTestTexts().getFr());
            moveEnterCovidcodeBoxTextToInfoBoxIfNecessary(
                    config.getWhatToDoPositiveTestTexts().getIt());
            moveEnterCovidcodeBoxTextToInfoBoxIfNecessary(
                    config.getWhatToDoPositiveTestTexts().getEn());
            moveEnterCovidcodeBoxTextToInfoBoxIfNecessary(
                    config.getWhatToDoPositiveTestTexts().getPt());
            moveEnterCovidcodeBoxTextToInfoBoxIfNecessary(
                    config.getWhatToDoPositiveTestTexts().getEs());
            moveEnterCovidcodeBoxTextToInfoBoxIfNecessary(
                    config.getWhatToDoPositiveTestTexts().getSq());
            moveEnterCovidcodeBoxTextToInfoBoxIfNecessary(
                    config.getWhatToDoPositiveTestTexts().getBs());
            moveEnterCovidcodeBoxTextToInfoBoxIfNecessary(
                    config.getWhatToDoPositiveTestTexts().getHr());
            moveEnterCovidcodeBoxTextToInfoBoxIfNecessary(
                    config.getWhatToDoPositiveTestTexts().getSr());
            moveEnterCovidcodeBoxTextToInfoBoxIfNecessary(
                    config.getWhatToDoPositiveTestTexts().getRm());
            moveEnterCovidcodeBoxTextToInfoBoxIfNecessary(
                    config.getWhatToDoPositiveTestTexts().getTr());
            moveEnterCovidcodeBoxTextToInfoBoxIfNecessary(
                    config.getWhatToDoPositiveTestTexts().getTi());
        }

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(Duration.ofMinutes(5)))
                .body(config);
    }

    @CrossOrigin(origins = {"https://editor.swagger.io"})
    @GetMapping(value = "/testinfobox/config")
    public @ResponseBody ResponseEntity<ConfigResponse> getGhettoboxConfig(
            @Documentation(description = "Version of the App installed", example = "ios-1.0.7")
                    @RequestParam
                    String appversion,
            @Documentation(description = "Version of the OS", example = "ios13.6") @RequestParam
                    String osversion,
            @Documentation(description = "Build number of the app", example = "ios-200619.2333.175")
                    @RequestParam
                    String buildnr) {
        ConfigResponse body = MockHelper.mockConfigResponseWithInfoBox(true, messages);
        return ResponseEntity.ok(body);
    }

    private ConfigResponse testFlightUpdate() {
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
        infoBoxes.setMsg(
                "En el futuro la aplicación dejará de estar disponible a través de Textflight.");
        infoBoxes.setTitle("Actualización de la app en el App Store");
        infoBoxes.setUrlTitle("Actualizar");
        infoBoxes.setUrl(iosURL);
        InfoBox infoBoxsq = new InfoBox();
        infoBoxsq.setMsg(
                "Në të ardhmen aplikacioni nuk do të jetë më i disponueshëm përmes Testflight.");
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

        return configResponse;
    }




    private InfoBoxCollection appDeactivationInfobox() {
        InfoBox infoBoxde = new InfoBox();
        infoBoxde.setMsg(
                "PLACEHOLDER app wird denn abgstellt im fall");
        infoBoxde.setTitle("PLACEHOLDER COVID IS NO MORE");
        infoBoxde.setIsDismissible(false);

        InfoBox infoBoxfr = new InfoBox();
        infoBoxfr.setMsg(
                "PLACEHOLDER TEXT FR");
        infoBoxfr.setTitle("PLACEHOLDER TITLE FR");
        infoBoxfr.setIsDismissible(false);

        InfoBox infoBoxit = new InfoBox();
        infoBoxit.setMsg(
                "PLACEHOLDER TEXT IT");
        infoBoxit.setTitle("PLACEHOLDER TITLE IT");
        infoBoxit.setIsDismissible(false);

        InfoBox infoBoxen = new InfoBox();
        infoBoxen.setMsg(
                "PLACEHOLDER TEXT EN");
        infoBoxen.setTitle("PLACEHOLDER TITLE EN");
        infoBoxen.setIsDismissible(false);

        InfoBox infoBoxpt = new InfoBox();
        infoBoxpt.setMsg(
                "PLACEHOLDER TEXT");
        infoBoxpt.setTitle("PLACEHOLDER TITLE");
        infoBoxpt.setIsDismissible(false);

        InfoBox infoBoxes = new InfoBox();
        infoBoxes.setMsg(
                "PLACEHOLDER TEXT");
        infoBoxes.setTitle("PLACEHOLDER TITLE");
        infoBoxes.setIsDismissible(false);

        InfoBox infoBoxsq = new InfoBox();
        infoBoxsq.setMsg(
                "PLACEHOLDER TEXT");
        infoBoxsq.setTitle("PLACEHOLDER TITLE");
        infoBoxsq.setIsDismissible(false);

        InfoBox infoBoxbs = new InfoBox();
        infoBoxbs.setMsg(
                "PLACEHOLDER TEXT");
        infoBoxbs.setTitle("PLACEHOLDER TITLE");
        infoBoxbs.setIsDismissible(false);

        InfoBox infoBoxhr = new InfoBox();
        infoBoxhr.setMsg(
                "PLACEHOLDER TEXT");
        infoBoxhr.setTitle("PLACEHOLDER TITLE");
        infoBoxhr.setIsDismissible(false);

        InfoBox infoBoxrm = new InfoBox();
        infoBoxrm.setMsg(
                "PLACEHOLDER TEXT");
        infoBoxrm.setTitle("PLACEHOLDER TITLE");
        infoBoxrm.setIsDismissible(false);

        InfoBox infoBoxsr = new InfoBox();
        infoBoxsr.setMsg(
                "PLACEHOLDER TEXT");
        infoBoxsr.setTitle("PLACEHOLDER TITLE");
        infoBoxsr.setIsDismissible(false);

        InfoBox infoBoxtr = new InfoBox();
        infoBoxtr.setMsg(
                "PLACEHOLDER TEXT");
        infoBoxtr.setTitle("PLACEHOLDER TITLE");
        infoBoxtr.setIsDismissible(false);

        InfoBox infoBoxti = new InfoBox();
        infoBoxti.setMsg(
                "PLACEHOLDER TEXT");
        infoBoxti.setTitle("PLACEHOLDER TITLE");
        infoBoxti.setIsDismissible(false);

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
        collection.setTiInfoBox(infoBoxti);
        collection.setTrInfoBox(infoBoxtr);

        return collection;
    }



    private ConfigResponse generalUpdateRelease(boolean isIos) {
        ConfigResponse configResponse = new ConfigResponse();
        String appstoreUrl =
                isIos
                        ? "https://apps.apple.com/ch/app/id1509275381"
                        : "https://play.google.com/store/apps/details?id=ch.admin.bag.dp3t";

        String store = isIos ? "App Store" : "Play Store";
        String storeFr = isIos ? "l'App Store" : "le Play Store";
        String storeRm = isIos ? "da l'App Store" : "dal Play Store";

        InfoBox infoBoxde = new InfoBox();
        infoBoxde.setMsg(
                "Es ist eine neuere Version von SwissCovid verfügbar. Um die bestmögliche Funktionsweise der App zu erhalten, laden Sie die neuste Version vom "
                        + store);
        infoBoxde.setTitle("App-Update verfügbar");
        infoBoxde.setUrlTitle("Aktualisieren");
        infoBoxde.setUrl(appstoreUrl);
        infoBoxde.setIsDismissible(false);

        InfoBox infoBoxfr = new InfoBox();
        infoBoxfr.setMsg(
                "Une nouvelle version de SwissCovid est disponible. Afin que l'application fonctionne au mieux, téléchargez la dernière version sur "
                        + storeFr);
        infoBoxfr.setTitle("Mise à jour disponible");
        infoBoxfr.setUrlTitle("Mettre à jour");
        infoBoxfr.setUrl(appstoreUrl);
        infoBoxfr.setIsDismissible(false);

        InfoBox infoBoxit = new InfoBox();
        infoBoxit.setMsg(
                "È disponibile una versione più recente di SwissCovid. Per ottimizzare la funzionalità dell'app, scarica l'ultima versione da "
                        + store);
        infoBoxit.setTitle("È disponibile un aggiornamento dell'app");
        infoBoxit.setUrlTitle("Aggiorna");
        infoBoxit.setUrl(appstoreUrl);
        infoBoxit.setIsDismissible(false);

        InfoBox infoBoxen = new InfoBox();
        infoBoxen.setMsg(
                "An updated version of SwissCovid is available. To guarantee the app works as well as possible, download the latest version from the "
                        + store);
        infoBoxen.setTitle("App update available");
        infoBoxen.setUrlTitle("Update");
        infoBoxen.setUrl(appstoreUrl);
        infoBoxen.setIsDismissible(false);

        InfoBox infoBoxpt = new InfoBox();
        infoBoxpt.setMsg(
                "Está disponível uma nova versão da SwissCovid. Para que a app trabalhe com toda a eficiência, carregue a versão mais recente a partir da "
                        + store);
        infoBoxpt.setTitle("Atualização da app disponível");
        infoBoxpt.setUrlTitle("Atualizar");
        infoBoxpt.setUrl(appstoreUrl);
        infoBoxpt.setIsDismissible(false);

        InfoBox infoBoxes = new InfoBox();
        infoBoxes.setMsg(
                "Hay una nueva versión de SwissCovid disponible. Para garantizar el mejor funcionamiento posible, descargue siempre la versión más nueva en el "
                        + store);
        infoBoxes.setTitle("Actualización de la app disponible");
        infoBoxes.setUrlTitle("Actualizar");
        infoBoxes.setUrl(appstoreUrl);
        infoBoxes.setIsDismissible(false);

        InfoBox infoBoxsq = new InfoBox();
        infoBoxsq.setMsg(
                "Është i disponueshëm një version i ri nga SwissCovid. Për të marrë mënyrën më të mirë të mundshme të funksionit të aplikacionit, ngarkoni versionin më të ri nga "
                        + store);
        infoBoxsq.setTitle("Update i aplikacionit i disponueshëm");
        infoBoxsq.setUrlTitle("Përditësimi");
        infoBoxsq.setUrl(appstoreUrl);
        infoBoxsq.setIsDismissible(false);

        InfoBox infoBoxbs = new InfoBox();
        infoBoxbs.setMsg(
                "Dostupna je novija verzija aplikacije SwissCovid. Da biste održavali najbolju moguću funkcionalnost aplikacije, preuzmite najnoviju verziju iz trgovine aplikacijama "
                        + store);
        infoBoxbs.setTitle("Dostupno ažuriranje aplikacije");
        infoBoxbs.setUrlTitle("Ažuriraj");
        infoBoxbs.setUrl(appstoreUrl);
        infoBoxbs.setIsDismissible(false);

        InfoBox infoBoxhr = new InfoBox();
        infoBoxhr.setMsg(
                "Dostupna je novija verzija aplikacije SwissCovid. Da biste održavali najbolju moguću funkcionalnost aplikacije, preuzmite najnoviju verziju iz trgovine aplikacijama "
                        + store);
        infoBoxhr.setTitle("Dostupno ažuriranje aplikacije");
        infoBoxhr.setUrlTitle("Ažuriraj");
        infoBoxhr.setUrl(appstoreUrl);
        infoBoxhr.setIsDismissible(false);

        InfoBox infoBoxrm = new InfoBox();
        infoBoxrm.setMsg(
                "Ina versiun pli nova da SwissCovid è disponibla. Chargiai giu l'ultima versiun "
                        + storeRm
                        + ", per che l'app funcziunia il meglier pussaivel.");
        infoBoxrm.setTitle("Actualisaziun da l'app è disponibla");
        infoBoxrm.setUrlTitle("Actualisar");
        infoBoxrm.setUrl(appstoreUrl);
        infoBoxrm.setIsDismissible(false);

        InfoBox infoBoxsr = new InfoBox();
        infoBoxsr.setMsg(
                "Dostupna je novija verzija aplikacije SwissCovid. Da biste održavali najbolju moguću funkcionalnost aplikacije, preuzmite najnoviju verziju iz trgovine aplikacijama "
                        + store);
        infoBoxsr.setTitle("Dostupno ažuriranje aplikacije");
        infoBoxsr.setUrlTitle("Ažuriraj");
        infoBoxsr.setUrl(appstoreUrl);
        infoBoxsr.setIsDismissible(false);

        InfoBox infoBoxtr = new InfoBox();
        infoBoxtr.setMsg(
                "SwissCovid uygulamasının yeni sürümü bulunuyor. Uygulamayı en iyi şekilde kullanabilmek için AppStore'dan uygulamanın son sürümünü yükleyin.");
        infoBoxtr.setTitle("Güncelleştirme mevcut");
        infoBoxtr.setUrlTitle("Güncelle");
        infoBoxtr.setUrl(appstoreUrl);
        infoBoxtr.setIsDismissible(false);

        InfoBox infoBoxti = new InfoBox();
        infoBoxti.setMsg(
                "ሓድሽ ቨርዝዮን ናይ SwissCovid ተቐሪቡ። ዝበለጸ ኣሰራርሓ ናይቲ ኤፕ መታን ክወሃበኩም፣ እቲ ሓድሽ ቨርዝዮን ካብ AppStore ብዳውንሎድ ኣምጽኡ ኢኹም።");
        infoBoxti.setTitle("ሓድሽ ኤፕ-ኣፕደይት ኣሎ");
        infoBoxti.setUrlTitle("ምምሕዳስ");
        infoBoxti.setUrl(appstoreUrl);
        infoBoxti.setIsDismissible(false);

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
        collection.setTiInfoBox(infoBoxti);
        collection.setTrInfoBox(infoBoxtr);

        configResponse.setInfoBox(collection);

        return configResponse;
    }

    private void moveEnterCovidcodeBoxTextToInfoBoxIfNecessary(WhatToDoPositiveTestTexts texts) {
        if (texts.getInfoBox() == null) {
            texts.setInfoBox(
                    new InfoBox() {
                        {
                            setTitle("");
                            setMsg(texts.getEnterCovidcodeBoxText());
                            setIsDismissible(false);
                        }
                    });
            texts.setEnterCovidcodeBoxText("");
        }
    }

    private WhatToDoPositiveTestTextsCollection whatToDoPositiveTestTexts(
            Version appVersion, Messages messages) {
        final String prefix;
        if (appVersion.isSmallerVersionThan(new Version("2.0.0"))) {
            prefix = "v1_legacy_";
        } else {
            prefix = "";
        }

        return new WhatToDoPositiveTestTextsCollection() {
            {
                setDe(getWhatToDoPositiveTestText(messages, Language.DE.toLocale(), prefix));
                setFr(getWhatToDoPositiveTestText(messages, Language.FR.toLocale(), prefix));
                setIt(getWhatToDoPositiveTestText(messages, Language.IT.toLocale(), prefix));
                setEn(getWhatToDoPositiveTestText(messages, Language.EN.toLocale(), prefix));
                setPt(getWhatToDoPositiveTestText(messages, Language.PT.toLocale(), prefix));
                setEs(getWhatToDoPositiveTestText(messages, Language.ES.toLocale(), prefix));
                setSq(getWhatToDoPositiveTestText(messages, Language.SQ.toLocale(), prefix));
                setBs(getWhatToDoPositiveTestText(messages, Language.BS.toLocale(), prefix));
                setHr(getWhatToDoPositiveTestText(messages, Language.HR.toLocale(), prefix));
                setSr(getWhatToDoPositiveTestText(messages, Language.SR.toLocale(), prefix));
                setRm(getWhatToDoPositiveTestText(messages, Language.RM.toLocale(), prefix));
                setTr(getWhatToDoPositiveTestText(messages, Language.TR.toLocale(), prefix));
                setTi(getWhatToDoPositiveTestText(messages, Language.TI.toLocale(), prefix));
            }
        };
    }

    private WhatToDoPositiveTestTexts getWhatToDoPositiveTestText(
            Messages messages, Locale locale, String prefix) {
        return new WhatToDoPositiveTestTexts() {
            {
                setEnterCovidcodeBoxSupertitle(
                        messages.getMessage(prefix + "inform_detail_box_subtitle", locale));
                setEnterCovidcodeBoxTitle(
                        messages.getMessage(prefix + "inform_detail_box_title", locale));
                setEnterCovidcodeBoxText(
                        messages.getMessage(prefix + "inform_detail_box_text", locale));
                setEnterCovidcodeBoxButtonTitle(
                        messages.getMessage(prefix + "inform_detail_box_button", locale));

                setInfoBox(getWhatToDoPositiveTestTextInfoBox(messages, locale, prefix));

                setFaqEntries(
                        Arrays.asList(
                                new FaqEntry() {
                                    {
                                        setTitle(
                                                messages.getMessage(
                                                        prefix + "inform_detail_faq1_title",
                                                        locale));
                                        setText(
                                                messages.getMessage(
                                                        prefix + "inform_detail_faq1_text",
                                                        locale));
                                        setIconAndroid("ic_verified_user");
                                        setIconIos("ic-verified-user");
                                    }
                                },
                                new FaqEntry() {
                                    {
                                        setTitle(
                                                messages.getMessage(
                                                        prefix + "inform_detail_faq2_title",
                                                        locale));
                                        setText(
                                                messages.getMessage(
                                                        prefix + "inform_detail_faq2_text",
                                                        locale));
                                        setIconAndroid("ic_key");
                                        setIconIos("ic-key");
                                    }
                                },
                                new FaqEntry() {
                                    {
                                        setTitle(
                                                messages.getMessage(
                                                        prefix + "inform_detail_faq3_title",
                                                        locale));
                                        setText(
                                                messages.getMessage(
                                                        prefix + "inform_detail_faq3_text",
                                                        locale));
                                        setIconAndroid("ic_person");
                                        setIconIos("ic-user");
                                    }
                                }));
            }
        };
    }

    private InfoBox getWhatToDoPositiveTestTextInfoBox(
            Messages messages, Locale locale, String prefix) {
        InfoBox infoBox = new InfoBox();
        infoBox.setTitle(messages.getMessage(prefix + "inform_detail_infobox1_title", locale));
        infoBox.setMsg(messages.getMessage(prefix + "inform_detail_infobox1_text", locale));
        infoBox.setUrlTitle(messages.getMessage(prefix + "infoline_coronavirus_number", locale));
        infoBox.setUrl(
                "tel:"
                        + messages.getMessage(prefix + "infoline_coronavirus_number", locale)
                                .replace(" ", ""));
        infoBox.setIsDismissible(false);
        infoBox.setHearingImpairedInfo(
                messages.getMessage(prefix + "hearing_impaired_info", locale));
        return infoBox;
    }
}
