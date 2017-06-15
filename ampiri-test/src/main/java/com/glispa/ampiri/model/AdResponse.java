package com.glispa.ampiri.model;

/**
 * @author abhishekrai
 * @since 09/05/2017
 */
public class AdResponse implements DefaultResponse {

    private String url;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "{" +
                "url:'" + url + '\'' +
                ", type:'" + type + '\'' +
                '}';
    }
}
