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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WhatToDoPositiveTestTexts {

    private String enterCovidcodeBoxSupertitle;
    private String enterCovidcodeBoxTitle;
    private String enterCovidcodeBoxText;
    private String enterCovidcodeBoxButtonTitle;

    //dismissible will be ignored by clients
    private InfoBox infoBox;

    private List<FaqEntry> faqEntries;

    public String getEnterCovidcodeBoxSupertitle() {
        return enterCovidcodeBoxSupertitle;
    }

    public void setEnterCovidcodeBoxSupertitle(String enterCovidcodeBoxSupertitle) {
        this.enterCovidcodeBoxSupertitle = enterCovidcodeBoxSupertitle;
    }

    public String getEnterCovidcodeBoxTitle() {
        return enterCovidcodeBoxTitle;
    }

    public void setEnterCovidcodeBoxTitle(String enterCovidcodeBoxTitle) {
        this.enterCovidcodeBoxTitle = enterCovidcodeBoxTitle;
    }

    public String getEnterCovidcodeBoxText() {
        return enterCovidcodeBoxText;
    }

    public void setEnterCovidcodeBoxText(String enterCovidcodeBoxText) {
        this.enterCovidcodeBoxText = enterCovidcodeBoxText;
    }

    public String getEnterCovidcodeBoxButtonTitle() {
        return enterCovidcodeBoxButtonTitle;
    }

    public void setEnterCovidcodeBoxButtonTitle(String enterCovidcodeBoxButtonTitle) {
        this.enterCovidcodeBoxButtonTitle = enterCovidcodeBoxButtonTitle;
    }

    public InfoBox getInfoBox() {
        return infoBox;
    }

    public void setInfoBox(InfoBox infoBox) {
        this.infoBox = infoBox;
    }

    public List<FaqEntry> getFaqEntries() {
        return faqEntries;
    }

    public void setFaqEntries(List<FaqEntry> faqEntries) {
        this.faqEntries = faqEntries;
    }
}
