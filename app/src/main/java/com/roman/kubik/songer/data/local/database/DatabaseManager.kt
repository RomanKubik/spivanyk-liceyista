package com.roman.kubik.songer.data.local.database

interface DatabaseManager {
    suspend fun reset()
    suspend fun createDatabase()

    companion object {
        const val DB_NAME = "spivanyk.db"
    }
}