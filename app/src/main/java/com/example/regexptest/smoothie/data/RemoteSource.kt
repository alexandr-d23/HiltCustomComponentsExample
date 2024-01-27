package com.example.regexptest.smoothie.data

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteSource @Inject constructor() {

    fun doAction() {
        Log.d("MYTAG", "$this")
    }
}