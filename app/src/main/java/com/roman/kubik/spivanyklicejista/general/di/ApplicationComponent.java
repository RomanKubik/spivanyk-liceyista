package com.roman.kubik.spivanyklicejista.general.di;

import android.content.Context;

import com.roman.kubik.spivanyklicejista.presentation.main.di.MainComponent;
import com.roman.kubik.spivanyklicejista.presentation.main.di.MainModule;
import com.roman.kubik.spivanyklicejista.presentation.song.di.SongComponent;
import com.roman.kubik.spivanyklicejista.presentation.song.di.SongModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Main application component
 * Created by kubik on 1/14/18.
 */

@Singleton
@Component(modules = {ApplicationModule.class, DatabaseModule.class, InteractionModule.class})
public interface ApplicationComponent {
    Context getApplicationContext();

    MainComponent mainComponent(MainModule mainModule);
    SongComponent songComponent(SongModule songModule);

}
