package com.roman.kubik.spivanyklicejista.presentation.preferences

import android.os.Bundle
import android.os.PersistableBundle
import com.roman.kubik.spivanyklicejista.R
import com.roman.kubik.spivanyklicejista.presentation.BaseActivity

/**
 * Created by kubik on 3/10/18.
 */
class PreferencesActivity : BaseActivity(), PreferencesContract.View {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_preferences)
    }
}