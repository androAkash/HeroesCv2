package com.akash.heroescv3.repository

import com.akash.heroescv3.data.HeroApi
import com.akash.heroescv3.db.FavouriteHeroDatabase
import com.akash.heroescv3.model.FavouriteHeroModelItem
import javax.inject.Inject

class HeroRepository @Inject constructor(private val heroApi: HeroApi, private val db : FavouriteHeroDatabase) {
    suspend fun getHeroApi()= heroApi.getHeroes()

    suspend fun addFavouriteHero(hero : FavouriteHeroModelItem) = db.getFabHeroDao().addFavouriteHero(hero)

    suspend fun deleteHero(hero:FavouriteHeroModelItem) = db.getFabHeroDao().deleteFabHeroes(hero)

    fun getAllFabHeroes() =db.getFabHeroDao().getAllFavouriteHero()
}