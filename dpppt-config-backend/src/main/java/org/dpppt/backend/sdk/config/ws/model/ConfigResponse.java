package org.dpppt.backend.sdk.config.ws.model;

public class ConfigResponse {

	private boolean forceUpdate = false;

	private InfoBox infoBox = null;

	public boolean isForceUpdate() {
		return forceUpdate;
	}

	public void setForceUpdate(boolean forceUpdate) {
		this.forceUpdate = forceUpdate;
	}

	public InfoBox getInfoBox() {
		return infoBox;
	}

	public void setInfoBox(InfoBox infoBox) {
		this.infoBox = infoBox;
	}

}
