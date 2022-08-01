package com.roman.kubik.songer.songs.domain.song

import com.roman.kubik.provider.SongSercherConfigUpdater


interface SongSearcherProvider: SongSercherConfigUpdater {

    fun getSongSearchers(): Set<SongsSearcher>

}