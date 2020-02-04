package com.roman.kubik.songer.data.favourite;

import com.roman.kubik.songer.data.song.SongEntity;

import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

/**
 * Created by kubik on 1/20/18.
 */

@Entity(tableName = "favourite",
        foreignKeys = @ForeignKey(entity = SongEntity.class,
                childColumns = "song_id",
                parentColumns = "id", onDelete = CASCADE))
public class FavouriteEntity {

    @PrimaryKey
    @NonNull
    private String id;
    @ColumnInfo(name = "song_id")
    @NonNull
    private String songId;

    @Ignore
    public FavouriteEntity(String songId) {
        this.id = UUID.randomUUID().toString();
        this.songId = songId;
    }

    public FavouriteEntity(String id, String songId) {
        this.id = id;
        this.songId = songId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }
}
