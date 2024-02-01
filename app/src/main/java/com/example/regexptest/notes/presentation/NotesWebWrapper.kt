package com.example.regexptest.notes.presentation

import android.util.Log
import com.example.regexptest.smoothie.presentation.WebWrapper

class NotesWebWrapper(private val appId: String) : WebWrapper {

    override fun doSomething() {
        Log.d("MYTAG", "AppId = $appId ; $this")
    }
}