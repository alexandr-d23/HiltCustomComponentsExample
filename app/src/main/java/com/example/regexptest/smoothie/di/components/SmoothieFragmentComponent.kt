package com.example.regexptest.smoothie.di.components

import com.example.regexptest.smoothie.presentation.WebWrapper
import dagger.BindsInstance
import dagger.hilt.DefineComponent
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@DefineComponent(parent = SingletonComponent::class)
@SmoothieViewModelScoped
interface SmoothieFragmentComponent

@DefineComponent.Builder
interface SmoothieFragmentComponentBuilder {

    fun singletonDependencies(
        @BindsInstance dependencies: CustomSingletonEntryPoint
    ): SmoothieFragmentComponentBuilder

    fun viewModelDependencies(
        @BindsInstance dependencies: SmoothieViewModelEntryPoint
    ): SmoothieFragmentComponentBuilder

    fun webWrapper(
        @BindsInstance webWrapper: WebWrapper
    ): SmoothieFragmentComponentBuilder

    fun build(): SmoothieFragmentComponent
}

@EntryPoint
@InstallIn(SmoothieFragmentComponent::class)
interface SmoothieFragmentEntryPoint {
    fun singletonDependencies(): CustomSingletonEntryPoint
    fun viewModelDependencies(): SmoothieViewModelEntryPoint
    fun webWrapper(): WebWrapper
}