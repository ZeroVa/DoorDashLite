package com.ahsanzahid.doordashlite.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ahsanzahid.doordashlite.R
import com.ahsanzahid.doordashlite.model.Outcome
import com.ahsanzahid.doordashlite.model.Restaurant
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val mainViewModel: MainViewModel by viewModel()
    private val restaurantListAdapter by lazy {
        context?.let { context ->
            RestaurantListAdapter(arrayListOf(), Glide.with(context))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mainViewModel.loadRestaurants()
        swipeRefreshLayout.setOnRefreshListener {
            mainViewModel.loadRestaurants()
        }
        restaurantsRecyclerView.adapter = restaurantListAdapter
        mainViewModel.restaurants.observe(this, Observer { outcome ->
            when (outcome) {
                is Outcome.Success -> {
                    showRestaurantsList(outcome.data)
                }
                is Outcome.Progress -> {
                    showLoadingState()
                }
                is Outcome.Failure -> {
                    showErrorState(outcome)

                }
            }

        })
    }

    private fun showRestaurantsList(restaurants: List<Restaurant>) {
        swipeRefreshLayout.isRefreshing = false
        restaurantsRecyclerView.visibility = View.VISIBLE
        loadingSpinner.visibility = View.GONE
        errorMessage.visibility = View.GONE
        restaurantListAdapter?.updateRestaurantList(restaurants)
    }

    private fun showErrorState(outcome: Outcome.Failure<List<Restaurant>>) {
        swipeRefreshLayout.isRefreshing = false
        restaurantsRecyclerView.visibility = View.GONE
        loadingSpinner.visibility = View.GONE
        errorMessage.visibility = View.VISIBLE
    }

    private fun showLoadingState() {
        restaurantsRecyclerView.visibility = View.GONE
        loadingSpinner.visibility = View.VISIBLE
        errorMessage.visibility = View.GONE
    }
}
