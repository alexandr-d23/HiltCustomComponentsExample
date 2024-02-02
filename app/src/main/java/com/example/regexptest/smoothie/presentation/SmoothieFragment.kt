package com.example.regexptest.smoothie.presentation

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.regexptest.smoothie.di.components.SmoothieFragmentEntryPoint
import com.example.regexptest.smoothie.presentation.viewmodel.SmoothieViewModel
import com.example.regexptest.smoothie.presentation.viewmodel.SmoothieViewModelProviderFactory
import dagger.Lazy
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class SmoothieFragment : Fragment() {

    abstract val fragmentEntryPoint: SmoothieFragmentEntryPoint
    abstract val viewModelProviderFactory: Lazy<SmoothieViewModelProviderFactory>

    val smoothieViewModel: SmoothieViewModel by viewModels { viewModelProviderFactory.get() }

    override fun onStart() {
        super.onStart()
        Log.d("MYTAG", "$this")
        smoothieViewModel.doSomething()
    }
}