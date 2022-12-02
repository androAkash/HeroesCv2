package com.akash.heroescv3.di

import android.content.Context
import androidx.room.Room
import com.akash.heroescv3.data.HeroApi
import com.akash.heroescv3.db.FavouriteHeroDatabase
import com.akash.heroescv3.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HeroModule {

    @Provides
@Singleton
    fun provideBaseUrl()=Constants.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL:String):HeroApi =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HeroApi::class.java)


    @Provides
    @Singleton
    fun provideHabitDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        FavouriteHeroDatabase::class.java,
        "fav_hero_db"
    ).build()
    @Singleton
    @Provides
    fun provideDao(database: FavouriteHeroDatabase) = database.getFabHeroDao()
}