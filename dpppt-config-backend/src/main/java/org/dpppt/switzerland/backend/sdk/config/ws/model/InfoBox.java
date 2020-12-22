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

/**
 * @author bachmann created on 28.04.20
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoBox {

	@Documentation(description = "Title of the infobox", example = "Alert")
	private String title;
	@Documentation(description = "Message shown to the user", example = "Please restart your phone")
	private String msg;
	@Documentation(description = "If given, adds a url to the message", example = "https://dp-3t.github.io/")
	private String url;
	@Documentation(description = "Title to be shown for the URL", example = "DP3T page")
	private String urlTitle;
	@Documentation(description = "If true, the user can dismiss the message", example = "true")
	private boolean isDismissible = false;

	public String getInfoId() {
		return Integer.toString(
				((getTitle() != null) ? getTitle().hashCode() : 0)
						+ ((getMsg() != null) ? getMsg().hashCode() : 0)
						+ ((getUrl() != null) ? getUrl().hashCode() : 0)
						+ ((getUrlTitle() != null) ? getUrlTitle().hashCode() : 0)
						+ (Boolean.hashCode(getIsDismissible())));
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlTitle() {
		return urlTitle;
	}

	public void setUrlTitle(String urlTitle) {
		this.urlTitle = urlTitle;
	}

	public boolean getIsDismissible() {
		return isDismissible;
	}

	public void setIsDismissible(boolean isDismissible) {
		this.isDismissible = isDismissible;
	}
}
