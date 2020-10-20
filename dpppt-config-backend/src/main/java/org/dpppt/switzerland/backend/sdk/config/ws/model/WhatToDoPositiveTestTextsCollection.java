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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class WhatToDoPositiveTestTextsCollection {

	private WhatToDoPositiveTestTexts de;
	private WhatToDoPositiveTestTexts fr;
	private WhatToDoPositiveTestTexts it;
	private WhatToDoPositiveTestTexts en;
	private WhatToDoPositiveTestTexts pt;
	private WhatToDoPositiveTestTexts es;
	private WhatToDoPositiveTestTexts sq;
	private WhatToDoPositiveTestTexts bs;
	private WhatToDoPositiveTestTexts hr;
	private WhatToDoPositiveTestTexts sr;
	private WhatToDoPositiveTestTexts rm;
	private WhatToDoPositiveTestTexts tr;
	private WhatToDoPositiveTestTexts ti;

	public WhatToDoPositiveTestTexts getDe() {
		return de;
	}

	public void setDe(WhatToDoPositiveTestTexts de) {
		this.de = de;
	}

	public WhatToDoPositiveTestTexts getFr() {
		return fr;
	}

	public void setFr(WhatToDoPositiveTestTexts fr) {
		this.fr = fr;
	}

	public WhatToDoPositiveTestTexts getIt() {
		return it;
	}

	public void setIt(WhatToDoPositiveTestTexts it) {
		this.it = it;
	}

	public WhatToDoPositiveTestTexts getEn() {
		return en;
	}

	public void setEn(WhatToDoPositiveTestTexts en) {
		this.en = en;
	}

	public WhatToDoPositiveTestTexts getPt() {
		return pt;
	}

	public void setPt(WhatToDoPositiveTestTexts pt) {
		this.pt = pt;
	}

	public WhatToDoPositiveTestTexts getEs() {
		return es;
	}

	public void setEs(WhatToDoPositiveTestTexts es) {
		this.es = es;
	}

	public WhatToDoPositiveTestTexts getSq() {
		return sq;
	}

	public void setSq(WhatToDoPositiveTestTexts sq) {
		this.sq = sq;
	}

	public WhatToDoPositiveTestTexts getBs() {
		return bs;
	}

	public void setBs(WhatToDoPositiveTestTexts bs) {
		this.bs = bs;
	}

	public WhatToDoPositiveTestTexts getHr() {
		return hr;
	}

	public void setHr(WhatToDoPositiveTestTexts hr) {
		this.hr = hr;
	}

	public WhatToDoPositiveTestTexts getSr() {
		return sr;
	}

	public void setSr(WhatToDoPositiveTestTexts sr) {
		this.sr = sr;
	}

	public WhatToDoPositiveTestTexts getRm() {
		return rm;
	}

	public void setRm(WhatToDoPositiveTestTexts rm) {
		this.rm = rm;
	}

	public WhatToDoPositiveTestTexts getTr() {
		return tr;
	}

	public void setTr(WhatToDoPositiveTestTexts tr) {
		this.tr = tr;
	}

	public WhatToDoPositiveTestTexts getTi() {
		return ti;
	}

	public void setTi(WhatToDoPositiveTestTexts ti) {
		this.ti = ti;
	}
}