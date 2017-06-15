package com.glispa.ampiri.model;

/**
 * @author abhishekrai
 * @since 09/05/2017
 */
public class AdDimensionRequest implements DefaultRequest {


    private int id;
    private int height;
    private int width;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "AdDimensionRequest{" +
                "id=" + id +
                ", height=" + height +
                ", width=" + width +
                '}';
    }
}
