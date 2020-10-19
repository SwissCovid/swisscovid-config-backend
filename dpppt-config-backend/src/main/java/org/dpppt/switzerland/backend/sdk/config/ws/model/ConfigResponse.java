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

import java.util.ArrayList;
import java.util.Arrays;

public class ConfigResponse {

	private boolean forceUpdate = false;
	private boolean forceTraceShutdown = false;

	private InfoBoxCollection infoBox = null;
	private WhatToDoPositiveTestTextsCollection whatToDoPositiveTestTexts = new WhatToDoPositiveTestTextsCollection(){{
	    setEn(new WhatToDoPositiveTestTexts(){{
            setEnterCovidcodeBoxSupertitle("Whith the Covidcode");
            setEnterCovidcodeBoxTitle("Break the chains of infectionn");
            setEnterCovidcodeBoxText("By entering the Covidcode blablabla");
            setEnterCovidcodeBoxButtonTitle("Enteer the covidcode");
            setInfoBox(new InfoBox(){{
                setTitle("New Infobox title");
                setMsg("infobox message that can be very long...");
                setUrl("tel://+41 12 345 67 89");
                setUrlTitle("Call the number");
                setIsDismissible(false);
            }});
            setFaqEntries(Arrays.asList(new FaqEntry(){{
                setTitle("What is a Covidcode");
                setText("People who have tested positive for the new Coronavirus.\n\nThis ensures that only confirmed cases are notified via the app.");
                setLinkTitle("+41  58 463 00 00");
                setLinkUrl("tel://+41584630000");
                setIconAndroid("ic_verified_user");
                setIconIos("ic-verified_user");
            }}, new FaqEntry(){{
                setTitle("What information gets sent?");
                setText("Only the random IDs from your app are sent, no personal data.");
                setIconAndroid("ic_key");
                setIconIos("ic-key");
            }}));
        }});
    }};

	private SDKConfig sdkConfig = new SDKConfig();
	private GAENSDKConfig iOSGaenSdkConfig = new GAENSDKConfig();
	private GAENSDKConfig androidGaenSdkConfig = new GAENSDKConfig();

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

    public WhatToDoPositiveTestTextsCollection getWhatToDoPositiveTestTexts() {
        return whatToDoPositiveTestTexts;
    }

    public void setWhatToDoPositiveTestTexts(WhatToDoPositiveTestTextsCollection whatToDoPositiveTestTexts) {
        this.whatToDoPositiveTestTexts = whatToDoPositiveTestTexts;
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
}
