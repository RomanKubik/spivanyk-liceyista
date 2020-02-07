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
import com.roman.kubik.songer.general.di.ActivityComponent
import com.roman.kubik.songer.presentation.BaseActivity
import com.roman.kubik.songer.presentation.main.di.MainModule
import com.roman.kubik.songer.presentation.tutorial.TutorialDialog
import com.roman.kubik.songer.presentation.tutorial.TutorialType
import com.roman.kubik.songer.utils.getPluralString
import com.roman.kubik.songer.utils.hasOpenDialog
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View, TutorialDialog.DismissListener {

    @Inject
    lateinit var presenter: MainContract.Presenter
    @Inject
    lateinit var logger: LoggerInteractor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onCreated()
        setContentView(R.layout.activity_main)
        init()
    }

    override fun onStart() {
        super.onStart()
        presenter.requestData()
    }

    override fun injectActivity(activityComponent: ActivityComponent) {
        activityComponent.mainComponent(MainModule(this)).inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.app_bar_add_song -> presenter.addSong()
            R.id.app_bar_settings -> presenter.showSettings()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setMySongsCount(count: Int) {
        mySongsCategory.setDescription(getPluralString(R.plurals.dsc_my_songs, count))
    }

    override fun setPatrioticsCount(count: Int) {
        patrioticCategory.setDescription(getPluralString(R.plurals.dsc_patriotic, count))
    }

    override fun setBonfiresCount(count: Int) {
        bonfireCategory.setDescription(getPluralString(R.plurals.dsc_bonfire_songs, count))
    }

    override fun setAbroadsCount(count: Int) {
        abroadCategory.setDescription(getPluralString(R.plurals.dsc_abroad_songs, count))
    }

    override fun setAllCount(count: Int) {
        allCategory.setDescription(getPluralString(R.plurals.dsc_all_songs, count))
    }

    override fun setFavouriteCount(count: Int) {
        favouriteCategory.setDescription(getPluralString(R.plurals.dsc_favourite, count))
    }

    override fun showTutorial(tutorialType: TutorialType) {
        if (!hasOpenDialog()) {
            val dialog = TutorialDialog.getInstance(tutorialType)
            dialog.dismissListener = this
            dialog.show(supportFragmentManager, TUTORIAL_DLG_TAG)
        }
    }

    override fun showError(error: Throwable) {
        error.printStackTrace()
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

    override fun onDismissed(tutorialType: TutorialType) = presenter.tutorialShown(tutorialType)

    private fun init() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }

    @OnClick(R.id.lastCategory)
    fun onLastClicked() {
        logger.log(CategoryEvent("lastCategory"))
        presenter.selectCategory(Category.LAST_ID)
    }

    @OnClick(R.id.mySongsCategory)
    fun onMySongsClicked() {
        logger.log(CategoryEvent("mySongsCategory"))
        presenter.selectCategory(Category.USERS_ID)
    }

    @OnClick(R.id.patrioticCategory)
    fun onPatrioticClicked() {
        logger.log(CategoryEvent("patrioticCategory"))
        presenter.selectCategory(Category.PATRIOTIC_ID)
    }

    @OnClick(R.id.bonfireCategory)
    fun onBonfireClicked() {
        logger.log(CategoryEvent("bonfireCategory"))
        presenter.selectCategory(Category.BONFIRE_ID)
    }

    @OnClick(R.id.abroadCategory)
    fun onAbroadClicked() {
        logger.log(CategoryEvent("abroadCategory"))
        presenter.selectCategory(Category.ABROAD_ID)
    }

    @OnClick(R.id.surpriseCategory)
    fun onRandomClicked() {
        logger.log(CategoryEvent("surpriseCategory"))
        presenter.requestRandom()
    }

    @OnClick(R.id.allCategory)
    fun onAllClicked() {
        logger.log(CategoryEvent("allCategory"))
        presenter.selectCategory(Category.ALL_ID)
    }

    @OnClick(R.id.favouriteCategory)
    fun onFavouriteClicked() {
        logger.log(CategoryEvent("favouriteCategory"))
        presenter.selectCategory(Category.FAVOURITE_ID)
    }

    @OnClick(R.id.webCategory)
    fun onWebClicked() {
        logger.log(CategoryEvent("webCategory"))
        presenter.selectCategory(Category.WEB_ID)
    }

    companion object {
        private const val TUTORIAL_DLG_TAG = "tutorial_dlg_tag"
    }
}
