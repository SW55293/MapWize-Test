package io.mapwize.mapwizeuicomponents



import android.app.Application
import io.mapwize.mapwizeformapbox.AccountManager


class MapwizeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        AccountManager.start(this, "d77d48111bf5feabde2b194436f537c0")

    }



}