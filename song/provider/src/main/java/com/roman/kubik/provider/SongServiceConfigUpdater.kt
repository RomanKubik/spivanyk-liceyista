package com.roman.kubik.provider


interface SongServiceConfigUpdater {

    fun updateSongConfig(config: Set<SongDataSource>)

}