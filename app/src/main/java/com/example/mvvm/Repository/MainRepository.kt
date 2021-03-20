package com.example.mvvm.Repository

import com.example.mvvm.model.Comment

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getComments()=apiHelper.getComments()

}