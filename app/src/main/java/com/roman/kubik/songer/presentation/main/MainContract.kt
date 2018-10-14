package com.roman.kubik.songer.presentation.main

import com.roman.kubik.songer.domain.category.Category
import com.roman.kubik.songer.presentation.tutorial.TutorialType

interface MainContract {

    interface View {
        fun setPatrioticsCount(count: Int)
        fun setBonfiresCount(count: Int)
        fun setAbroadsCount(count: Int)
        fun setAllCount(count: Int)
        fun setFavouriteCount(count: Int)
        fun showTutorial(tutorialType: TutorialType)
        fun showError(error: Throwable)
    }

    interface Presenter {
        fun requestData()
        fun requestRandom()
        fun selectCategory(@Category.CategoryId categoryId: Int)
        fun addSong()
        fun showSettings()
        fun tutorialShown(type: TutorialType)
        fun destroy()
    }
}