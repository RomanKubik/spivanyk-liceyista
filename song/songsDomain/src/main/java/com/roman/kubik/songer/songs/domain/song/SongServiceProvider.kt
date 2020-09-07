package com.roman.kubik.songer.songs.domain.song

import com.roman.kubik.provider.SongServiceConfigUpdater


interface SongServiceProvider: SongServiceConfigUpdater {

    fun getSongServices(): Set<SongsService>

}