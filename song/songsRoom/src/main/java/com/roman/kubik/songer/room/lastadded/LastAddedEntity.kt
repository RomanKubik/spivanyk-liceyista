package com.roman.kubik.songer.room.lastadded

import androidx.room.*
import com.roman.kubik.songer.room.song.SongEntity

@Entity(tableName = "lastAdded", foreignKeys = [ForeignKey(entity = SongEntity::class, childColumns = ["song_id"], parentColumns = ["id"], onDelete = ForeignKey.CASCADE)])
data class LastAddedEntity(@PrimaryKey
                      @ColumnInfo(name = "song_id") var songId: String) {

}