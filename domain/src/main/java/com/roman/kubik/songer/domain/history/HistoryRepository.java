package com.roman.kubik.songer.domain.history;

import com.roman.kubik.songer.domain.song.Song;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface HistoryRepository {

    Single<List<Song>> getLastSongs();

    Completable addSongToHistory(Song song);
}
