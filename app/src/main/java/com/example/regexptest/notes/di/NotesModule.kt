package com.example.regexptest.notes.di

import com.example.regexptest.notes.data.RoomLocalSource
import com.example.regexptest.notes.domain.NotesActionInteractor
import com.example.regexptest.notes.presentation.NotesWebWrapper
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
class NotesModule {

    @Provides
    @Singleton
    @NotesApp
    fun provideCustomSingletonEntryPoint(
        @NotesApp appId: String,
        @NotesApp localSource: LocalSource,
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
    @NotesApp
    fun provideSmoothieViewModelEntryPoint(
        @NotesApp singletonEntryPoint: CustomSingletonEntryPoint,
        viewModelComponentBuilder: SmoothieViewModelComponentBuilder,
        @NotesApp actionInteractor: ActionInteractor,
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
    @NotesApp
    fun provideSmoothieFragmentEntryPoint(
        @NotesApp singletonEntryPoint: CustomSingletonEntryPoint,
        @NotesApp viewModelEntryPoint: SmoothieViewModelEntryPoint,
        fragmentComponentBuilder: SmoothieFragmentComponentBuilder,
        @NotesApp webWrapper: WebWrapper,
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
    @NotesApp
    fun provideRoomLocalSource(@NotesApp appId: String): LocalSource {
        return RoomLocalSource(appId)
    }

    @Provides
    @Singleton
    @NotesApp
    fun provideAppId(): String {
        return "Notes"
    }

    @Provides
    @NotesApp
    fun provideActionInteractor(@NotesApp appId: String): ActionInteractor {
        return NotesActionInteractor(appId)
    }

    @Provides
    @NotesApp
    fun provideNotesWebWrapper(@NotesApp appId: String): WebWrapper {
        return NotesWebWrapper(appId)
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NotesApp