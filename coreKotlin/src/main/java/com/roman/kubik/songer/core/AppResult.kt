package com.roman.kubik.songer.core

sealed class AppResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : AppResult<T>()
    data class Error(val throwable: Throwable) : AppResult<Nothing>()

}