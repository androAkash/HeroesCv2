package com.akash.heroescv3.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.akash.heroescv3.model.FavouriteHeroModelItem
import com.akash.heroescv3.model.HeroModelItem

@Dao
interface FavouriteHeroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavouriteHero(hero: FavouriteHeroModelItem)

    @Query("SELECT * FROM fav_hero ORDER BY id DESC")
    fun getAllFavouriteHero(): LiveData<List<FavouriteHeroModelItem>>

    @Delete
    suspend fun deleteFabHeroes(hero:FavouriteHeroModelItem)
}