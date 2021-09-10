package com.roman.kubik.songer.room.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject


class DatabaseCopyHelper @Inject constructor(private val context: Context) : SQLiteOpenHelper(context, dbName, null, DB_VERSION) {

    internal val dbPath: String = context.applicationInfo.dataDir.toString() + "/databases/"

    fun createDataBase() {
        //If the database does not exist, copy it from the assets.
        if (!checkDataBase()) {
            this.readableDatabase
            close()
            copyDataBase()
            Log.d(TAG, "createDatabase: database created")
        }
    }

    private fun checkDataBase(): Boolean {
        val dbFile = File(dbPath + dbName)
        Log.d("dbFile", dbFile.toString() + "   " + dbFile.exists())
        return dbFile.exists()
    }

    //Copy the database from assets
    private fun copyDataBase() {
        val mInput: InputStream = context.assets.open(dbName)
        val outFileName: String = dbPath + dbName
        val output: OutputStream = FileOutputStream(outFileName)
        copy(mInput, output)
    }

    private fun copy(source: InputStream, destination: OutputStream) {
        try {
            val buffer = ByteArray(1024)
            var length: Int
            while (source.read(buffer).also { length = it } > 0) {
                destination.write(buffer, 0, length)
            }
        } finally {
            destination.flush()
            destination.close()
            source.close()
        }
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {}
    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {}

    companion object {
        private val TAG = DatabaseCopyHelper::class.java.simpleName
        private const val DB_VERSION = 1
        internal const val dbName: String = DatabaseManager.DB_NAME
    }
}