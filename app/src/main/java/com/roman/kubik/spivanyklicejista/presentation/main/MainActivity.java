package com.roman.kubik.spivanyklicejista.presentation.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.roman.kubik.spivanyklicejista.presentation.main.di.MainModule;

import javax.inject.Inject;

import static com.roman.kubik.spivanyklicejista.general.android.SpivanykApplication.component;

/**
 * Main activity
 * Created by kubik on 1/14/18.
 */

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @Inject
    MainContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component.mainComponent(new MainModule(this)).inject(this);
    }
}
