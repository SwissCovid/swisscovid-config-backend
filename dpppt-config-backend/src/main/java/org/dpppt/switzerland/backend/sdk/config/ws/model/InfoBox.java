package org.dpppt.switzerland.backend.sdk.config.ws.model;

/**
 * @author bachmann
 * created on 28.04.20
 **/
public class InfoBox {

    private String title;
    private String msg;
    private String url;
    private String urlTitle;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlTitle() {
        return urlTitle;
    }

    public void setUrlTitle(String urlTitle) {
        this.urlTitle = urlTitle;
    }
}
