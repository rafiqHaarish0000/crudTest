package com.example.scratch.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel

class MainViewModel(var name:String):ViewModel() {
    var myName = name
    init {
//        Log.d("CheckName","My name is $myName")
    }
    override fun onCleared() {
        super.onCleared()
    }

}