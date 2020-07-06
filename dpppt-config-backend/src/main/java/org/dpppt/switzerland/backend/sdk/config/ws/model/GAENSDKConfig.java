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

public class GAENSDKConfig {

	private Integer lowerThreshold = 53;
	private Integer higherThreshold = 60;
	private Double factorLow = 1.0d;
	private Double factorHigh = 0.5d;
	private Integer triggerThreshold = 15;

	public Integer getLowerThreshold() {
		return lowerThreshold;
	}

	public void setLowerThreshold(Integer lowerThreshold) {
		this.lowerThreshold = lowerThreshold;
	}

	public Integer getHigherThreshold() {
		return higherThreshold;
	}

	public void setHigherThreshold(Integer higherThreshold) {
		this.higherThreshold = higherThreshold;
	}

	public Double getFactorLow() {
		return factorLow;
	}

	public void setFactorLow(Double factorLow) {
		this.factorLow = factorLow;
	}

	public Double getFactorHigh() {
		return factorHigh;
	}

	public void setFactorHigh(Double factorHigh) {
		this.factorHigh = factorHigh;
	}

	public Integer getTriggerThreshold() {
		return triggerThreshold;
	}

	public void setTriggerThreshold(Integer triggerThreshold) {
		this.triggerThreshold = triggerThreshold;
	}

}
