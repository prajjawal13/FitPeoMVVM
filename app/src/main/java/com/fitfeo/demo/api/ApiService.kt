package com.fitfeo.demo.api

import com.fitfeo.demo.model.ImageModel
import com.fitfeo.demo.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

/**
 * ApiService interface contains all the list of getAllDataList
 */
interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getAllDataList(): Response<List<ImageModel>>
}