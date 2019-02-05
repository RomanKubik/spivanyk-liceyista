package com.roman.kubik.songer.data.deletion;

import com.roman.kubik.songer.data.song.SongEntity;

import java.util.UUID;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "deletion",
        foreignKeys = @ForeignKey(entity = SongEntity.class,
                childColumns = "song_id",
                parentColumns = "id", onDelete = CASCADE))
public class DeletionEntity {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "song_id")
    private int songId;
    @ColumnInfo(name = "timestamp")
    private long timestamp;

    @Ignore
    public DeletionEntity(int songId) {
        this.id = UUID.randomUUID().hashCode();
        this.songId = songId;
        this.timestamp = System.currentTimeMillis();
    }

    public DeletionEntity(int id, int songId, long timestamp) {
        this.id = id;
        this.songId = songId;
        this.timestamp = timestamp;
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
