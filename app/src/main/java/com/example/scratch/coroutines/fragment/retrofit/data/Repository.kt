package com.example.scratch.coroutines.fragment.retrofit.data

import androidx.lifecycle.MutableLiveData

internal val TAG = Repository::class.java.canonicalName

class Repository(private val retrofitInstance: RetrofitInstance) {
    suspend fun getDummyData(data: MutableLiveData<ArrayList<DeviceMain>>): ArrayList<DeviceMain> {
        return retrofitInstance.api.getDummyData()
    }

    suspend fun postDeviceManager(deviceManagerData: DeviceMain): DeviceMain {
        return retrofitInstance.api.createDeviceManager(deviceManagerData)
    }

    suspend fun createUserDetails(userDetails:UserData):UserData{
        return retrofitInstance.api.createUserDetails(userDetails)
    }

    suspend fun updateUserDetails(updateUser:UserData,text:String):UserData{
        return retrofitInstance.api.updateUserDetails(updateUser, text)
    }

    suspend fun deleteUserDetails(text: String):UserData{
        return retrofitInstance.api.deleteUserDetails(text)
    }

}