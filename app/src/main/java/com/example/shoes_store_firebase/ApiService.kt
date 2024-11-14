package com.example.shoes_store_firebase

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

data class User(
    val id :Int,
    val nom: String,
    val prenom: String,

    val mail:String,
    val motDePasse: String
)
data class AddResponse(
    val code:Int,
    val message: String,
    val fullName: String
)

data class Smartphone(
    val id: Int,
    val nom: String,    // This represents the name of the smartphone
    val prix: Double,   // This represents the price of the smartphone
    val image: String   // This represents the image URL for the smartphone
)


interface ApiService {

    @POST("/AccountAPI/login.php")
    fun login_user(@Body account_user: User): Call<AddResponse>


    @GET("/SmartphoneAPI/readAll.php")
    fun getSmartphones(): retrofit2.Call<List<Smartphone>>
}