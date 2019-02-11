package com.roman.kubik.songer.presentation.list

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.annimon.stream.function.Consumer
import com.google.android.material.snackbar.Snackbar
import com.roman.kubik.songer.Constants
import com.roman.kubik.songer.R
import com.roman.kubik.songer.domain.category.Category
import com.roman.kubik.songer.domain.song.Song
import com.roman.kubik.songer.general.di.ActivityComponent
import com.roman.kubik.songer.presentation.BaseActivity
import com.roman.kubik.songer.presentation.list.di.ListModule
import com.roman.kubik.songer.presentation.main.MainActivity
import com.roman.kubik.songer.presentation.tutorial.TutorialDialog
import com.roman.kubik.songer.presentation.tutorial.TutorialType
import com.roman.kubik.songer.utils.CategoryTitleMapper
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject


/**
 * Main list activity
 * Created by kubik on 1/14/18.
 */

class ListActivity : BaseActivity(), ListContract.View, SongsAdapter.OnItemClickListener {

    @Inject
    lateinit var presenter: ListContract.Presenter
    @Inject
    lateinit var songsAdapter: SongsAdapter
    @Inject
    lateinit var categoryTitleMapper: CategoryTitleMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val categoryId = intent.getIntExtra(Constants.Extras.CATEGORY_ID, Category.ALL_ID)
        init(categoryId)
    }

    override fun injectActivity(activityComponent: ActivityComponent) {
        activityComponent.listComponent(ListModule(this)).inject(this)
    }

    override fun onStart() {
        val categoryId = intent.getIntExtra(Constants.Extras.CATEGORY_ID, Category.ALL_ID)
        presenter.fetchSongByCategory(categoryId)
        presenter.fetchPreferences()
        super.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_song_list, menu)
        val menuItem = menu?.findItem(R.id.app_bar_search)
        val searchView = if (menuItem?.actionView != null) menuItem.actionView as SearchView else null
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.filter(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.filter(newText!!)
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> this.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }

    override fun showProgress(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showError(errorMessage: String?) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onPreferencesFetched(showChords: Boolean) {
        songsAdapter.setShowChords(showChords)
    }

    override fun onSongsFetched(songList: List<Song>) {
        songsAdapter.setSongList(songList)
    }

    override fun onSongRemoved(song: Song) {
        val snackbar = Snackbar.make(progressBar, getString(R.string.song_was_deleted, song.title), Snackbar.LENGTH_LONG)
        snackbar.setAction(R.string.undo) { presenter.undoDeletion() }
        snackbar.show()
    }

    override fun showDeletionTutorialDialog() {
        val dialog = TutorialDialog.getInstance(TutorialType.TYPE_DELETE_SONG)
        dialog.show(supportFragmentManager, null)
        presenter.onTutorialDialogShowed()
    }

    override fun onItemClicked(song: Song) {
        presenter.showSong(song)
    }

    override fun onItemLongClicked(song: Song) {
        AlertDialog.Builder(this)
                .setTitle(R.string.ttl_remove_song)
                .setMessage(R.string.msg_remove_song)
                .setPositiveButton(R.string.remove) { _, _ ->
                    presenter.deleteSong(song)
                }
                .setNegativeButton(R.string.discard) { _, _ ->
                }
                .show()
    }

    private fun init(categoryId: Int) {
        songsAdapter.setOnClickListener(this)
        songList.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        songList.adapter = songsAdapter
        addDividers()
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = categoryTitleMapper.getCategoryTitle(categoryId)
    }

    private fun addDividers() {
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        songList.addItemDecoration(dividerItemDecoration)
    }

}
