package com.ahsanzahid.doordashlite.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ahsanzahid.doordashlite.R
import com.ahsanzahid.doordashlite.model.Outcome
import com.ahsanzahid.doordashlite.model.Restaurant
import kotlinx.android.synthetic.main.main_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val mainViewModel: MainViewModel by viewModel()

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
        mainViewModel.restaurants.observe(this, Observer { outcome ->
            Log.e("OUTCOME", outcome.toString())
            when (outcome) {
                is Outcome.Success -> {
//                    TODO: Success State
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
        context?.let { context ->
            Toast.makeText(context, restaurants.size.toString(), Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun showErrorState(outcome: Outcome.Failure<List<Restaurant>>) {
        swipeRefreshLayout.isRefreshing = false
        restaurantsRecyclerView.visibility = View.GONE
        loadingSpinner.visibility = View.GONE
        errorMessage.visibility = View.VISIBLE
        Log.e("DOORDASHLITE", outcome.e.message)
    }

    private fun showLoadingState() {
        restaurantsRecyclerView.visibility = View.GONE
        loadingSpinner.visibility = View.VISIBLE
        errorMessage.visibility = View.GONE
        Log.e("DOORDASHLITE", "LOADING")
    }
}
