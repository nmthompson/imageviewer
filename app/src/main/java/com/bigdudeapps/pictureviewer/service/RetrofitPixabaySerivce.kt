package com.bigdudeapps.pictureviewer.service

import com.bigdudeapps.pictureviewer.service.model.PixaResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitPixabaySerivce {

    companion object {
        fun create(): RetrofitPixabaySerivce {
            val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://pixabay.com/api/")
                    .build()
            return retrofit.create(RetrofitPixabaySerivce::class.java)
        }
    }

    @GET(".")
    fun getPixabayImages(@Query("key") key: String,
                         @Query("q") search: String,
                         @Query("perPage") perPage: Int)
            : Call<PixaResponse>
}