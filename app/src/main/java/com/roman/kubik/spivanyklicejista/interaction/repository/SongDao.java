package com.roman.kubik.spivanyklicejista.interaction.repository;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.roman.kubik.spivanyklicejista.interaction.entity.Song;

import java.util.List;

/**
 * Created by kubik on 12/25/17.
 */

@Dao
public interface SongDao {
    @Query("SELECT * FROM song")
    List<Song> getAll();
}
