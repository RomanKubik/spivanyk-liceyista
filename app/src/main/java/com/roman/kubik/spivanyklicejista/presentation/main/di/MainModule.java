package com.roman.kubik.spivanyklicejista.presentation.main.di;

import android.app.Activity;

import com.roman.kubik.spivanyklicejista.general.di.ActivityScope;
import com.roman.kubik.spivanyklicejista.presentation.main.MainActivity;
import com.roman.kubik.spivanyklicejista.presentation.main.MainContract;

import dagger.Module;
import dagger.Provides;

/**
 * {@link MainActivity} module
 * Created by kubik on 1/14/18.
 */
@Module
public class MainModule {

    private MainActivity activity;

    public MainModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public MainContract.View getView() {
        return activity;
    }

    @Provides
    @ActivityScope
    public Activity getActivity() {
        return activity;
    }

}
