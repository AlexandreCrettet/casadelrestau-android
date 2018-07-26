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
import com.cheerz.casadelrestau.login.signUp.SignUp
import com.cheerz.casadelrestau.login.singIn.SignIn
import com.cheerz.casadelrestau.network.data.MiamzReqPlaceData
import com.cheerz.casadelrestau.places.PlaceMarkerAssets
import com.cheerz.casadelrestau.places.Places
import com.cheerz.casadelrestau.places.PlacesModel
import com.cheerz.casadelrestau.places.PlacesPresenter
import com.cheerz.casadelrestau.user.UserStorage
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_main.sign_in_view
import kotlinx.android.synthetic.main.activity_main.sign_up_view


class MainActivity : AppCompatActivity(),
        OnMapReadyCallback,
        LocationListener,
        SignIn.Listener,
        SignUp.Listener,
        Places.View {

    private lateinit var mMap: GoogleMap
    private val locationRefreshTimeMillis = 500L
    private val locationRefreshDistanceMeters = 100f
    private val czLocation = LatLng(48.88060188, 2.32590994)
    private var mLocationManager: LocationManager? = null
    private var placesPresenter: Places.Presenter? = null
    private var lastLocation: LatLng? = null
    private val distanceSeenMeters = 500 //TODO: it should depend on the zoom

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        UserStorage.initialize(this)
        showMap()
        fetchCurrentLocation()
        placesPresenter = PlacesPresenter(this, PlacesModel())
        showLogin()
    }

    private fun showLogin() {
        UserStorage.retrieveToken() ?: sign_up_view.show()
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

    private fun showMap() {
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
            lastLocation = LatLng(location.latitude, location.longitude).apply {
                showLocation(this)
                mLocationManager?.removeUpdates(this@MainActivity)
            }
            onMapShown()
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

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String?) {
    }

    override fun onProviderDisabled(provider: String?) {
    }

    override fun onSignInClicked() {
        sign_up_view.hide()
        sign_in_view.show()
    }

    override fun onSignUpClicked() {
        sign_up_view.show()
        sign_in_view.hide()
    }

    override fun goToMapView() {
        sign_in_view.hide()
        sign_up_view.hide()
        onMapShown()
    }

    private fun onMapShown() {
        placesPresenter?.onMapShown(lastLocation!!.latitude, lastLocation!!.longitude, distanceSeenMeters)
    }

    override fun showPlaces(places: List<MiamzReqPlaceData>) {
        places.map { place ->
            val toLocation = LatLng(place.lat, place.lng)
            val assetRes = PlaceMarkerAssets.getAssetRes(place.place_category_tag) ?: return
            mMap.addMarker(this, toLocation, place.name, assetRes, 100) //TODO size
        }
    }
}
