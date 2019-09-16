package com.ahsanzahid.doordashlite.network

import androidx.lifecycle.LiveData
import com.ahsanzahid.doordashlite.model.Outcome
import com.ahsanzahid.doordashlite.model.Restaurant

class RestaurantsRepository(private val webservice: DoorDashAPIService) {

    fun loadRestaurants(): LiveData<Outcome<List<Restaurant>>> {
        return NetworkCall<List<Restaurant>>().makeCall(
            webservice.nearbyRestaurants("37.422740", "-122.139956")
        )
    }
}
