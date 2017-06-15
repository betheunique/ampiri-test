package com.glispa.ampiri.cache;

import com.glispa.ampiri.dao.AdDimensionRepository;
import com.glispa.ampiri.model.AdDimension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.TreeMap;

/**
 * @author abhishekrai
 * @since 08/05/2017
 * Maintaines In-App cache for pre-defined Ad Dimension.
 */
@Component
public class AdDimensionCache {

    private static Logger logger = LoggerFactory.getLogger(AdDimensionCache.class);

    @Autowired
    private AdDimensionRepository adDimensionRepository;

    /**
     *
     */
    private TreeMap<Integer, Integer> heightVsWidth = new TreeMap<>();
    private TreeMap<Integer, Integer> widthVsHeight = new TreeMap<>();

    @PostConstruct
    public void initialize() {
        logger.info("Initializing predefined advertisment dimension");
        List<AdDimension> adDimensionList = adDimensionRepository.findAll();

        for (AdDimension adDimension: adDimensionList) {
            heightVsWidth.put(adDimension.getHeight(), adDimension.getWidth());
            widthVsHeight.put(adDimension.getWidth(), adDimension.getHeight());
        }
    }

    public int getHeightFloor(int originalHeight) {
        return heightVsWidth.floorKey(originalHeight);
    }

    public int getHeightCeile(int originalHeight) {
        return heightVsWidth.ceilingKey(originalHeight);
    }

    public int getWidthValue(int height) {
        if(null != heightVsWidth.get(height)) {
            return heightVsWidth.get(height);
        }
        return 0;
    }

    public int getWidthFloor(int originalWidth) {
        return widthVsHeight.floorKey(originalWidth);
    }

    public int getWidthCeile(int originalWidth) {
        return widthVsHeight.ceilingKey(originalWidth);
    }

    public int getHeightValue(int width) {
        if(null != widthVsHeight.get(width)) {
            return widthVsHeight.get(width);
        }
        return 0;
    }

    public void reInitialize() {
        logger.info("re-initializing ad dimension");
        initialize();
    }
}
