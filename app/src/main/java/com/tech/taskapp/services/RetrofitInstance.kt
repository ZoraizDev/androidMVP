package com.tech.taskapp.services

import Service
import com.tech.taskapp.utils.Utils.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {

        // Create retrofit instance lazily
        private val retrofit by lazy {

            // to log responses of retrofit
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder().addInterceptor(logging).build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        // Create API service instance lazily
        val api: Service by lazy {
            retrofit.create(Service::class.java)
        }
    }
}
