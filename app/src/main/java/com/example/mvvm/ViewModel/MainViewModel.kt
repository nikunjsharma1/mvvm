package com.example.mvvm.ViewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

import com.example.mvvm.Repository.MainRepository
import com.example.mvvm.Utils.Resource
import kotlinx.coroutines.Dispatchers


class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getComments() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data=mainRepository.getComments()))
        }catch (exception:Exception){
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }

    }

}