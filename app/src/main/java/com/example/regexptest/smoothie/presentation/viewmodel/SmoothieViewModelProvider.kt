package com.example.regexptest.smoothie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.regexptest.smoothie.di.viewmodel.SmoothieViewModelEntryPoint
import dagger.assisted.AssistedFactory

@AssistedFactory
interface SmoothieViewModelFactory {
    fun create(viewModelEntryPoint: SmoothieViewModelEntryPoint): SmoothieViewModel
}

object SmoothieViewModelProvider {

    fun provideFactory(
        assistedFactory: SmoothieViewModelFactory,
        viewModelEntryPoint: SmoothieViewModelEntryPoint,
    ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return assistedFactory.create(viewModelEntryPoint) as T
        }
    }
}