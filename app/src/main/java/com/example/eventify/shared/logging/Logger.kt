package com.example.eventify.shared.logging

import timber.log.Timber

object Logger {
    fun debug(message: String){
        Timber.d(message)
    }

}