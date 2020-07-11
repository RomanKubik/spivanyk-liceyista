package com.roman.kubik.songer.room.song

import androidx.room.*
import com.roman.kubik.songer.room.category.CategoryEntity


@Entity(indices = [Index(value = ["id"], name = "song_id_clustered_index"), Index(value = ["title"], name = "song_title_nonclustered_index")],
        tableName = "song",
        foreignKeys = [ForeignKey(entity = CategoryEntity::class, parentColumns = ["id"], childColumns = ["category_id"])])
data class SongEntity(@PrimaryKey var id: String,
                      @ColumnInfo(name = "title") var title: String,
                      @ColumnInfo(name = "lyrics") var lyrics: String,
                      @ColumnInfo(name = "category_id") var categoryId: Int)
