package com.tech.taskapp.repository
import com.tech.taskapp.services.RetrofitInstance

class NewsRepository {
    private val api = RetrofitInstance.api

    suspend fun getTopHeadlines() = RetrofitInstance.api.getTopHeadlines()
}
