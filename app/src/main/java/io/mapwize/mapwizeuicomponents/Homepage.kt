package io.mapwize.mapwizeuicomponents

import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.os.Bundle


class Homepage : AppCompatActivity() {


    override fun onCreate( savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.homepage)

        title = "Homepage"

        val but = findViewById<Button>(R.id.but)
        but.setOnClickListener{ openMainActivity() }
    }

    fun openMainActivity() {
       val intent = Intent(this@Homepage, MainActivity::class.java)

        this@Homepage.startActivity(intent)

    }
}