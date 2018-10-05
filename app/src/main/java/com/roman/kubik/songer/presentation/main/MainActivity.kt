package com.roman.kubik.songer.presentation.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import butterknife.OnClick
import com.roman.kubik.songer.R
import com.roman.kubik.songer.domain.category.Category
import com.roman.kubik.songer.domain.logger.LoggerInteractor
import com.roman.kubik.songer.domain.logger.event.CategoryEvent
import com.roman.kubik.songer.domain.song.Song
import com.roman.kubik.songer.general.di.ActivityComponent
import com.roman.kubik.songer.presentation.BaseActivity
import com.roman.kubik.songer.presentation.main.di.MainModule
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {
    @Inject
    lateinit var presenter: MainContract.Presenter
    @Inject
    lateinit var logger: LoggerInteractor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    override fun injectActivity(activityComponent: ActivityComponent) {
        activityComponent.mainComponent(MainModule(this)).inject(this)
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
        logger.log(CategoryEvent("lastCategory"))
        Navigate.toListActivity(this, Category.LAST_ID)
    }

    @OnClick(R.id.patrioticCategory)
    fun onPatrioticClicked() {
        logger.log(CategoryEvent("patrioticCategory"))
        Navigate.toListActivity(this, Category.PATRIOTIC_ID)
    }

    @OnClick(R.id.bonfireCategory)
    fun onBonfireClicked() {
        logger.log(CategoryEvent("bonfireCategory"))
        Navigate.toListActivity(this, Category.BONFIRE_ID)
    }

    @OnClick(R.id.abroadCategory)
    fun onAbroadClicked() {
        logger.log(CategoryEvent("abroadCategory"))
        Navigate.toListActivity(this, Category.ABROAD_ID)
    }

    @OnClick(R.id.surpriseCategory)
    fun onRandomClicked() {
        logger.log(CategoryEvent("surpriseCategory"))
        presenter.requestRandom()
    }

    @OnClick(R.id.allCategory)
    fun onAllClicked() {
        logger.log(CategoryEvent("allCategory"))
        Navigate.toListActivity(this, Category.ALL_ID)
    }

    @OnClick(R.id.favouriteCategory)
    fun onFavouriteClicked() {
        logger.log(CategoryEvent("favouriteCategory"))
        Navigate.toListActivity(this, Category.FAVOURITE_ID)
    }
}
