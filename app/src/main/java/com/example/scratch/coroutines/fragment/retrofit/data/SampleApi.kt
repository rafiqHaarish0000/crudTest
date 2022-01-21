package com.example.scratch.coroutines.fragment.retrofit.data

import retrofit2.http.*


interface SampleApi {

    @GET("posts")
    suspend fun getDummyData(): ArrayList<DeviceMain> {
        return RetrofitInstance.api.getDummyData()
    }

    @POST("device")
    suspend fun createDeviceManager(@Body posts: DeviceMain): DeviceMain {
        return RetrofitInstance.api.createDeviceManager(posts)
    }

    @POST("user")
    suspend fun createUserDetails(@Body posts: UserData): UserData {
        return RetrofitInstance.api.createUserDetails(posts)
    }

    @PUT("user/{id}")
    suspend fun updateUserDetails(@Body posts: UserData, @Path("id") id: String): UserData

    @DELETE("user/{id}")
    suspend fun deleteUserDetails(@Path("id") id: String): UserData


}