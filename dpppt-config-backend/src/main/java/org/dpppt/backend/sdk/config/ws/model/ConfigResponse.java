package org.dpppt.backend.sdk.config.ws.model;

public class ConfigResponse {

	private boolean forceUpdate = false;

	private GhettoBox ghettoBox = null;

	public boolean isForceUpdate() {
		return forceUpdate;
	}

	public void setForceUpdate(boolean forceUpdate) {
		this.forceUpdate = forceUpdate;
	}

	public GhettoBox getGhettoBox() {
		return ghettoBox;
	}

	public void setGhettoBox(GhettoBox ghettoBox) {
		this.ghettoBox = ghettoBox;
	}

}
