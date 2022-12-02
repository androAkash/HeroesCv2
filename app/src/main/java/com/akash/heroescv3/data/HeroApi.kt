package com.akash.heroescv3.data

import com.akash.heroescv3.model.HeroResponse
import com.akash.heroescv3.util.Constants
import retrofit2.Response
import retrofit2.http.GET

interface HeroApi {
    @GET(Constants.END_POINT)
    suspend fun getHeroes():Response<HeroResponse>
}