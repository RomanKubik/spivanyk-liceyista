package com.roman.kubik.spivanyklicejista.presentation.song

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import com.roman.kubik.spivanyklicejista.Constants
import com.roman.kubik.spivanyklicejista.R
import com.roman.kubik.spivanyklicejista.domain.song.Song
import com.roman.kubik.spivanyklicejista.general.android.SpivanykApplication
import com.roman.kubik.spivanyklicejista.presentation.BaseActivity
import com.roman.kubik.spivanyklicejista.presentation.song.di.SongModule
import com.roman.kubik.spivanyklicejista.utils.OnChordClickListener
import com.roman.kubik.spivanyklicejista.utils.SpannableStringChordsCreator
import kotlinx.android.synthetic.main.activity_song.*
import javax.inject.Inject


/**
 * Created by kubik on 1/20/18.
 */

class SongActivity : BaseActivity(), SongContract.View {

    @Inject
    lateinit var presenter: SongContract.Presenter
    @Inject
    lateinit var chordsCreator: SpannableStringChordsCreator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)
        SpivanykApplication.component.songComponent(SongModule(this)).inject(this)
        init()
    }

    override fun showSong(song: Song) {
        tvTitle.text = song.title
        tvLyrics.text = chordsCreator.selectChords(song.lyrics, object : OnChordClickListener {
            override fun onChordClicked(chord: String) {
                Log.d(TAG, "chordClicked: $chord")
            }
        })
    }

    override fun showError(errorMessage: String) {
        Log.d(TAG, "showError: $errorMessage")
    }

    private fun init() {
        tvLyrics.movementMethod = LinkMovementMethod.getInstance()
        presenter.fetchSong(intent.getIntExtra(Constants.Extras.SONG_ID, 0))
    }

    companion object {

        private val TAG = SongActivity::class.java.simpleName
    }
}
