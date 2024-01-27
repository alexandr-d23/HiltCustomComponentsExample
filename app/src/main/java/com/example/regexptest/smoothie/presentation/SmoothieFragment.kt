package com.example.regexptest.smoothie.presentation

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.regexptest.smoothie.di.singleton.CustomSingletonEntryPoint
import com.example.regexptest.smoothie.di.viewmodel.SmoothieViewModelComponentBuilder
import com.example.regexptest.smoothie.presentation.viewmodel.SmoothieViewModel
import com.example.regexptest.smoothie.presentation.viewmodel.SmoothieViewModelFactory
import com.example.regexptest.smoothie.presentation.viewmodel.SmoothieViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class SmoothieFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: SmoothieViewModelFactory
    @Inject
    lateinit var viewModelComponentBuilder: SmoothieViewModelComponentBuilder

    abstract val customSingletonEntryPoint: CustomSingletonEntryPoint

    private val smoothieViewModel: SmoothieViewModel by viewModels {
        SmoothieViewModelProvider.provideFactory(
            assistedFactory = viewModelFactory,
            viewModelComponentBuilder = viewModelComponentBuilder,
            singletonEntryPoint = customSingletonEntryPoint,
        )
    }

    override fun onStart() {
        super.onStart()
        val appId = smoothieViewModel.component?.singletonDependencies()?.appId()
        Log.d("MYTAG", "AppId = $appId ; $this")
        smoothieViewModel.doSomething()
    }
}