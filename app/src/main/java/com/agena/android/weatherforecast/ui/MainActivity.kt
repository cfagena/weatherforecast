package com.agena.android.weatherforecast.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.agena.android.weatherforecast.databinding.ActivityMainBinding
import com.agena.android.weatherforecast.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG: String = this::class.java.declaringClass.simpleName
    }

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModel()
    private val listAdapter: CityWeatherAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupRecyclerView()
        setupObservers()
        setupButtons()
        initializeData()
    }

    private fun setupButtons() {
        binding.swipeRefresh.setOnRefreshListener {
            mainViewModel.getCurrentWeather()
        }
    }

    private fun initializeData() {
        binding.swipeRefresh.isRefreshing = true
        mainViewModel.getCurrentWeather()
    }

    private fun setupObservers() {
        mainViewModel.weather.observe(this) {
            it?.let {
                Log.d(TAG, "Current weather observer: list ${it.size}")
                listAdapter.addList(it)
            }
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun setupRecyclerView() {
        binding.cityWeatherRecyclerview.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }
}
