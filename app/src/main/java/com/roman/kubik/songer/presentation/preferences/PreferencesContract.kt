package com.roman.kubik.songer.presentation.preferences

import com.roman.kubik.songer.domain.user.User

/**
 * Created by kubik on 3/10/18.
 */

interface PreferencesContract {
    interface View {
        fun showResetError()
        fun showUser(user: User)
    }

    interface Presenter {
        fun destroy()
        fun reset()
        fun signIn()
        fun onProfileUpdated()
    }

    companion object {
        const val CODE_SIGN_IN = 1
    }
}
