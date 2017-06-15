package com.glispa.ampiri.service;

import com.glispa.ampiri.model.AdRequest;
import com.glispa.ampiri.model.AdResponse;

/**
 * @author abhishekrai
 * @since 08/05/2017
 */
public interface ServeAd {
    public AdResponse serveAd(AdRequest adRequest);
}
