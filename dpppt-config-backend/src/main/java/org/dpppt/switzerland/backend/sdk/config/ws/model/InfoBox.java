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

/**
 * @author bachmann created on 28.04.20
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class InfoBox {

	private String title;
	private String msg;
	private String url;
	private String urlTitle;
	private boolean isDismissible = false;

	public String getInfoId() {
		return Integer.toString(getTitle().hashCode() + getMsg().hashCode() + getUrl().hashCode()
				+ getUrlTitle().hashCode() + Boolean.hashCode(getIsDismissible()));
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
