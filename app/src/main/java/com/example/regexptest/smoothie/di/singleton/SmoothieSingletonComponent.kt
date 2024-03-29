package com.example.regexptest.smoothie.di.singleton

import com.example.regexptest.smoothie.data.SmoothieRepository
import dagger.BindsInstance
import dagger.hilt.DefineComponent
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Scope

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class SmoothieSingleton

@DefineComponent(parent = SingletonComponent::class)
@SmoothieSingleton
interface SmoothieSingletonComponent {

    companion object {
        val components: HashMap<String, CustomSingletonEntryPoint> = hashMapOf()

        @Synchronized
        fun getOrCreateComponent(
            appId: String,
            builder: SmoothieSingletonComponentBuilder,
        ): CustomSingletonEntryPoint {
            if (components[appId] == null) {
                components[appId] = EntryPoints.get(
                    builder.appId(appId).build(),
                    CustomSingletonEntryPoint::class.java
                )
            }
            return components[appId]!!
        }
    }
}

@DefineComponent.Builder
interface SmoothieSingletonComponentBuilder {
    fun appId(@BindsInstance appId: String): SmoothieSingletonComponentBuilder
    fun build(): SmoothieSingletonComponent
}

@EntryPoint
@InstallIn(SmoothieSingletonComponent::class)
interface CustomSingletonEntryPoint {
    fun appId(): String
    fun repository(): SmoothieRepository
}
