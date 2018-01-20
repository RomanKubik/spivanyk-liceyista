package com.roman.kubik.spivanyklicejista.data.favourite;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by kubik on 1/20/18.
 */

@Entity(tableName = "favourite")
public class FavouriteEntity {

    @PrimaryKey
    private int id;
    @ColumnInfo(name = "song_id")
    private int songId;

    public FavouriteEntity(int songId) {
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
