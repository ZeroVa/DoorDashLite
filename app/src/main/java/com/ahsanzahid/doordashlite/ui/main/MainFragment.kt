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
        mainViewModel.restaurants.observe(this, Observer { outcome ->
            Log.e("OUTCOME", outcome.toString())
            when (outcome) {
                is Outcome.Success -> {
//                    TODO: Success State
                    Log.e("DOORDASHLITE", outcome.data.toString())
                    context?.let { context ->
                        Toast.makeText(context, outcome.data.size.toString(), Toast.LENGTH_LONG)
                            .show()
                    }
                }
                is Outcome.Progress -> {
//                    TODO: Loading state
                    Log.e("DOORDASHLITE", "LOADING")
                }
                is Outcome.Failure -> {
                    Log.e("DOORDASHLITE", outcome.e.message)

                }
            }

        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
