package com.roman.kubik.spivanyklicejista.general.android;

import android.app.Application;

import com.roman.kubik.spivanyklicejista.general.di.ApplicationComponent;
import com.roman.kubik.spivanyklicejista.general.di.ApplicationModule;
import com.roman.kubik.spivanyklicejista.general.di.DaggerApplicationComponent;

/**
 * Spivanyk application
 * Created by kubik on 1/14/18.
 */

public class SpivanykApplication extends Application {

    public static ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeDi();
    }

    private void initializeDi() {
        SpivanykApplication.component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(getApplicationContext()))
                .build();
    }
}
