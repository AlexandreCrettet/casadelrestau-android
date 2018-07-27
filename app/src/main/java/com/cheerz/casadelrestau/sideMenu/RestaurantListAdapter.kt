package com.cheerz.casadelrestau.sideMenu

import android.annotation.SuppressLint
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cheerz.casadelrestau.R
import com.cheerz.casadelrestau.hide
import com.cheerz.casadelrestau.network.HttpClient
import com.cheerz.casadelrestau.network.data.MiamzEvent
import com.cheerz.casadelrestau.network.data.MiamzReqPlaceData
import com.cheerz.casadelrestau.places.PlaceMarkerAssets
import com.cheerz.casadelrestau.places.PlacesRepository
import com.cheerz.casadelrestau.show
import com.cheerz.casadelrestau.toast
import com.cheerz.casadelrestau.user.UserStorage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.restaurant_details.view.address
import kotlinx.android.synthetic.main.restaurant_details.view.burger
import kotlinx.android.synthetic.main.restaurant_details.view.category
import kotlinx.android.synthetic.main.restaurant_details.view.guest
import kotlinx.android.synthetic.main.restaurant_details.view.hours
import kotlinx.android.synthetic.main.restaurant_details.view.separator

class RestaurantListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var placesList: List<Pair<MiamzReqPlaceData, MiamzEvent>> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.restaurant_details, parent, false))
    }

    override fun getItemCount(): Int {
        return placesList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = placesList[position]
        val participantList = item.second.participants
        val participantsString = participantList.joinToString(", ")
        val nickname = UserStorage.retrieveUser()!!.username
        val isUserParticipating = participantList.contains(nickname)

        holder.itemView.category.text = item.first.tags.firstOrNull()
        holder.itemView.address.text = item.first.name
        holder.itemView.hours.text = "${item.second.start_at} : ${item.second.stop_at}"
        holder.itemView.guest.text = participantsString
        PlaceMarkerAssets.find(item.first.place_category_tag)?.pinkAssets?.let {
            holder.itemView.burger.setBackgroundResource(it)
        }
        holder.itemView.separator.apply {
            if (position == (itemCount - 1)) hide() else show()
        }
        if (!isUserParticipating) {
            holder.itemView.hours.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.pink))
            holder.itemView.hours.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.black))
        } else {
            holder.itemView.hours.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.black))
            holder.itemView.hours.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
        }
        holder.itemView.hours.setOnClickListener {
            val isDeleting: Boolean
            if (isUserParticipating) {
                isDeleting = true
                HttpClient.service.deleteEventRegistration(item.second.id)
            } else {
                isDeleting = false
                HttpClient.service.postEventRegistration(item.second.id)
            }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if (isDeleting) {
                            holder.itemView.hours.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.pink))
                            holder.itemView.hours.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.black))
                        } else {
                            holder.itemView.hours.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.black))
                            holder.itemView.hours.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
                        }
                        PlacesRepository.updatePlace(item.first.id, it)
                    }, {
                        toast(holder.itemView.context, "Error")
                    })
        }
    }

    fun updateItems(items: List<MiamzReqPlaceData>) {
        placesList = items.flatMap { place -> place.events.map { place to it } }
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
