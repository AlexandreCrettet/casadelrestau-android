package com.cheerz.casadelrestau.sideMenu

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheerz.casadelrestau.R
import com.cheerz.casadelrestau.network.data.MiamzReqPlaceData
import kotlinx.android.synthetic.main.restaurant_details.view.*

class RestaurantListAdapter(val placesList: List<MiamzReqPlaceData>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.restaurant_details, parent, false))
    }

    override fun getItemCount(): Int {
        return placesList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.name.text = placesList[itemCount - 1].name
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text = view.name
    }
}
