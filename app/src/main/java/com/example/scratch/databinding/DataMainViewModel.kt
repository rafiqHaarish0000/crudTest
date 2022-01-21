package com.example.scratch.databinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DataMainViewModel():ViewModel() {
    val message:MutableLiveData<String> = MutableLiveData()

    fun getMessage(text:String){
        message.value = text
    }
}