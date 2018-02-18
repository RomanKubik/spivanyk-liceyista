package com.roman.kubik.spivanyklicejista.presentation.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.roman.kubik.spivanyklicejista.data.database.DatabaseCopyHelper
import com.roman.kubik.spivanyklicejista.presentation.Navigate

import java.io.IOException

/**
 * Launcher activity
 * Created by kubik on 1/14/18.
 */

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            DatabaseCopyHelper(this).createDataBase()
            Navigate.toMainActivity(this)
        } catch (ignored: IOException) {
        }
        finish()
    }
}
