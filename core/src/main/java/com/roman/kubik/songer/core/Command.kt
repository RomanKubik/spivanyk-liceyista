package com.roman.kubik.songer.core

import androidx.lifecycle.MutableLiveData

class Command<T>: MutableLiveData<T>() {

    fun clear() {
        postValue(null)
    }
}