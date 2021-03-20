package com.example.mvvm

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

   const val BASE_URL ="https://jsonplaceholder.typicode.com/"

   private fun getRetrofit() : Retrofit {
      return Retrofit.Builder()
         .baseUrl(BASE_URL)
         .addConverterFactory(GsonConverterFactory.create())
         .build()
   }
   val client:Client = getRetrofit().create(Client::class.java)
}