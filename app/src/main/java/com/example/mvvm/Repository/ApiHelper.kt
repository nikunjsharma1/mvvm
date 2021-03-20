package com.example.mvvm.Repository

import com.example.mvvm.Client

class ApiHelper(private val client:Client) {
    suspend fun getComments()=client.getComments()

}