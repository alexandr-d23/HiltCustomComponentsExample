package com.example.regexptest.notes.data

import android.util.Log
import com.example.regexptest.smoothie.data.LocalSource

class RoomLocalSource(private val appId: String) : LocalSource {

    override fun doAction() {
        Log.d("MYTAG", "AppId = $appId ; $this \n\n" +
            " ")
    }
}