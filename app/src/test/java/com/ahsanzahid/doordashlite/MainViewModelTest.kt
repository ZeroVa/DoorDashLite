package com.ahsanzahid.doordashlite

import com.ahsanzahid.doordashlite.ui.main.MainViewModel
import org.junit.Before
import org.junit.Test

class MainViewModelTest {
    lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel()
    }

    @Test
    fun testViewModelExists() {
        print(viewModel)
    }
}
