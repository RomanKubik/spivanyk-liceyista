package com.roman.kubik.songer.domain.logger;

import com.roman.kubik.songer.domain.logger.event.CategoryEvent;
import com.roman.kubik.songer.domain.logger.event.SettingsEvent;

public interface Logger {

    void log(CategoryEvent categoryEvent);

    void log(SettingsEvent settingsEvent);

}
