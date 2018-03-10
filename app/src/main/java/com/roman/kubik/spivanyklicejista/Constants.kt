package com.roman.kubik.spivanyklicejista

/**
 * Application constants
 * Created by kubik on 1/20/18.
 */

interface Constants {
    companion object {

        const val APP_DB_FILE_NAME = "spivanyk.db"
    }

    interface Extras {
        companion object {
            const val SONG_ID = "song.id"
        }
    }

    interface RequestCode {
        companion object {
            const val PREFERENCES_ACTIVITY = 7901
        }
    }
}
