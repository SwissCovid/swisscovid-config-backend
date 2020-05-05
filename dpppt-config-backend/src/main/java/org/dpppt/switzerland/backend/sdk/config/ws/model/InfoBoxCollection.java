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

public class InfoBoxCollection {
    private InfoBox deInfoBox;
    private InfoBox frInfoBox;
    private InfoBox itInfoBox;
    private InfoBox enInfoBox;

    public InfoBox getDeInfoBox() {
        return deInfoBox;
    }

    public InfoBox getEnInfoBox() {
        return enInfoBox;
    }

    public void setEnInfoBox(InfoBox enInfoBox) {
        this.enInfoBox = enInfoBox;
    }

    public InfoBox getItInfoBox() {
        return itInfoBox;
    }

    public void setItInfoBox(InfoBox itInfoBox) {
        this.itInfoBox = itInfoBox;
    }

    public InfoBox getFrInfoBox() {
        return frInfoBox;
    }

    public void setFrInfoBox(InfoBox frInfoBox) {
        this.frInfoBox = frInfoBox;
    }

    public void setDeInfoBox(InfoBox deInfoBox) {
        this.deInfoBox = deInfoBox;
    }
}