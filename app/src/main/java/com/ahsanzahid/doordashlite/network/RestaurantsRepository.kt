package com.ahsanzahid.doordashlite.network

import com.ahsanzahid.doordashlite.model.Restaurant

class RestaurantsRepository {
    fun loadRestaurants(): List<Restaurant> {
        return listOf(Restaurant())
    }
}
