package com.glispa.ampiri.service;

import com.glispa.ampiri.model.AdPlace;
import com.glispa.ampiri.model.AdPlaceRequest;

/**
 * @author abhishekrai
 * @since 10/05/2017
 */
public interface IAdPlace {

    public AdPlace addApp(AdPlaceRequest adPlaceRequest);
}
