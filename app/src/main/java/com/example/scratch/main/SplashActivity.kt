package com.example.scratch.main


import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.scratch.coroutines.fragment.retrofit.data.DeviceData
import com.example.scratch.coroutines.fragment.retrofit.data.DeviceMain
import com.example.scratch.coroutines.fragment.retrofit.data.Repository
import com.example.scratch.coroutines.fragment.retrofit.data.RetrofitInstance
import com.example.scratch.coroutines.fragment.retrofit.model.MainModelFactory
import com.example.scratch.coroutines.fragment.retrofit.model.MainViewModel
import com.example.scratch.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


internal val TAG = SplashActivity::class.java.canonicalName

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var modelview: MainViewModel
    lateinit var observer: Observer<List<DeviceMain>>

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //device data:
        val androidVersion: Int = Build.VERSION.SDK_INT
        val batteryLife = applicationContext.getSystemService(BATTERY_SERVICE) as BatteryManager
        val batteryLevel = batteryLife.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        val date = Date()
        val format = SimpleDateFormat("yyyy-MM-dd hh:mm:ss a")
        val dateTime = format.format(date)
        val manager = this.packageManager
        val appInfo = manager.getPackageInfo(this.packageName, PackageManager.GET_ACTIVITIES)
        val appVersion = appInfo.versionName

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            val startIntent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(startIntent)
            create(androidVersion, batteryLevel, dateTime, appVersion)
            message("Device manager saved success..!")
            finish()
        }, 5000L)
    }

    private fun create(
        androidVersion: Int,
        batterLevel: Int,
        dateTime: String,
        appVersion: String
    ) {
        val deviceList = DeviceData(androidVersion, batterLevel, dateTime, appVersion)
        val deviceData = DeviceMain("OK", "Device Details Saved Successfully", deviceList)

        val retrofitInstance = RetrofitInstance
        val repository = Repository(retrofitInstance)
        val modelFactory = MainModelFactory(repository)
        modelview = ViewModelProvider(this, modelFactory).get(MainViewModel::class.java)
        modelview.viewModelScope.launch {
            modelview.postDeviceManager(deviceMain = deviceData)
        }
    }

    private fun message(text:String){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
    }

    /* private fun postData(androidVersion:Int,batterLevel:Int,dateTime:String,appVersion:String) {
         val deviceList = DeviceData(androidVersion,batterLevel,dateTime,appVersion)
         val deviceData = DeviceMain("OK","Device Details Saved Successfully",deviceList)
         val retrofitInstance = RetrofitInstance

         //Create
         val call: Call<DeviceMain> = retrofitInstance.api.create(deviceData)

         call.enqueue(object : Callback<DeviceMain> {
             override fun onResponse(call: Call<DeviceMain>, response: Response<DeviceMain>) {
                 val resultData = response.body()!!
                 if (response.isSuccessful) {
                     Log.i(TAG, "onResponse: ${resultData.data}")
                 }
             }
             override fun onFailure(call: Call<DeviceMain>, t: Throwable) {
                 Log.i(TAG, "onFailure: ${t.message}")
             }

         })

     }*/

}


/*private fun updateData(updateTitleText: String) {
       val dummyData = DummyData(updateTitleText)
       val retrofitInstance = RetrofitInstance
       val call: Call<DummyData> = retrofitInstance.api.sendUpdate(dummyData)

       call.enqueue(object : Callback<DummyData> {
           override fun onResponse(call: Call<DummyData>, response: Response<DummyData>) {
               Log.i(TAG, "onResponse: ${response.body()}")
               val updateData: DummyData? = response.body()
               val responseString = """
                       Response Code : ${response.code()}
                       Update Name : ${updateData?.title}"""
               binding.responseUpdate.text = responseString
           }

           @SuppressLint("SetTextI18n")
           override fun onFailure(call: Call<DummyData>, t: Throwable) {
               binding.responseUpdate.text = "Error found is ${t.message}"
           }

       })

   }*/


/*private fun deleteData() {
    val retrofitInstance = RetrofitInstance
    val call: Call<DummyData> = retrofitInstance.api.delete()

    call.enqueue(object : Callback<DummyData> {
        override fun onResponse(call: Call<DummyData>, response: Response<DummyData>) {
            Log.i(TAG, "onResponse: ${response.code()}")
            val responseString = "Response: ${response.code()}"
            binding.responseDelete.text = responseString
        }

        @SuppressLint("SetTextI18n")
        override fun onFailure(call: Call<DummyData>, t: Throwable) {
            binding.responseDelete.text = "Error found is ${t.message}"
        }

    })

}*/
