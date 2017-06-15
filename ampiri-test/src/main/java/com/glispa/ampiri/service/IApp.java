package com.glispa.ampiri.service;

import com.glispa.ampiri.model.App;
import com.glispa.ampiri.model.AppRequest;

/**
 * @author abhishekrai
 * @since 10/05/2017
 */
public interface IApp {
    public App addApp(AppRequest appRequest);
}
