package com.roman.kubik.spivanyklicejista.general.di;

import android.content.Context;

import com.roman.kubik.spivanyklicejista.presentation.main.di.MainComponent;
import com.roman.kubik.spivanyklicejista.presentation.main.di.MainModule;

import javax.inject.Singleton;

import dagger.Component;

/** Main application component
 * Created by kubik on 1/14/18.
 */

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    Context getApplicationContext();

    MainComponent mainComponent(MainModule mainModule);
}
