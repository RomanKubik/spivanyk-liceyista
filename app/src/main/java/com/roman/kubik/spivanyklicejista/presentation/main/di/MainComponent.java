package com.roman.kubik.spivanyklicejista.presentation.main.di;

import com.roman.kubik.spivanyklicejista.general.di.ActivityScope;
import com.roman.kubik.spivanyklicejista.presentation.main.MainActivity;

import dagger.Subcomponent;

/**
 * {@link MainActivity} component
 * Created by kubik on 1/14/18.
 */
@ActivityScope
@Subcomponent(modules = MainModule.class)
public interface MainComponent {

    void inject(MainActivity mainActivity);
}
