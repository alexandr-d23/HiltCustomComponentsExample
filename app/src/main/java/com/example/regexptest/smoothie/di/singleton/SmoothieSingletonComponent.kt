package com.example.regexptest.smoothie.di.singleton

import com.example.regexptest.smoothie.data.SmoothieRepository
import com.example.regexptest.smoothie.data.LocalSource
import dagger.BindsInstance
import dagger.hilt.DefineComponent
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Scope

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class SmoothieSingleton

@DefineComponent(parent = SingletonComponent::class)
@SmoothieSingleton
interface SmoothieSingletonComponent

@DefineComponent.Builder
interface SmoothieSingletonComponentBuilder {
    fun appId(@BindsInstance appId: String): SmoothieSingletonComponentBuilder
    fun localSource(@BindsInstance localSource: LocalSource): SmoothieSingletonComponentBuilder
    fun build(): SmoothieSingletonComponent
}

@EntryPoint
@InstallIn(SmoothieSingletonComponent::class)
interface CustomSingletonEntryPoint {
    fun appId(): String
    fun localSource(): LocalSource
    fun repository(): SmoothieRepository
}
