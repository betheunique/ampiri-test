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
@Table(name = "t_app")
public class App implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2432925421770020648L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "banned")
    private boolean banned;

    @Column(name = "min_height")
    private int minimumHeight;

    @Column(name = "max_height")
    private int maximumHeight;

    @Column(name = "min_width")
    private int minimumWidth;

    @Column(name = "max_width")
    private int maximumWidth;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMaximumHeight() {
        return maximumHeight;
    }

    public void setMaximumHeight(int maximumHeight) {
        this.maximumHeight = maximumHeight;
    }

    public int getMaximumWidth() {
        return maximumWidth;
    }

    public void setMaximumWidth(int maximumWidth) {
        this.maximumWidth = maximumWidth;
    }

    public int getMinimumHeight() {
        return minimumHeight;
    }

    public void setMinimumHeight(int minimumHeight) {
        this.minimumHeight = minimumHeight;
    }

    public int getMinimumWidth() {
        return minimumWidth;
    }

    public void setMinimumWidth(int minimumWidth) {
        this.minimumWidth = minimumWidth;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    @Override
    public String toString() {
        return "App{" +
                "id='" + id + '\'' +
                ", banned=" + banned +
                ", minimumHeight=" + minimumHeight +
                ", maximumHeight=" + maximumHeight +
                ", minimumWidth=" + minimumWidth +
                ", maximumWidth=" + maximumWidth +
                '}';
    }
}
