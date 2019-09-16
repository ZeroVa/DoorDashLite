package com.ahsanzahid.doordashlite.network

import com.ahsanzahid.doordashlite.model.Outcome
import com.ahsanzahid.doordashlite.model.Restaurant

class RestaurantsRepository {
    fun loadRestaurants(): Outcome<List<Restaurant>> {
        return Outcome.success(listOf(Restaurant()))
    }
}
