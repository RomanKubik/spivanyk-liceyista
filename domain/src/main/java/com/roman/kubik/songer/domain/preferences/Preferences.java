package com.roman.kubik.songer.domain.preferences;

public class Preferences {
    private boolean isChordsVisible = false;
    private String selectedInstrument;
    private TutorialPreferences tutorialPreferences = new TutorialPreferences();

    public Preferences() {
    }

    public Preferences(boolean isChordsVisible, String selectedInstrument, TutorialPreferences tutorialPreferences) {
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

    public TutorialPreferences getTutorialPreferences() {
        return tutorialPreferences;
    }

    public void setTutorialPreferences(TutorialPreferences tutorialPreferences) {
        this.tutorialPreferences = tutorialPreferences;
    }
}
