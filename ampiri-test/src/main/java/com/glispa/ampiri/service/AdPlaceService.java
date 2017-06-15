package com.glispa.ampiri.service;

import com.glispa.ampiri.dao.AdPlaceRepository;
import com.glispa.ampiri.model.AdPlace;
import com.glispa.ampiri.model.AdPlaceRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author abhishekrai
 * @since 10/05/2017
 */
@Service
public class AdPlaceService implements IAdPlace {

    private static Logger logger = LoggerFactory.getLogger(AppService.class);

    @Autowired
    private AdPlaceRepository adPlaceRepository;

    public AdPlace addApp(AdPlaceRequest adPlaceRequest) {
        AdPlace adPlace = new AdPlace();
        adPlace.setAdId(adPlaceRequest.getAdPlaceId());
        adPlace.setAdType(adPlaceRequest.getAdType());
        adPlace.setAppId(adPlaceRequest.getAppId());

        AdPlace newAdPlace = adPlaceRepository.save(adPlace);
        return newAdPlace;
    }
}
