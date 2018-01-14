package com.roman.kubik.spivanyklicejista.presentation.main;

import com.roman.kubik.spivanyklicejista.general.di.ActivityScope;

import javax.inject.Inject;

/**
 * Presenter for main activity
 * Created by kubik on 1/14/18.
 */

@ActivityScope
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    @Inject
    public MainPresenter(MainContract.View view) {
        this.view = view;
    }
}
