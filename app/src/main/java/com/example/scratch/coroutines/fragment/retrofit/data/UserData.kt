package com.example.scratch.coroutines.fragment.retrofit.data

data class UserData(
    val userDetails:Details
)
data class Details(
    val userName:String,
    val email:String,
    val password:String
)
