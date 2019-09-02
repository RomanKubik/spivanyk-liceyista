package com.roman.kubik.songer.domain.preferences;

import com.roman.kubik.songer.domain.chord.Instruments;

public class Preferences {
    private boolean isChordsVisible = true;
    @Instruments.Instrument
    private String selectedInstrument = Instruments.GUITAR;
    private TutorialPreferences tutorialPreferences = new TutorialPreferences();

    public Preferences() {
    }

    public Preferences(boolean isChordsVisible, @Instruments.Instrument String selectedInstrument, TutorialPreferences tutorialPreferences) {
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

    public TutorialPreferences getTutorialPreferences() {
        return tutorialPreferences;
    }

    public void setTutorialPreferences(TutorialPreferences tutorialPreferences) {
        this.tutorialPreferences = tutorialPreferences;
    }
}
