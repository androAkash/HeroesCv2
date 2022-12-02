package com.akash.heroescv3.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_hero")
data class FavouriteHeroModelItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val slug: String,
    val images: Images
)