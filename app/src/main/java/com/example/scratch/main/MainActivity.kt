package com.example.scratch.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.scratch.coroutines.fragment.retrofit.data.Details
import com.example.scratch.coroutines.fragment.retrofit.data.Repository
import com.example.scratch.coroutines.fragment.retrofit.data.RetrofitInstance
import com.example.scratch.coroutines.fragment.retrofit.data.UserData
import com.example.scratch.coroutines.fragment.retrofit.model.MainModelFactory
import com.example.scratch.coroutines.fragment.retrofit.model.MainViewModel
import com.example.scratch.databinding.ActivityMainBinding
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createBtn.setOnClickListener {
            if(setValidation()){
                if(checkValidation()){
                    create(binding.userName.text.toString(),binding.eMail.text.toString(),
                    binding.passWord.text.toString())
                }
            }
        }

        binding.deletebtn.setOnClickListener {
            if(setValidation()){
                if(checkValidation()){
                    delete(binding.userName.text.toString(),binding.eMail.text.toString(),
                        binding.passWord.text.toString())
                }
            }
        }

        binding.updateBtn.setOnClickListener {
            if(setValidation()){
                if(checkValidation()){
                    update(binding.userName.text.toString(),binding.eMail.text.toString(),
                        binding.passWord.text.toString())
                }
            }
        }

    }


    fun create(userName:String,emailId:String,password:String) {
        val details = Details(userName,emailId,password)
        val getData = UserData(details)
        val retrofitInstance = RetrofitInstance
        val repository = Repository(retrofitInstance)
        val modelFactory = MainModelFactory(repository)
        mainViewModel = ViewModelProvider(this, modelFactory).get(MainViewModel::class.java)

        mainViewModel.viewModelScope.launch {
            mainViewModel.createUserDetails(getData)
        }
    }

    fun update(userName:String,emailId:String,password:String) {
        val details = Details(userName,emailId,password)
        val getData = UserData(details)
        val retrofitInstance = RetrofitInstance
        val repository = Repository(retrofitInstance)
        val modelFactory = MainModelFactory(repository)

        mainViewModel = ViewModelProvider(this, modelFactory).get(MainViewModel::class.java)

        mainViewModel.viewModelScope.launch {
            mainViewModel.updateUserDetails(getData,details.userName)
        }
    }

    fun delete(userName:String,emailId:String,password:String){
        val details = Details(userName,emailId,password)
        val getData = UserData(details)
        val retrofitInstance = RetrofitInstance
        val repository = Repository(retrofitInstance)
        val modelFactory = MainModelFactory(repository)
        mainViewModel = ViewModelProvider(this, modelFactory).get(MainViewModel::class.java)

        mainViewModel.viewModelScope.launch {
            mainViewModel.deleteUserDetails(details.userName)
        }
    }

    fun checkValidation(): Boolean {

        var flag = true
        if (binding.userName.text.isEmpty()) {
            message("Username invalid")
            flag = false
        }
        if (binding.eMail.text.isEmpty()) {
            message("E-mail invalid")
            flag = false
        }
        if (binding.passWord.text.isEmpty()) {
            message("Password invalid")
            flag = false
        }
        return flag
    }


    fun setValidation(): Boolean {
        var flag = true
        val listOfFields = arrayListOf(binding.userName, binding.eMail, binding.passWord)
        for (item in listOfFields) {
            if (item.text.isEmpty()) {
                item.error = "Required field missing"
                flag = false
            }
        }
        return flag
    }

    private fun message(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
    override fun onBackPressed() {

    }

}
