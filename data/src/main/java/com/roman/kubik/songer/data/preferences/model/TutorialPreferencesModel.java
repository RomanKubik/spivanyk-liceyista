package com.roman.kubik.songer.data.preferences.model;

import com.google.gson.annotations.SerializedName;

public class TutorialPreferencesModel {
    @SerializedName("isShakeShown")
    private boolean isShakeShown = false;
    @SerializedName("isAddSongShown")
    private boolean isAddSongShown = false;
    @SerializedName("isMarkChordsShown")
    private boolean isMarkChordsShown = false;
    @SerializedName("isDeleteShown")
    private boolean isDeleteShown = false;

    public TutorialPreferencesModel() {
    }

    public TutorialPreferencesModel(boolean isShakeShown, boolean isAddSongShown, boolean isMarkChordsShown, boolean isDeleteShown) {
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
