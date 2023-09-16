package com.bogazliyan.randevuapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Denizhan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_denizhan)


        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val url = "https://www.linkedin.com/in/denizhan-%C5%9Fahin-a5136b211/"

            val defaultBrowserIntent = Intent(Intent.ACTION_VIEW)
            defaultBrowserIntent.data = Uri.parse(url)
            startActivity(defaultBrowserIntent)
        }


        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            val url = "https://www.denizhansahin.com/"

            val defaultBrowserIntent = Intent(Intent.ACTION_VIEW)
            defaultBrowserIntent.data = Uri.parse(url)
            startActivity(defaultBrowserIntent)
        }


        val button8 = findViewById<Button>(R.id.button8)
        button8.setOnClickListener {
            val url = "https://www.instagram.com/bogazliyanmobil/"

            val defaultBrowserIntent = Intent(Intent.ACTION_VIEW)
            defaultBrowserIntent.data = Uri.parse(url)
            startActivity(defaultBrowserIntent)
        }


    }
}