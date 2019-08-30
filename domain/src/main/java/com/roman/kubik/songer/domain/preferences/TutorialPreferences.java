package com.roman.kubik.songer.domain.preferences;

public class TutorialPreferences {
    private boolean isShakeShown = false;
    private boolean isAddSongShown = false;
    private boolean isMarkChordsShown = false;
    private boolean isDeleteShown = false;

    public TutorialPreferences() {
    }

    public TutorialPreferences(boolean isShakeShown, boolean isAddSongShown, boolean isMarkChordsShown, boolean isDeleteShown) {
        this.isShakeShown = isShakeShown;
        this.isAddSongShown = isAddSongShown;
        this.isMarkChordsShown = isMarkChordsShown;
        this.isDeleteShown = isDeleteShown;
    }

    public boolean isShakeShown() {
        return isShakeShown;
    }

    public void setShakeShown(boolean shakeShown) {
        isShakeShown = shakeShown;
    }

    public boolean isAddSongShown() {
        return isAddSongShown;
    }

    public void setAddSongShown(boolean addSongShown) {
        isAddSongShown = addSongShown;
    }

    public boolean isMarkChordsShown() {
        return isMarkChordsShown;
    }

    public void setMarkChordsShown(boolean markChordsShown) {
        isMarkChordsShown = markChordsShown;
    }

    public boolean isDeleteShown() {
        return isDeleteShown;
    }

    public void setDeleteShown(boolean deleteShown) {
        isDeleteShown = deleteShown;
    }
}
