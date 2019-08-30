package com.roman.kubik.songer.data.preferences.mapper;

import com.roman.kubik.songer.data.preferences.model.TutorialPreferencesModel;
import com.roman.kubik.songer.domain.EntityModelMapper;
import com.roman.kubik.songer.domain.preferences.TutorialPreferences;

import javax.inject.Inject;

public class TutorialPreferencesModelMapper implements EntityModelMapper<TutorialPreferencesModel, TutorialPreferences> {

    @Inject
    public TutorialPreferencesModelMapper() {
    }

    @Override
    public TutorialPreferences fromEntity(TutorialPreferencesModel from) {
        return from != null
                ? new TutorialPreferences(from.isShakeShown(), from.isAddSongShown(), from.isMarkChordsShown(), from.isDeleteShown())
                : new TutorialPreferences();
    }

    @Override
    public TutorialPreferencesModel toEntity(TutorialPreferences from) {
        return from != null
                ? new TutorialPreferencesModel(from.isShakeShown(), from.isAddSongShown(), from.isMarkChordsShown(), from.isDeleteShown())
                : new TutorialPreferencesModel();
    }
}
