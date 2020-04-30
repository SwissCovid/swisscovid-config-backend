package org.dpppt.switzerland.backend.sdk.config.ws.model;

public class ConfigResponse {

	private boolean forceUpdate = false;

	private InfoBoxCollection infoBox = null;

	private SDKConfig sdkConfig = null;

	public boolean isForceUpdate() {
		return forceUpdate;
	}

	public SDKConfig getSdkConfig() {
		return sdkConfig;
	}

	public void setSdkConfig(SDKConfig sdkConfig) {
		this.sdkConfig = sdkConfig;
	}

	public void setForceUpdate(boolean forceUpdate) {
		this.forceUpdate = forceUpdate;
	}

	public InfoBoxCollection getInfoBox() {
		return infoBox;
	}

	public void setInfoBox(InfoBoxCollection infoBox) {
		this.infoBox = infoBox;
	}

}
