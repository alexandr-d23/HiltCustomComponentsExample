package com.example.regexptest.smoothie.di.models

import com.example.regexptest.smoothie.di.components.CustomSingletonEntryPoint
import com.example.regexptest.smoothie.di.components.SmoothieViewModelScoped
import com.example.regexptest.smoothie.di.components.SmoothieViewModelComponent
import com.example.regexptest.smoothie.domain.SmoothieInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

@Module
@InstallIn(SmoothieViewModelComponent::class)
class ViewModelModule {

    @Provides
    @SmoothieViewModelScoped
    fun provideInteractor(
        dependencies: CustomSingletonEntryPoint,
    ): SmoothieInteractor {
        return SmoothieInteractor(dependencies.appId(), dependencies.repository())
    }
}