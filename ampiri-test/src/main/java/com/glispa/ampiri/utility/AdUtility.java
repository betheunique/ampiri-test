package com.glispa.ampiri.utility;

import com.glispa.ampiri.constants.AdConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Formatter;
import java.util.List;

/**
 * @author abhishekrai
 * @since 09/05/2017
 * Performs all the utility activities like sorting and formatting url.
 */
public class AdUtility {
    private static Logger logger = LoggerFactory.getLogger(AdUtility.class);

    /**
     * Responsible for sorting max, min, floor and ceiling.
     * @param list
     * @return
     */
    public static Integer[] sortedArray(List<Integer> list) {
        Integer[] maxToMinArray = new Integer[4];
        Collections.sort(list);
        Collections.reverse(list);
        list.toArray(maxToMinArray);
        for (int i = 0; i < maxToMinArray.length; i++) {
            logger.info("max to min array :" + maxToMinArray[i]);
        }
        return maxToMinArray;
    }

    /**
     * Responsible for formatting advertisement's url.
     * @param adType
     * @param height
     * @param width
     * @return
     */
    public static String prepareUrl(String adType, int height, int width) {
        logger.info("Marking adType coming as :" + adType);
        String adUrl = null;
        String urlExtension = null;
        Formatter formatter = new Formatter();
        if(adType.equals(AdConstants.ADTYPE_IAMGE)) {
            adUrl = AdConstants.imageURL;
            urlExtension = formatter.format("%dx%d.png", height, width).toString();
        }
        else if(adType.equals(AdConstants.ADTYPE_ANIMATION)) {
            adUrl = AdConstants.imageURL;
            urlExtension = formatter.format("%dx%d.gif", height, width).toString();
        }
        else if(adType.equals(AdConstants.ADTYPE_VIDEO)) {
            adUrl = AdConstants.videoURL;
            urlExtension = formatter.format("%dx%d.mp4", height, width).toString();
        }
        return adUrl + urlExtension;
    }
}
