package com.roman.kubik.songer.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class Command<T>: MutableLiveData<T>() {

    private fun clear() {
        postValue(null)
    }

    override fun observe(lifecycleOwner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(lifecycleOwner) {
            if (it != null) {
                observer.onChanged(it)
                clear()
            }
        }
    }
}