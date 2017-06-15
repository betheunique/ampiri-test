package com.glispa.ampiri.model;

/**
 * @author abhishekrai
 * @since 10/05/2017
 */
public class AdPlaceRequest implements DefaultRequest {

    private String adPlaceId;
    private String appId;
    private String adType;

    public String getAdPlaceId() {
        return adPlaceId;
    }

    public void setAdPlaceId(String adPlaceId) {
        this.adPlaceId = adPlaceId;
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "AdPlaceRequest{" +
                "adPlaceId='" + adPlaceId + '\'' +
                ", appId='" + appId + '\'' +
                ", adType='" + adType + '\'' +
                '}';
    }
}
