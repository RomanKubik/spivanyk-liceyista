package com.roman.kubik.songer.presentation.preferences

/**
 * Created by kubik on 3/10/18.
 */

interface PreferencesContract {
    interface View {
        fun showResetError()
    }

    interface Presenter {
        fun destroy()
        fun reset()
        fun signIn()
    }

    companion object {
        const val CODE_SIGN_IN = 1
    }
}
