package com.roman.kubik.songer.room.database

interface DatabaseManager {
    suspend fun reset()
    suspend fun createDatabase()

    companion object {
        const val DB_NAME = "spivanyk.db"
    }
}