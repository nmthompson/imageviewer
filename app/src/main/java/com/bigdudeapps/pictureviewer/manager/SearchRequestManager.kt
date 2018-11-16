package com.bigdudeapps.pictureviewer.manager

import com.bigdudeapps.pictureviewer.BuildConfig
import com.bigdudeapps.pictureviewer.service.RetrofitPixabaySerivce
import com.bigdudeapps.pictureviewer.service.model.PixaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchRequestManager(var query: String, var searchRequestResponseListener: SearchRequestResponseListener) {

    interface SearchRequestResponseListener {
        fun onRequestResponse(pixaResponse: PixaResponse?)
    }

    fun requestPixaImages() {
        //TODO Figure out why API isn't responding to perPage requests
        RetrofitPixabaySerivce.create().getPixabayImages(BuildConfig.PixaBayKey, query, 30).enqueue(object : Callback<PixaResponse> {
            override fun onResponse(call: Call<PixaResponse>, response: Response<PixaResponse>) {
                if (!response.isSuccessful && response.body() != null) {
                    //TODO fail message or w/e
                } else {
                    response.body()?.let { searchRequestResponseListener.onRequestResponse(it) }
                }
            }

            override fun onFailure(call: Call<PixaResponse>, t: Throwable) {
            }
        })
    }
}