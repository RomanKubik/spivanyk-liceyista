package com.roman.kubik.songer.data.song.local;

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
    @NonNull
    private String id;
    @ColumnInfo(name = "title")
    @NonNull
    private String title;
    @ColumnInfo(name = "lyrics")
    @NonNull
    private String lyrics;
    @ColumnInfo(name = "category_id")
    private int categoryId;

    public SongEntity(@NonNull String id, @NonNull String title, @NonNull String lyrics, int categoryId) {
        this.id = id;
        this.title = title;
        this.lyrics = lyrics;
        this.categoryId = categoryId;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }
    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }
    @NonNull
    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(@NonNull String lyrics) {
        this.lyrics = lyrics;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

}
