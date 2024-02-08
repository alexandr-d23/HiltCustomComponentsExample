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
import com.example.regexptest.smoothie.presentation.viewmodel.SmoothieViewModelFactory
import com.example.regexptest.smoothie.presentation.viewmodel.SmoothieViewModelProviderFactory
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
        val singletonComponent = singletonEntryPointBuilder
            .appId(appId)
            .localSource(localSource)
            .build()
        return EntryPoints.get(
            singletonComponent,
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
        val viewModelComponent = viewModelComponentBuilder
            .singletonDependencies(singletonEntryPoint)
            .actionInteractor(actionInteractor)
            .build()
        return EntryPoints.get(
            viewModelComponent,
            SmoothieViewModelEntryPoint::class.java
        )
    }

    @Provides
    @CalendarApp
    fun provideSmoothieFragmentEntryPoint(
        @CalendarApp singletonEntryPoint: CustomSingletonEntryPoint,
        fragmentComponentBuilder: SmoothieFragmentComponentBuilder,
        @CalendarApp webWrapper: WebWrapper,
    ): SmoothieFragmentEntryPoint {
        val fragmentComponent = fragmentComponentBuilder
            .singletonDependencies(singletonEntryPoint)
            .webWrapper(webWrapper)
            .build()
        return EntryPoints.get(
            fragmentComponent,
            SmoothieFragmentEntryPoint::class.java
        )
    }

    @Provides
    @CalendarApp
    fun provideSmoothieViewModelProviderFactory(
        viewModelFactory: SmoothieViewModelFactory,
        @CalendarApp singletonEntryPoint: CustomSingletonEntryPoint,
        @CalendarApp viewModelEntryPoint: SmoothieViewModelEntryPoint,
    ): SmoothieViewModelProviderFactory {
        return SmoothieViewModelProviderFactory(
            assistedFactory = viewModelFactory,
            smoothieInteractor = viewModelEntryPoint.interactor(),
            actionInteractor = viewModelEntryPoint.actionInteractor(),
            appId = singletonEntryPoint.appId()
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