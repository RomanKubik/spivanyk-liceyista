package com.roman.kubik.spivanyklicejista.presentation.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.roman.kubik.spivanyklicejista.data.database.DatabaseCopyHelper;
import com.roman.kubik.spivanyklicejista.presentation.Navigate;

import java.io.IOException;

import javax.inject.Inject;

/**
 * Launcher activity
 * Created by kubik on 1/14/18.
 */

public class SplashActivity extends AppCompatActivity {

    @Inject
    DatabaseCopyHelper databaseCopyHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            databaseCopyHelper.createDataBase();
            Navigate.toMainActivity(this);
        } catch (IOException ignored) {
        }
        finish();
    }
}
