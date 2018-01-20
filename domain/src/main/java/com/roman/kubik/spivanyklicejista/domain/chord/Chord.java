package com.roman.kubik.spivanyklicejista.domain.chord;

/**
 * Represents Chord data model
 * Created by kubik on 1/14/18.
 */

public class Chord {

    private int id;
    private String name;
    private String imagePath;
    private String soundPath;

    public Chord(int id, String name, String imagePath, String soundPath) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.soundPath = soundPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getSoundPath() {
        return soundPath;
    }

    public void setSoundPath(String soundPath) {
        this.soundPath = soundPath;
    }
}
