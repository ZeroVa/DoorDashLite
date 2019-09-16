package com.ahsanzahid.doordashlite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
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
    val fakeRestaurants = listOf(
        Restaurant(
            "123",
            "Restaurant1",
            "A test restaurant",
            "http://lorempixel.com/200/200",
            "22 minutes",
            500
        ),
        Restaurant(
            "124",
            "Restaurant2",
            "Another test restaurant",
            "http://lorempixel.com/200/200",
            "22 minutes",
            500
        )
    )

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = MainViewModel(restaurantsRepository)
    }

    @Test
    fun testThatViewCanRequestRestaurants() {
        every { restaurantsRepository.loadRestaurants() }
            .returns(
                MutableLiveData(Outcome.success(fakeRestaurants))
            )
        viewModel.loadRestaurants()
        viewModel.restaurants.observeForever {
            assert(it is Outcome.Success)
            assert((it as Outcome.Success).data.isNotEmpty())
        }
    }

    @Test
    fun testThatLoadingStatePassesThrough() {
        every {
            restaurantsRepository.loadRestaurants()
        }
            .returns(MutableLiveData(Outcome.loading(true)))
        viewModel.loadRestaurants()
        viewModel.restaurants.observeForever {
            assert(it is Outcome.Progress)
        }
    }

    @Test
    fun testThatRepositoryErrorPassesError() {
        every {
            restaurantsRepository.loadRestaurants()
        }
            .returns(MutableLiveData(Outcome.failure(Throwable("Network Error!"))))

        viewModel.loadRestaurants()
        viewModel.restaurants.observeForever {
            assert(it is Outcome.Failure)
        }
    }


}
