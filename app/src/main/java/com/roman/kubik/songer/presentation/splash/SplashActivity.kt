package com.roman.kubik.songer.presentation.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.roman.kubik.songer.data.database.DatabaseCopyHelper
import com.roman.kubik.songer.presentation.Navigate

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
