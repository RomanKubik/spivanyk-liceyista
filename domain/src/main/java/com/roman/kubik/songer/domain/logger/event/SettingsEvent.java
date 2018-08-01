package com.roman.kubik.songer.domain.logger.event;

public class SettingsEvent {

    private String selectedInstrument;
    private boolean showChord;

    public SettingsEvent(final String selectedInstrument, final boolean showChord) {
        this.selectedInstrument = selectedInstrument;
        this.showChord = showChord;
    }

    public String getSelectedInstrument() {
        return selectedInstrument;
    }

    public boolean isShowChord() {
        return showChord;
    }
}
