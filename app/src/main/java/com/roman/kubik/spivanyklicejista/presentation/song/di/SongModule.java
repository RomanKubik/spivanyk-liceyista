package com.roman.kubik.spivanyklicejista.presentation.song.di;

import com.roman.kubik.spivanyklicejista.domain.song.SongInteractor;
import com.roman.kubik.spivanyklicejista.general.di.ActivityScope;
import com.roman.kubik.spivanyklicejista.presentation.song.SongActivity;
import com.roman.kubik.spivanyklicejista.presentation.song.SongContract;
import com.roman.kubik.spivanyklicejista.presentation.song.SongPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kubik on 1/20/18.
 */
@Module
public class SongModule {

    private SongActivity activity;

    public SongModule(SongActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public SongContract.View getView() {
        return activity;
    }

    @Provides
    @ActivityScope
    public SongContract.Presenter getPresenter(SongInteractor songInteractor) {
        return new SongPresenter(activity, songInteractor);
    }
}
