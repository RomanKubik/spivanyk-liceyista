package com.roman.kubik.spivanyklicejista.presentation.song

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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

    private lateinit var bookmarkItem: MenuItem

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d("MyTag", "onCreateOptionsMenu")
        menuInflater.inflate(R.menu.menu_song, menu)
        bookmarkItem = menu?.findItem(R.id.app_bar_bookmark)!!
        presenter.isFavouriteSong()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.app_bar_bookmark -> presenter.addToFavourite()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showSong(song: Song) {
        songTitle.text = song.title
        lyrics.text = chordsCreator.selectChords(song.lyrics, object : OnChordClickListener {
            override fun onChordClicked(chord: String) {
                Log.d(TAG, "chordClicked: $chord")
            }
        })
    }

    override fun showError(errorMessage: String) {
        Log.d(TAG, "showError: $errorMessage")
    }

    override fun isFavouriteSong(isFavourite: Boolean) {
        bookmarkItem.setIcon(if (isFavourite) R.drawable.ic_bookmark_black_24dp else R.drawable.ic_bookmark_border_black_24dp)
    }

    private fun init() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        lyrics.movementMethod = LinkMovementMethod.getInstance()
        presenter.fetchSong(intent.getIntExtra(Constants.Extras.SONG_ID, 0))
    }

    companion object {

        private val TAG = SongActivity::class.java.simpleName
    }
}
