package com.example.shoes_store_firebase

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Telephony.Mms.Intents
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Products : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2) // Display 2 cards per row

        // API call to fetch smartphones
        fetchSmartphones()
    }

    private fun fetchSmartphones() {
        // Configure Retrofit to call the new API
        val retrofit = Retrofit.Builder()
            .baseUrl("https://apiyes.net/") // Keep the base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Call API to fetch the list of smartphones
                val response = apiService.getSmartphones().execute()

                if (response.isSuccessful && response.body() != null) {
                    val smartphones = response.body()!!

                    withContext(Dispatchers.Main) {
                        // Initialize adapter and set data to RecyclerView
                        productAdapter = ProductAdapter(smartphones)
                        recyclerView.adapter = productAdapter
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@Products, "Erreur de chargement des smartphones", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@Products, "Erreur réseau", Toast.LENGTH_SHORT).show()
                }
            }
        }
        val panier = findViewById<ImageButton>(R.id.cartButton)

        panier.setOnClickListener{
            val intent = Intent(this,Panier::class.java)
            startActivity(intent)
        }
    }

}
