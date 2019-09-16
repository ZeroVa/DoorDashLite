package com.ahsanzahid.doordashlite.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahsanzahid.doordashlite.model.Restaurant

class MainViewModel : ViewModel() {

    private val _restaurants: MutableLiveData<List<Restaurant>> = MutableLiveData()
    val restaurants: LiveData<List<Restaurant>>
        get() = _restaurants

    fun loadRestaurants() {
        _restaurants.value = listOf(Restaurant())

    }

}
