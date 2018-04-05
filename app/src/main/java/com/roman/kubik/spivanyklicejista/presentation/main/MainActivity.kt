package com.roman.kubik.spivanyklicejista.presentation.main

import android.os.Bundle
import com.roman.kubik.spivanyklicejista.R
import com.roman.kubik.spivanyklicejista.general.android.SpivanykApplication.Companion.component
import com.roman.kubik.spivanyklicejista.presentation.BaseActivity
import com.roman.kubik.spivanyklicejista.presentation.main.di.MainModule
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity: BaseActivity(), MainContract.View {

    @Inject
    lateinit var prsenter: MainContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.mainComponent(MainModule(this)).inject(this)
        prsenter.requestData()

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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
