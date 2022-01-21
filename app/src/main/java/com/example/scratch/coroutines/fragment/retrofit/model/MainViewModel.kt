package com.example.scratch.coroutines.fragment.retrofit.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.scratch.coroutines.fragment.retrofit.data.DeviceMain
import com.example.scratch.coroutines.fragment.retrofit.data.Repository
import com.example.scratch.coroutines.fragment.retrofit.data.UserData


class MainViewModel(private val repository: Repository) : ViewModel() {
    val data: MutableLiveData<ArrayList<DeviceMain>> = MutableLiveData()
    val deviceManager = MutableLiveData<DeviceMain>()
    val userDetails = MutableLiveData<UserData>()

    suspend fun getDummyData() {
        val response = repository.getDummyData(data)
        data.value = response
    }
    suspend fun postDeviceManager(deviceMain: DeviceMain){
        val responseDeviceManager = repository.postDeviceManager(deviceMain)
        deviceManager.postValue(responseDeviceManager)
    }
    suspend fun createUserDetails(userdata:UserData){
        val responseUserDetails = repository.createUserDetails(userdata)
        userDetails.postValue(responseUserDetails)
    }
    suspend fun updateUserDetails(userdata:UserData,text:String){
        val responseUserDetails = repository.updateUserDetails(userdata,text)
        userDetails.postValue(responseUserDetails)
    }
    suspend fun deleteUserDetails(text: String){
        val responseUserDetails = repository.deleteUserDetails(text)
        userDetails.postValue(responseUserDetails)
    }

}