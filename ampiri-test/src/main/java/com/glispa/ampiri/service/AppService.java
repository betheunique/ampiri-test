package com.glispa.ampiri.service;

import com.glispa.ampiri.dao.AppRepository;
import com.glispa.ampiri.model.App;
import com.glispa.ampiri.model.AppRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author abhishekrai
 * @since 10/05/2017
 */
@Service
public class AppService implements IApp {

    private static Logger logger = LoggerFactory.getLogger(AppService.class);

    @Autowired
    private AppRepository appRepository;

    public App addApp(AppRequest appRequest) {
        App app = new App();
        app.setId(appRequest.getAppId());
        app.setBanned(appRequest.isBanned());
        app.setMaximumHeight(appRequest.getMaxHeight());
        app.setMinimumHeight(appRequest.getMinHeight());
        app.setMaximumWidth(appRequest.getMaxWidth());
        app.setMinimumWidth(appRequest.getMinWidth());

        App newApp = appRepository.save(app);
        return newApp;
    }
}
