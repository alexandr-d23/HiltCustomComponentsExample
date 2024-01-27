package com.example.regexptest.smoothie.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.regexptest.smoothie.di.viewmodel.SmoothieViewModelEntryPoint
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class SmoothieViewModel @AssistedInject constructor(
    @Assisted viewModelEntryPoint: SmoothieViewModelEntryPoint,
) : ViewModel() {

    var component: SmoothieViewModelEntryPoint? = viewModelEntryPoint
    private val smoothieInteractor = viewModelEntryPoint.interactor()
    private val appId = viewModelEntryPoint.singletonDependencies().appId()

    fun doSomething() {
        Log.d("MYTAG", "AppId = $appId ; $this \n")
        smoothieInteractor.doSomething()
    }

    override fun onCleared() {
        component = null
        super.onCleared()
    }
}