package com.roman.kubik.songer.data.preferences.model;

import com.google.gson.annotations.SerializedName;
import com.roman.kubik.songer.domain.chord.Instruments;

public class PreferencesModel {
    @SerializedName("isChordsVisible")
    private boolean isChordsVisible = true;
    @SerializedName("selectedInstrument")
    @Instruments.Instrument
    private String selectedInstrument = Instruments.GUITAR;
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

    @Instruments.Instrument
    public String getSelectedInstrument() {
        return selectedInstrument;
    }

    public void setSelectedInstrument(@Instruments.Instrument String selectedInstrument) {
        this.selectedInstrument = selectedInstrument;
    }

    public TutorialPreferencesModel getTutorialPreferences() {
        return tutorialPreferences;
    }

    public void setTutorialPreferences(TutorialPreferencesModel tutorialPreferences) {
        this.tutorialPreferences = tutorialPreferences;
    }
}
