package com.example.scratch.coroutines.samplle.fragment.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel():ViewModel() {
    val message = MutableLiveData<String>()

    fun sendMessage(text:String){
        message.value = text
    }
}