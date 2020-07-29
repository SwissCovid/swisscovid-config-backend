package org.dpppt.switzerland.backend.sdk.config.ws.model;

public class Country {
    private String isoCountryCode;

    public Country(String isoCountryCode) {
        this.isoCountryCode = isoCountryCode;
    }

    public String getIsoCountryCode() {
        return isoCountryCode;
    }

    public void setIsoCountryCode(String isoCountryCode) {
        this.isoCountryCode = isoCountryCode;
    }
}