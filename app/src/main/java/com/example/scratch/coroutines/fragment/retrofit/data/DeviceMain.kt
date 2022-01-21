package com.example.scratch.coroutines.fragment.retrofit.data

data class DeviceMain(
    val result: String,
    val message:String,
    var data: DeviceData
)

data class DeviceData(
    val androidVersion: Int,
    val batterLife: Int,
    val dataTime: String,
    val appVersion: String
)
