package com.glispa.ampiri.model;

/**
 * @author abhishekrai
 * @since 10/05/2017
 */
public class AppRequest implements DefaultRequest {

    private String appId;
    private boolean banned;
    private int maxHeight;
    private int minHeight;
    private int maxWidth;
    private int minWidth;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public int getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    @Override
    public String toString() {
        return "AppRequest{" +
                "appId='" + appId + '\'' +
                ", banned=" + banned +
                ", maxHeight=" + maxHeight +
                ", minHeight=" + minHeight +
                ", maxWidth=" + maxWidth +
                ", minWidth=" + minWidth +
                '}';
    }
}
