package com.roman.kubik.spivanyklicejista.presentation.main

import android.os.Bundle
import android.widget.Toast
import butterknife.OnClick
import com.roman.kubik.spivanyklicejista.R
import com.roman.kubik.spivanyklicejista.domain.category.Category
import com.roman.kubik.spivanyklicejista.domain.song.Song
import com.roman.kubik.spivanyklicejista.general.android.SpivanykApplication.Companion.component
import com.roman.kubik.spivanyklicejista.presentation.BaseActivity
import com.roman.kubik.spivanyklicejista.presentation.Navigate
import com.roman.kubik.spivanyklicejista.presentation.main.di.MainModule
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity: BaseActivity(), MainContract.View {
    @Inject
    lateinit var presenter: MainContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.mainComponent(MainModule(this)).inject(this)
        presenter.requestData()
    }

    override fun setPatrioticsCount(count: Int) {
        categoryDescriptionPatriotic.text = String.format(getString(R.string.dsc_patriotic), count)
    }

    override fun setBonfiresCount(count: Int) {
        categoryDescriptionBonfire.text = String.format(getString(R.string.dsc_bonfire_songs), count)
    }

    override fun setAbroadsCount(count: Int) {
        categoryDescriptionAbroad.text = String.format(getString(R.string.dsc_abroad_songs), count)
    }

    override fun setAllCount(count: Int) {
        categoryDescriptionAll.text = String.format(getString(R.string.dsc_all_songs), count)
    }

    override fun showError(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

    override fun navigateToCategory(category: Category?) {
        Navigate.toListActivity(this, category)
    }

    override fun navigateToSong(song: Song) {
        Navigate.toSongActivity(this, song)
    }


    @OnClick(R.id.layoutLast)
    fun onLastClicked() {
        presenter.onLastClicked()
    }

    @OnClick(R.id.layoutPatriotic)
    fun onPatrioticClicked() {
        presenter.onPatrioticClicked()
    }

    @OnClick(R.id.layoutBonfire)
    fun onBonfireClicked() {
        presenter.onBonfireClicked()
    }

    @OnClick(R.id.layoutAbroad)
    fun onAbroadClicked() {
        presenter.onAbroadClicked()
    }

    @OnClick(R.id.layoutRandom)
    fun onRandomClicked() {
        presenter.requestRandom()
    }

    @OnClick(R.id.layoutAll)
    fun onAllClicked() {
        presenter.onAllClicked()
    }
}
