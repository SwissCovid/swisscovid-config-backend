package org.dpppt.switzerland.backend.sdk.config.ws.model;

import javax.validation.constraints.NotNull;

public class VaccinationBookingCanton {
    @NotNull private String name;
    @NotNull private String iconAndroid;
    @NotNull private String iconIos;
    @NotNull private String linkUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconAndroid() {
        return iconAndroid;
    }

    public void setIconAndroid(String iconAndroid) {
        this.iconAndroid = iconAndroid;
    }

    public String getIconIos() {
        return iconIos;
    }

    public void setIconIos(String iconIos) {
        this.iconIos = iconIos;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
