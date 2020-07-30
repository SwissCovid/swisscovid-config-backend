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

public class GAENSDKConfig {

	@Documentation(description = "Lower threshold that is sent to the GAEN to count encounters with other devices. It" +
			" is expressed in dB attenuation from the Bluetooth chip.",
			example="53")
	private Integer lowerThreshold = 55;
	@Documentation(description = "Higher threshold that is sent to the GAEN to count encounters with other devices " +
			".It is expressed in dB attenuation from the Bluetooth chip.",
			example="60")
	private Integer higherThreshold = 63;
	@Documentation(description = "Multiplication factor used to weigh the return value of the GAEN in " +
			"attenuationDuration[0]",
			example = "1.0d")
	private Double factorLow = 1.0d;
	@Documentation(description = "Multiplication factor used to weigh the return value of the GAEN in " +
			"attenuationDuration[1]",
			example = "0.5d")
	private Double factorHigh = 0.5d;
	@Documentation(description = "Minimum duration of exposure during one day reported by the GAEN before the user " +
			"is alerted of an exposure risk",
			example = "15")
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
