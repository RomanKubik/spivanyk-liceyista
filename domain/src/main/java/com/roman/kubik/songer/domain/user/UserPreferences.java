package com.roman.kubik.songer.domain.user;

import com.annimon.stream.Objects;

public class UserPreferences {

    private boolean isChordsVisible;
    private String selectedInstrument;

    public UserPreferences(boolean isChordsVisible, String selectedInstrument) {
        this.isChordsVisible = isChordsVisible;
        this.selectedInstrument = selectedInstrument;
    }

    public UserPreferences() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPreferences that = (UserPreferences) o;
        return isChordsVisible == that.isChordsVisible &&
                Objects.equals(selectedInstrument, that.selectedInstrument);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isChordsVisible, selectedInstrument);
    }
}
