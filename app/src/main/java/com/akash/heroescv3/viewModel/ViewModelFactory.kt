package com.akash.heroescv3.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.akash.heroescv3.repository.HeroRepository

class ViewModelFactory(private val heroRepository: HeroRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HeroViewModel(heroRepository) as T
    }
}