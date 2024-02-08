package com.example.regexptest.notes.domain

import android.util.Log
import com.example.regexptest.smoothie.domain.ActionInteractor

class NotesActionInteractor(private val appId: String) : ActionInteractor {

    override fun doAction() {
        Log.d("MYTAG", "AppId = $appId ; $this")
    }
}