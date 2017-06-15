package com.glispa.ampiri.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author abhishekrai
 * @since 08/05/2017
 */
@Entity
@Table(name = "t_ad_dimension")
public class AdDimension implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5069346764754011130L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "height")
    private int height;

    @Column(name = "width")
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
        return "AdDimension{" +
                "id=" + id +
                ", height=" + height +
                ", width=" + width +
                '}';
    }
}
