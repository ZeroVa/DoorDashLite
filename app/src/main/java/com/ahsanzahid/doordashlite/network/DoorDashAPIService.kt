package com.ahsanzahid.doordashlite.network

import com.ahsanzahid.doordashlite.model.Restaurant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DoorDashAPIService {

    @GET("/v2/restaurant")
    fun nearbyRestaurants(
        @Query("lat") latitude: String,
        @Query("lng") longitude: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 50
    ):
            Call<List<Restaurant>>

}