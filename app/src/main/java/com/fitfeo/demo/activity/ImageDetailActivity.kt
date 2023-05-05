package com.fitfeo.demo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fitfeo.demo.databinding.ActivityDetailImageBinding
import com.squareup.picasso.Picasso

/**
 *  This ImageDetailsActivity holds the data related to the ImageDetail Screen
 */
class ImageDetailActivity : AppCompatActivity() {

     private lateinit var activityImageDetailBinding: ActivityDetailImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityImageDetailBinding = ActivityDetailImageBinding.inflate(layoutInflater)
        setContentView(activityImageDetailBinding.root)
        val i = intent
        val image = i.getStringExtra("imageurl")
        val title = i.getStringExtra("title")
        Picasso.get().load(image).into(activityImageDetailBinding.imageView)
        activityImageDetailBinding.tvDescription.text=title
    }
}