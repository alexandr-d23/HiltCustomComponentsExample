package com.example.regexptest.calendar.data

import android.util.Log
import com.example.regexptest.smoothie.data.LocalSource

class SharedPrefLocalSource(private val appId: String) : LocalSource {

    override fun doAction() {
        Log.d("MYTAG", "AppId = $appId ; $this \n\n" +
            " ")
    }
}