package com.roman.kubik.songer.domain.logger;

import com.roman.kubik.songer.domain.logger.event.CategoryEvent;
import com.roman.kubik.songer.domain.logger.event.SettingsEvent;

import javax.inject.Inject;

public class LoggerInteractor {

    private Logger logger;

    @Inject
    public LoggerInteractor(final Logger logger) {
        this.logger = logger;
    }

    public void log(CategoryEvent categoryEvent) {
        logger.log(categoryEvent);
    }

    public void log(SettingsEvent settingsEvent) {
        logger.log(settingsEvent);
    }

}
