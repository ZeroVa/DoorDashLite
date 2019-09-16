package com.ahsanzahid.doordashlite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahsanzahid.doordashlite.model.Outcome
import com.ahsanzahid.doordashlite.model.Restaurant
import com.ahsanzahid.doordashlite.network.RestaurantsRepository
import com.ahsanzahid.doordashlite.ui.main.MainViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MainViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @MockK
    lateinit var restaurantsRepository: RestaurantsRepository

    lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = MainViewModel(restaurantsRepository)
    }

    @Test
    fun testThatViewCanRequestRestaurants() {
        every { restaurantsRepository.loadRestaurants() }
            .returns(Outcome.success(listOf(Restaurant())))
        viewModel.loadRestaurants()
        assert(viewModel.restaurants.value!! is Outcome.Success)
        assert((viewModel.restaurants.value!! as Outcome.Success).data.isNotEmpty())
    }
}
