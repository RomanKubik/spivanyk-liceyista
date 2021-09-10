package com.roman.kubik.ads.google

import android.app.Activity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.roman.kubik.ads.core.AdsModule
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoogleAdsModule @Inject constructor() : AdsModule {

    private val adRequest = AdRequest.Builder().build()
    private var mInterstitialAd: InterstitialAd? = null
    var activity: Activity? = null
        set(value) {
            field = value
            MobileAds.initialize(field ?: return)
            loadAd()
        }

    override fun loadAd() {
        activity?.let {
            InterstitialAd.load(it,
                    it.getString(R.string.google_ads_ad_id),
                    adRequest,
                    object : InterstitialAdLoadCallback() {
                        override fun onAdFailedToLoad(adError: LoadAdError) {
                            mInterstitialAd = null
                        }

                        override fun onAdLoaded(interstitialAd: InterstitialAd) {
                            mInterstitialAd = interstitialAd
                        }
                    })
        }
    }

    override fun showAd() {
        activity?.let { mInterstitialAd?.show(it) }

    }
}