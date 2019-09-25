package com.roman.kubik.songer.data.history;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.roman.kubik.songer.data.song.SongEntity;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "history",
        foreignKeys = @ForeignKey(entity = SongEntity.class,
                childColumns = "song_id",
                parentColumns = "id", onDelete = CASCADE))
public class HistoryEntity {

    @PrimaryKey
    @ColumnInfo(name = "song_id")
    private int songId;
    @ColumnInfo(name = "timestamp")
    private long timestamp;

    @Ignore
    public HistoryEntity(int songId) {
        this.songId = songId;
        this.timestamp = System.currentTimeMillis();
    }

    public HistoryEntity(int songId, long timestamp) {
        this.songId = songId;
        this.timestamp = timestamp;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
