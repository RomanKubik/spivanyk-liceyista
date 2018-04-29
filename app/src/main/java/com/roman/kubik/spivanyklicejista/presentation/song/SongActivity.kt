package com.roman.kubik.spivanyklicejista.presentation.song

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.annimon.stream.function.Consumer
import com.roman.kubik.spivanyklicejista.Constants
import com.roman.kubik.spivanyklicejista.R
import com.roman.kubik.spivanyklicejista.domain.category.Category
import com.roman.kubik.spivanyklicejista.domain.chord.Chord
import com.roman.kubik.spivanyklicejista.domain.song.Song
import com.roman.kubik.spivanyklicejista.domain.utils.ChordsRemover
import com.roman.kubik.spivanyklicejista.general.android.SpivanykApplication
import com.roman.kubik.spivanyklicejista.presentation.BaseActivity
import com.roman.kubik.spivanyklicejista.presentation.Navigate
import com.roman.kubik.spivanyklicejista.presentation.song.di.SongModule
import com.roman.kubik.spivanyklicejista.presentation.view.ChordDialog
import com.roman.kubik.spivanyklicejista.utils.AssetsDrawableLoader
import com.roman.kubik.spivanyklicejista.utils.OnChordClickListener
import com.roman.kubik.spivanyklicejista.utils.SpannableStringChordsCreator
import kotlinx.android.synthetic.main.activity_song.*
import javax.inject.Inject


/**
 * Created by kubik on 1/20/18.
 */

class SongActivity : BaseActivity(), SongContract.View {

    private lateinit var bookmarkItem: MenuItem
    private lateinit var showChordsItem: MenuItem
    private lateinit var chordDialog: ChordDialog

    @Inject
    lateinit var presenter: SongContract.Presenter
    @Inject
    lateinit var chordsCreator: SpannableStringChordsCreator
    @Inject
    lateinit var chordsRemover: ChordsRemover
    @Inject
    lateinit var assetsDrawableLoader: AssetsDrawableLoader
    @Inject
    lateinit var chordsAdapter: ChordsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)
        SpivanykApplication.component.songComponent(SongModule(this)).inject(this)
        init()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d(TAG, "onCreateOptionsMenu")
        menuInflater.inflate(R.menu.menu_song, menu)
        bookmarkItem = menu?.findItem(R.id.app_bar_bookmark)!!
        showChordsItem = menu.findItem(R.id.app_bar_show_chords)
        presenter.fetchPreferences()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.app_bar_bookmark -> presenter.addToFavourite()
            R.id.app_bar_share -> presenter.shareSong()
            R.id.app_bar_edit -> presenter.edit()
            R.id.app_bar_show_chords -> presenter.showChords()
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Constants.RequestCodes.EDIT_SONG && resultCode == Activity.RESULT_OK) {
            presenter.fetchSong(intent.getIntExtra(Constants.Extras.SONG_ID, 0))
        }
    }

    override fun showSong(song: Song) {
        songTitle.text = song.title
        if (showChordsItem.isChecked) {
            lyrics.text = chordsCreator.selectChords(song.lyrics, object : OnChordClickListener {
                override fun onChordClicked(chord: String) {
                    showChord(chord)
                }
            }, Color.BLACK, resources.getColor(R.color.transparent_grey))
        } else {
            lyrics.text = chordsRemover.removeChords(song.lyrics)
        }
    }

    override fun showError(errorMessage: String) {
        Log.d(TAG, "showError: $errorMessage")
    }

    override fun isFavouriteSong(isFavourite: Boolean) {
        bookmarkItem.setIcon(if (isFavourite) R.drawable.ic_bookmark_black_24dp else R.drawable.ic_bookmark_border_black_24dp)
    }

    override fun showCategory(category: Category) {
        categoryView.text = category.name
    }

    override fun showDifficulty(difficulty: String) {

    }

    override fun showChords(chords: List<Chord>) {
        chordsAdapter.setChords(chords)
        chordDialog = ChordDialog(this, chords, assetsDrawableLoader)
    }

    override fun share(type: String, title: String, lyrics: String) {
        val intent = Intent()
                .setAction(Intent.ACTION_SEND)
                .setType(type)
                .putExtra(Intent.EXTRA_TITLE, title)
                .putExtra(Intent.EXTRA_TEXT, lyrics)
        startActivity(intent)
    }

    override fun edit(song: Song) {
        Navigate.toEditActivity(this, song)
    }

    override fun chordsVisible(visible: Boolean) {
        showChordsItem.isChecked = visible
        chordsList.visibility = if (visible) View.VISIBLE else View.GONE
        chords.visibility = if (visible) View.VISIBLE else View.GONE
        presenter.fetchSong(intent.getIntExtra(Constants.Extras.SONG_ID, 0))
    }

    private fun init() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        lyrics.movementMethod = LinkMovementMethod.getInstance()
        chordsAdapter.chordClickListener = Consumer { showChord(it.name) }
        chordsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        chordsList.adapter = chordsAdapter
    }

    private fun showChord(chordName: String) {
        chordDialog.showActiveChordName(chordName)
    }

    companion object {

        private val TAG = SongActivity::class.java.simpleName
    }
}
