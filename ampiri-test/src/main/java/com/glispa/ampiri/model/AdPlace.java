package com.glispa.ampiri.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author abhishekrai
 * @since 08/05/2017
 */
@Entity
@Table(name = "t_ad_place")
public class AdPlace implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1155805077117415725L;

    @Id
    @Column(name = "ad_id")
    private String adId;

    @Column(name = "f_app_id")
    private String appId;

    @Column(name = "ad_type")
    private String adType;

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getAdId() {
        return adId;
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
        return "AdPlace{" +
                ", adId='" + adId + '\'' +
                ", appId='" + appId + '\'' +
                ", adType='" + adType + '\'' +
                '}';
    }
}
