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
import com.cheerz.casadelrestau.events.Event
import com.cheerz.casadelrestau.login.signUp.SignUp
import com.cheerz.casadelrestau.login.singIn.SignIn
import com.cheerz.casadelrestau.network.data.MiamzReqPlaceData
import com.cheerz.casadelrestau.places.PlaceMarkerAssets
import com.cheerz.casadelrestau.places.Places
import com.cheerz.casadelrestau.places.PlacesModel
import com.cheerz.casadelrestau.places.PlacesPresenter
import com.cheerz.casadelrestau.places.PlacesRepository
import com.cheerz.casadelrestau.user.UserStorage
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.activity_main.bottom_bar_place_view
import kotlinx.android.synthetic.main.activity_main.create_events
import kotlinx.android.synthetic.main.activity_main.left_menu
import kotlinx.android.synthetic.main.activity_main.map
import kotlinx.android.synthetic.main.activity_main.sign_in_view
import kotlinx.android.synthetic.main.activity_main.sign_up_view
import kotlinx.android.synthetic.main.bottom_bar_place_view.bottom_bar_bottom


class MainActivity : AppCompatActivity(),
        OnMapReadyCallback,
        LocationListener,
        SignIn.Listener,
        SignUp.Listener,
        Places.View,
        Event.CreateEventDisplayer {

    private lateinit var mMap: GoogleMap
    private val locationRefreshTimeMillis = 500L
    private val locationRefreshDistanceMeters = 100f
    private val czLocation = LatLng(48.88060188, 2.32590994)
    private var mLocationManager: LocationManager? = null
    private var placesPresenter: Places.Presenter? = null
    private var lastLocation: LatLng? = null
    private val distanceSeenMeters = 2000
    private var isMapLoaded: Boolean = false

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
        UserStorage.retrieveToken()
                ?.let { goToMapView() }
                ?: sign_up_view.show()
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
        isMapLoaded = true

        // Request the permissions to locate the user on the map
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true
        }
        showLocation(czLocation)
        mMap.setOnCameraIdleListener { onMapShown(mMap.cameraPosition.target) }
        mMap.setOnMarkerClickListener { onMarkerClicked(it) }
    }

    private fun onMarkerClicked(marker: Marker): Boolean {
        val markerId = Integer.parseInt(marker.snippet!!)
        val place = PlacesRepository.getPlaceWithId(markerId) ?: return false
        fillPlace(place)
        showPlace()
        return true
    }

    private fun fillPlace(place: MiamzReqPlaceData) {
        bottom_bar_place_view.fillFields(place)
    }

    private fun showPlace() {
        val startY = map.view!!.height.toFloat()
        bottom_bar_place_view
                .animate()
                .y(startY - bottom_bar_bottom.y)
                .withStartAction {
                    bottom_bar_place_view.y = startY
                    bottom_bar_place_view.show()
                }
                .start()
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
        if (isMapLoaded) {
            val zoom = CameraUpdateFactory.zoomTo(18f)
            mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
            mMap.animateCamera(zoom)
        }
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

    private fun showMenu() {
        left_menu.show()
    }

    override fun goToMapView() {
        sign_in_view.hide()
        sign_up_view.hide()
        showMenu()
        onMapShown()
    }

    private fun onMapShown(location: LatLng? = null) {
        val onLocation = location ?: lastLocation ?: return
        placesPresenter?.onMapShown(onLocation.latitude, onLocation.longitude, distanceSeenMeters)
    }

    override fun showPlaces(places: List<MiamzReqPlaceData>) {
        places.map { place ->
            val toLocation = LatLng(place.lat, place.lng)
            val assetRes = PlaceMarkerAssets.getAssetRes(place.place_category_tag) ?: return
            mMap.addMarker(this, place.id, toLocation, place.name, assetRes, 75) //TODO size
        }
    }

    override fun showCreateEvent(name: String, id: Int) {
        create_events.show()
    }
}
