package com.roman.kubik.spivanyklicejista.presentation.preferences

/**
 * Created by kubik on 3/10/18.
 */

interface PreferencesContract {
    interface View

    interface Presenter {
        fun start()
        fun destroy()
    }
}
