package com.example.regexptest.calendar.di

import com.example.regexptest.calendar.data.SharedPrefLocalSource
import com.example.regexptest.smoothie.di.singleton.CustomSingletonEntryPoint
import com.example.regexptest.smoothie.di.singleton.SmoothieSingletonComponentBuilder
import com.example.regexptest.smoothie.data.LocalSource
import com.example.regexptest.smoothie.di.viewmodel.SmoothieViewModelComponentBuilder
import com.example.regexptest.smoothie.di.viewmodel.SmoothieViewModelEntryPoint
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CalendarModule {

    @Provides
    @Singleton
    @CalendarApp
    fun provideCustomSingletonEntryPoint(
        @CalendarApp appId: String,
        @CalendarApp localSource: LocalSource,
        singletonEntryPointBuilder: SmoothieSingletonComponentBuilder,
    ): CustomSingletonEntryPoint {
        return EntryPoints.get(
            singletonEntryPointBuilder
                .appId(appId)
                .localSource(localSource)
                .build(),
            CustomSingletonEntryPoint::class.java
        )
    }

    @Provides
    @CalendarApp
    fun provideSmoothieViewModelEntryPoint(
        @CalendarApp singletonEntryPoint: CustomSingletonEntryPoint,
        viewModelComponentBuilder: SmoothieViewModelComponentBuilder,
    ): SmoothieViewModelEntryPoint {
        return EntryPoints.get(
            viewModelComponentBuilder
                .singletonDependencies(singletonEntryPoint)
                .build(),
            SmoothieViewModelEntryPoint::class.java
        )
    }

    @Provides
    @Singleton
    @CalendarApp
    fun provideSharedPrefLocalSource(@CalendarApp appId: String): LocalSource {
        return SharedPrefLocalSource(appId)
    }

    @Provides
    @Singleton
    @CalendarApp
    fun provideAppId(): String {
        return "Calendar"
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CalendarApp