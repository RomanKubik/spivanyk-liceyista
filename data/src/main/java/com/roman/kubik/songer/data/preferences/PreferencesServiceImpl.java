package com.roman.kubik.songer.data.preferences;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.roman.kubik.songer.data.preferences.mapper.PreferencesModelMapper;
import com.roman.kubik.songer.data.preferences.model.PreferencesModel;
import com.roman.kubik.songer.domain.preferences.Preferences;
import com.roman.kubik.songer.domain.preferences.PreferencesService;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Single;

@Singleton
public class PreferencesServiceImpl implements PreferencesService {

    private final SharedPreferences sharedPreferences;
    private final Gson gson;
    private final PreferencesModelMapper preferencesMapper;

    @Inject
    public PreferencesServiceImpl(SharedPreferences sharedPreferences, Gson gson, PreferencesModelMapper preferencesMapper) {
        this.sharedPreferences = sharedPreferences;
        this.gson = gson;
        this.preferencesMapper = preferencesMapper;
    }

    @Override
    public Single<Preferences> getPreferences() {
        PreferencesModel preferencesModel
                = gson.fromJson(sharedPreferences.getString(Keys.PREFERENCES, ""), PreferencesModel.class);
        return Single.just(preferencesMapper.fromEntity(preferencesModel));
    }

    @Override
    public Completable updatePreferences(Preferences preferences) {
        return Completable.fromAction(
                sharedPreferences.edit()
                        .putString(Keys.PREFERENCES, gson.toJson(preferencesMapper.toEntity(preferences)))
                        ::commit);
    }
}
