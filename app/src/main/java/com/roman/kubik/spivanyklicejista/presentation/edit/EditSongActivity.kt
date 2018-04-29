package com.roman.kubik.spivanyklicejista.presentation.edit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.roman.kubik.spivanyklicejista.R
import com.roman.kubik.spivanyklicejista.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_edit_song.*

class EditSongActivity : BaseActivity(), EditSongContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_song)
    }

    override fun showProgress(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showError(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
