package com.roman.kubik.songer.room.song

import com.roman.kubik.songer.room.category.CategoryEntity
import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.domain.song.SongCategory

internal fun SongEntity.toSong(isFavourite: Boolean = false): Song {
    val category: SongCategory = when (categoryId) {
        CategoryEntity.CATEGORY_PATRIOTIC -> SongCategory.PATRIOTIC
        CategoryEntity.CATEGORY_BONFIRE -> SongCategory.BONFIRE
        CategoryEntity.CATEGORY_ABROAD -> SongCategory.ABROAD
        else -> SongCategory.MY_SONGS
    }
    return Song(id, title, lyrics, category, isFavourite)
}

internal fun Song.toSongEntity(): SongEntity {
    val category: Int = when (category) {
        SongCategory.PATRIOTIC -> CategoryEntity.CATEGORY_PATRIOTIC
        SongCategory.BONFIRE -> CategoryEntity.CATEGORY_BONFIRE
        SongCategory.ABROAD -> CategoryEntity.CATEGORY_ABROAD
        else -> CategoryEntity.CATEGORY_USERS
    }
    return SongEntity(id, title, lyrics, category)
}