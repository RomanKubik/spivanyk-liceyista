package com.roman.kubik.songer.domain.history;

import com.roman.kubik.songer.domain.song.Song;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface HistoryRepository {

    Single<List<Song>> getLastSongs();

    Single<List<Song>> search(String query);

    Completable addSongToHistory(Song song);
}
