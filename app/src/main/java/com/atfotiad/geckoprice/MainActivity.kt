package com.atfotiad.geckoprice

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.atfotiad.geckoprice.databinding.ActivityMainBinding
import com.atfotiad.geckoprice.ui.CryptoViewModel
import com.atfotiad.geckoprice.ui.GeckoCoinAdapter
import com.atfotiad.geckoprice.ui.GeckoLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GeckoCoinAdapter
    private val viewModel by viewModels<CryptoViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeAdapter()
        initializeObserver()
    }


    private fun initializeAdapter() {
        adapter = GeckoCoinAdapter()
        binding.apply {

            swipeRefresh.setOnRefreshListener {
                adapter.refresh()
            }

            cryptoList.adapter = adapter.withLoadStateFooter(
                footer = GeckoLoadStateAdapter {adapter.retry()}
            )

            cryptoList.setHasFixedSize(true)
        }
    }

    private fun initializeObserver() {

        viewModel.coins.observe(this, {
            adapter.submitData(this.lifecycle, it)
            binding.swipeRefresh.isRefreshing = false
        })
    }
}