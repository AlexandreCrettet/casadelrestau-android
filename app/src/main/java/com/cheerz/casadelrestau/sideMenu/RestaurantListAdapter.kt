package com.cheerz.casadelrestau.sideMenu

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheerz.casadelrestau.R
import com.cheerz.casadelrestau.hide
import com.cheerz.casadelrestau.network.data.MiamzEvent
import com.cheerz.casadelrestau.network.data.MiamzReqPlaceData
import com.cheerz.casadelrestau.places.PlaceMarkerAssets
import com.cheerz.casadelrestau.show
import kotlinx.android.synthetic.main.restaurant_details.view.*

class RestaurantListAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var placesList: List<Pair<MiamzReqPlaceData, MiamzEvent>> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.restaurant_details, parent, false))
    }

    override fun getItemCount(): Int {
        return placesList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = placesList[position]
        val participants = item.second.participants.joinToString(", ")

        holder.itemView.category.text = item.first.tags.firstOrNull()
        holder.itemView.address.text = item.first.name
        holder.itemView.hours.text = "${item.second.start_at} : ${item.second.stop_at}"
        holder.itemView.guest.text = participants
        PlaceMarkerAssets.find(item.first.place_category_tag)?.pinkAssets?.let {
            holder.itemView.burger.setBackgroundResource(it)
        }
        holder.itemView.separator.apply {
            if (position == (itemCount - 1)) hide() else show()
        }
    }

    fun updateItems(items: List<MiamzReqPlaceData>) {
        placesList = items.flatMap { place -> place.events.map { place to it } }
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
