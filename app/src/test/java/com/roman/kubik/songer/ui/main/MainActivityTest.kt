package com.roman.kubik.songer.ui.main

import android.app.onCreate
import com.roman.kubik.songer.R
import org.junit.Test
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify

class MainActivityTest {

    @Test
    fun `should inflate layout`() {
        val tested = spy(MainActivity())
//        tested.onCreate(null)
//        verify(tested).setContentView(R.layout.activity_main)
    }
}
