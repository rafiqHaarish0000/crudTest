package com.example.scratch.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.example.scratch.R
import com.example.scratch.databinding.ActivityViewModelBinding
import com.example.scratch.viewmodel.MainViewModel
import com.example.scratch.viewmodel.ViewModelFactory

class ViewModelActivity : AppCompatActivity(),LifecycleOwner {
    lateinit var binding:ActivityViewModelBinding
    lateinit var mainViewModel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModelBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
//        binding.textView2.text = viewModel.number.toString()
//        binding.button.setOnClickListener {
//            viewModel.addNumber()
//            binding.textView2.text = viewModel.number.toString()
//        }
//        val viewModelFactory = ViewModelFactory("Mohammed Rafiq")
//        ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

    }
}