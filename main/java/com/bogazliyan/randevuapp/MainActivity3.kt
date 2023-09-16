package com.bogazliyan.randevuapp

import android.content.Intent
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity3 : AppCompatActivity() {
    private val TAG = "MainActivity3"
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        val buttonCikis = findViewById<Button>(R.id.button3)
        buttonCikis.setOnClickListener {
            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)
            finish() // MainActivity4'yi kapat
        }

        val aktif_tum_randevular = findViewById<Button>(R.id.button4)
        aktif_tum_randevular.setOnClickListener {
            val intent = Intent(this, MainActivity8::class.java)
            startActivity(intent)
            finish() // MainActivity4'yi kapat
        }

        val gizlilik = findViewById<Button>(R.id.gizlilik2)
        gizlilik.setOnClickListener {
            val intent = Intent(this, MainActivity9::class.java)
            startActivity(intent)
            finish()
        }


        val hakkinda = findViewById<Button>(R.id.button6)
        hakkinda.setOnClickListener {
            val intent = Intent(this, Denizhan::class.java)
            startActivity(intent)
            finish()
        }

        val calendarView = findViewById<CalendarView>(R.id.calendarView2)
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            // Burada tıklanan günün bilgisini kullanabilirsiniz
            // Örneğin:
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, month, dayOfMonth)
            val dayOfWeek = selectedDate.get(Calendar.DAY_OF_WEEK)

            // Günün bilgisini kullanarak istediğiniz işlemleri gerçekleştirebilirsiniz
            // Örneğin:
            val dayOfWeekString = when (dayOfWeek) {
                Calendar.SUNDAY -> "Pazar"
                Calendar.MONDAY -> "Pazartesi"
                Calendar.TUESDAY -> "Salı"
                Calendar.WEDNESDAY -> "Çarşamba"
                Calendar.THURSDAY -> "Perşembe"
                Calendar.FRIDAY -> "Cuma"
                Calendar.SATURDAY -> "Cumartesi"
                else -> ""
            }

            // İstenilen bilgiyi kullanabilirsiniz
            println("Seçilen tarih: $dayOfMonth/$month/$year, $dayOfWeekString")
            Toast.makeText(this@MainActivity3, "Seçilen tarih: $dayOfMonth-$month-$year, $dayOfWeekString", Toast.LENGTH_SHORT).show()

            val tarihbilgisi = "$dayOfMonth-$month-$year"

            firestore.collection("tarihler").document(tarihbilgisi).get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.data == null){
                    Toast.makeText(this@MainActivity3, "Bu tarihte ders saatleri yoktur", Toast.LENGTH_SHORT).show()
                }else if(documentSnapshot.data != null){
                    //Toast.makeText(this@MainActivity2, "${documentSnapshot.data}", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@MainActivity3, MainActivity7::class.java)
                    intent.putExtra("zaman",tarihbilgisi)
                    startActivity(intent)
                }


            }.addOnFailureListener { e ->
                // Hata durumunda işlemleri ele alma
                Log.e(TAG, "Veri alma hatası: $e")
            }


        }

    }
}