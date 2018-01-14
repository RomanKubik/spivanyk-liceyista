package com.roman.kubik.spivanyklicejista.song;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import com.roman.kubik.spivanyklicejista.category.CategoryEntity;

/**
 * Room-specific Song Entity data type.
 * Created by kubik on 1/14/18.
 */
@Entity(tableName = "song",
        foreignKeys = @ForeignKey(entity = CategoryEntity.class,
                parentColumns = "id",
                childColumns = "category_id"))
public class SongEntity {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "lyrics")
    private String lyrics;
    @ColumnInfo(name = "category_id")
    private int categoryId;

    public SongEntity(int id, String title, String lyrics, int categoryId) {
        this.id = id;
        this.title = title;
        this.lyrics = lyrics;
        this.categoryId = categoryId;
    }

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
