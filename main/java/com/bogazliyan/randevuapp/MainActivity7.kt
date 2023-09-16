package com.bogazliyan.randevuapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity7 : AppCompatActivity() {
    private val TAG = "MainActivity7"
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val intent = intent
        val deger = intent.getStringExtra("zaman")
        val textView : TextView = findViewById(R.id.textView3)
        textView.text = deger.toString()

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        firestore.collection("tarihler").document(deger.toString()).get().addOnSuccessListener { documentSnapshot ->
            val data = ArrayList<RandevuViewAdapter>()
            if (documentSnapshot.data == null){
                Toast.makeText(this@MainActivity7, "Bu tarihte ders saatleri yoktur", Toast.LENGTH_SHORT).show()
            }
            else if(documentSnapshot.data != null){
                val context : Context


                for(document in documentSnapshot.data!!){
                    if(document.value.toString().isEmpty()){
                        data.add(RandevuViewAdapter(document.key.toString(),deger.toString(), this))
                    }else if(document.value.toString() != "yok"){
                        data.add(RandevuViewAdapter(document.key.toString(),deger.toString(), this))
                    }
                }

                textView.text = data.size.toString()

                val adapter = YoneticiRandevu(data)

                // Setting the Adapter with the recyclerview
                recyclerview.adapter = adapter

            }

        }.addOnFailureListener { e ->
            // Hata durumunda işlemleri ele alma
            Log.e(TAG, "Veri alma hatası: $e")
        }

    }
}