package com.akash.heroescv3.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.akash.heroescv3.model.FavouriteHeroModelItem
import com.akash.heroescv3.model.HeroModelItem

@Database(entities = [FavouriteHeroModelItem::class], version = 1)
@TypeConverters(Converters::class)
abstract class FavouriteHeroDatabase : RoomDatabase() {
    abstract fun getFabHeroDao():FavouriteHeroDao

//    companion object{
//        @Volatile
//        private var instance : FavouriteHeroDatabase ? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance?:createDatabase(context).also { instance = it }
//        }
//        private fun createDatabase(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                FavouriteHeroDatabase::class.java,
//                "fav_hero_db"
//            ).build()
//
//    }
}