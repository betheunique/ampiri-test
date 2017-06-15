package com.glispa.ampiri.service;

import com.glispa.ampiri.cache.AdDimensionCache;
import com.glispa.ampiri.dao.AdPlaceRepository;
import com.glispa.ampiri.dao.AppRepository;
import com.glispa.ampiri.model.AdPlace;
import com.glispa.ampiri.model.AdRequest;
import com.glispa.ampiri.model.AdResponse;
import com.glispa.ampiri.model.App;
import com.glispa.ampiri.utility.AdUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author abhishekrai
 * @since 08/05/2017
 * Responsible for serving proper Ad dimension url.
 * <p>Algorithm responsible for selecting proper dimension from available ad dimension considering
 * max height, max width, min height and min width</p>
 */
@Service
public class ServeAdService implements ServeAd {
    private static Logger logger = LoggerFactory.getLogger(ServeAdService.class);

    @Autowired
    private AppRepository appRepository;

    @Autowired
    private AdPlaceRepository adPlaceRepository;

    @Autowired
    private AdDimensionCache adDimensionCache;

    public AdResponse serveAd(AdRequest adRequest) {
        logger.info("ad place id :" + adRequest);
        AdPlace adPlace = adPlaceRepository.findById(adRequest.getAdId());
        logger.info("Ad place details found as :" + adPlace);
        int optimalHeightForAd = 0, optimalWidthForAd = 0;
        AdResponse adResponse =null;
        if(null != adPlace) {
            App app = appRepository.findById(adPlace.getAppId());
            logger.info("app details found as :" + app);

            // Ad is banned for App or not.
            if(app.isBanned()) {
                return adResponse;
            }

            logger.info("height and width :" + adRequest.getHeight() + "\t" + adRequest.getWidth());
            // Requested height & Width is less than App's minimum height & width.
            if(adRequest.getHeight() < app.getMinimumHeight() || adRequest.getWidth() < app.getMinimumWidth()) {
                return adResponse;
            }

            int heightFloor = adDimensionCache.getHeightFloor(adRequest.getHeight());
            int heightCeile = adDimensionCache.getHeightCeile(adRequest.getHeight());

            logger.info("height floor and ceile : " + heightFloor + "\t" + heightCeile);

            int widthFloor = adDimensionCache.getWidthFloor(adRequest.getWidth());
            int widthCeile = adDimensionCache.getWidthCeile(adRequest.getWidth());

            logger.info("width floor and ceile : " + widthFloor + "\t" + widthCeile);

            List<Integer> heightList = new LinkedList<>();
            heightList.add(app.getMaximumHeight());
            heightList.add(app.getMinimumHeight());
            heightList.add(heightFloor);
            heightList.add(heightCeile);


            List<Integer> widthList = new LinkedList<>();
            widthList.add(app.getMaximumWidth());
            widthList.add(app.getMinimumWidth());
            widthList.add(widthFloor);
            widthList.add(widthCeile);


            Integer[] heightSelectionArray = AdUtility.sortedArray(heightList);
            optimalHeightForAd = optimalHeight(heightSelectionArray, heightFloor, heightCeile, app);
            logger.info("optimal height :" + optimalHeightForAd);

            if(optimalHeightForAd != 0) {
                Integer[] widthSelectionArray = AdUtility.sortedArray(widthList);

                optimalWidthForAd = optimalWidth(widthSelectionArray, widthFloor, widthCeile, app);

                logger.info("optimal width for ad :" + optimalWidthForAd);


                if(optimalWidthForAd >= adDimensionCache.getWidthValue(optimalHeightForAd) && optimalWidthForAd !=0) {
                    optimalWidthForAd = adDimensionCache.getWidthValue(optimalHeightForAd);
                } else if(optimalWidthForAd < adDimensionCache.getWidthValue(optimalHeightForAd) && optimalWidthForAd !=0) {
                    optimalHeightForAd = adDimensionCache.getHeightValue(optimalWidthForAd);
                    if(optimalHeightForAd < app.getMinimumHeight()) {
                        optimalHeightForAd = 0; // marking height as zero. optimalHeight is less than app's minimum height.
                    }
                } else {
                    // marking width as zero.
                    optimalWidthForAd = 0;
                }
            }

            if(optimalHeightForAd == 0 || optimalWidthForAd == 0) {
                return adResponse;
            }else {
                adResponse = new AdResponse();
                adResponse.setType(adPlace.getAdType());
                adResponse.setUrl(AdUtility.prepareUrl(adPlace.getAdType(), optimalHeightForAd, optimalWidthForAd));
                return adResponse;
            }
        }
        return adResponse;
    }

    private int optimalHeight(Integer[] heightSelectionArray, int heightFloor, int heightCeile, App app) {
        if(heightSelectionArray[1]== heightCeile) {
            return heightCeile;
        } else if(heightSelectionArray[2] == heightFloor) {
            return heightFloor;
        }else {
            int maxHeightFloor = adDimensionCache.getHeightFloor(app.getMaximumHeight());
            if(maxHeightFloor >= app.getMinimumHeight()) {
                return maxHeightFloor;
            } else {
                return 0;
            }
        }
    }

    private int optimalWidth(Integer[] widthSelectionArray, int widthFloor, int widthCeile, App app) {
        if(widthSelectionArray[1] == widthCeile) {
            return widthCeile;
        } else if(widthSelectionArray[2] == widthFloor) {
            return widthFloor;
        }else {
            int maxWidthFloor = adDimensionCache.getHeightFloor(app.getMaximumWidth());
            if(maxWidthFloor >= app.getMinimumWidth()) {
                return maxWidthFloor;
            } else {
                return 0;
            }
        }
    }
}
