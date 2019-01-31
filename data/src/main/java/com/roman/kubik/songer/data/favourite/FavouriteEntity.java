package com.roman.kubik.songer.data.favourite;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.roman.kubik.songer.data.song.SongEntity;

import java.util.UUID;

/**
 * Created by kubik on 1/20/18.
 */

@Entity(tableName = "favourite",
        foreignKeys = @ForeignKey(entity = SongEntity.class,
                childColumns = "song_id",
                parentColumns = "id"))
public class FavouriteEntity {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "song_id")
    private int songId;

    @Ignore
    public FavouriteEntity(int songId) {
        this.id = UUID.randomUUID().hashCode();
        this.songId = songId;
    }

    public FavouriteEntity(int id, int songId) {
        this.id = id;
        this.songId = songId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }
}
