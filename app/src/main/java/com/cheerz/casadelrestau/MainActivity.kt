package com.cheerz.casadelrestau

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.cheerz.casadelrestau.login.hide
import com.cheerz.casadelrestau.login.show
import com.cheerz.casadelrestau.login.signUp.SignUp
import com.cheerz.casadelrestau.login.singIn.SignIn
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback, MainMvp.View, LocationListener, SignIn.Listener, SignUp.Listener {

    override fun onSignInClicked() {
        sign_up_view.show()
        sign_in_view.hide()
    }

    override fun onSignUpClicked() {
        sign_up_view.hide()
        sign_in_view.show()
    }

    private lateinit var mMap: GoogleMap
    private val locationRefreshTimeMillis = 500L
    private val locationRefreshDistanceMeters = 100f
    private val czLocation = LatLng(48.88060188, 2.32590994)
    private var mLocationManager: LocationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showMap()
        fetchCurrentLocation()
    }

    private fun fetchCurrentLocation() {
        val permissionStatus = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        when (permissionStatus) {
            PackageManager.PERMISSION_GRANTED -> requestLocationUpdates()
            PackageManager.PERMISSION_DENIED -> requestLocationPermissions()
        }
    }

    /**
     * Request for locations updates. You MUST check for the permissions before calling this method.
     */
    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates() {
        mLocationManager = mLocationManager ?: getSystemService(LOCATION_SERVICE) as LocationManager
        mLocationManager!!.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                locationRefreshTimeMillis,
                locationRefreshDistanceMeters,
                this
        )
    }

    private fun requestLocationPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 0)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            0 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    fetchCurrentLocation()
            }
        }
    }

    override fun showMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Request the permissions to locate the user on the map
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true
        }
        showLocation(czLocation)
    }

    override fun onLocationChanged(location: Location?) {
        location?.let {
            showLocation(LatLng(location.latitude, location.longitude))
            mLocationManager?.removeUpdates(this)
        }
    }

    /**
     * Move the camera on the current location
     */
    private fun showLocation(location: LatLng) {
        val zoom = CameraUpdateFactory.zoomTo(15f)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
        mMap.animateCamera(zoom)
    }

    private fun addMarker(toLocation: LatLng, title: String) {
        mMap.addMarker(MarkerOptions().position(toLocation).title(title))
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }

    override fun showLogin() {
        TODO()
    }
}
