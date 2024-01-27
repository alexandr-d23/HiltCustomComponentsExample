package com.example.regexptest.smoothie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.regexptest.smoothie.di.singleton.CustomSingletonEntryPoint
import com.example.regexptest.smoothie.di.viewmodel.SmoothieViewModelComponentBuilder
import com.example.regexptest.smoothie.di.viewmodel.SmoothieViewModelEntryPoint
import dagger.assisted.AssistedFactory
import dagger.hilt.EntryPoints

@AssistedFactory
interface SmoothieViewModelFactory {
    fun create(viewModelEntryPoint: SmoothieViewModelEntryPoint): SmoothieViewModel
}

object SmoothieViewModelProvider {

    fun provideFactory(
        assistedFactory: SmoothieViewModelFactory,
        viewModelComponentBuilder: SmoothieViewModelComponentBuilder,
        singletonEntryPoint: CustomSingletonEntryPoint,
    ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val viewModelEntryPoint = EntryPoints.get(
                viewModelComponentBuilder.singletonDependencies(singletonEntryPoint).build(),
                SmoothieViewModelEntryPoint::class.java
            )
            return assistedFactory.create(viewModelEntryPoint) as T
        }
    }
}