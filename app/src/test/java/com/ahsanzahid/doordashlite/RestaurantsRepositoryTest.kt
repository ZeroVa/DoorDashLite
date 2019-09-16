package com.ahsanzahid.doordashlite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahsanzahid.doordashlite.model.Outcome
import com.ahsanzahid.doordashlite.network.RestaurantsRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class RestaurantsRepositoryTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var repository: RestaurantsRepository

    @Before
    fun setup() {
        repository = RestaurantsRepository()
    }

    @Test
    fun testThatRepositoryLoadsData() {
        val responseOutcome = repository.loadRestaurants()
        assert(responseOutcome is Outcome.Success)
    }
}