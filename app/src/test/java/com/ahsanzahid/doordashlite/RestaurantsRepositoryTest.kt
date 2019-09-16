package com.ahsanzahid.doordashlite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahsanzahid.doordashlite.model.Outcome
import com.ahsanzahid.doordashlite.model.Restaurant
import com.ahsanzahid.doordashlite.network.DoorDashAPIService
import com.ahsanzahid.doordashlite.network.RestaurantsRepository
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantsRepositoryTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @MockK
    var webservice: DoorDashAPIService = mockk()

    private lateinit var repository: RestaurantsRepository

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
        val call = mockk<Call<List<Restaurant>>>(relaxed = true)
//        Mocking the call and sending our fake call over the network
        every { call.enqueue(any()) }
            .answers {
                (firstArg() as Callback<List<Restaurant>>).onResponse(
                    call,
                    Response.success(fakeRestaurants)
                )
            }
        every { webservice.nearbyRestaurants(any(), any()) }
            .returns(call)
        repository = RestaurantsRepository(webservice)
    }

    @Test
    fun testThatRepositoryLoadsData() {
        val responseOutcome = repository.loadRestaurants()
        responseOutcome.value?.let { outcome ->
            print(outcome)
            assert(outcome is Outcome.Success)
            (outcome as? Outcome.Success)?.let { outcome ->
                assert(outcome.data.isNotEmpty())
            }
        }
    }
}