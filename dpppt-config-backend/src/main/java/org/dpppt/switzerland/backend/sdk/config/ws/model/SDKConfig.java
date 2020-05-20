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

public class SDKConfig {
	private Integer numberOfWindowsForExposure = 3;

	@Deprecated
	private Float eventThreshold = 0.8f;

	@Deprecated
	private Float badAttenuationThreshold = 73.0f;

	private Float contactAttenuationThreshold = 73.0f;

	public Integer getNumberOfWindowsForExposure() {
		return numberOfWindowsForExposure;
	}

	public Float getContactAttenuationThreshold() {
		return contactAttenuationThreshold;
	}

	public void setContactAttenuationThreshold(Float contactAttenuationThreshold) {
		this.contactAttenuationThreshold = contactAttenuationThreshold;
	}

	public Float getBadAttenuationThreshold() {
		return badAttenuationThreshold;
	}

	public void setBadAttenuationThreshold(Float badAttenuationThreshold) {
		this.badAttenuationThreshold = badAttenuationThreshold;
	}

	public Float getEventThreshold() {
		return eventThreshold;
	}

	public void setEventThreshold(Float eventThreshold) {
		this.eventThreshold = eventThreshold;
	}

	public void setNumberOfWindowsForExposure(Integer numberOfWindowsForExposure) {
		this.numberOfWindowsForExposure = numberOfWindowsForExposure;
	}
}