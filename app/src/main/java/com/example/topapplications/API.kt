package com.example.topapplications

import com.example.topapplications.model.MyFeed
import retrofit2.Call
import retrofit2.http.GET

interface API {

    @get:GET("limit=10/xml")
    val topTen: Call<MyFeed?>?

    @get:GET("limit=100/xml")
    val topHundred: Call<MyFeed?>?


}