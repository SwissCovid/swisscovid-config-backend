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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class TestLocationCollection {

	private List<TestLocation> de;
	private List<TestLocation> fr;
	private List<TestLocation> it;
	private List<TestLocation> en;
	private List<TestLocation> pt;
	private List<TestLocation> es;
	private List<TestLocation> sq;
	private List<TestLocation> bs;
	private List<TestLocation> hr;
	private List<TestLocation> sr;
	private List<TestLocation> rm;
	private List<TestLocation> tr;
	private List<TestLocation> ti;

	public List<TestLocation> getDe() {
		return de;
	}

	public void setDe(List<TestLocation> de) {
		this.de = de;
	}

	public List<TestLocation> getFr() {
		return fr;
	}

	public void setFr(List<TestLocation> fr) {
		this.fr = fr;
	}

	public List<TestLocation> getIt() {
		return it;
	}

	public void setIt(List<TestLocation> it) {
		this.it = it;
	}

	public List<TestLocation> getEn() {
		return en;
	}

	public void setEn(List<TestLocation> en) {
		this.en = en;
	}

	public List<TestLocation> getPt() {
		return pt;
	}

	public void setPt(List<TestLocation> pt) {
		this.pt = pt;
	}

	public List<TestLocation> getEs() {
		return es;
	}

	public void setEs(List<TestLocation> es) {
		this.es = es;
	}

	public List<TestLocation> getSq() {
		return sq;
	}

	public void setSq(List<TestLocation> sq) {
		this.sq = sq;
	}

	public List<TestLocation> getBs() {
		return bs;
	}

	public void setBs(List<TestLocation> bs) {
		this.bs = bs;
	}

	public List<TestLocation> getHr() {
		return hr;
	}

	public void setHr(List<TestLocation> hr) {
		this.hr = hr;
	}

	public List<TestLocation> getSr() {
		return sr;
	}

	public void setSr(List<TestLocation> sr) {
		this.sr = sr;
	}

	public List<TestLocation> getRm() {
		return rm;
	}

	public void setRm(List<TestLocation> rm) {
		this.rm = rm;
	}

	public List<TestLocation> getTr() {
		return tr;
	}

	public void setTr(List<TestLocation> tr) {
		this.tr = tr;
	}

	public List<TestLocation> getTi() {
		return ti;
	}

	public void setTi(List<TestLocation> ti) {
		this.ti = ti;
	}

}