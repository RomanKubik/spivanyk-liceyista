package com.roman.kubik.songer.presentation.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import butterknife.OnClick
import com.roman.kubik.songer.R
import com.roman.kubik.songer.domain.category.Category
import com.roman.kubik.songer.domain.logger.LoggerInteractor
import com.roman.kubik.songer.domain.logger.event.CategoryEvent
import com.roman.kubik.songer.general.di.FragmentComponent
import com.roman.kubik.songer.presentation.base.BaseFragment
import com.roman.kubik.songer.presentation.main.di.MainModule
import com.roman.kubik.songer.presentation.tutorial.TutorialDialog
import com.roman.kubik.songer.presentation.tutorial.TutorialType
import com.roman.kubik.songer.utils.getPluralString
import com.roman.kubik.songer.utils.hasOpenDialog
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : BaseFragment(), MainContract.View, TutorialDialog.DismissListener {

    @Inject
    lateinit var presenter: MainContract.Presenter
    @Inject
    lateinit var logger: LoggerInteractor

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        presenter.requestData()
    }

    private fun init() {
        setHasOptionsMenu(true)
        getBaseActivity().setSupportActionBar(toolbar)
        getBaseActivity().supportActionBar?.title = getString(R.string.app_name)
    }

    override fun injectFragment(fragmentComponent: FragmentComponent) {
        fragmentComponent.mainComponent(MainModule(this)).inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
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
        if (!hasOpenDialog() && fragmentManager != null) {
            val dialog = TutorialDialog.getInstance(tutorialType)
            dialog.dismissListener = this
            dialog.show(fragmentManager!!, TUTORIAL_DLG_TAG)
        }
    }

    override fun showError(error: Throwable) {
        error.printStackTrace()
        Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
    }

    override fun onDismissed(tutorialType: TutorialType) = presenter.tutorialShown(tutorialType)

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

    companion object {
        private const val TUTORIAL_DLG_TAG = "tutorial_dlg_tag"
    }
}
