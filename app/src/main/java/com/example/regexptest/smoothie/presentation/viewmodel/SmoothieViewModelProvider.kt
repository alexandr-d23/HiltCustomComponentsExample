package com.example.regexptest.smoothie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.regexptest.smoothie.domain.ActionInteractor
import com.example.regexptest.smoothie.domain.SmoothieInteractor
import dagger.assisted.AssistedFactory

@AssistedFactory
interface SmoothieViewModelFactory {
    fun create(
        smoothieInteractor: SmoothieInteractor,
        actionInteractor: ActionInteractor,
        appId: String,
    ): SmoothieViewModel
}

class SmoothieViewModelProviderFactory(
    private val assistedFactory: SmoothieViewModelFactory,
    private val smoothieInteractor: SmoothieInteractor,
    private val actionInteractor: ActionInteractor,
    private val appId: String,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return assistedFactory.create(
            smoothieInteractor = smoothieInteractor,
            actionInteractor = actionInteractor,
            appId = appId,
        ) as T
    }
}
