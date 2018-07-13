package com.roman.kubik.songer.presentation.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import butterknife.OnClick
import com.roman.kubik.songer.Constants
import com.roman.kubik.songer.R
import com.roman.kubik.songer.domain.category.Category
import com.roman.kubik.songer.domain.song.Song
import com.roman.kubik.songer.general.android.SpivanykApplication.Companion.component
import com.roman.kubik.songer.presentation.BaseActivity
import com.roman.kubik.songer.presentation.Navigate
import com.roman.kubik.songer.presentation.main.di.MainModule
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {
    @Inject
    lateinit var presenter: MainContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.mainComponent(MainModule(this)).inject(this)
        init()
    }

    override fun onStart() {
        super.onStart()
        presenter.requestData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.app_bar_add_song -> Navigate.toEditActivity(this)
            R.id.app_bar_settings -> Navigate.toPreferencesActivity(this)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setPatrioticsCount(count: Int) {
        patrioticCategory.setDescription(String.format(getString(R.string.dsc_patriotic), count))
    }

    override fun setBonfiresCount(count: Int) {
        bonfireCategory.setDescription(String.format(getString(R.string.dsc_bonfire_songs), count))
    }

    override fun setAbroadsCount(count: Int) {
        abroadCategory.setDescription(String.format(getString(R.string.dsc_abroad_songs), count))
    }

    override fun setAllCount(count: Int) {
        allCategory.setDescription(String.format(getString(R.string.dsc_all_songs), count))
    }

    override fun setFavouriteCount(count: Int) {
        favouriteCategory.setDescription(String.format(getString(R.string.dsc_favourite), count))
    }

    override fun showError(error: Throwable) {
        error.printStackTrace()
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToSong(song: Song) {
        Navigate.toSongActivity(this, song)
    }

    private fun init() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }

    @OnClick(R.id.lastCategory)
    fun onLastClicked() {
        Navigate.toListActivity(this, Constants.Category.LAST_ID)
    }

    @OnClick(R.id.patrioticCategory)
    fun onPatrioticClicked() {
        Navigate.toListActivity(this, Constants.Category.PATRIOTIC_ID)
    }

    @OnClick(R.id.bonfireCategory)
    fun onBonfireClicked() {
        Navigate.toListActivity(this, Constants.Category.BONFIRE_ID)
    }

    @OnClick(R.id.abroadCategory)
    fun onAbroadClicked() {
        Navigate.toListActivity(this, Constants.Category.ABROAD_ID)
    }

    @OnClick(R.id.surpriseCategory)
    fun onRandomClicked() {
        presenter.requestRandom()
    }

    @OnClick(R.id.allCategory)
    fun onAllClicked() {
        Navigate.toListActivity(this, Constants.Category.ALL_ID)
    }

    @OnClick(R.id.favouriteCategory)
    fun onFavouriteClicked() {
        Navigate.toListActivity(this, Constants.Category.FAVOURITE_ID)
    }
}
