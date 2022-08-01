package com.roman.kubik.songer.room.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "category")
data class CategoryEntity(
        @PrimaryKey var id: Int,
        @ColumnInfo(name = "name") var name: String) {

    companion object {
        const val CATEGORY_PATRIOTIC = 1
        const val CATEGORY_BONFIRE = 2
        const val CATEGORY_ABROAD = 3
        const val CATEGORY_USERS = 4
    }
}