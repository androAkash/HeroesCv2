package com.akash.heroescv3.local.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.akash.heroescv3.db.FavouriteHeroDao
import com.akash.heroescv3.db.FavouriteHeroDatabase
import com.akash.heroescv3.model.FavouriteHeroModelItem
import com.akash.heroescv3.model.Images
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class FavouriteHeroTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database : FavouriteHeroDatabase
    private lateinit var dao : FavouriteHeroDao

    @Before
    fun setUp(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FavouriteHeroDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.getFabHeroDao()
    }

    @After
    fun tearDown(){
        database.close()
    }

    //insert
    @Test
    fun addFavouriteHero() = runTest {
        val favouriteHero = FavouriteHeroModelItem(
            1,"name","slug", Images(lg = "")
        )
        dao.addFavouriteHero(favouriteHero)

        val getAllHeroes = dao.getAllFavouriteHero().getOrAwaitValueTest()

        assertThat(getAllHeroes).contains(favouriteHero)
    }

    @Test
    fun deleteFavouriteHero() = runTest {
        val favouriteHero = FavouriteHeroModelItem(
            1,"name","slug", Images(lg = "")
        )
        dao.addFavouriteHero(favouriteHero)
        dao.deleteFabHeroes(favouriteHero)

        val getAllHeroes = dao.getAllFavouriteHero().getOrAwaitValueTest()

        assertThat(getAllHeroes).doesNotContain(favouriteHero)
    }
}