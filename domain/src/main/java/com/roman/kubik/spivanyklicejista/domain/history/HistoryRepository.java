package com.roman.kubik.spivanyklicejista.domain.history;

import com.roman.kubik.spivanyklicejista.domain.song.Song;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface HistoryRepository {

    Single<List<Song>> getLastSongs();

    Completable addSongToHistory(Song song);
}
