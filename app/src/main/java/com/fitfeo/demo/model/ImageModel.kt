package com.fitfeo.demo.model

/**
 * This ImageModel class contains the model related data
 */
data class ImageModel(
    val albumId:Int,
    val id:Int,
    val title:String,
    val url:String,
    val thumbnailUtils: String
)