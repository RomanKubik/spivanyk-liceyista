package com.roman.kubik.spivanyklicejista.presentation.list

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.annimon.stream.function.Consumer
import com.roman.kubik.spivanyklicejista.Constants
import com.roman.kubik.spivanyklicejista.R
import com.roman.kubik.spivanyklicejista.domain.song.Song
import com.roman.kubik.spivanyklicejista.general.android.SpivanykApplication.Companion.component
import com.roman.kubik.spivanyklicejista.presentation.BaseActivity
import com.roman.kubik.spivanyklicejista.presentation.Navigate
import com.roman.kubik.spivanyklicejista.presentation.list.di.ListModule
import com.roman.kubik.spivanyklicejista.utils.CategoryTitleMapper
import kotlinx.android.synthetic.main.activity_list.*
import javax.inject.Inject


/**
 * Main list activity
 * Created by kubik on 1/14/18.
 */

class ListActivity : BaseActivity(), ListContract.View {

    @Inject
    lateinit var presenter: ListContract.Presenter
    @Inject
    lateinit var songsAdapter: SongsAdapter
    @Inject
    lateinit var categoryTitleMapper: CategoryTitleMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        component.listComponent(ListModule(this)).inject(this)
        val categoryId = intent.getIntExtra(Constants.Extras.CATEGORY_ID, Constants.Category.ALL_ID)
        init(categoryId)
        presenter.fetchSongByCategory(categoryId)
    }

    override fun onStart() {
        presenter.fetchPreferences()
        super.onStart()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val menuItem = menu?.findItem(R.id.app_bar_search)
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

    private fun init(categoryId: Int) {
        songsAdapter.setOnClickListener(Consumer { s ->
            Navigate.toSongActivity(this, s)
        })
        songList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        songList.adapter = songsAdapter
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = categoryTitleMapper.getCategoryTitle(categoryId)
        addDividers()
    }

    private fun addDividers() {
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        songList.addItemDecoration(dividerItemDecoration)
    }

    companion object {

        private val TAG = ListActivity::class.java.simpleName
    }
}
