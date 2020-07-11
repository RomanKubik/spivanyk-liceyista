package com.roman.kubik.songer.room.history

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.roman.kubik.songer.room.song.SongEntity


@Entity(tableName = "history", foreignKeys = [ForeignKey(entity = SongEntity::class, childColumns = ["song_id"], parentColumns = ["id"], onDelete = CASCADE)])
class HistoryEntity {
    @PrimaryKey
    @ColumnInfo(name = "song_id")
    var songId: String

    @ColumnInfo(name = "timestamp")
    var timestamp: Long

    @Ignore
    constructor(songId: String) {
        this.songId = songId
        timestamp = System.currentTimeMillis()
    }

    constructor(songId: String, timestamp: Long) {
        this.songId = songId
        this.timestamp = timestamp
    }

}