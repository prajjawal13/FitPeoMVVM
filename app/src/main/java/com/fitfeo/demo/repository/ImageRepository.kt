package com.fitfeo.demo.repository

import androidx.lifecycle.MutableLiveData
import com.fitfeo.demo.api.ApiService
import com.fitfeo.demo.model.ImageModel
import javax.inject.Inject

/**
 * This ImageRepository class get the data from the api
 * @param: contains the reference of ApiService instance
 */
class ImageRepository @Inject constructor(private val apiService: ApiService) {

    val mutableLiveData = MutableLiveData<List<ImageModel>>()

    suspend fun getList() {
        val result = apiService.getAllDataList()
        if (result.isSuccessful && result.body() != null) {
            mutableLiveData.postValue(result.body())
        }
    }
}