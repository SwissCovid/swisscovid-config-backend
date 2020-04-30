package org.dpppt.switzerland.backend.sdk.config.ws.model;

public class SDKConfig {
    private Integer numberOfWindowsForExposure = 15;
    private Float eventThreshold = 0.8f;
    private Float badAttenuationThreshold;
    private Float contactAttenuationThreshold;

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