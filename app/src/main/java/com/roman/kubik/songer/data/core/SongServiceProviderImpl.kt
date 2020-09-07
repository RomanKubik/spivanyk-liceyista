package com.roman.kubik.songer.data.core

import com.roman.kubik.provider.SongDataSource
import com.roman.kubik.songer.mychords.song.MyChordsSongsService
import com.roman.kubik.songer.room.song.RoomSongService
import com.roman.kubik.songer.songs.domain.song.SongServiceProvider
import com.roman.kubik.songer.songs.domain.song.SongsService
import com.roman.kubik.songer.songs.pisniorgua.PisniOrgUaSongsService

class SongServiceProviderImpl constructor(
        private val roomSongService: RoomSongService,
        private val myChordsSongsService: MyChordsSongsService,
        private val pisniOrgUaSongsService: PisniOrgUaSongsService
) : SongServiceProvider {

    private var services = emptySet<SongsService>()

    override fun getSongServices(): Set<SongsService> {
        return services
    }

    override fun updateSongConfig(config: Set<SongDataSource>) {
        val services = linkedSetOf<SongsService>()
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