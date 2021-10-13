package org.dpppt.switzerland.backend.sdk.config.ws.model;

import javax.validation.constraints.NotNull;

public class VaccinationBookingInfo {
    @NotNull private String title;
    @NotNull private String text;
    @NotNull private String info;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
