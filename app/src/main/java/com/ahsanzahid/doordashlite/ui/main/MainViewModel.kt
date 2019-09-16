package com.ahsanzahid.doordashlite.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.ahsanzahid.doordashlite.model.Outcome
import com.ahsanzahid.doordashlite.model.Restaurant
import com.ahsanzahid.doordashlite.network.RestaurantsRepository

class MainViewModel(private val repository: RestaurantsRepository) : ViewModel() {

    private val _restaurants: MediatorLiveData<Outcome<List<Restaurant>>> = MediatorLiveData()
    val restaurants: LiveData<Outcome<List<Restaurant>>>
        get() = _restaurants

    fun loadRestaurants() {
        val repoLiveData = repository.loadRestaurants()
        _restaurants.addSource(repoLiveData) { outcome ->
            _restaurants.postValue(outcome)
        }

    }

}
