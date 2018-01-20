package com.roman.kubik.spivanyklicejista.general.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.roman.kubik.spivanyklicejista.Constants;
import com.roman.kubik.spivanyklicejista.data.database.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Main application module
 * Created by kubik on 1/14/18.
 */
@Module
public class ApplicationModule {
    private Context applicationContext;

    public ApplicationModule(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Provides
    @Singleton
    Context getApplicationContext() {
        return applicationContext;
    }

}
