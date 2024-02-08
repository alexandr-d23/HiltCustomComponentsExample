package com.example.regexptest.smoothie.data

import android.util.Log
import com.example.regexptest.smoothie.di.components.SmoothieSingletonScoped
import javax.inject.Inject

@SmoothieSingletonScoped
class SmoothieRepository @Inject constructor(
    private val appId: String,
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource,
) {
    fun getSomething(){
        Log.d("MYTAG", "AppId = $appId ; $this \n")
        remoteSource.doAction()
        localSource.doAction()
    }
}