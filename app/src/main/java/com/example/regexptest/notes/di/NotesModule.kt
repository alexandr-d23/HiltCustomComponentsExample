package com.example.regexptest.notes.di

import com.example.regexptest.notes.data.RoomLocalSource
import com.example.regexptest.smoothie.di.singleton.CustomSingletonEntryPoint
import com.example.regexptest.smoothie.di.singleton.SmoothieSingletonComponentBuilder
import com.example.regexptest.smoothie.data.LocalSource
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
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NotesApp