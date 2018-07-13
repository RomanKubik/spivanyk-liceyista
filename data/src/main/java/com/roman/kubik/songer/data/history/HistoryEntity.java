package com.roman.kubik.songer.data.history;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.roman.kubik.songer.data.song.SongEntity;

import java.util.UUID;

@Entity(tableName = "history",
        foreignKeys = @ForeignKey(entity = SongEntity.class,
                childColumns = "song_id",
                parentColumns = "id"))
public class HistoryEntity {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "song_id")
    private int songId;

    @Ignore
    public HistoryEntity(int songId) {
        this.id = UUID.randomUUID().hashCode();
        this.songId = songId;
    }

    public HistoryEntity(int id, int songId) {
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
