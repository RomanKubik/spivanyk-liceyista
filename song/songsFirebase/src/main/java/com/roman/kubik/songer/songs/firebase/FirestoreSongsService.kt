package com.roman.kubik.songer.songs.firebase

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.roman.kubik.songer.core.AppResult
import com.roman.kubik.songer.songs.domain.song.Song
import com.roman.kubik.songer.songs.domain.song.SongCategory
import com.roman.kubik.songer.songs.domain.song.SongsUpdateService
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject

class FirestoreSongsService @Inject constructor(context: Context) : SongsUpdateService {

    private val database: FirebaseFirestore = Firebase.firestore
    private val preferences: SharedPreferences = context.getSharedPreferences(FIRESTORE_PREFERENCES, Context.MODE_PRIVATE)

    @SuppressLint("ApplySharedPref")
    override suspend fun fetchNewSongs(forceFetch: Boolean): AppResult<List<Song>> {
        val lastUpdateTimestamp = preferences.getLong(FIRESTORE_LAST_UPDATED_KEY, -1)
        val uncompletedForceFetch = preferences.getBoolean(FIRESTORE_FORCE_UPDATE_KEY, false)

        if (!(forceFetch || uncompletedForceFetch)) {
            if (System.currentTimeMillis() - lastUpdateTimestamp < MIN_UPDATE_TIMEOUT_MILLIS)
                return AppResult.Success(emptyList())

            val remoteLastUpdateTimestamp = database.document(FIRESTORE_METADATA_DOC)
                    .get()
                    .await()
                    .getTimestamp(FIRESTORE_METADATA_LAST_UPDATED)?.seconds?.times(1000L) ?: -1

            if (remoteLastUpdateTimestamp <= lastUpdateTimestamp)
                return AppResult.Success(emptyList())

            preferences.edit().apply {
                putBoolean(FIRESTORE_FORCE_UPDATE_KEY, true)
            }.commit()
        }
        val timestamp = Timestamp(Date(lastUpdateTimestamp))

        val data = database.collection(FIRESTORE_SONGS_DOC)
                .whereGreaterThanOrEqualTo(FIRESTORE_SONGS_TIMESTAMP, timestamp)
                .get()
                .await()

        val result = mutableListOf<Song>()

        for (doc in data.documents) {
            val song = Song(
                    doc.id,
                    doc[FIRESTORE_SONGS_TITLE].toString(),
                    doc[FIRESTORE_SONGS_LYRICS].toString(),
                    mapFirestoreCategoryType(doc[FIRESTORE_SONGS_CATEGORY_TYPE].toString()),
                    false
            )
            result.add(song)
        }

        preferences.edit().apply {
            putLong(FIRESTORE_LAST_UPDATED_KEY, System.currentTimeMillis())
            putBoolean(FIRESTORE_FORCE_UPDATE_KEY, false)
        }.commit()

        return AppResult.Success(result)
    }

    private fun mapFirestoreCategoryType(categoryType: String?): SongCategory {
        return when (categoryType) {
            "patriotic" -> SongCategory.PATRIOTIC
            "bonfire" -> SongCategory.BONFIRE
            "abroad" -> SongCategory.ABROAD
            else -> SongCategory.MY_SONGS
        }
    }

    companion object {
        private const val FIRESTORE_PREFERENCES = "firestore.preferences"

        private const val FIRESTORE_LAST_UPDATED_KEY = "firestore.last.updated"
        private const val FIRESTORE_FORCE_UPDATE_KEY = "firestore.force.update"

        private const val MIN_UPDATE_TIMEOUT_MILLIS = 2_419_200_000L // 4 weeks

        private const val FIRESTORE_METADATA_DOC = "meta/data"
        private const val FIRESTORE_METADATA_LAST_UPDATED = "lastUpdate"

        private const val FIRESTORE_SONGS_DOC = "songs"
        private const val FIRESTORE_SONGS_TIMESTAMP = "timestamp"
        private const val FIRESTORE_SONGS_TITLE = "title"
        private const val FIRESTORE_SONGS_LYRICS = "lyrics"
        private const val FIRESTORE_SONGS_CATEGORY_TYPE = "category"
    }

}