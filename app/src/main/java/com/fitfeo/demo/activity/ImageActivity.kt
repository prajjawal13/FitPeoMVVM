package com.fitfeo.demo.activity

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.fitfeo.demo.adapter.ImageAdapter
import com.fitfeo.demo.databinding.ActivityImageBinding
import com.fitfeo.demo.viewmodel.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


/**
 *  This ImageActivity holds the data related to the ImageActivity Screen
 */
@AndroidEntryPoint
class ImageActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityImageBinding
    private lateinit var characterAdapter: ImageAdapter
    private val viewModel: ImageViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * set the reclyerView using LinearLayoutManager with adapter
     */
    private fun setupRecyclerView() {
        characterAdapter = ImageAdapter(this)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ImageActivity)
            setHasFixedSize(true)
            adapter = characterAdapter
        }
        if (!isNetworkConnected()) {
            binding.progressBar.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    /**
     *  load the data and returns the list of data in a RecylerView.
     */
    private fun loadData() {
        
        lifecycleScope.launch {
            viewModel.liveData.observe(this@ImageActivity, Observer {
                Log.d(TAG, "GetListObserver: ${it}")
                if (it != null) {
                    binding.progressBar.visibility = View.GONE
                    characterAdapter.setDataList(it)
                } else {
                    Toast.makeText(
                        this@ImageActivity,
                        "Error for fetching Data......",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isNetworkConnected()) {
            Toast.makeText(this, "Internet not available Please Turn on the Internet!...", Toast.LENGTH_SHORT).show()
        } else {
            setupRecyclerView()
            loadData()
        }
    }

    override fun onPause() {
        super.onPause()

    }

    /**
     * check whether the internet turn on or off if turn on return true
     */
    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected
    }
}