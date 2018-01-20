package com.roman.kubik.spivanyklicejista.presentation.song.di;

import com.roman.kubik.spivanyklicejista.general.di.ActivityScope;
import com.roman.kubik.spivanyklicejista.presentation.song.SongActivity;

import dagger.Component;

/**
 * Created by kubik on 1/20/18.
 */

@ActivityScope
@Component(modules = SongModule.class)
public interface SongComponent {

    void inject(SongActivity songActivity);

}
