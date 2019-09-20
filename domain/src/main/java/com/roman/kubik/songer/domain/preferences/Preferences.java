package com.roman.kubik.songer.domain.preferences;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Preferences {
    public static final String GUITAR = "guitar";
    public static final String UKULELE = "ukulele";

    public static final String THEME_LIGHT = "light";
    public static final String THEME_DARK = "dark";
    public static final String THEME_SYSTEM_DEFAULT = "system_default";
    public static final String THEME_SET_BY_BATTERY = "set_by_battery";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({GUITAR, UKULELE})
    public @interface Instrument {
    }

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({THEME_LIGHT, THEME_DARK, THEME_SYSTEM_DEFAULT, THEME_SET_BY_BATTERY})
    public @interface Theme {
    }

    private boolean isChordsVisible = true;
    @Instrument
    private String selectedInstrument = GUITAR;
    @Theme
    private String selectedTheme;
    private TutorialPreferences tutorialPreferences = new TutorialPreferences();

    public Preferences() {
    }

    public Preferences(boolean isChordsVisible, @Instrument String selectedInstrument,
                       @Theme String selectedTheme, TutorialPreferences tutorialPreferences) {
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

    @Instrument
    public String getSelectedInstrument() {
        return selectedInstrument;
    }

    public void setSelectedInstrument(@Instrument String selectedInstrument) {
        this.selectedInstrument = selectedInstrument;
    }

    @Theme
    public String getSelectedTheme() {
        return selectedTheme;
    }

    public void setSelectedTheme(@Theme String selectedTheme) {
        this.selectedTheme = selectedTheme;
    }

    public TutorialPreferences getTutorialPreferences() {
        return tutorialPreferences;
    }

    public void setTutorialPreferences(TutorialPreferences tutorialPreferences) {
        this.tutorialPreferences = tutorialPreferences;
    }
}
