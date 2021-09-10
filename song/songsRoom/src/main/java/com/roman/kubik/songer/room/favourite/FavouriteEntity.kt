package com.roman.kubik.songer.room.favourite

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.roman.kubik.songer.room.song.SongEntity
import java.util.*


@Entity(tableName = "favourite", foreignKeys = [ForeignKey(entity = SongEntity::class, childColumns = ["song_id"], parentColumns = ["id"], onDelete = CASCADE)])
class FavouriteEntity {
    @PrimaryKey
    var id: String

    @ColumnInfo(name = "song_id")
    var songId: String

    @Ignore
    constructor(songId: String) {
        id = UUID.randomUUID().toString()
        this.songId = songId
    }

    constructor(id: String, songId: String) {
        this.id = id
        this.songId = songId
    }

}