package com.glispa.ampiri.service;

import com.glispa.ampiri.model.AdDimension;
import com.glispa.ampiri.model.AdDimensionRequest;

import java.util.List;

/**
 * @author abhishekrai
 * @since 09/05/2017
 */
public interface AdvDimension {
    public AdDimension addAdDimension(AdDimensionRequest adDimensionRequest);
    public void removeAdDimension(int adDimensionId);
    public AdDimension updateAdDimension(AdDimensionRequest adDimensionRequest);
    public List<AdDimension> allAdDimension();
    public void clearAdDimensionInAppCache();
}
