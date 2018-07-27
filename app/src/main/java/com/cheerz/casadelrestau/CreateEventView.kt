package com.cheerz.casadelrestau

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import com.cheerz.casadelrestau.network.HttpClient
import com.cheerz.casadelrestau.network.data.MiamzEvent
import com.cheerz.casadelrestau.network.data.MiamzReqEvent
import com.cheerz.casadelrestau.network.data.MiamzReqEventWrapper
import com.cheerz.casadelrestau.places.PlacesRepository
import com.cheerz.casadelrestau.user.UserStorage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.book_place.view.bookButton
import kotlinx.android.synthetic.main.book_place.view.hour
import kotlinx.android.synthetic.main.book_place.view.minutes
import kotlinx.android.synthetic.main.book_place.view.name
import kotlinx.android.synthetic.main.book_place.view.title

class CreateEventView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private var placeId: Int = -1

    init {
        inflate(context, R.layout.book_place, this)
        val username = UserStorage.retrieveUser()!!.username
        title.text = context.getString(R.string.hey_nickname, username)
        bookButton.setOnClickListener {
            bookButtonClicked()
        }
    }

    private fun postNewEvent(context: Context) {
        val startAt = "12:00"
        val stopAt = "12:15"
        val event = MiamzReqEventWrapper(MiamzReqEvent(placeId, startAt, stopAt))
        HttpClient.service.postNewEvent(event)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    onSuccessCreateEvent(it)
                }, {
                    toast(context, "Error")
                })
    }

    private fun onSuccessCreateEvent(event: MiamzEvent) {
        PlacesRepository.updatePlace(placeId, event)
        hide()
    }

    fun setPlace(id: Int, placeName: String) {
        placeId = id
        name.text = placeName
    }

    private fun bookButtonClicked() {
        val hour = hour.text.toString()
        val minutes = minutes.text.toString()
        if (hour.toInt() in 24 downTo -1 && minutes.toInt() in 60 downTo -1)
            postNewEvent(context)
        else
            toast(this.context, "Use proper value for your reservation")
    }
}
