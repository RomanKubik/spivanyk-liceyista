package com.roman.kubik.songer.data.local.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "category")
data class CategoryEntity(
        @PrimaryKey var id: Int,
        @ColumnInfo(name = "name") var name: String)