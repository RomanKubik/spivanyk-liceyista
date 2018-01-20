package com.roman.kubik.spivanyklicejista.data.chord;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by kubik on 1/20/18.
 */
@Entity(tableName = "chord")
public class ChordEntity {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "img_path")
    private String imagePath;
    @ColumnInfo(name = "sound_path")
    private String soundPath;

    public ChordEntity(int id, String name, String imagePath, String soundPath) {
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
