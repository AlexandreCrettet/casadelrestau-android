package com.cheerz.casadelrestau

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
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
    val bitmap = getBitmapFromVectorDrawable(context, markerRes)
    val scaledBitmap = Bitmap.createScaledBitmap(bitmap, size, size, false)
    val bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(scaledBitmap)
    val marker = MarkerOptions()
            .position(toLocation)
            .title(title)
            .icon(bitmapDescriptor)
    addMarker(marker)
}

private fun getBitmapFromVectorDrawable(context: Context, drawableId: Int): Bitmap {
    var drawable = ContextCompat.getDrawable(context, drawableId)
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
        drawable = DrawableCompat.wrap(drawable).mutate()
    }

    val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth,
            drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)

    return bitmap
}
