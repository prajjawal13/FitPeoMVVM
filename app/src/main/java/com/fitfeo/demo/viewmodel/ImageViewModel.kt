package com.fitfeo.demo.viewmodel

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import com.fitfeo.demo.model.ImageModel
import com.fitfeo.demo.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *  This ImageViewModel stored the data related to the ImageActivity
 *  @param: contains the reference of the ImageRepository
 */
@HiltViewModel
class ImageViewModel @Inject constructor(private val customRepository: ImageRepository) : ViewModel() {

    private  val TAG = "CharacterViewModel"
    val liveData : LiveData<List<ImageModel>>
    get() = customRepository.mutableLiveData
    init {
        viewModelScope.launch {
            var list=customRepository.getList()
            Log.d(TAG, "GetList : ${list}")
        }
    }
}
























