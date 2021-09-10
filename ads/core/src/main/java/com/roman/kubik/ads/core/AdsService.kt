package com.roman.kubik.ads.core

import javax.inject.Inject

class AdsService @Inject constructor(private val adsModule: AdsModule) {

    private var adsShowTimes: Int = 0

    fun tryShowAd() {
        if (adsShowTimes != 0 && adsShowTimes % 10 == 0) {
            adsModule.showAd()
            adsModule.loadAd()
            adsShowTimes = 0
        } else {
            adsShowTimes++
        }
    }

}