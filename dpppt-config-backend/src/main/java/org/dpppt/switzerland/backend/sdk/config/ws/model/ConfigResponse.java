/*
 * Copyright (c) 2020 Ubique Innovation AG <https://www.ubique.ch>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * SPDX-License-Identifier: MPL-2.0
 */

package org.dpppt.switzerland.backend.sdk.config.ws.model;

import ch.ubique.openapi.docannotations.Documentation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Documentation(description = "ConfigResponse description")
public class ConfigResponse {

    @Documentation(
            description =
                    "Blocks the app and shows a link to the app-store. The user can only continue once "
                            + "she updated the app")
    private boolean forceUpdate = false;

    @Documentation(description = "Holds a message translated in different languages")
    private InfoBoxCollection infoBox = null;

    private WhatToDoPositiveTestTextsCollection whatToDoPositiveTestTexts;

    @Documentation(description = "Holds a url for test locations for each canton and Liechtenstein")
    private TestLocationCollection testLocations;

    @Documentation(description = "GAEN epidemiological parameters for iOS")
    private GAENSDKConfig iOSGaenSdkConfig = new GAENSDKConfig();

    @Documentation(description = "GAEN epidemiological parameters for Android")
    private GAENSDKConfig androidGaenSdkConfig = new GAENSDKConfig();

    @Documentation(
            description =
                    "list of ISO 3166-1 alpha-2 country codes describing the available interops countries")
    private List<String> interOpsCountries = new ArrayList<>();

    @Documentation(description = "Localized urls to test information website")
    private Map<String, String> testInformationUrls;

    @Documentation(
            description =
                    "Flag to enable notifications informing users about the new checkin feature")
    private boolean checkInUpdateNotificationEnabled = false;

    public boolean isForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(boolean forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public InfoBoxCollection getInfoBox() {
        return infoBox;
    }

    public void setInfoBox(InfoBoxCollection infoBox) {
        this.infoBox = infoBox;
    }

    public WhatToDoPositiveTestTextsCollection getWhatToDoPositiveTestTexts() {
        return whatToDoPositiveTestTexts;
    }

    public void setWhatToDoPositiveTestTexts(
            WhatToDoPositiveTestTextsCollection whatToDoPositiveTestTexts) {
        this.whatToDoPositiveTestTexts = whatToDoPositiveTestTexts;
    }

    public GAENSDKConfig getiOSGaenSdkConfig() {
        return iOSGaenSdkConfig;
    }

    public void setiOSGaenSdkConfig(GAENSDKConfig iOSGaenSdkConfig) {
        this.iOSGaenSdkConfig = iOSGaenSdkConfig;
    }

    public GAENSDKConfig getAndroidGaenSdkConfig() {
        return androidGaenSdkConfig;
    }

    public void setAndroidGaenSdkConfig(GAENSDKConfig androidGaenSdkConfig) {
        this.androidGaenSdkConfig = androidGaenSdkConfig;
    }

    public TestLocationCollection getTestLocations() {
        return testLocations;
    }

    public void setTestLocations(TestLocationCollection testLocations) {
        this.testLocations = testLocations;
    }

    public List<String> getInterOpsCountries() {
        return interOpsCountries;
    }

    public void setInterOpsCountries(List<String> interOpsCountries) {
        this.interOpsCountries = interOpsCountries;
    }

    public Map<String, String> getTestInformationUrls() {
        return testInformationUrls;
    }

    public void setTestInformationUrls(Map<String, String> testInformationUrls) {
        this.testInformationUrls = testInformationUrls;
    }

    public void setCheckInUpdateNotificationEnabled(boolean checkInUpdateNotificationEnabled) {
        this.checkInUpdateNotificationEnabled = checkInUpdateNotificationEnabled;
    }

    public boolean isCheckInUpdateNotificationEnabled() {
        return checkInUpdateNotificationEnabled;
    }
}
