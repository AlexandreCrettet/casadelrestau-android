package com.cheerz.casadelrestau

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Toast
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

fun View.show() {
    visibility= View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun toast(context : Context, text : String) {
    val duration = Toast.LENGTH_SHORT
    val toast = Toast.makeText(context, text, duration)
    toast.show()
}

fun GoogleMap.addMarker(context: Context, toLocation: LatLng, title: String, @DrawableRes markerRes: Int, size: Int) {
    val bitmap = (ContextCompat.getDrawable(context, markerRes) as BitmapDrawable).bitmap
    val scaledBitmap = Bitmap.createScaledBitmap(bitmap, size, size, false)
    val bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(scaledBitmap)
    val marker = MarkerOptions()
            .position(toLocation)
            .title(title)
            .icon(bitmapDescriptor)
    addMarker(marker)
}
