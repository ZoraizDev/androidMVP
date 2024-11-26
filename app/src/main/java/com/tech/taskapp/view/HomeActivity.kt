package com.tech.taskapp.view

import NewsAdapter
import NewsViewModel
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tech.taskapp.R
import com.tech.taskapp.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val recyclerView: RecyclerView = findViewById(R.id.recyclerList)
//        if (recyclerView == null) {
//            Log.e("HomeActivity", "RecyclerView with ID recyclerList not found!")
//        }
        binding.recyclerList.layoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = LinearLayoutManager(this)

        newsAdapter = NewsAdapter()
        binding.recyclerList.adapter = newsAdapter

        // Initialize ViewModel
        newsViewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        // Observe LiveData
        newsViewModel.newsResponse.observe(this, Observer { response ->
            response?.let {
                newsAdapter.submitList(it.articles)
            }
        })

        newsViewModel.isLoading.observe(this, Observer { isLoading ->
            // Show a loading spinner if needed
        })

        newsViewModel.errorMessage.observe(this, Observer { errorMessage ->
            // Display error message if any
        })

        // Fetch data
        newsViewModel.fetchTopHeadlines()
    }
}
