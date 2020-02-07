package com.roman.kubik.songer.data.history;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.roman.kubik.songer.data.song.local.SongEntity;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "history",
        foreignKeys = @ForeignKey(entity = SongEntity.class,
                childColumns = "song_id",
                parentColumns = "id", onDelete = CASCADE))
public class HistoryEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "song_id")
    private String songId;
    @ColumnInfo(name = "timestamp")
    private long timestamp;

    @Ignore
    public HistoryEntity(String songId) {
        this.songId = songId;
        this.timestamp = System.currentTimeMillis();
    }

    public HistoryEntity(String songId, long timestamp) {
        this.songId = songId;
        this.timestamp = timestamp;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
