package com.glispa.ampiri.model;

/**
 * @author abhishekrai
 * @since 09/05/2017
 */
public class AdRequest implements DefaultRequest {

    private String adId;
    private int height;
    private int width;

    public String getAdId() {
        return adId;
    }

    public void setAddId(String adId) {
        this.adId = adId;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "AdRequest{" +
                "addId='" + adId + '\'' +
                ", height=" + height +
                ", width=" + width +
                '}';
    }
}
