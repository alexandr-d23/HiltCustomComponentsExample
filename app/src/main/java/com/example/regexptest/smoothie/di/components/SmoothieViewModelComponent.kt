package com.example.regexptest.smoothie.di.components

import com.example.regexptest.smoothie.domain.ActionInteractor
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
annotation class SmoothieViewModelScoped

@DefineComponent(parent = SingletonComponent::class)
@SmoothieViewModelScoped
interface SmoothieViewModelComponent

@DefineComponent.Builder
interface SmoothieViewModelComponentBuilder {

    fun singletonDependencies(
        @BindsInstance dependencies: CustomSingletonEntryPoint
    ): SmoothieViewModelComponentBuilder

    fun actionInteractor(
        @BindsInstance actionInteractor: ActionInteractor
    ): SmoothieViewModelComponentBuilder

    fun build(): SmoothieViewModelComponent
}

@EntryPoint
@InstallIn(SmoothieViewModelComponent::class)
interface SmoothieViewModelEntryPoint {
    fun singletonDependencies(): CustomSingletonEntryPoint
    fun actionInteractor(): ActionInteractor
    fun interactor(): SmoothieInteractor
}