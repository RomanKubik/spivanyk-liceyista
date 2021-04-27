package com.roman.kubik.songer.songs.domain.song

import com.roman.kubik.songer.core.AppResult

interface SongsUpdateService {

    suspend fun fetchNewSongs(forceFetch: Boolean): AppResult<List<Song>>

}