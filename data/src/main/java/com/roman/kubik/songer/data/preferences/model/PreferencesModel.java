package com.roman.kubik.songer.data.preferences.model;

import android.os.Build;

import com.google.gson.annotations.SerializedName;
import com.roman.kubik.songer.domain.preferences.Preferences;

public class PreferencesModel {
    @SerializedName("isChordsVisible")
    private boolean isChordsVisible = true;
    @SerializedName("selectedInstrument")
    @Preferences.Instrument
    private String selectedInstrument = Preferences.GUITAR;
    @SerializedName("slectedTheme")
    @Preferences.Theme
    private String selectedTheme = Build.VERSION.SDK_INT > Build.VERSION_CODES.P ? Preferences.THEME_SYSTEM_DEFAULT : Preferences.THEME_SET_BY_BATTERY;
    @SerializedName("tutorialPreferences")
    private TutorialPreferencesModel tutorialPreferences = new TutorialPreferencesModel();

    public PreferencesModel() {
    }

    public PreferencesModel(boolean isChordsVisible,
                            @Preferences.Instrument String selectedInstrument,
                            @Preferences.Theme String selectedTheme,
                            TutorialPreferencesModel tutorialPreferences) {
        this.isChordsVisible = isChordsVisible;
        this.selectedInstrument = selectedInstrument;
        this.selectedTheme = selectedTheme;
        this.tutorialPreferences = tutorialPreferences;
    }

    public boolean isChordsVisible() {
        return isChordsVisible;
    }

    public void setChordsVisible(boolean chordsVisible) {
        isChordsVisible = chordsVisible;
    }

    @Preferences.Instrument
    public String getSelectedInstrument() {
        return selectedInstrument;
    }

    public void setSelectedInstrument(@Preferences.Instrument String selectedInstrument) {
        this.selectedInstrument = selectedInstrument;
    }

    @Preferences.Theme
    public String getSelectedTheme() {
        return selectedTheme;
    }

    public void setSelectedTheme(@Preferences.Theme String selectedTheme) {
        this.selectedTheme = selectedTheme;
    }

    public TutorialPreferencesModel getTutorialPreferences() {
        return tutorialPreferences;
    }

    public void setTutorialPreferences(TutorialPreferencesModel tutorialPreferences) {
        this.tutorialPreferences = tutorialPreferences;
    }
}
