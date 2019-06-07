package com.roman.kubik.songer.domain.user;

import com.annimon.stream.Objects;

public class User {

    private String id;
    private String email;
    private String fullName;
    private boolean isChordsVisible;
    private String selectedInstrument;

    public User(String id, String email, String fullName, boolean isChordsVisible, String selectedInstrument) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.isChordsVisible = isChordsVisible;
        this.selectedInstrument = selectedInstrument;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, fullName, isChordsVisible, selectedInstrument);
    }
}
