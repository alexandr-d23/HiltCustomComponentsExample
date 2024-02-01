package com.example.regexptest.calendar.presentation

import android.util.Log
import com.example.regexptest.smoothie.presentation.WebWrapper

class CalendarWebWrapper(private val appId: String) : WebWrapper {

    override fun doSomething() {
        Log.d("MYTAG", "AppId = $appId ; $this")
    }
}