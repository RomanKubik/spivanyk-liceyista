package com.roman.kubik.songer.data.preferences.model;

import com.google.gson.annotations.SerializedName;

public class PreferencesModel {
    @SerializedName("isChordsVisible")
    private boolean isChordsVisible = false;
    @SerializedName("selectedInstrument")
    private String selectedInstrument;
    @SerializedName("tutorialPreferences")
    private TutorialPreferencesModel tutorialPreferences = new TutorialPreferencesModel();

    public PreferencesModel() {
    }

    public PreferencesModel(boolean isChordsVisible, String selectedInstrument, TutorialPreferencesModel tutorialPreferences) {
        this.isChordsVisible = isChordsVisible;
        this.selectedInstrument = selectedInstrument;
        this.tutorialPreferences = tutorialPreferences;
    }

    public boolean isChordsVisible() {
        return isChordsVisible;
    }

    public void setChordsVisible(boolean chordsVisible) {
        isChordsVisible = chordsVisible;
    }

    public String getSelectedInstrument() {
        return selectedInstrument;
    }

    public void setSelectedInstrument(String selectedInstrument) {
        this.selectedInstrument = selectedInstrument;
    }

    public TutorialPreferencesModel getTutorialPreferences() {
        return tutorialPreferences;
    }

    public void setTutorialPreferences(TutorialPreferencesModel tutorialPreferences) {
        this.tutorialPreferences = tutorialPreferences;
    }
}
