package org.dpppt.switzerland.backend.sdk.config.ws.model;

import javax.validation.constraints.NotNull;

public class VaccinationBookingInfo {

  @NotNull
  private String title;
  @NotNull
  private String text;
  @NotNull
  private String info;
  @NotNull
  private String impfcheckTitle;
  @NotNull
  private String impfcheckText;
  @NotNull
  private String impfcheckButton;
  @NotNull
  private String impfcheckUrl;

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

  public String getImpfcheckTitle() {
    return impfcheckTitle;
  }

  public void setImpfcheckTitle(String impfcheckTitle) {
    this.impfcheckTitle = impfcheckTitle;
  }

  public String getImpfcheckText() {
    return impfcheckText;
  }

  public void setImpfcheckText(String impfcheckText) {
    this.impfcheckText = impfcheckText;
  }

  public String getImpfcheckButton() {
    return impfcheckButton;
  }

  public void setImpfcheckButton(String impfcheckButton) {
    this.impfcheckButton = impfcheckButton;
  }

  public String getImpfcheckUrl() {
    return impfcheckUrl;
  }

  public void setImpfcheckUrl(String impfcheckUrl) {
    this.impfcheckUrl = impfcheckUrl;
  }
}
