package com.getir.patika.foodcouriers

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private var mGoogleMap: GoogleMap? = null
    private lateinit var autocompleteFragment:AutocompleteSupportFragment
    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Places.initialize(applicationContext,getString(R.string.google_map_api_key))
        autocompleteFragment= supportFragmentManager.findFragmentById(R.id.autocomplete_fragment)
                as AutocompleteSupportFragment
        autocompleteFragment.setPlaceFields(listOf(Place.Field.ADDRESS,Place.Field.LAT_LNG))
        autocompleteFragment.setOnPlaceSelectedListener(object :PlaceSelectionListener{
            override fun onError(p0: Status) {
                Toast.makeText(this@MainActivity,"Some Error in Search", Toast.LENGTH_SHORT).show()
            }

            override fun onPlaceSelected(place: Place) {
                val latLng=place.latLng
            }
        })

        val mapFragment=supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }
    override fun onMapReady(googleMap: GoogleMap){
        mGoogleMap=googleMap
    }
}

