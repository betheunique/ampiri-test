package com.glispa.ampiri.service;

import com.glispa.ampiri.cache.AdDimensionCache;
import com.glispa.ampiri.dao.AdDimensionRepository;
import com.glispa.ampiri.model.AdDimension;
import com.glispa.ampiri.model.AdDimensionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author abhishekrai
 * @since 09/05/2017
 */
@Service
public class AdvDimensionService implements AdvDimension{

    private static Logger logger = LoggerFactory.getLogger(AdvDimensionService.class);

    @Autowired
    private AdDimensionRepository adDimensionRepository;

    @Autowired
    private AdDimensionCache adDimensionCache;

    public AdDimension addAdDimension(AdDimensionRequest adDimensionRequest) {
        AdDimension adDimension = new AdDimension();
        adDimension.setHeight(adDimensionRequest.getHeight());
        adDimension.setWidth(adDimensionRequest.getWidth());
        AdDimension newAdDimension = adDimensionRepository.save(adDimension);
        if(null != newAdDimension) {
            adDimensionCache.reInitialize();
        }
        return newAdDimension;
    }

    public void removeAdDimension(int adDimensionId) {
        AdDimension adDimension = adDimensionRepository.findById(adDimensionId);
        adDimensionRepository.delete(adDimension);
        adDimensionCache.reInitialize();
    }

    public AdDimension updateAdDimension(AdDimensionRequest adDimensionRequest) {
        AdDimension adDimension = adDimensionRepository.findById(adDimensionRequest.getId());
        if(null == adDimension) {
            return null;
        }
        if(adDimensionRequest.getHeight() != 0) {
            adDimension.setHeight(adDimensionRequest.getHeight());
        }
        if(adDimensionRequest.getWidth() != 0) {
            adDimension.setWidth(adDimensionRequest.getWidth());
        }
        AdDimension updatedAdDimension = adDimensionRepository.save(adDimension);
        return updatedAdDimension;
    }

    public List<AdDimension> allAdDimension() {
        return adDimensionRepository.findAll();
    }

    public void clearAdDimensionInAppCache() {
        adDimensionCache.reInitialize();
    }

}
