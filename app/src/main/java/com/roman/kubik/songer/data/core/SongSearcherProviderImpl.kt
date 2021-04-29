package com.roman.kubik.songer.data.core

import com.roman.kubik.provider.SongDataSource
import com.roman.kubik.songer.mychords.song.MyChordsSongsService
import com.roman.kubik.songer.room.song.RoomSongService
import com.roman.kubik.songer.songs.domain.song.SongSearcherProvider
import com.roman.kubik.songer.songs.domain.song.SongsSearcher
import com.roman.kubik.songer.songs.pisniorgua.PisniOrgUaSongsService

class SongSearcherProviderImpl constructor(
        private val roomSongService: RoomSongService,
        private val myChordsSongsService: MyChordsSongsService,
        private val pisniOrgUaSongsService: PisniOrgUaSongsService
) : SongSearcherProvider {

    private var services = emptySet<SongsSearcher>()

    override fun getSongSearchers(): Set<SongsSearcher> {
        return services
    }

    override fun updateSongConfig(config: Set<SongDataSource>) {
        val services = linkedSetOf<SongsSearcher>()
        services.add(roomSongService)

        if (config.contains(SongDataSource.PISNI_ORG_UA)) {
            services.add(pisniOrgUaSongsService)
        }
        if (config.contains(SongDataSource.MY_CHORDS_NET)) {
            services.add(myChordsSongsService)
        }

        this.services = services
    }

}