package com.bogazliyan.randevuapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.bogazliyan.randevuapp.databinding.ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {

    private lateinit var binding: ActivityMain4Binding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.buttonCikis.setOnClickListener {
            firebaseAuth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // MainActivity4'yi kapat
        }
    }
}