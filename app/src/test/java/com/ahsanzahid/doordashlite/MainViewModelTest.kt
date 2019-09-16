package com.ahsanzahid.doordashlite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahsanzahid.doordashlite.ui.main.MainViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MainViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel()
    }

    @Test
    fun testThatViewCanRequestRestaurants() {
        viewModel.loadRestaurants()
        assert(viewModel.restaurants.value!!.isNotEmpty())
    }
}
