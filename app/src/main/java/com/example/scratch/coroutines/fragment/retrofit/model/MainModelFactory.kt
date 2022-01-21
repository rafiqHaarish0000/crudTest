package com.example.scratch.coroutines.fragment.retrofit.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.scratch.coroutines.fragment.retrofit.data.Repository
import java.lang.IllegalArgumentException

class MainModelFactory(private val repository: Repository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            MainViewModel(repository) as T
        }else{
            throw IllegalArgumentException("ViewModel is not found...")
        }
    }
}