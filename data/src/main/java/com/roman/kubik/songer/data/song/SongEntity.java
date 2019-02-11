package com.roman.kubik.songer.data.song;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

import com.roman.kubik.songer.data.category.CategoryEntity;


/**
 * Room-specific Song Entity data type.
 * Created by kubik on 1/14/18.
 */
@Entity(indices = {@Index(value = "id", name = "song_id_clustered_index"), @Index(value = "title", name = "song_title_nonclustered_index")}, tableName = "song",
        foreignKeys = @ForeignKey(entity = CategoryEntity.class,
                parentColumns = "id",
                childColumns = "category_id"))
public class SongEntity {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "title")
    @NonNull
    private String title;
    @ColumnInfo(name = "lyrics")
    @NonNull
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
