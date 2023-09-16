package com.bogazliyan.randevuapp


import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

open class YoneticiRandevu(private val mList: List<RandevuViewAdapter>) :
    RecyclerView.Adapter<YoneticiRandevu.ViewHolder>() {


    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.my_randevu_cardview, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mList[position]

        firebaseAuth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()



        holder.textView.text = itemsViewModel.zaman



        // kim olduğuna ait olan epostadan kullanıcı bilgisi bulma, zaman bilgisine tanımlı epostadan
        val collection_db2 = firestore.collection("tarihler")
        collection_db2.document("${itemsViewModel.tarih.toString()}").get().addOnSuccessListener { documents->
            val alanDegeri = documents.getString("${itemsViewModel.zaman}")

            if(alanDegeri != "yok"){
                holder.textView8.text = alanDegeri.toString()
            }

            firestore.collection("kullaniciBilgileri").get().addOnSuccessListener { documents->
                for (document in documents){
                    val data = document.data
                    for((field, value) in data){
                        if(value == alanDegeri){
                            val deger = data.get("isim")
                            holder.textView8.text = deger.toString()

                        }
                    }
                }
            }


        }



        holder.button.setOnClickListener{



            // context değişkeni burada tanımlandı
            val context = itemsViewModel.context_view
            val alertDialogBuilder = AlertDialog.Builder(context)
            alertDialogBuilder.setTitle("Uyarı")
            alertDialogBuilder.setMessage("Bu işlemi gerçekleştirmek istediğinize emin misiniz?")
            alertDialogBuilder.setPositiveButton("Tamam") { dialog, _ ->
                // Tamam butonuna basıldığında yapılacak işlemler
                dialog.dismiss() // Uyarı kutusunu kapat


                val kullanici_email =  firebaseAuth.currentUser
                val kullanici_email2 = kullanici_email?.email

                val collection_db = firestore.collection("tarihler")
                val zaman_document = collection_db.document("${itemsViewModel.tarih.toString()}")
                val veri = hashMapOf("${itemsViewModel.zaman.toString()}" to "yok")
                zaman_document.set(veri, SetOptions.merge())

                val intent = Intent(context, MainActivity::class.java)
                context?.startActivity(intent)


            }
            //
            alertDialogBuilder.show()


        }


    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView = itemView.findViewById(R.id.textView9)
        var textView8: TextView = itemView.findViewById(R.id.textView8)
        var button : Button = itemView.findViewById(R.id.button4)


    }

}
