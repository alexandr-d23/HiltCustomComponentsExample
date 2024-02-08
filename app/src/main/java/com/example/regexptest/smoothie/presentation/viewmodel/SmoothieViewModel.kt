package com.example.regexptest.smoothie.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.regexptest.smoothie.domain.ActionInteractor
import com.example.regexptest.smoothie.domain.SmoothieInteractor
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class SmoothieViewModel @AssistedInject constructor(
    @Assisted val smoothieInteractor: SmoothieInteractor,
    @Assisted val actionInteractor: ActionInteractor,
    @Assisted val appId: String,
) : ViewModel() {

    fun doSomething() {
        Log.d("MYTAG", "AppId = $appId ; $this \n")
        actionInteractor.doAction()
        smoothieInteractor.doSomething()
    }
}