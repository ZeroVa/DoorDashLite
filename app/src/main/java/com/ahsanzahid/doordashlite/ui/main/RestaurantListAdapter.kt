package com.ahsanzahid.doordashlite.ui.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahsanzahid.doordashlite.R
import com.ahsanzahid.doordashlite.databinding.HolderRestaurantBinding
import com.ahsanzahid.doordashlite.model.Restaurant
import com.bumptech.glide.RequestManager

class RestaurantListAdapter(
    private val restaurantList: ArrayList<Restaurant>,
    private val glide: RequestManager,
    val favoritesList: ArrayList<String>
) :
    RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.
    class RestaurantViewHolder(
        val binding: HolderRestaurantBinding
    ) : RecyclerView.ViewHolder(binding.root)


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantListAdapter.RestaurantViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.holder_restaurant, parent, false)
        // set the view's size, margins, paddings and layout parameters
        return RestaurantViewHolder(HolderRestaurantBinding.bind(view))
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if (position < 0) {
            return
        }

        val restaurant = restaurantList[position]
        with(holder.binding) {
            restaurantName.text = restaurant.name
            restaurantDescription.text = restaurant.description
            restaurantStatus.text = restaurant.status
            glide.load(restaurant.coverImageUrl)
                .centerCrop()
                .placeholder(R.color.colorPrimary)
                .into(coverImage)
            if (favoritesList.contains(restaurant.id)) {
                favoriteButton.setBackgroundColor(Color.RED)
            } else {
                favoriteButton.setBackgroundColor(Color.WHITE)
            }

            favoriteButton.setOnClickListener {
                if (favoritesList.contains(restaurant.id)) {
                    favoritesList.remove(restaurant.id)
                } else {
                    favoritesList.add(restaurant.id)
                }
                notifyDataSetChanged()
            }
        }
    }

    fun updateRestaurantList(newRestaurantList: List<Restaurant>) {
        restaurantList.clear()
        restaurantList.addAll(newRestaurantList)
        notifyDataSetChanged()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = restaurantList.size
}