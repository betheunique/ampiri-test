package com.glispa.ampiri.controller;

import com.glispa.ampiri.model.AdDimension;
import com.glispa.ampiri.model.AdDimensionRequest;
import com.glispa.ampiri.model.AdPlace;
import com.glispa.ampiri.model.AppRequest;
import com.glispa.ampiri.model.GenericResponse;
import com.glispa.ampiri.model.AdPlaceRequest;
import com.glispa.ampiri.model.AdRequest;
import com.glispa.ampiri.model.AdResponse;
import com.glispa.ampiri.model.App;
import com.glispa.ampiri.model.DefaultResponse;
import com.glispa.ampiri.model.EmptyResponse;
import com.glispa.ampiri.service.AdvDimension;
import com.glispa.ampiri.service.IAdPlace;
import com.glispa.ampiri.service.IApp;
import com.glispa.ampiri.service.ServeAd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author abhishekrai
 * @since 08/05/2017
 * Class Responsible for Plug & play end points.
 */
@Controller
@RequestMapping(value = "/v1")
public class AdvertisementController {
    private static Logger logger = LoggerFactory.getLogger(AdvertisementController.class);

    @Autowired
    private ServeAd serveAdService;

    @Autowired
    private AdvDimension adVDimensionService;

    @Autowired
    private IApp appService;

    @Autowired
    private IAdPlace adPlaceService;


    /**
     * @param adRequest
     * @return {DefaultResponse}
     * <p>It will return closest and maximum Advertisement Dimension for ad place id specified app.</p>
     */
    @RequestMapping(value = "/serveAd", method = RequestMethod.POST)
    @ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DefaultResponse getAdvertisement(@RequestBody final AdRequest adRequest) {
        logger.info("Ad request coming as : " + adRequest);
        if(null != adRequest) {
            AdResponse adResponse = serveAdService.serveAd(adRequest);
            logger.info("adResponse coming as :" + adResponse);
            if(null != adResponse) {
                logger.info("adResponse not coming as null");
                return adResponse;
            } else {
                return new EmptyResponse();
            }
        } else {
            return new EmptyResponse();
        }
    }

    /**
     * Responsible for adding new pre-defined Ad dimension into system and re-initializing system cache.
     * @param adDimensionRequest
     * @return {DefaultResponse}
     */
    @RequestMapping(value = "/addAdDimension", method = RequestMethod.POST)
    @ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DefaultResponse addAdDimension(@RequestBody final AdDimensionRequest adDimensionRequest) {
        GenericResponse adDimensionResponse = new GenericResponse();
        logger.info("Dimension request coming as :" + adDimensionRequest);
        if(null != adDimensionRequest) {
            AdDimension adDimension = adVDimensionService.addAdDimension(adDimensionRequest);
            if(adDimension != null) {
                adDimensionResponse.setStatus(0);
                adDimensionResponse.setMessage("New Ad dimesnion added successfully");
                return adDimensionResponse;
            }
        }
        adDimensionResponse.setStatus(-1);
        adDimensionResponse.setMessage("Unsuccessful");
        return adDimensionResponse;
    }

    /**
     * Responsible for exsisting ad dimension update.
     * @param adDimensionRequest
     * @return
     */

    @RequestMapping(value = "/updateAdDimension", method = RequestMethod.POST)
    @ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DefaultResponse updateAdDimension(@RequestBody final AdDimensionRequest adDimensionRequest) {
        GenericResponse adDimensionResponse = new GenericResponse();
        logger.info("Ad Dimension request coming as :" + adDimensionRequest);
        if(null != adDimensionRequest) {
            AdDimension adDimension = adVDimensionService.updateAdDimension(adDimensionRequest);
            if(adDimension != null) {
                adDimensionResponse.setStatus(0);
                adDimensionResponse.setMessage(" Ad dimension Update successfully");
                return adDimensionResponse;
            }
        }
        adDimensionResponse.setStatus(-1);
        adDimensionResponse.setMessage("Unsuccessful or Didn't find Ad Dimension in DB");
        return adDimensionResponse;
    }

    /**
     * Remove specific dimension from the system.
     * @param adDimensionId
     * @return
     */
    @RequestMapping(value = "/removeAdDimension", method = RequestMethod.GET)
    @ResponseBody
    public int updateAdDimension(@RequestParam("adDimensionId") final int adDimensionId) {
        logger.info("Ad Dimension id coming as :" + adDimensionId);
        if(adDimensionId != 0) {
            adVDimensionService.removeAdDimension(adDimensionId);
            return 1;
        }

        return 0;
    }

    @RequestMapping(value = "/allAdDimension", method = RequestMethod.GET)
    @ResponseBody
    @Produces(MediaType.APPLICATION_JSON)
    public List<AdDimension> adDimensionList() {
        List<AdDimension> adDimensionList = adVDimensionService.allAdDimension();
        if(null != adDimensionList) {
            return  adDimensionList;
        }
        return null;
    }

    @RequestMapping(value = "/addAdPlace", method = RequestMethod.POST)
    @ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DefaultResponse addAdPlace(@RequestBody final AdPlaceRequest adPlaceRequest) {
        logger.info("Ad place request coming as :" + adPlaceRequest);
        GenericResponse genericResponse = new GenericResponse();
        if(null != adPlaceRequest) {
            AdPlace adPlace = adPlaceService.addApp(adPlaceRequest);
            if(adPlace != null) {
                genericResponse.setStatus(0);
                genericResponse.setMessage("New Ad placed successfully");
                return genericResponse;
            }
        }
        genericResponse.setStatus(-1);
        genericResponse.setMessage("Unsuccessful");
        return genericResponse;
    }

    @RequestMapping(value = "/addApp", method = RequestMethod.POST)
    @ResponseBody
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public DefaultResponse addApp(@RequestBody final AppRequest appRequest) {
        logger.info("App request coming as :" + appRequest);
        GenericResponse genericResponse = new GenericResponse();
        if(null != appRequest) {
            App app = appService.addApp(appRequest);
            if(app != null) {
                genericResponse.setStatus(0);
                genericResponse.setMessage("New App added successfully");
                return genericResponse;
            }
        }
        genericResponse.setStatus(-1);
        genericResponse.setMessage("Unsuccessful");
        return genericResponse;
    }

    @RequestMapping(value = "/clearCache", method = RequestMethod.GET)
    @ResponseBody
    public int clearAdDimensioncache() {
        adVDimensionService.clearAdDimensionInAppCache();
        return 1;
    }

}
