package com.roman.kubik.songer.domain.user;

import com.annimon.stream.Objects;

public class User {

    private String id;
    private String email;
    private String fullName;
    private String picturePath;
    private UserPreferences userPreferences;

    public User(String id, String email, String fullName, String picturePath, UserPreferences userPreferences) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.picturePath = picturePath;
        this.userPreferences = userPreferences;
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

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public UserPreferences getUserPreferences() {
        return userPreferences;
    }

    public void setUserPreferences(UserPreferences userPreferences) {
        this.userPreferences = userPreferences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, fullName, userPreferences);
    }
}
