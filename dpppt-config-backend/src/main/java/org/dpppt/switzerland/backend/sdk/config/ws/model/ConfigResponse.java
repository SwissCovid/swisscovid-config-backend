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

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConfigResponse {

	private boolean forceUpdate = false;
	private boolean forceTraceShutdown = false;

	private InfoBoxCollection infoBox = null;

	private SDKConfig sdkConfig = new SDKConfig();
	private GAENSDKConfig iOSGaenSdkConfig = new GAENSDKConfig();
	private GAENSDKConfig androidGaenSdkConfig = new GAENSDKConfig();

	private List<Country> supportedCountries = Stream.of(new Country("DE"), 
														 new Country("IT"), 
														 new Country("AT"),
														 new Country("DK"), 
														 new Country("IE"), 
														 new Country("LV"), 
														 new Country("PT")).collect(Collectors.toList());

	public boolean isForceUpdate() {
		return forceUpdate;
	}

	public SDKConfig getSdkConfig() {
		return sdkConfig;
	}

	public void setSdkConfig(SDKConfig sdkConfig) {
		this.sdkConfig = sdkConfig;
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

	public boolean isForceTraceShutdown() {
		return forceTraceShutdown;
	}

	public void setForceTraceShutdown(boolean forceTraceShutdown) {
		this.forceTraceShutdown = forceTraceShutdown;
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

	public List<Country> getSupportedCountries() {
		return supportedCountries;
	}

	public void setSupportedCountries(List<Country> supportedCountries) {
		this.supportedCountries = supportedCountries;
	}
}
