package com.roman.kubik.songer

/**
 * Application constants
 * Created by kubik on 1/20/18.
 */

interface Constants {
    companion object {

        const val APP_DB_FILE_NAME = "spivanyk.db"
        const val SHARE_TEXT_TYPE = "text/plain"
    }

    interface Extras {
        companion object {
            const val CATEGORY_ID = "category.id"
            const val SONG_ID = "song.id"
        }
    }

    interface RequestCode {
        companion object {
            const val EDIT_SONG = 1401
            const val PREFERENCES_ACTIVITY = 1402
        }
    }
}
