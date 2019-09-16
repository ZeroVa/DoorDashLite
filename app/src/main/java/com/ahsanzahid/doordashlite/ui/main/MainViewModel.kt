package com.ahsanzahid.doordashlite.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahsanzahid.doordashlite.model.Outcome
import com.ahsanzahid.doordashlite.model.Restaurant
import com.ahsanzahid.doordashlite.network.RestaurantsRepository

class MainViewModel(val repository: RestaurantsRepository) : ViewModel() {

    private val _restaurants: MutableLiveData<Outcome<List<Restaurant>>> = MutableLiveData()
    val restaurants: LiveData<Outcome<List<Restaurant>>>
        get() = _restaurants

    fun loadRestaurants() {
        _restaurants.value = repository.loadRestaurants()

    }

}
