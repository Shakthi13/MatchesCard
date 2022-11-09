package com.example.matchescard.room

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.matchescard.data.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object RetrofitClient {
    private const val BASE_URL = "https://randomuser.me"
    interface PersonApi{
        @GET("https://randomuser.me/api/?results=10")
        fun getAllItem():Call<Response>
    }

    private fun getRetrofit(): PersonApi {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(PersonApi::class.java)
    }

    fun getAllItems(size:Int):LiveData<List<Person>>{
        val data = MutableLiveData<List<Person>>()
        val apiInterface = getRetrofit().getAllItem()
        apiInterface.enqueue(object : Callback<Response>{
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                Log.d("Retrofit_error", "vbsd: ${response.body()}")
                if (response.body() != null && response.isSuccessful){
                    val result = response.body()?.results?.map {
                        it.toPerson()
                    }!!
                    data.postValue(result)
                } else
                    Log.d("Retrofit_error", "else: ${response.body()}")
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                data.postValue(listOf())
                Log.d("Retrofit_error", "onFailure: ${t.message} ${t.printStackTrace()}")
            }

        })
        return data
    }
}