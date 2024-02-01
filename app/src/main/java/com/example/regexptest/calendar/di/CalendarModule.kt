package com.example.regexptest.calendar.di

import com.example.regexptest.calendar.data.SharedPrefLocalSource
import com.example.regexptest.calendar.domain.CalendarActionInteractor
import com.example.regexptest.calendar.presentation.CalendarWebWrapper
import com.example.regexptest.smoothie.di.components.CustomSingletonEntryPoint
import com.example.regexptest.smoothie.di.components.SmoothieSingletonComponentBuilder
import com.example.regexptest.smoothie.data.LocalSource
import com.example.regexptest.smoothie.di.components.SmoothieFragmentComponentBuilder
import com.example.regexptest.smoothie.di.components.SmoothieFragmentEntryPoint
import com.example.regexptest.smoothie.di.components.SmoothieViewModelComponentBuilder
import com.example.regexptest.smoothie.di.components.SmoothieViewModelEntryPoint
import com.example.regexptest.smoothie.domain.ActionInteractor
import com.example.regexptest.smoothie.presentation.WebWrapper
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
        @CalendarApp actionInteractor: ActionInteractor,
    ): SmoothieViewModelEntryPoint {
        return EntryPoints.get(
            viewModelComponentBuilder
                .singletonDependencies(singletonEntryPoint)
                .actionInteractor(actionInteractor)
                .build(),
            SmoothieViewModelEntryPoint::class.java
        )
    }

    @Provides
    @CalendarApp
    fun provideSmoothieFragmentEntryPoint(
        @CalendarApp singletonEntryPoint: CustomSingletonEntryPoint,
        @CalendarApp viewModelEntryPoint: SmoothieViewModelEntryPoint,
        fragmentComponentBuilder: SmoothieFragmentComponentBuilder,
        @CalendarApp webWrapper: WebWrapper,
    ): SmoothieFragmentEntryPoint {
        return EntryPoints.get(
            fragmentComponentBuilder
                .singletonDependencies(singletonEntryPoint)
                .viewModelDependencies(viewModelEntryPoint)
                .webWrapper(webWrapper)
                .build(),
            SmoothieFragmentEntryPoint::class.java
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

    @Provides
    @CalendarApp
    fun provideActionInteractor(@CalendarApp appId: String): ActionInteractor {
        return CalendarActionInteractor(appId)
    }

    @Provides
    @CalendarApp
    fun provideCalendarWebWrapper(@CalendarApp appId: String): WebWrapper {
        return CalendarWebWrapper(appId)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CalendarApp