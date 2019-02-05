package com.roman.kubik.songer.data.deletion;

import com.roman.kubik.songer.data.song.SongEntity;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Single;

@Dao
public interface DeletionDao {

    @Query("SELECT * FROM deletion INNER JOIN song ON song_id = song.id")
    Single<List<SongEntity>> getAll();

//    @Query("SELECT *, MAX(timestamp) FROM deletion INNER JOIN song ON deletion.song_id = song.id GROUP BY id")
    @Query("SELECT * FROM deletion INNER JOIN song ON deletion.song_id = song.id ORDER BY timestamp DESC LIMIT 1")
    Single<SongEntity> getLastDeleted();

    @Insert
    void addDeletion(DeletionEntity deletion);

    @Query("DELETE FROM deletion WHERE song_id = :songId")
    void removeDeletion(int songId);
}
