package com.bogazliyan.randevuapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity8 : AppCompatActivity() {
    private val TAG = "MainActivity8"
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val intent = intent

        val textView : TextView = findViewById(R.id.textView3)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        val kullanici_email =  firebaseAuth.currentUser

        val collection = firestore.collection("tarihler") // Koleksiyon adını buraya ekleyin

        collection.get().addOnSuccessListener { documents ->
            val data2 =  ArrayList<RandevuViewAdapter>() // Alan adı ve belge kimliğini saklamak için bir liste

            for (document in documents) {
                val data = document.data // Belge içindeki tüm veriyi alın
                for ((field, value) in data) { // Belge içindeki her alanı kontrol edin
                    if (value != "yok") { // Değer eşleşiyorsa
                        data2.add(RandevuViewAdapter(field, document.id.toString(),this)) // Alan adı ve belge kimliğini ekle
                    }
                }
            }

            textView.text = data2.size.toString()

            val adapter = TumRandevularYonetici(data2)

            // Setting the Adapter with the recyclerview
            recyclerview.adapter = adapter

        }.addOnFailureListener { exception ->
            // Sorgu sırasında bir hata oluşursa burada işlemler yapabilirsiniz
        }




    }
}