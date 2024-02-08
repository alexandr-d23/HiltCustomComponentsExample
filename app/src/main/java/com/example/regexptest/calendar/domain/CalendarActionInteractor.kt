package com.example.regexptest.calendar.domain

import android.util.Log
import com.example.regexptest.smoothie.domain.ActionInteractor

class CalendarActionInteractor(private val appId: String) : ActionInteractor {

    override fun doAction() {
        Log.d("MYTAG", "AppId = $appId ; $this")
    }
}