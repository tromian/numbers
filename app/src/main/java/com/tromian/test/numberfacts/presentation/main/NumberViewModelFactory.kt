package com.tromian.test.numberfacts.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tromian.test.numberfacts.model.NumbersRepository
import javax.inject.Inject

class NumberViewModelFactory @Inject constructor(
    private val repository: NumbersRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            MainScreenVM::class.java -> {
                MainScreenVM(repository)
            }
            else -> throw IllegalStateException("Unknown view model class")
        }
        return viewModel as T
    }

}