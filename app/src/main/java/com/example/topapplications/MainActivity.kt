package com.example.topapplications

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topapplications.databinding.ActivityMainBinding
import com.example.topapplications.model.MyFeed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appList: ArrayList<Application>
    private lateinit var myAdapter : AdapterApp
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        getApp(true)
        appList = arrayListOf()
        myAdapter = AdapterApp(appList,this)
        binding.rvMain.adapter = myAdapter
        binding.rvMain.layoutManager = GridLayoutManager(this,2)

        binding.btnGet10.setOnClickListener {
            binding.btnGet10.setTextColor(Color.parseColor("#6DE41515"))
            binding.btnGet100.setTextColor(Color.parseColor("#FF000000"))
            getApp(true)
        }
        binding.btnGet100.setOnClickListener {
            binding.btnGet100.setTextColor(Color.parseColor("#6DE41515"))
            binding.btnGet10.setTextColor(Color.parseColor("#FF000000"))
            getApp(false)
        }

    }

//    private fun requestAPI(isTen: Boolean){
//        CoroutineScope(IO).launch {
//            val data = async { getApp(isTen) }.await()
//            if(data.isNotEmpty()) {
//                try {
//                Log.d("MAIN", "$data")
//                myAdapter.notifyDataSetChanged()
//            }catch (e: Exception){
//            Log.e("TAG", "try-catch: Unable : " + e)
//        }
//            }else{
//                Log.d("MAIN", "Unable to get data")
//            }
//        }
//    }

    private fun getApp(isTen:Boolean){
        val retrofitVar = Retrofit.Builder()
            .baseUrl("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/").
            addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
        val feedAPI = retrofitVar.create(API::class.java)
        var call : Call<MyFeed?>? = if(isTen) {
            feedAPI.topTen
        }else {
            feedAPI.topHundred
        }

        call!!.enqueue(object : Callback<MyFeed?> {
            override fun onResponse(call: Call<MyFeed?>, response: Response<MyFeed?>) {
                Log.d("TAG", "onResponse: feed: " + response.body()?.entrys?.get(0)?.name)
                    binding.tvMainTitle.text = response.body()!!.title
                    val apps = response.body()!!.entrys!!
                appList.clear()
                for (app in apps) {
                        appList.add(Application( app.name!!,  app.summary!!,app.image!![2].url!!,app.link!!))
                }
                myAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<MyFeed?>, t: Throwable) {
                Log.e("TAG", "onFailure: Unable to retrieve RSS: " + t.message)
                Toast.makeText(this@MainActivity, "An Error Occurred", Toast.LENGTH_SHORT).show()
            }
        })
    }

     fun toDisplay(app:Application){
        val toDisplayActivity = Intent(this,DisplayActivity::class.java)
        toDisplayActivity.putExtra("appName",app.name)
        toDisplayActivity.putExtra("appSummary",app.summary)
        toDisplayActivity.putExtra("appLogo",app.url)
         toDisplayActivity.putExtra("appLink",app.webLink)

         startActivity(toDisplayActivity)

    }
}