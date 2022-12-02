package com.akash.heroescv3.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akash.heroescv3.model.FavouriteHeroModelItem
import com.akash.heroescv3.model.HeroModelItem
import com.akash.heroescv3.repository.HeroRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val heroRepository: HeroRepository
    ) : ViewModel() {
        private val _response = MutableLiveData<List<HeroModelItem>>()
    val responseHero : LiveData<List<HeroModelItem>>
    get() = _response

    init {
        getAllHeroes()
    }

    fun addFabHero(hero:FavouriteHeroModelItem) = viewModelScope.launch {
        heroRepository.addFavouriteHero(hero)
    }

    fun delete(hero: FavouriteHeroModelItem) = viewModelScope.launch {
        heroRepository.deleteHero(hero)
    }

    fun getAllFabHeroes() = heroRepository.getAllFabHeroes()

    private fun getAllHeroes()  = viewModelScope.launch {
        heroRepository.getHeroApi().let { response ->
            if (response.isSuccessful){
                _response.postValue(response.body())
            }else{
                Log.d("TAG", "getAllHeroes: ${response.code()}")
            }
        }
    }

}