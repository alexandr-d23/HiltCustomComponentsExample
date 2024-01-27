package com.example.regexptest.smoothie.di.viewmodel

import com.example.regexptest.smoothie.di.singleton.CustomSingletonEntryPoint
import com.example.regexptest.smoothie.domain.SmoothieInteractor
import dagger.BindsInstance
import dagger.hilt.DefineComponent
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Scope

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class SmoothieViewModel

@DefineComponent(parent = SingletonComponent::class)
@SmoothieViewModel
interface SmoothieViewModelComponent

@DefineComponent.Builder
interface SmoothieViewModelComponentBuilder {
    fun singletonDependencies(@BindsInstance dependencies: CustomSingletonEntryPoint): SmoothieViewModelComponentBuilder
    fun build(): SmoothieViewModelComponent
}

@EntryPoint
@InstallIn(SmoothieViewModelComponent::class)
interface SmoothieViewModelEntryPoint {
    fun singletonDependencies(): CustomSingletonEntryPoint
    fun interactor(): SmoothieInteractor
}