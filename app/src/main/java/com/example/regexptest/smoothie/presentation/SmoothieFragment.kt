package com.example.regexptest.smoothie.presentation

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.regexptest.smoothie.di.components.SmoothieFragmentEntryPoint
import com.example.regexptest.smoothie.di.components.SmoothieViewModelEntryPoint
import com.example.regexptest.smoothie.presentation.viewmodel.SmoothieViewModel
import com.example.regexptest.smoothie.presentation.viewmodel.SmoothieViewModelFactory
import com.example.regexptest.smoothie.presentation.viewmodel.SmoothieViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
abstract class SmoothieFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: SmoothieViewModelFactory
    abstract val viewModelEntryPoint: SmoothieViewModelEntryPoint
    abstract val fragmentEntryPoint: SmoothieFragmentEntryPoint

    private val smoothieViewModel: SmoothieViewModel by viewModels {
        SmoothieViewModelProvider.provideFactory(
            assistedFactory = viewModelFactory,
            viewModelEntryPoint = viewModelEntryPoint,
        )
    }

    override fun onStart() {
        super.onStart()
        val appId = smoothieViewModel.component?.singletonDependencies()?.appId()
        Log.d("MYTAG", "AppId = $appId ; $this")
        smoothieViewModel.doSomething()
    }
}