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
    private InfoBox ptInfoBox;
    private InfoBox esInfoBox;
    private InfoBox sqInfoBox;
    private InfoBox bsInfoBox;
    private InfoBox hrInfoBox;
    private InfoBox srInfoBox;
    private InfoBox rmInfoBox;

    public InfoBox getPtInfoBox() {
        return this.ptInfoBox;
    }

    public void setPtInfoBox(InfoBox ptInfoBox) {
        this.ptInfoBox = ptInfoBox;
    }

    public InfoBox getEsInfoBox() {
        return this.esInfoBox;
    }

    public void setEsInfoBox(InfoBox esInfoBox) {
        this.esInfoBox = esInfoBox;
    }

    public InfoBox getSqInfoBox() {
        return this.sqInfoBox;
    }

    public void setSqInfoBox(InfoBox sqInfoBox) {
        this.sqInfoBox = sqInfoBox;
    }

    public InfoBox getBsInfoBox() {
        return this.bsInfoBox;
    }

    public void setBsInfoBox(InfoBox bsInfoBox) {
        this.bsInfoBox = bsInfoBox;
    }

    public InfoBox getHrInfoBox() {
        return this.hrInfoBox;
    }

    public void setHrInfoBox(InfoBox hrInfoBox) {
        this.hrInfoBox = hrInfoBox;
    }

    public InfoBox getSrInfoBox() {
        return this.srInfoBox;
    }

    public void setSrInfoBox(InfoBox srInfoBox) {
        this.srInfoBox = srInfoBox;
    }

    public InfoBox getRmInfoBox() {
        return this.rmInfoBox;
    }

    public void setRmInfoBox(InfoBox rmInfoBox) {
        this.rmInfoBox = rmInfoBox;
    }

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