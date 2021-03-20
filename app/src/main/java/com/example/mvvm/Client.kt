package com.example.mvvm

import com.example.mvvm.model.Comment
import retrofit2.http.GET

interface Client {
    @GET("/comments")
   suspend fun getComments() : List<Comment>
}