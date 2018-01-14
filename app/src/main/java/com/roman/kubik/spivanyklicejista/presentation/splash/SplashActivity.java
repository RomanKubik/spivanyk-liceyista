package com.roman.kubik.spivanyklicejista.presentation.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.roman.kubik.spivanyklicejista.general.android.SpivanykApplication;
import com.roman.kubik.spivanyklicejista.general.di.ApplicationModule;
import com.roman.kubik.spivanyklicejista.general.di.DaggerApplicationComponent;
import com.roman.kubik.spivanyklicejista.presentation.Navigate;

/**
 * Launcher activity
 * Created by kubik on 1/14/18.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeDi();
        Navigate.toMainActivty(this);
    }

    private void initializeDi() {
        SpivanykApplication.component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(getApplicationContext()))
                .build();
    }
}
