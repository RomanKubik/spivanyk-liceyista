package com.roman.kubik.spivanyklicejista.interaction.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by kubik on 12/25/17.
 */
@Entity(tableName = "song",
        foreignKeys = @ForeignKey(entity = Category.class,
                parentColumns = "id",
                childColumns = "category_id"))
public class Song {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "lyrics")
    private String lyrics;
    @ColumnInfo(name = "category_id")
    private int categoryId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
