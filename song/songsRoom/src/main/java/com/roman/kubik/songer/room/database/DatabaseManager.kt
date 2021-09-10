package com.roman.kubik.songer.room.database

import com.roman.kubik.settings.domain.database.DatabaseController

interface DatabaseManager : DatabaseController {
    suspend fun createDatabase()

    companion object {
        const val DB_NAME = "spivanyk.db"
    }
}