package com.roman.kubik.songer.data.preferences.mapper;

import com.roman.kubik.songer.data.preferences.model.PreferencesModel;
import com.roman.kubik.songer.domain.EntityModelMapper;
import com.roman.kubik.songer.domain.preferences.Preferences;

import javax.inject.Inject;

public class PreferencesModelMapper implements EntityModelMapper<PreferencesModel, Preferences> {

    private final TutorialPreferencesModelMapper tutorialMapper;

    @Inject
    public PreferencesModelMapper(TutorialPreferencesModelMapper tutorialMapper) {
        this.tutorialMapper = tutorialMapper;
    }

    @Override
    public Preferences fromEntity(PreferencesModel from) {
        return from != null
                ? new Preferences(from.isChordsVisible(), from.getSelectedInstrument(), tutorialMapper.fromEntity(from.getTutorialPreferences()))
                : new Preferences();
    }

    @Override
    public PreferencesModel toEntity(Preferences from) {
        return from != null
                ? new PreferencesModel(from.isChordsVisible(), from.getSelectedInstrument(), tutorialMapper.toEntity(from.getTutorialPreferences()))
                : new PreferencesModel();
    }
}
