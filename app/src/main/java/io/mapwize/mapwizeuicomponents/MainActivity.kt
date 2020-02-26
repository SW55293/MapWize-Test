package io.mapwize.mapwizeuicomponents


//import io.indoorlocation.gps.GPSIndoorLocationProvider


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.mapbox.mapboxsdk.maps.MapboxMap
import io.indoorlocation.core.IndoorLocation
import io.indoorlocation.manual.ManualIndoorLocationProvider
import io.mapwize.mapwizecomponents.ui.MapwizeFragment
import io.mapwize.mapwizecomponents.ui.MapwizeFragmentUISettings
import io.mapwize.mapwizeformapbox.api.MapwizeObject
import io.mapwize.mapwizeformapbox.api.Place
import io.mapwize.mapwizeformapbox.map.MapOptions
import io.mapwize.mapwizeformapbox.map.MapwizePlugin
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity(), MapwizeFragment.OnFragmentInteractionListener {

    private var mapwizeFragment: MapwizeFragment? = null
    private var mapboxMap: MapboxMap? = null
    private var mapwizePlugin: MapwizePlugin? = null
    private var locationProvider: ManualIndoorLocationProvider? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Uncomment and fill place holder to test MapwizeUI on your venue
        val opts = MapOptions.Builder()
                //.restrictContentToOrganization("YOUR_ORGANIZATION_ID")
                //.restrictContentToVenue("5da776f9c62d660016e50380")//venue id
                .centerOnVenue("Jacaranda")
                //.centerOnPlace("YOUR_PLACE_ID")
                .build()


        //Uncomment and change value to test different settings configuration
        val uiSettings = MapwizeFragmentUISettings.Builder()
                .menuButtonHidden(false)
                .followUserButtonHidden(false)//button that finds geolocation
                .floorControllerHidden(false)
                .compassHidden(false)
                .build()


        mapwizeFragment = MapwizeFragment.newInstance(opts, uiSettings)
        mapwizeFragment = MapwizeFragment.newInstance(MapOptions.Builder().build())
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()

        ft.add(fragmentContainer.id, mapwizeFragment!!)

        ft.commit()


    }


    /**
     * Fragment listener
     */
    override fun onFragmentReady(mapboxMap: MapboxMap?, mapwizePlugin: MapwizePlugin?) {
        this.mapboxMap = mapboxMap
        this.mapwizePlugin = mapwizePlugin




        this.locationProvider = ManualIndoorLocationProvider()
        this.mapwizePlugin?.setLocationProvider(this.locationProvider!!)
        //val gpsIndoorLocationProvider = GPS (this)
        //this.mapwizePlugin?.setLocationProvider(gpsIndoorLocationProvider)


        //button
        this.mapwizePlugin?.addOnLongClickListener {
            val indoorLocation = IndoorLocation("manual_provider", it.latLngFloor.latitude, it.latLngFloor.longitude, it.latLngFloor.floor, System.currentTimeMillis())
            //val gpsIndoorLocationProvider = GPSIndoorLocationProvider()
            this.locationProvider?.setIndoorLocation(indoorLocation)
            //this.mapwizePlugin?.setLocationProvider(gpsIndoorLocationProvider)


        }

    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onMenuButtonClick() {

    }

    override fun onInformationButtonClick(mapwizeObject: MapwizeObject?) {


    }


    override fun onFollowUserButtonClickWithoutLocation() {
        // val gpsIndoorLocationProvider = GPS (this)
        //this.mapwizePlugin?.setLocationProvider(gpsIndoorLocationProvider)

        //Log.i("Debug", "onFollowUserButtonClickWithoutLocation")


    }

    override fun shouldDisplayInformationButton(mapwizeObject: MapwizeObject?): Boolean {
        Log.i("Debug", "shouldDisplayInformationButton")
        when (mapwizeObject) {
            is Place -> return true
        }
        return false
    }

    override fun shouldDisplayFloorController(floors: MutableList<Double>?): Boolean {
        Log.i("Debug", "shouldDisplayFloorController")
        if (floors == null || floors.size <= 1) {
            return false
        }
        return true
    }

    @Override
    override fun onStart() {
        super.onStart()
    }

    @Override
    override fun onResume() {
        super.onResume()
    }

    @Override
    override fun onStop() {
        super.onStop()
    }

    @Override
    override fun onPause() {
        super.onPause()
    }

    @Override
    override fun onLowMemory() {
        super.onLowMemory()
    }

    @Override
    override fun onDestroy() {
        super.onDestroy()
    }

}